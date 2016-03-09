package ua.nure.order.server.dao;

import java.util.Map;

import ua.nure.order.entity.book.Book;

public interface OrderDAO {
	int makeOrder(Map<Book, Integer> items, int userId) throws DAOException;
}
