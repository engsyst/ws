package ua.nure.order.server.dao.derby;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.order.entity.book.Book;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;

public class DerbyBookDAO implements BookDAO {
	private static final Logger log = Logger.getLogger(DerbyBookDAO.class);

	private static DerbyBookDAO dao;
	
	public static BookDAO getInstance() {
		if (dao == null) 
			dao = new DerbyBookDAO();
		return dao;
	}


	@Override
	public int addBook(Book item) throws DAOException {
		Connection con = null;
		try {
			con = DerbyDAOFactory.getConnection();
			addBook(con, item);
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DerbyDAOFactory.close(con);
		}
		// TODO Auto-generated method stub
		return 0;
	}

	void addBook(Connection con, Book item) {
		// TODO Auto-generated method stub
		
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


	@Override
	public List<String> listAuthors() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
