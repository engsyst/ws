CREATE DEFINER = CURRENT_USER TRIGGER `ws`.`book_has_order_AFTER_INSERT` AFTER INSERT ON `book_has_order` FOR EACH ROW
begin
	SET @price = (select price from book where id = NEW.book_id);
    SET @newcount = @price - NEW.count;
    if (@newcount < 0) then
		SIGNAL sqlstate '45001' set message_text = "Not enough book!";
    end if;
		
    UPDATE book SET book.count = @newcount where id = NEW.book_id;
    UPDATE book_has_order SET price = @price where book_id = NEW.book_id and order_id = NEW.order_id;
end;
