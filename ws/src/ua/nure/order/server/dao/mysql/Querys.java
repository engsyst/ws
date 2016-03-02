package ua.nure.order.server.dao.mysql;

import java.util.List;

public interface Querys {
	static final String SQL_ADD_BOOK = "INSERT INTO `ws`.`book` "
			+ "(`title`, `isbn`, `price`, `count`, `category_id`) "
			+ "VALUES (?, ?, ?, ?, ?);";
	
	/**
	 *  use makeAuthorsValues(List<String> authors, int bookId)
	 *  to generate values string in format: (name1,n),(name2,n) ... ;
	 */
	static final String SQL_ADD_AUTHOR = "INSERT INTO `ws`.`author` "
			+ "(`title`) VALUES ";

	static final String SQL_GET_CATEGORY_ID = "SELECT id FROM category WHERE title = ?";

	static final String SQL_GET_AUTHORS = "SELECT id, title FROM autor WHERE ";
	
}
