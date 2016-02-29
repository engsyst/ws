package ua.nure.order.server.dao;

import java.util.Collection;

import ua.nure.order.entity.OrderItem;
import ua.nure.order.entity.book.Book;

public interface Card {

	/**
	 * Add book to card. If Book already exist in card increase amount of Book in OrderItem
	 * 
	 * @param item
	 * @param count
	 * @return id of OrderItem
	 * @throws DAOException
	 */
	public abstract int addBook(Book item, Integer count) 
			throws DAOException;

	/**
	 * Remove Book from card. if count != null decrees count of Book in OrderItem
	 * @param item
	 * @param count
	 * @return amount of removed Book
	 * @throws DAOException
	 */
	public abstract int removeBook(Book item, Integer count)
			throws DAOException;

	public abstract Collection<OrderItem> findByTitle(String pattern);

	public abstract Collection<OrderItem> findByAuthor(String pattern);

	public abstract Collection<OrderItem> getOrders();

	public abstract OrderItem findById(int id);

	public abstract OrderItem getByBook(Book book);

}