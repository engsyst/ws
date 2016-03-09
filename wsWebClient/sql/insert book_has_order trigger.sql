CREATE
DEFINER=`root`@`localhost`
TRIGGER `ws`.`book_has_order_AFTER_INSERT`
AFTER INSERT ON `ws`.`book_has_order`
FOR EACH ROW
begin
	SET @bid = book_id;
	SET @oid = order_id;
	SET @price = (select price from book where id = @bid);
    SET @newcount = (select count from book where id = @bid) - NEW.count;
    if (@newcount < 0) then
		SIGNAL sqlstate '45001' set message_text = "Not enough book!";
    end if;
		
    UPDATE book_has_order SET price = @price WHERE book_id = @bid and order_id = @oid;
    UPDATE book SET book.count = @newcount where id = @bid;
end