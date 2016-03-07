CREATE FUNCTION `concatauthors` (aid INT(11)) RETURNS VARCHAR(512)
BEGIN
	DECLARE done INT DEFAULT 0;
	DECLARE k INT DEFAULT 0;
    DECLARE retv VARCHAR(512);
    DECLARE temp VARCHAR(45);
	DECLARE cur CURSOR FOR 
      SELECT `author`.`title` FROM `author_has_book`,`author` 
       WHERE  `author_has_book`.`book_id` = `aid` and `author_has_book`.`author_id` = `author`.`id`;
    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
    
    OPEN cur;
    SET retv:='';
    
    REPEAT
		FETCH cur INTO temp;
        IF NOT done THEN
			IF k = 0 THEN
				SET retv:=CONCAT(retv, temp);
                SET k := k +1;
			ELSE
				SET retv:=CONCAT(retv, ",", temp);
                END IF;
		END IF;
	UNTIL done END REPEAT;
    RETURN retv;
END
