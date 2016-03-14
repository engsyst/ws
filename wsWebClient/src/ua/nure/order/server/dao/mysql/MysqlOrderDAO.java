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
			con = MysqlDAOFactory.getConnection();
		} catch (SQLException e) {
			con = MysqlDAOFactory.getDBConnection();
		}
		return con;
		
	}

	@Override
	public int makeOrder(Map<Product, Integer> items, int userId) throws DAOException {
		Connection con = null;
		int orderId = 0;
		try {
			con = getConnection();
			orderId = addOrder(con, items, userId);
			for (Entry<Product, Integer> e : items.entrySet()) {
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
		return orderId;
	}

	private void addBookHasOrder(Connection con, Integer bookId, int orderId, Integer count) 
			throws SQLException {
		
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(Querys.SQL_INSERT_BOOK_HAS_ORDER);
			st.setInt(1, bookId);
			st.setInt(2, orderId);
			st.setInt(3, count);
			st.executeUpdate();
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
	}

	public int addOrder(Connection con, Map<Product, Integer> items, int userId) throws SQLException {
		
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(Querys.SQL_INSERT_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);
			st.setInt(1, 0);
			st.setInt(2, userId);
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
	}

	@Override
	public List<Order> list(String pattern, String orderColumn, boolean ascending, int start, int count,
			SQLCountWrapper total) throws DAOException {
		List<Order> orders = null;
		Connection con = null;
		try {
			con = getConnection();
			orders = listOrders(con, pattern, orderColumn, ascending, start, count, total);
		} catch (SQLException e) {
			log.error("listBooks: Can not listBooks", e);
			throw new DAOException("Can not listBooks", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return orders;
	}

	private List<Order> listOrders(Connection con, String pattern, String orderColumn,
			boolean ascending, int start, int count, SQLCountWrapper total) 
					throws SQLException {
		List<Order> orders = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		PreparedStatement st = null;
		try {
			String where = pattern == null || pattern.length() == 0 ? "" : 
				" WHERE `status` = '" + pattern + "' ";
			String order = orderColumn == null || orderColumn.length() == 0 
					? "ORDER BY `order_id` DESC" 
					: "ORDER BY " + orderColumn + (ascending ? " ASC" : " DESC")
					+ ",`order_id` DESC";
			String limit = (count == 0 ? "" : " LIMIT " + start + "," + count);
			String query = Querys.SQL_GET_ORDERS_ID + where + order + limit;
			st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			list = SqlUtil.unmapIdList(rs);
			rs.close();
			st.close();
			
			String whereBooks = " WHERE `order_id` IN " + SqlUtil.listToIN(list);
			query = Querys.SQL_GET_FULL_ORDERS + whereBooks + order;
			st = con.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				orders.add(unmapOrder(rs));
			}
			rs.close();
			st.close();
			
			if (total != null) {
				query = Querys.SQL_FIND_ORDERS_COUNT + where;
				st = con.prepareStatement(query);
				rs = st.executeQuery();
				while (rs.next()) {
					total.setCount(rs.getInt(1));
				}
				rs.close();
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		return orders;
	}

	Order unmapOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getInt("order_id"));
		order.setTitle(rs.getString("login"));
		order.setStatus(OrderStatus.valueOf(rs.getString("status")));
		order.setPrice(rs.getInt("price"));
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
	public Order getOrder(int id) throws DAOException {
		Connection con = null;
		Order order = null;
		try {
			con = getConnection();
			order = getOrder(con, id);
		} catch (SQLException e) {
			MysqlDAOFactory.roolback(con);
			log.error("Can not add order", e);
			throw new DAOException("Can not add order", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return order;
	}

	Order getOrder(Connection con, int id) throws SQLException {
		PreparedStatement st = null;
		Order order = new Order();
		try {
			st = con.prepareStatement(Querys.SQL_GET_ORDER_BY_ID);
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
}
