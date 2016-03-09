CREATE DEFINER = CURRENT_USER TRIGGER `ws`.`book_has_order_BEFORE_INSERT` BEFORE INSERT ON `book_has_order` FOR EACH ROW
begin
	SET @price = (select price from book where id = NEW.book_id);
    SET @newcount = (select count from book where id = @bid) - NEW.count;
    SET NEW.price = @price;
    UPDATE book SET book.count = @newcount where id = @bid;
end