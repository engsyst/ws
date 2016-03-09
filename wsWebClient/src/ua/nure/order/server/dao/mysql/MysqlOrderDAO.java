package ua.nure.order.server.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import ua.nure.order.entity.book.Book;
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
	public int makeOrder(Map<Book, Integer> items, int userId) throws DAOException {
		Connection con = null;
		int orderId = 0;
		try {
			con = getConnection();
			orderId = addOrder(con, items, userId);
			for (Entry<Book, Integer> e : items.entrySet()) {
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

	public int addOrder(Connection con, Map<Book, Integer> items, int userId) throws SQLException {
		
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
	
}
