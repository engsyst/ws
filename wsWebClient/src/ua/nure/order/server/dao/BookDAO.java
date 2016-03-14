package ua.nure.order.server.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ua.nure.order.client.Paginable;
import ua.nure.order.client.SQLCountWrapper;
import ua.nure.order.entity.book.Book;

public interface BookDAO extends Paginable<Book> {
	/**
	 * Add a book to order
	 * 
	 * @param book
	 * @return
	 * @throws DAOException 
	 */
	public int addBook(Book item) throws DAOException;

	/**
	 * Add a book to order
	 * 
	 * @param book
	 * @return
	 * @throws DAOException 
	 */
	public Book deleteBook(int id) throws DAOException;
	
	/**
	 * Update count of the book
	 * 
	 * @param book
	 * @return
	 * @throws DAOException 
	 */
	public boolean updateBookCount(int id, int count) throws DAOException;
	
	/**
	 * Get count of each book
	 * 
	 * @param book
	 * @return
	 * @throws DAOException 
	 */
	public boolean getBooksCount(Set<Book> books) throws DAOException;
	
	/**
	 * Find book with pattern
	 * 
	 * @param name
	 * @return
	 */
	public Collection<Book> findByTitle(String pattern);

	/**
	 * Find book with pattern author in the order
	 * 
	 * @param name
	 * @return
	 */
	public Collection<Book> findByAuthor(String pattern);

	/**
	 * 
	 * @return All books in order
	 * @throws DAOException 
	 */
	public List<Book> listBooks(String pattern) throws DAOException;

	@Override
	public List<Book> list(String pattern, String orderColumn, boolean ascending, int start, int count,
			SQLCountWrapper total) throws DAOException;
	
	public Book findById(Integer id) throws DAOException;

	Map<Integer, String> listAuthors() throws DAOException;

	public Book getBook(int id) throws DAOException;

}
