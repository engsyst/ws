package ua.nure.order.server.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.DAOFactory;
import ua.nure.order.server.dao.OrderDAO;
import ua.nure.order.server.dao.UserDAO;

public class MysqlDAOFactory extends DAOFactory {
	private static final Logger log = Logger.getLogger(MysqlDAOFactory.class);

	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost:3306/ws?user=root&password=root";
	
	public static String getDriver() {
		return DRIVER;
	}

	public static void setDriver(String driver) {
		DRIVER = driver;
	}

	public static String getDbUrl() {
		return DB_URL;
	}

	public static void setDbUrl(String db_url) {
		DB_URL = db_url;
	}

	public MysqlDAOFactory() {
	}

	/**
	 * method to create MSSQL pooled connections using application context
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	static Connection getConnection() throws SQLException {
		log.trace("Start");			
		Connection con = null;
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			
			DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
			con = ds.getConnection();
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			con.setAutoCommit(false);
		} catch (NamingException ex) {
			log.error("Cannot obtain a connection from the pool", ex);			
			throw new SQLException("Cannot obtain a connection from the pool", ex);
		}
		log.trace("Finish");			
		return con;
	}

	/**
	 * method to create MSSQL connections using DriverManager
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	protected static Connection getDBConnection()
			throws SQLException {
		log.trace("Start");			
		Connection con = null;
		try {
			con = DriverManager.getConnection(DB_URL);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			con.setAutoCommit(false);
		} catch (SQLException e) {
			log.error("Can not get connection.", e);
			throw e;
		}
		log.trace("Finish");			
		return con;
	}

	public static void roolback(Connection con) throws DAOException {
		if (con != null) {
			try {
				log.debug("Try rollback.");
				con.rollback();
			} catch (SQLException e) {
				log.error("Can not rollback transaction.", e);
				throw new DAOException("Can not rollback transaction", e);
			}
		}
	}

	protected static void commit(Connection con) {
		try {
			log.debug("Try commit transaction");
			con.commit();
		} catch (SQLException e) {
			log.error("Can not commit transaction." + e);
			try {
				log.debug("Try rollback transaction.");
				con.rollback();
			} catch (SQLException e1) {
				log.error("Can not rollback transaction." + e1);
			}
		}
	}

	protected static void close(Connection con) {
		try {
			log.debug("Try close connection.");
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			log.error("Can not close connection." + e.getMessage());
		}
	}

	static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				log.debug("Try close statement");
				stmt.close();
			} catch (SQLException e) {
				log.error(e.getLocalizedMessage(), e);
			}
		}
	}

	protected static void commitAndClose(Connection con) {
		commit(con);
		close(con);
	}

	@Override
	public UserDAO getUserDAO() {
		return MysqlUserDAO.getInstance();
	}

	@Override
	public BookDAO getBookDAO() {
		return MysqlBookDAO.getInstance();
	}

	@Override
	public OrderDAO getOrderDAO() {
		return MysqlOrderDAO.getInstance();
	}
}
