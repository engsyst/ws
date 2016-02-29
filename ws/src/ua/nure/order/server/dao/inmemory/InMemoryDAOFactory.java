package ua.nure.order.server.dao.inmemory;


import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOFactory;

public class InMemoryDAOFactory extends DAOFactory {

	@Override
	public BookDAO getBookDAO() {
		return InMemoryBookDAO.getInstance();
	}

}
