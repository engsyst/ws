SELECT `author`.`id`, `author`.`title` FROM `author` INNER JOIN `author_has_book` on `author`.`id` = `author_id` and `book_id` = 25;
			/*INNER JOIN `book` on  = `book`.`id` where `book`.`id` = 25*/

SELECT * FROM `book` WHERE title LIKE "%а%" OR title LIKE "%а%";

SELECT book.title, author.title, author_has_book.book_id, author_has_book.author_id,
       concatauthors(author_has_book.book_id) 
  from author_has_book, book, author 
  where author_has_book.author_id = author.id and author_has_book.book_id = book.id;

SELECT * FROM books;

SELECT book.*, author.*
	FROM author_has_book, book, author 
	WHERE author_has_book.author_id = author.id AND author_has_book.book_id = book.id
    ORDER BY 2;

SELECT book.id, book.title, concatauthors(book.id) as `authors`, book.isbn, book.price, book.count, category.title as category
  from book, category WHERE category.id = category_id;
  
SELECT `id`, `title`, `authors`, `isbn`, `price`, `count`, `category` FROM `books` ORDER BY title DESC LIMIT 0,6;