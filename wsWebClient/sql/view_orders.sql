CREATE VIEW `orders` AS SELECT 
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
