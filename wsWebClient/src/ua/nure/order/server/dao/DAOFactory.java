package ua.nure.order.server.dao;

import ua.nure.order.server.dao.mysql.MysqlDAOFactory;

/**
 * <p>Represent factory methods for creating Domain DAO</p>
 * <p>see {@link <a href="http://www.oracle.com/technetwork/java/dataaccessobject-138824.html">DAO pattern</a>}.</p>
 * Example:
 * {@code UserDAO = DAOFactory.getDAOFactory().getUserDAO();}
 * @author engsyst
 *
 */
public abstract class DAOFactory {

	// List of DAO types supported by the factory
	public static final int MYSQL = 1;
	public static final int WSFACTORY = 2;
	private static int defaultFactory = MYSQL;

	// There will be a method for each DAO that can be
	// created. The concrete factories will have to
	// implement these methods.
	public abstract UserDAO getUserDAO();

	public abstract BookDAO getBookDAO();

	public abstract OrderDAO getOrderDAO();

	public static DAOFactory getDAOFactory(int whichFactory) {

		switch (whichFactory) {
		case MYSQL:
			return new MysqlDAOFactory();
//		case INMEMORY:
//			return new InMemoryDAOFactory();
		default:
			return null;
		}
	}

	public static DAOFactory getDAOFactory() {
		return getDAOFactory(defaultFactory);
	}

	public static void setDefaultFactory(int whichFactory) {
		defaultFactory = whichFactory;
	}
		
}
