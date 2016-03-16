package ua.nure.order.server.dao.mysql;

public interface Querys {
	static final String SQL_ADD_BOOK = "INSERT INTO `ws`.`book` "
			+ "(`title`, `isbn`, `price`, `count`, `category_id`) "
			+ "VALUES (?, ?, ?, ?, ?);";
	
	static final String SQL_FIND_BOOKS = "SELECT `id`, `title`, `authors`, "
			+ "`isbn`, `price`, `count`, `category` FROM `books` ";
	static final String SQL_FIND_BOOK_BY_ID = "SELECT `id`, `title`, `authors`, "
			+ "`isbn`, `price`, `count`, `category` FROM `books` WHERE ID = ?";
	static final String SQL_FIND_BOOKS_COUNT = "SELECT count(*) FROM `books` ";
	static final String SQL_UPDATE_BOOKS_COUNT = "SELECT count(*) FROM `books` ";
	
	/**
	 *  use makeAuthorsValues(List? authors, int bookId)
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
	
	static final String SQL_GET_USER = "SELECT id, login, password, role FROM user WHERE login = ?";

	static final String SQL_ADD_USER = "INSERT INTO `user` (`login`,`password`,`role`,`e-mail`,`phone`,`name`,`address`,`avatar`,`description`) "
			+ "VALUES (?,?,?,?,?,?,?,?,?)";
	
	static final String SQL_INSERT_ORDER = "INSERT INTO `order` (`no`, `user_id`) VALUES (?, ?);";

	static final String SQL_INSERT_BOOK_HAS_ORDER = "INSERT INTO `book_has_order` "
			+ "(`book_id`, `order_id`, `count`) VALUES (?, ?, ?);";

	static final String SQL_GET_BOOKS_COUNT = "SELECT `id`, `count` FROM `book` WHERE `id` IN ";
	
	static final String SQL_GET_FULL_ORDERS = "SELECT `user_id`,`login`,`order_id`,`status`,`book_id`,`title`,`count`,`price`,`osum` FROM orders ";
	
	static final String SQL_GET_ORDER_BY_ID = "SELECT `id`,`no`,`user_id`,`date`,`status` FROM `order` WHERE `id` = ?";

	static final String SQL_GET_ORDER_STATUS = "SELECT `id`,`status` FROM `order` WHERE `id` = ?";

	static final String SQL_GET_ORDER_DETAL = "SELECT `user_id`,`login`,`order_id`,`status`,`book_id`,`title`,`count`,`price`,`osum` FROM orders WHERE `order_id` = ?";

	static final String SQL_UPDATE_ORDER_STATUS = "UPDATE `order` SET `status` = ? WHERE `id` = ?";

	static final String SQL_FIND_ORDERS_COUNT = "SELECT count(*) FROM `order` ";

	static final String SQL_GET_ORDERS_ID = "SELECT DISTINCT `order_id` FROM `orders` ";
}
