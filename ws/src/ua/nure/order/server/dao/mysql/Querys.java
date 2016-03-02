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
	static final String SQL_ADD_BOOK_AUTHORS = "INSERT INTO `ws`.`author_has_book` (`author_id`, `book_id`) VALUES ";

	static final String SQL_GET_CATEGORY_ID = "SELECT id FROM category WHERE title = ?";

	static final String SQL_GET_AUTHORS = "SELECT id, title FROM author WHERE ";
	static final String SQL_GET_BOOK_AUTHORS = "select author.id, author.title from author "
			+ "inner join author_has_book on author.id = author_id "
			+ "inner join book on book_id = book.id where book.id = ?";
	static final String SQL_LIST_AUTHORS = "SELECT id, title FROM author";
	
}
