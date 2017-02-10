package ua.nure.order.server.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.order.client.SQLCountWrapper;
import ua.nure.order.entity.book.Author;
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.server.dao.BookDAO;
import ua.nure.order.server.dao.DAOException;
import ua.nure.order.server.dao.InsertException;
import ua.nure.order.server.dao.UpdateException;

class MysqlBookDAO implements BookDAO {
	private static final Logger log = Logger.getLogger(MysqlBookDAO.class);

	private static MysqlBookDAO dao;
	
	public static BookDAO getInstance() {
		log.trace("Try get instance.");
		if (dao == null) {
			log.trace("Instance not found. Create new.");
			dao = new MysqlBookDAO();
		}
		return dao;
	}
	
	Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			log.trace("Try get connection from pool.");
			con = MysqlDAOFactory.getConnection();
		} catch (SQLException e) {
			log.error("Cannot get connection from pool. Try use DriverManager.");
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
		log.trace("Start");
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
		log.debug("Query --> " + getQuery);
		
		List<Integer> res = new ArrayList<>();
		PreparedStatement st = null;
		try {
			log.debug("Try execute");
			st = con.prepareStatement(getQuery);
			ResultSet rs = st.executeQuery();
			log.debug("Try get result");
			while (rs.next()) {
				res.add(rs.getInt(1));
				auth.remove(new Author(rs.getString(2)));
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		log.debug("Result --> " + res);
		log.trace("Finish");
		return res;
		
	}
	
	@Override
	public int addBook(Book item) throws InsertException {
		Connection con = null;
		int bookId = 0;
		try {
			con = getConnection();
			log.debug("Try add book");
			bookId = addBook(con, item);
			con.commit();
		} catch (SQLException e) {
			MysqlDAOFactory.rollback(con);
			log.error("Can not add book", e);
			throw new InsertException("Can not add book", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return bookId;
	}

	int addBook(Connection con, Book item) throws SQLException {
		log.trace("Start");
		int bookId = 0;
		PreparedStatement st = null;
		try {
			log.debug("Query --> " + Querys.SQL_ADD_BOOK);
			log.debug("Book --> " + item);
			st = con.prepareStatement(Querys.SQL_ADD_BOOK, PreparedStatement.RETURN_GENERATED_KEYS);
			mapBook(st, item);
			int count = st.executeUpdate();
			if (count == 0) {
				throw new SQLException("addAuthors: No data inserted");
			}
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				bookId = rs.getInt(1);
				log.debug("Inserted book id --> " + bookId);
			} else {
				log.error("No data inserted");
				throw new SQLException("addBook: No data inserted");
			}
			
			log.debug("Try add authors");
			List<Integer> aIds = addAuthors(con, item.getAuthor());
			String q = Querys.SQL_ADD_BOOK_AUTHORS + SqlUtil.pairToValues(aIds, bookId);
			log.debug("Query --> " + q);
			st = con.prepareStatement(q, PreparedStatement.RETURN_GENERATED_KEYS);
			count = st.executeUpdate();
			
		} finally {
			st.close();
		}
		log.debug("Result --> " + bookId);
		log.trace("Finish");
		return bookId;
		
	}

	@Override
	public List<Book> listBooks(String pattern) throws DAOException {
		return list(pattern, "title", true, 0, 0, null);
	}

	@Override
	public List<Book> list(String pattern, String orderColumn, boolean ascending, 
			int start, int count, SQLCountWrapper total) throws DAOException {
		log.trace("Start");
		List<Book> books = null;
		Connection con = null;
		try {
			con = getConnection();
			log.debug("Try list book with pattern --> " + pattern + "; orderColumn --> " + orderColumn 
					+ "; ascending --> " + ascending + "; start --> " + start + "; count --> " + count);
			books = listBooks(con, pattern, orderColumn, ascending, start, count, total);
		} catch (SQLException e) {
			log.error("Can not listBooks", e);
			throw new DAOException("Can not listBooks", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		log.trace("Finish");
		return books;
	}

	List<Book> listBooks(Connection con, String pattern, String orderColumn, boolean ascending, 
			int start, int count, SQLCountWrapper total) throws SQLException {
		log.trace("Start");
		List<Book> books = new ArrayList<>();
		PreparedStatement st = null;
		try {
			String where = pattern == null || pattern.length() == 0 ? "" : 
				" WHERE title LIKE '%" + pattern + "%' OR authors LIKE '%" + pattern + "%' ";
			String order = orderColumn == null || orderColumn.length() == 0 ? "" : "ORDER BY " 
					+ orderColumn + (ascending ? " ASC" : " DESC");
			String limit = (count == 0 ? "" : " LIMIT " + start + "," + count);
			String query = Querys.SQL_FIND_BOOKS + where + order + limit;
			log.debug("Query --> " + query);
			st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				books.add(unmapBook(rs));
			}
			st.close();
			if (total != null) {
				log.debug("Try get total books.");
				query = Querys.SQL_FIND_BOOKS_COUNT + where;
				log.debug("Query --> " + query);
				st = con.prepareStatement(query);
				rs = st.executeQuery();
				while (rs.next()) {
					total.setCount(rs.getInt(1));
					log.debug("Total --> " + total);
				}
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		log.debug("Result -- >" + books);
		log.trace("Finish");
		return books;
	}
	
	@Override
	public Map<Integer, String> listAuthors() throws DAOException {
		log.trace("Start");
		Map<Integer, String> authors = null;
		Connection con = null;
		try {
			con = getConnection();
			log.debug("Try list authors.");
			authors = listAuthors(con);
		} catch (SQLException e) {
			log.error("Can not add book", e);
			throw new DAOException("Can not add book", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		log.trace("Finish");
		return authors;
	}

	public Map<Integer, String> listAuthors(Connection con) throws SQLException {
		log.trace("Start");
		Map<Integer, String> authors = new TreeMap<>();
		PreparedStatement st = null;
		try {
			log.debug("Query --> " + Querys.SQL_LIST_AUTHORS);
			st = con.prepareStatement(Querys.SQL_LIST_AUTHORS);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				authors.put(rs.getInt(1), rs.getString(2));
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		
		log.debug("Result -- >" + authors);
		log.trace("Finish");
		return authors;
	}
	
	List<Integer> addAuthors(Connection con, List<Author> list) throws SQLException {
		log.trace("Start");
		assert list.isEmpty() : "Empty authors";
		List<Author> auth = new ArrayList<>(list);
		log.debug("Make copy of book authors --> " + auth);
		log.debug("Try get Existed Authors and remove it from insert -- >");
		List<Integer> res = getExistedAuthors(con, auth);
		if (auth.isEmpty()) {
			log.debug("Nothing to add.");
			log.trace("Finish");
			return res;
		}
		List<String> toAdd = new ArrayList<>();
		for (Author a : auth) {
			toAdd.add(a.getTitle());
		}
		String addQuery = Querys.SQL_ADD_AUTHOR + SqlUtil.listToValues(toAdd);
		PreparedStatement st = null;
		try {
			log.debug("Query -- >" + addQuery);
			st = con.prepareStatement(addQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			int count = st.executeUpdate();
			if (count == 0) {
				log.error("No data inserted");
				throw new SQLException("addAuthors: No data inserted");
			}
			ResultSet rs = st.getGeneratedKeys();
			while (rs.next()) {
				res.add(rs.getInt(1));
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		log.debug("Result -- >" + res);
		log.trace("Finish");
		return res;
	}

	@Override
	public Book getBook(int id) throws DAOException {
		log.trace("Start");
		Connection con = null;
		try {
			con = getConnection();
			log.debug("Try get book --> " + id);
			return getBook(con, id);
		} catch (SQLException e) {
			log.error("listBooks: Can not listBooks", e);
			throw new DAOException("Can not listBooks", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
	}

	public Book getBook(Connection con, int id) throws SQLException {
		log.trace("Start");
		Book book = null;
		PreparedStatement st = null;
		try {
			log.debug("Query --> " + Querys.SQL_FIND_BOOK_BY_ID);
			st = con.prepareStatement(Querys.SQL_FIND_BOOK_BY_ID);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return unmapBook(rs);
			}
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		log.debug("Result -- >" + book);
		log.trace("Finish");
		return book;
	}

	@Override
	public boolean getBooksCount(Set<Book> books) throws DAOException {
		log.trace("Start");
		Connection con = null;
		try {
			con = getConnection();
			log.debug("Try get books count.");
			return getBooksCount(con, books);
		} catch (SQLException e) {
			log.error("Can not get book count", e);
			throw new DAOException("Can not get book count", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
	}

	boolean getBooksCount(Connection con, Set<Book> books) throws SQLException {
		log.trace("Start");
		if (books == null || books.isEmpty()) {
			log.debug("Initial --> " + books);
			return false;
		}
		PreparedStatement st = null;
		List<Integer> ids = new ArrayList<>();
		for (Book b : books) {
			ids.add(b.getId());
		}
		log.debug("Initial --> " + books);
		try {
			String query = Querys.SQL_GET_BOOKS_COUNT + SqlUtil.listToIN(ids);
			log.debug("Query --> " + query);
			st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				int c = rs.getInt(2);
				for (Book b : books) {
					if (b.getId() == id) {
						b.setCount(c);
						break;
					}
				}
			}
			log.debug("Result -- >" + books);
			log.trace("Finish");
			return true;
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
	}

	@Override
	public void updateBook(Book item) throws UpdateException {
		// TODO 
		// 1. ?????????
		// getBook with id
		// replace changed fields
		// updateBook
		// OR
		// 2. ????????????
		// simply updateBook
		log.trace("Start");
		Connection con = null;
		try {
			con = getConnection();
			log.debug("Try add book");
			updateBook(con, item);
			con.commit();
		} catch (SQLException e) {
			MysqlDAOFactory.rollback(con);
			log.error("Can not update book", e);
			throw new UpdateException("Can not update book", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		log.trace("Finish");
	}

	void updateBook(Connection con, Book item) throws SQLException {
		log.trace("Start");
		Book book = null;
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(Querys.SQL_UPDATE_BOOK);
			int k = 0;
			// SET `title` = ?,`isbn` = ?,`price` = ?,`count` = ?,`category_id`= ?,`cover` = ?,`description`= ? WHERE `id` = ?;

			st.setString(++k, item.getTitle());
			st.setString(++k, item.getIsbn());
			st.setDouble(++k, item.getPrice());
			st.setInt(++k, item.getCount());
			st.setInt(++k, item.getCategory().ordinal() + 1);
			st.setString(++k, item.getCover());
			st.setString(++k, item.getDescription());
			st.setInt(++k, item.getId());
			log.debug("Query --> " + st);
			st.executeUpdate();
			st.close();
			st = con.prepareStatement(Querys.SQL_DELETE_AUTHOR_HAS_BOOK);
			st.setInt(1, item.getId());
			log.debug("Query --> " + st);
			st.executeUpdate();
			log.debug("Try add authors");
			List<Integer> aIds = addAuthors(con, item.getAuthor());
			String q = Querys.SQL_ADD_BOOK_AUTHORS + SqlUtil.pairToValues(aIds, item.getId());
			log.debug("Query --> " + q);
			st = con.prepareStatement(q, PreparedStatement.RETURN_GENERATED_KEYS);
			st.executeUpdate();
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		log.debug("Result -- >" + book);
		log.trace("Finish");
	}

	@Override
	public Map<Integer, String> getCategories() throws DAOException {
		Connection con = null;
		Map<Integer, String> cats = null;
		try {
			con = getConnection();
			log.debug("Try add book");
			cats = getCategories(con);
			con.commit();
		} catch (SQLException e) {
			MysqlDAOFactory.rollback(con);
			log.error("Can not update book", e);
			throw new UpdateException("Can not update book", e);
		} finally {
			MysqlDAOFactory.close(con);
		}
		return cats;
	}
	
	Map<Integer, String> getCategories(Connection con) throws SQLException {
		log.trace("Start");
		LinkedHashMap<Integer, String> cats = new LinkedHashMap<>();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(Querys.SQL_GET_CATEGORIES);
			log.debug("Query --> " + st);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				cats.put(rs.getInt(1), rs.getString(2));
			}
			rs.close();
		} finally {
			MysqlDAOFactory.closeStatement(st);
		}
		log.debug("Result -- >" + cats);
		log.trace("Finish");
		return cats;
	}

}
