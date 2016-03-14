CREATE VIEW `sum_by_order` AS SELECT `order_id` `oid`, sum(`price` * `count`) `osum` FROM `book_has_order` GROUP BY `order_id`
