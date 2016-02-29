package ua.nure.order.server.dao.derby;

import java.util.Collection;

import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;

public class DerbyBookDAO implements BookDAO {

	private static DerbyBookDAO dao;
	
	public static BookDAO getInstance() {
		if (dao == null) 
			dao = new DerbyBookDAO();
		return dao;
	}


	@Override
	public int addBook(Book item) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Book deleteBook(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBookCount(int id, int count) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Book> findByTitle(String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Book> findByAuthor(String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Book> listBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findById(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
