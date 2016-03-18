package ua.nure.order.server.dao;

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
	 * Get count of each book
	 * 
	 * @param book
	 * @return
	 * @throws DAOException 
	 */
	public boolean getBooksCount(Set<Book> books) throws DAOException;
	
	/**
	 * 
	 * @return All books in order
	 * @throws DAOException 
	 */
	public List<Book> listBooks(String pattern) throws DAOException;

	@Override
	public List<Book> list(String pattern, String orderColumn, boolean ascending, int start, int count,
			SQLCountWrapper total) throws DAOException;
	
	Map<Integer, String> listAuthors() throws DAOException;

	public Book getBook(int id) throws DAOException;

	public void updateBook(Book book) throws DAOException;

	public Map<Integer, String> getCategories() throws DAOException;

}
