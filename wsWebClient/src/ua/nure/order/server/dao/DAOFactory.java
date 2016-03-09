package ua.nure.order.server.dao;

import ua.nure.order.server.dao.mysql.MysqlDAOFactory;

public abstract class DAOFactory {

	// List of DAO types supported by the factory
	public static final int DERBY = 1;
	public static final int INMEMORY = 2;
	public static final int MYSQL = 3;

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



}
