package ua.nure.order.server.dao;

import ua.nure.order.server.dao.derby.DerbyDAOFactory;
import ua.nure.order.server.dao.inmemory.InMemoryDAOFactory;

public abstract class DAOFactory {

	// List of DAO types supported by the factory
	public static final int DERBY = 1;
	public static final int INMEMORY = 2;

	// There will be a method for each DAO that can be
	// created. The concrete factories will have to
	// implement these methods.
	public abstract BookDAO getBookDAO();


	public static DAOFactory getDAOFactory(int whichFactory) {

		switch (whichFactory) {
		case DERBY:
			return new DerbyDAOFactory();
		case INMEMORY:
			return new InMemoryDAOFactory();
		default:
			return null;
		}
	}



}
