SELECT `author`.`id`, `author`.`title` FROM `author` INNER JOIN `author_has_book` on `author`.`id` = `author_id` and `book_id` = 25;
			/*INNER JOIN `book` on  = `book`.`id` where `book`.`id` = 25*/

SELECT * FROM `book` WHERE title LIKE "%а%" OR title LIKE "%а%";

SELECT book.title, author.title, author_has_book.book_id, author_has_book.author_id,
       concatauthors(author_has_book.book_id) 
  from author_has_book, book, author 
  where author_has_book.author_id = author.id and author_has_book.book_id = book.id;

SELECT * FROM books LIMIT 10,5;

SELECT book.*, author.*, count(book.id)
	FROM author_has_book, book, author 
	WHERE author_has_book.author_id = author.id AND author_has_book.book_id = book.id
    GROUP BY book.id
    ORDER BY 2;

SELECT book.id, book.title, concatauthors(book.id) as `authors`, book.isbn, book.price, book.count, category.title as category
  from book, category WHERE category.id = cbook_has_order_BEFORE_INSERTategory_id;
  
SELECT `id`, `title`, `authors`, `isbn`, `price`, `count`, `category` FROM `books` ORDER BY title DESC LIMIT 0,6;

SELECT `id`, `title`, `authors`, `isbn`, `price`, `count`, `category` FROM `books`  WHERE title LIKE '%ка%' OR authors LIKE '%ка%' ;

SELECT `id`, `title`, `authors`, `isbn`, `price`, `count`, `category` FROM `books` WHERE ID = 24;

INSERT INTO `ws`.`order` (`no`, `user_id`) VALUES (1, 2);
INSERT INTO `ws`.`book_has_order` (`book_id`, `order_id`, `count`) VALUES ('24', '1', '1');

SELECT `id`, `count` FROM `book` WHERE `id` IN ('20','22','24');

SELECT `order`.`id` as `oid`, `user`.`id` as `uid`, `user`.`login`, `book`.`id` as `bid`, `book`.`title`, `book_has_order`.`book_id`, `book_has_order`.`count`, `book_has_order`.`price`, sum(`book_has_order`.`price` * `book_has_order`.`count`)
	FROM `order`, `book`, `book_has_order`, `user`
	WHERE `user`.`id` = `order`.`user_id` AND `order`.`id` = `book_has_order`.`order_id` AND `book_has_order`.`book_id` = `book`.`id`
    GROUP BY `order`.`id`, `user`.`id`;

SELECT 
	`order_id`, sum(`price` * `count`)
	FROM `book_has_order`
    GROUP BY `order_id`;

SELECT 
	`user`.`id`, `user`.`login`, `order`.`id`, `book`.`id`, `book`.`title`, `book_has_order`.`count`, `book_has_order`.`price`, `osum`.`s`
    FROM `user`, `order`, `book`, `book_has_order`, (SELECT `order_id` oid, sum(`price` * `count`) s FROM `book_has_order` GROUP BY `order_id`) osum
	WHERE `user`.`id` = `order`.`user_id` AND `order`.`id` = `book_has_order`.`order_id` AND `book_has_order`.`book_id` = `book`.`id` AND `order`.`id` = osum.oid
	ORDER BY `user`.`id`, `order`.`id`, `book`.`id`;

SELECT * from sum_by_order;

SELECT * FROM orders;
SELECT `user_id`,`login`,`order_id`,`status`,`book_id`,`title`,`count`,`price`,`osum` FROM orders;

SELECT 
        `user`.`id` `user_id`,
        `user`.`login` `login`,
        `order`.`id` `order_id`,
        `book`.`id` `book_id`,
        `book`.`title` `title`,
        `book_has_order`.`count` `count`,
        `book_has_order`.`price` `price`,
        `sum_by_order`.`osum` `osum`
    FROM
        `user`,
        `order`,
        `book`,
        `book_has_order`,
        `sum_by_order`
    WHERE
        `user`.`id` = `order`.`user_id`
            AND `order`.`id` = `book_has_order`.`order_id`
            AND `book_has_order`.`book_id` = `book`.`id`
            AND `order`.`id` = `sum_by_order`.`oid`
    ORDER BY `user`.`id` , `order`.`id` , `book`.`id`;

SELECT `user_id`,`login`,`order_id`,`status`,`book_id`,`title`,`count`,`price`,`osum` FROM orders ORDER BY login ASC,`order_id` DESC;

SELECT distinct order_id FROM `orders`;

SELECT `id`,`status` FROM `order` WHERE `id` = 20;

UPDATE `order` SET `status` = 'newed' WHERE `id` = 20;

SELECT `id`,`no`,`user_id`,`date`,`status` FROM `order`;

SELECT 
        `order`.`user_id` `user_id`,
        (SELECT `login` FROM `user` WHERE `user`.`id` = `order`.`user_id`) `login`,
        `order`.`id` `order_id`,
        `order`.`status` `status`,
        `book`.`id` `book_id`,
        `book`.`title` `title`,
        `book_has_order`.`count` `count`,
        `book_has_order`.`price` `price`,
        `sum_by_order`.`osum` `osum`,
        `delivery_id` `delivery_id`
    FROM
        `order`,
        `book`,
        `book_has_order`,
        `sum_by_order`,
        `delivery`
    WHERE
        `order`.`id` = `book_has_order`.`order_id`
            AND `book_has_order`.`book_id` = `book`.`id`
            AND `order`.`id` = `sum_by_order`.`oid`
    ORDER BY `order`.`user_id` , `order`.`id` , `book`.`id`;
    
SELECT DISTINCT `order_id` FROM `orders`  WHERE `status` = 'newed' ORDER BY login ASC,`order_id` DESC LIMIT 0,10;

SELECT DISTINCT `orders`.`user_id`,`login`,`order_id`,`status`,`book_id`,`title`,`count`,`price`,`osum`,`name`, `phone`,`email`,`address`,`description`
FROM `orders`,`delivery` 
WHERE `order_id` = 35 AND `delivery_id` = `delivery`.`id`;