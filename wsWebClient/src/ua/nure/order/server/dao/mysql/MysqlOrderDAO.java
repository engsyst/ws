package ua.nure.order.server.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import ua.nure.order.client.Delivery;
import ua.nure.order.client.SQLCountWrapper;
import ua.nure.order.entity.Product;
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.order.OrderStatus;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.OrderDAO;

public class MysqlOrderDAO implements OrderDAO {

	private static final Logger log = Logger.getLogger(MysqlOrderDAO.class);

	private static MysqlOrderDAO dao;

	public static OrderDAO getInstance() {
		if (dao == null)
			dao = new MysqlOrderDAO();
		return dao;
	}

	Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			log.debug("Try get pooled conection.");
			con = MysqlDAOFactory.getConnection();
		} catch (SQLException e) {
			log.error("Failure. Try get conection using DriverManager.");
			con = MysqlDAOFactory.getDBConnection();
		}
		return con;
		
	}

	@Override
	public int makeOrder(Map<Product, Integer> items, Delivery delivery, Integer userId) 
			throws DAOException {
		log.trace("Start");
		Connection con = null;
		int orderId = 0;
		int dId = 0;
		try {
			con = getConnection();
			log.debug("Try add delivery.");
			dId = addDelivery(con, delivery, userId);
			log.debug("Try add order.");
			orderId = addOrder(con, items, dId, userId);
			for (Entry<Product, Integer> e : items.entrySet()) {
				log.debug("Try add book to the order." + e);
				addBookHasOrder(con, e.getKey().getId(), orderId, e.getValue());
			}
			con.commit();
		} catch (SQLException e) {
			MysqlDAOFactory.roolback(con);
			log.error("Can not add order", e);
			throw new DAOException("Can not add order", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		log.debug("Result --> " + orderId);
		log.trace("Start");
		return orderId;
	}

	int addDelivery(Connection con, Delivery delivery, Integer userId) throws SQLException {
		log.trace("Start");
		
		PreparedStatement st = null;
		try {
			// "INSERT INTO `delivery` (`name`, `phone`, `email`, `address`,  `description`, `user_id`) VALUES (?, ?, ?, ?, ?, ?)"
			st = con.prepareStatement(Querys.SQL_INSERT_DELIVERY, PreparedStatement.RETURN_GENERATED_KEYS);
			int k = 0;
			st.setString(++k, delivery.getName());
			st.setString(++k, delivery.getPhone());
			st.setString(++k, delivery.getEmail());
			st.setString(++k, delivery.getAddress());
			st.setString(++k, delivery.getDescription());
			SqlUtil.setIntOrNull(st, ++k, userId, 0);
			log.debug("Query --> " + st);
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			rs.next();
			int newId =  rs.getInt(1);
			rs.close();
			log.debug("Result --> " + newId);
			log.trace("Finish");
			return newId;
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
	}

	private void addBookHasOrder(Connection con, Integer bookId, int orderId, Integer count) 
			throws SQLException {
		log.trace("Start");
		
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(Querys.SQL_INSERT_BOOK_HAS_ORDER);
			st.setInt(1, bookId);
			st.setInt(2, orderId);
			st.setInt(3, count);
			log.debug("Query --> " + st);
			st.executeUpdate();
			log.trace("Finish");
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
	}

	public int addOrder(Connection con, Map<Product, Integer> items, int deliveryId, Integer userId) throws SQLException {
		log.trace("Start");
		
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(Querys.SQL_INSERT_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);
			st.setInt(1, 0);
			st.setInt(2, deliveryId);
			SqlUtil.setIntOrNull(st, 3, userId, 0);
			log.debug("Query --> " + st);
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			rs.next();
			int newId = rs.getInt(1);
			log.debug("Result --> " + newId);
			log.trace("Finish");
			return newId;
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
	}

	@Override
	public List<Order> list(String pattern, String orderColumn, boolean ascending, int start, int count,
			SQLCountWrapper total) throws DAOException {
		log.trace("Start");
		List<Order> orders = null;
		Connection con = null;
		try {
			con = getConnection();
			log.debug("Try list orders with params --> " + "pattern" + pattern + ", orderColumn " 
					+ orderColumn + ", ascending " + ascending + ", start" + start 
					+ ", count" + count + ", total" + total);
			orders = listOrders(con, pattern, orderColumn, ascending, start, count, total);
		} catch (SQLException e) {
			log.error("listBooks: Can not listBooks", e);
			throw new DAOException("Can not listBooks", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		log.trace("Finish");
		return orders;
	}

	private List<Order> listOrders(Connection con, String pattern, String orderColumn,
			boolean ascending, int start, int count, SQLCountWrapper total) 
					throws SQLException {
		log.trace("Start");
		List<Order> orders = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		PreparedStatement st = null;
		try {
			log.debug("Get orders ID with given pattern.");
			String where = pattern == null || pattern.length() == 0 ? "" : 
				" WHERE `status` = '" + pattern + "' ";
			String order = orderColumn == null || orderColumn.length() == 0 
					? " ORDER BY `order_id` DESC" 
					: " ORDER BY " + orderColumn + (ascending ? " ASC" : " DESC")
					+ ",`order_id` DESC";
			String limit = (count == 0 ? "" : " LIMIT " + start + "," + count);
			String query = Querys.SQL_GET_ORDERS_ID + where + order + limit;
			st = con.prepareStatement(query);
			log.debug("Query --> " + query);
			ResultSet rs = st.executeQuery();
			list = SqlUtil.unmapIdList(rs);
			rs.close();
			// return if no orders with given status found
			if (list.isEmpty())
				return orders;
			st.close();
			
			log.debug("Get orders with given IDs.");
			String whereBooks = " WHERE `order_id` IN " + SqlUtil.listToIN(list);
			query = Querys.SQL_GET_FULL_ORDERS + whereBooks + order;
			st = con.prepareStatement(query);
			log.debug("Query --> " + query);
			rs = st.executeQuery();
			while (rs.next()) {
				orders.add(unmapOrder(rs));
			}
			rs.close();
			st.close();
			
			if (total != null) {
				log.debug("Get count of founded orders.");
				query = Querys.SQL_FIND_ORDERS_COUNT + where;
				log.debug("Query --> " + query);
				st = con.prepareStatement(query);
				rs = st.executeQuery();
				while (rs.next()) {
					total.setCount(rs.getInt(1));
					log.debug("Count of orders --> " + total.getCount());
				}
				rs.close();
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		log.trace("Finish");
		return orders;
	}

	Order unmapOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getInt("order_id"));
		order.setTitle(rs.getString("login"));
		order.setStatus(OrderStatus.valueOf(rs.getString("status")));
		order.setPrice(rs.getInt("price"));
		try {
			order.setDelivery(unmapDelivery(rs));
		} catch (SQLException e) {
			// DO: nothing
		}
		order.setItems(new Hashtable<Product, Integer>());
		Product item = unmapProductForOrder(rs);
		order.getItems().put(item, Integer.valueOf(rs.getInt("count")));
		while(rs.next()) {
			if (rs.getInt("order_id") != (int) order.getId()) {
				rs.previous();
				break;
			}
			item = unmapProductForOrder(rs);
			order.getItems().put(item, Integer.valueOf(rs.getInt("count")));
		}
		return order;
	}

	Delivery unmapDelivery(ResultSet rs) throws SQLException {
		Delivery delivery = new Delivery();
		delivery.setName(rs.getString("name"));
		delivery.setPhone(rs.getString("phone"));
		delivery.setEmail(rs.getString("email"));
		delivery.setAddress(rs.getString("address"));
		delivery.setDescription(rs.getString("description"));
		return delivery;

	}
	Product unmapProductForOrder(ResultSet rs) throws SQLException {
		Product item = new Book();
		item.setId(rs.getInt("book_id"));
		item.setTitle(rs.getString("title"));
		item.setPrice(rs.getDouble("price"));
		return item;
	}
	
	@Override
	public List<Order> getOrders() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrderStatus(int id) throws DAOException {
		Connection con = null;
		Order order = null;
		try {
			con = getConnection();
			order = getOrderStatus(con, id);
		} catch (SQLException e) {
			MysqlDAOFactory.roolback(con);
			log.error("Can not add order", e);
			throw new DAOException("Can not add order", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return order;
	}

	Order getOrderStatus(Connection con, int id) throws SQLException {
		PreparedStatement st = null;
		Order order = new Order();
		try {
			st = con.prepareStatement(Querys.SQL_GET_ORDER_STATUS);
			st.setInt(1, id);
			st.executeQuery();
			ResultSet rs = st.getResultSet();
			rs.next();
			order.setId(rs.getInt(1));
			order.setStatus(OrderStatus.valueOf(rs.getString(2)));
			return order;
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
	}

	@Override
	public void updateStatus(int id, OrderStatus status) throws DAOException {
		Connection con = null;
		try {
			con = getConnection();
			updateOrder(con, id, status);
			con.commit();
		} catch (SQLException e) {
			MysqlDAOFactory.roolback(con);
			log.error("Can not add order", e);
			throw new DAOException("Can not update order status | " + e.getMessage(), e);
		} finally {
			MysqlDAOFactory.close(con);
		}
	}

	private void updateOrder(Connection con, int id, OrderStatus status) throws SQLException {
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(Querys.SQL_UPDATE_ORDER_STATUS);
			st.setInt(2, id);
			st.setString(1, status.toString());
			st.executeUpdate();
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
	}

	@Override
	public Order getOrderDetal(int id) throws DAOException {
		Connection con = null;
		Order order = null;
		try {
			con = getConnection();
			order = getOrderDetal(con, id);
		} catch (SQLException e) {
			log.error("Can not add order", e);
			throw new DAOException("Can not get order | " + e.getMessage(), e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return order;
	}

	Order getOrderDetal(Connection con, int id) throws SQLException {
		Order order = new Order();
		PreparedStatement st = null;
		try {
			String query = Querys.SQL_GET_ORDER_DETAL;
			st = con.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				order = unmapOrder(rs);
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		return order;
	}
	
}
