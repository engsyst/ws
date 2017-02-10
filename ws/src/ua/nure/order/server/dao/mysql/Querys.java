package ua.nure.order.server.dao.mysql;

public interface Querys {
	static final String SQL_ADD_BOOK = "INSERT INTO `ws`.`book` "
			+ "(`title`, `isbn`, `price`, `count`, `category_id`) "
			+ "VALUES (?, ?, ?, ?, ?);";
	
	/**
	 *  use makeAuthorsValues(List<String> authors, int bookId)
	 *  to generate values string in format: (name1,n),(name2,n) ... ;
	 */
	static final String SQL_ADD_AUTHOR = "INSERT INTO `author` (`title`) VALUES ";
	static final String SQL_ADD_BOOK_AUTHORS = "INSERT INTO `author_has_book` (`author_id`, `book_id`) VALUES ";

	static final String SQL_GET_CATEGORY_ID = "SELECT id FROM category WHERE title = ?";

	static final String SQL_GET_AUTHORS = "SELECT `id`, `title` FROM `author` WHERE ";
	static final String SQL_GET_BOOK_AUTHORS = "SELECT `author`.`id`, `author`.`title` FROM `author` "
			+ "INNER JOIN `author_has_book` ON `author`.`id` = `author_id` AND `book_id` = ?";
	static final String SQL_LIST_AUTHORS = "SELECT `id`, `title` FROM `author`";
	String SQL_GET_USER = "SELECT `id`, `login`, `password`, `role` FROM `user` WHERE `login` = ?"; 
	
}
