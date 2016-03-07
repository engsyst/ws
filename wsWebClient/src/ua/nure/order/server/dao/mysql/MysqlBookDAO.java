package ua.nure.order.server.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.order.client.SQLCountWrapper;
import ua.nure.order.entity.book.Author;
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;

class MysqlBookDAO implements BookDAO {
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


	void mapBook(PreparedStatement st, Book item) throws SQLException {
		// book` (`title`, `isbn`, `price`, `count`, `category_id`) 
		int k = 0;
		st.setString(++k, item.getTitle());
		st.setString(++k, item.getIsbn());
		st.setDouble(++k, item.getPrice());
		st.setInt(++k, item.getCount());
		st.setInt(++k, item.getCategory().ordinal() + 1);
	}

	Book unmapBook(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		String[] a = rs.getString("authors").split(",");
		for (String title : a) {
			authors.add(new Author(title));
		}
		Book book = new Book(rs.getInt("id"), rs.getString("title"), authors, rs.getString("isbn"),
				rs.getDouble("price"), Category.fromValue(rs.getString("category")), rs.getInt("count"));
		return book;
	}

	List<Integer> getExistedAuthors(Connection con, List<Author> auth) throws SQLException {
		// Make query string
		StringBuilder sb = new StringBuilder();
		for (Author a : auth) {
			sb.append("title = '");
			sb.append(a.getTitle());
			sb.append("' or ");
		}
		sb.delete(sb.lastIndexOf("'")+1, sb.length());
		sb.append(";");
		String getQuery = Querys.SQL_GET_AUTHORS + sb.toString();
		
		List<Integer> res = new ArrayList<>();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(getQuery);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				res.add(rs.getInt(1));
				auth.remove(rs.getString(2));
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		return res;
		
	}
	
	<T> String listToValues(List<T> list) {
		StringBuilder sb = new StringBuilder();
		String start, end;
		T temp = list.get(0);
		if (temp instanceof String) {
			start = "('";
			end = "'),";
		} else {
			start = "(";
			end = "),";
		}
		for (T a : list) {
			sb.append(start);
			sb.append(a);
			sb.append(end);
		}
		sb.replace(sb.length() - 1, sb.length(), ";");
		return sb.toString();
	}

	<T, R> String pairToValues(List<T> fst, R sec) {
		StringBuilder sb = new StringBuilder();
		String start, middle, end;
		T temp = fst.get(0);
		if (temp instanceof String) {
			start = "('";
			middle = "',";
		} else {
			start = "(";
			middle = ",";
		}
		if (!(sec instanceof String)) {
			end = "),";
		} else {
			middle = middle + "'";
			end = "'),";
		}
		for (T t : fst) {
			sb.append(start);
			sb.append(t);
			sb.append(middle);
			sb.append(sec);
			sb.append(end);
		}
		sb.replace(sb.length() - 1, sb.length(), ";");
		return sb.toString();
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
			MysqlDAOFactory.roolback(con);
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
			} else {
				throw new SQLException("addBook: No data inserted");
			}
			
			List<Integer> aIds = addAuthors(con, item.getAuthor());
			String q = Querys.SQL_ADD_BOOK_AUTHORS + pairToValues(aIds, bookId);
			st = con.prepareStatement(q, PreparedStatement.RETURN_GENERATED_KEYS);
			count = st.executeUpdate();
			
		} finally {
			st.close();
		}
		return bookId;
		
	}

	@Override
	public Book deleteBook(int id) throws DAOException {
		throw new UnsupportedOperationException();
//		return null;
	}

	@Override
	public boolean updateBookCount(int id, int count) throws DAOException {
		Connection con = null;
		try {
			con = getConnection();
			boolean res = updateBookCount(con, id, count);
			MysqlDAOFactory.commit(con);
			return res;
		} catch (SQLException e) {
			log.error("listBooks: Can not listBooks", e);
			throw new DAOException("Can not listBooks", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
	}

	public boolean updateBookCount(Connection con, int id, int count) throws SQLException {
		
		return false;
	}
	
	@Override
	public Collection<Book> findByTitle(String pattern) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
//		return null;
	}

	@Override
	public Collection<Book> findByAuthor(String pattern) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
//		return null;
	}

	@Override
	public List<Book> listBooks(String pattern) throws DAOException {
		return list(pattern, "title", true, 0, 0, null);
	}

	@Override
	public List<Book> list(String pattern, String orderColumn, boolean ascending, 
			int start, int count, SQLCountWrapper total) throws DAOException {
		List<Book> books = null;
		Connection con = null;
		try {
			con = getConnection();
			books = listBooks(con, pattern, orderColumn, true, start, count, total);
		} catch (SQLException e) {
			log.error("listBooks: Can not listBooks", e);
			throw new DAOException("Can not listBooks", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return books;
	}

	List<Book> listBooks(Connection con, String pattern, String orderColumn, boolean ascending, 
			int start, int count, SQLCountWrapper total) throws SQLException {
		List<Book> books = new ArrayList<>();
		PreparedStatement st = null;
		try {
			String where = pattern == null || pattern.length() == 0 ? "" : 
				" WHERE title LIKE '%" + pattern + "%' OR authors LIKE '%" + pattern + "%' ";
			String order = orderColumn == null || orderColumn.length() == 0 ? "" : "ORDER BY " 
					+ orderColumn + (ascending ? " ASC" : " DESC");
			String limit = (count == 0 ? "" : " LIMIT " + start + "," + count);
			String query = Querys.SQL_FIND_BOOKS + where + order + limit;
			st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				books.add(unmapBook(rs));
			}
			st.close();
			if (total != null) {
				query = Querys.SQL_FIND_BOOKS_COUNT + where;
				st = con.prepareStatement(query);
				rs = st.executeQuery();
				while (rs.next()) {
					total.setCount(rs.getInt(1));
				}
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		return books;
	}
	
	@Override
	public Book findById(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> listAuthors() throws DAOException {
		Map<Integer, String> authors = null;
		Connection con = null;
		try {
			con = getConnection();
			authors = listAuthors(con);
		} catch (SQLException e) {
			log.error("Can not add book", e);
			throw new DAOException("Can not add book", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return authors;
	}

	public Map<Integer, String> listAuthors(Connection con) throws SQLException {
		Map<Integer, String> authors = new TreeMap<>();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(Querys.SQL_LIST_AUTHORS);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				authors.put(rs.getInt(1), rs.getString(2));
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		
		return authors;
	}
	
	List<Integer> addAuthors(Connection con, List<Author> list) throws SQLException {
		assert list.isEmpty() : "Empty authors";
		List<Author> auth = new ArrayList<>(list);
		List<Integer> res = getExistedAuthors(con, auth);
		if (auth.isEmpty()) {
			return res;
		}
		List<String> toAdd = new ArrayList<>();
		for (Author a : auth) {
			toAdd.add(a.getTitle());
		}
		String addQuery = Querys.SQL_ADD_AUTHOR + listToValues(toAdd);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(addQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			int count = st.executeUpdate();
			if (count == 0) {
				throw new SQLException("addAuthors: No data inserted");
			}
			ResultSet rs = st.getGeneratedKeys();
			while (rs.next()) {
				res.add(rs.getInt(1));
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		return res;
	}

	@Override
	public Book getBook(int id) throws DAOException {
		Connection con = null;
		try {
			con = getConnection();
			return getBook(con, id);
		} catch (SQLException e) {
			log.error("listBooks: Can not listBooks", e);
			throw new DAOException("Can not listBooks", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
	}

	public Book getBook(Connection con, int id) throws SQLException {
		Book book = null;
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(Querys.SQL_FIND_BOOK_BY_ID);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return unmapBook(rs);
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		return book;
	}
	
}
