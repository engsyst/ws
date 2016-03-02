package ua.nure.order.server.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;

public class MysqlBookDAO implements BookDAO {
	private static final Logger log = Logger.getLogger(MysqlBookDAO.class);

	private static MysqlBookDAO dao;
	
	public static BookDAO getInstance() {
		if (dao == null) 
			dao = new MysqlBookDAO();
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
	public int addBook(Book item) throws DAOException {
		Connection con = null;
		int bookId = 0;
		try {
			con = getConnection();
			bookId = addBook(con, item);
			con.commit();
		} catch (SQLException e) {
			log.error("Can not add book", e);
			throw new DAOException("Can not add book", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return bookId;
	}

	int addBook(Connection con, Book item) throws SQLException {
		int bookId = 0;
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(Querys.SQL_ADD_BOOK, PreparedStatement.RETURN_GENERATED_KEYS);
			mapBook(st, item);
			int count = st.executeUpdate();
			if (count == 0) {
				throw new SQLException("addAuthors: No data inserted");
			}
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				bookId = rs.getInt(1);
			}
			if (bookId == 0) {
				throw new SQLException("addBook: No data inserted");
			}
			addNotExistedAuthors(con, item.getAuthor(), bookId);
		} finally {
			st.close();
		}
		return bookId;
		
	}
	
	void mapBook(PreparedStatement st, Book item) throws SQLException {
		// book` (`title`, `isbn`, `price`, `count`, `category_id`) 
		int k = 0;
		st.setString(++k, item.getTitle());
		st.setString(++k, item.getIsbn());
		st.setDouble(++k, item.getPrice());
		st.setInt(++k, item.getCount());
		st.setInt(++k, item.getCategory().ordinal() + 1);
	}

	List<Integer> getExistedAuthors(Connection con, List<String> authors) throws SQLException {
		// Make query string
		StringBuilder sb = new StringBuilder();
		for (String a : authors) {
			sb.append("title = '");
			sb.append(a);
			sb.append("' or ");
		}
		sb.delete(sb.lastIndexOf("'"), sb.length());
		sb.append(";");
		String getQuery = Querys.SQL_GET_AUTHORS + sb.toString();
		
		List<Integer> res = new ArrayList<>();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(getQuery);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				res.add(rs.getInt(1));
				authors.remove(rs.getString(2));
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		return res;
		
	}
	
	List<Integer> addNotExistedAuthors(Connection con, List<String> authors, int bookId) throws SQLException {
		List<String> auth = new ArrayList<>(authors);
		List<Integer> res = getExistedAuthors(con, auth);
		String addQuery = Querys.SQL_ADD_AUTHOR + makeAuthorsValues(auth, bookId);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(addQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			int count = st.executeUpdate();
			if (count == 0) {
				throw new SQLException("addAuthors: No data inserted");
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		return res;
	}
	
	String makeAuthorsValues(List<String> authors, int bookId) {
		StringBuilder sb = new StringBuilder();
		for (String a : authors) {
			sb.append("('");
			sb.append(a);
			sb.append("),");
		}
		sb.replace(sb.length() - 1, sb.length(), ";");
		return sb.toString();
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
