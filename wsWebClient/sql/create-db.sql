-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema books
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `books` ;

-- -----------------------------------------------------
-- Schema books
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `books` DEFAULT CHARACTER SET utf8 ;
USE `books` ;

-- -----------------------------------------------------
-- Table `author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `author` ;

CREATE TABLE IF NOT EXISTS `author` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `author_title_idx` (`title` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `book` ;

CREATE TABLE IF NOT EXISTS `book` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `isbn` VARCHAR(45) NULL DEFAULT NULL,
  `price` DOUBLE UNSIGNED NOT NULL DEFAULT '0',
  `count` INT(11) UNSIGNED NOT NULL DEFAULT '0',
  `category_id` INT(11) NOT NULL,
  `cover` VARCHAR(1000) NULL,
  `description` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `book_title_idx` (`title` ASC),
  INDEX `fk_book_category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_book_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `author_has_book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `author_has_book` ;

CREATE TABLE IF NOT EXISTS `author_has_book` (
  `author_id` INT(11) NOT NULL,
  `book_id` INT(11) NOT NULL,
  PRIMARY KEY (`author_id`, `book_id`),
  INDEX `fk_author_has_book_book1_idx` (`book_id` ASC),
  INDEX `fk_author_has_book_author1_idx` (`author_id` ASC),
  CONSTRAINT `fk_author_has_book_author1`
    FOREIGN KEY (`author_id`)
    REFERENCES `author` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_author_has_book_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(65) NOT NULL,
  `role` ENUM('client','admin') NOT NULL DEFAULT 'client',
  `e-mail` VARCHAR(45) NULL,
  `phone` VARCHAR(15) NULL,
  `name` VARCHAR(45) NULL,
  `address` VARCHAR(1000) NULL,
  `avatar` VARCHAR(1000) NULL,
  `description` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_login_idx` (`login` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `delivery`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery` ;

CREATE TABLE IF NOT EXISTS `delivery` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `phone` VARCHAR(15) NULL,
  `email` VARCHAR(45) NULL,
  `address` VARCHAR(1000) NULL,
  `description` VARCHAR(1000) NULL,
  `user_id` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_delivery_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_delivery_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order` ;

CREATE TABLE IF NOT EXISTS `order` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `no` INT(11) NULL,
  `user_id` INT(11) NULL,
  `date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP(),
  `status` ENUM('newed','inprogress','completed','rejected') NULL DEFAULT 'newed',
  `delivery_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_user1_idx` (`user_id` ASC),
  INDEX `fk_order_delivery1_idx` (`delivery_id` ASC),
  INDEX `order_date_idx` (`date` DESC),
  INDEX `order_status_idx` (`date` ASC),
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_delivery1`
    FOREIGN KEY (`delivery_id`)
    REFERENCES `delivery` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `book_has_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `book_has_order` ;

CREATE TABLE IF NOT EXISTS `book_has_order` (
  `book_id` INT(11) NOT NULL,
  `order_id` INT(11) NOT NULL,
  `count` INT(11) UNSIGNED NOT NULL,
  `price` DOUBLE UNSIGNED NOT NULL DEFAULT 0.0,
  PRIMARY KEY (`book_id`, `order_id`),
  INDEX `fk_book_has_order_order1_idx` (`order_id` ASC),
  INDEX `fk_book_has_order_book1_idx` (`book_id` ASC),
  CONSTRAINT `fk_book_has_order_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_has_order_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `books` ;

-- -----------------------------------------------------
-- Placeholder table for view `books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `books` (`id` INT, `title` INT, `authors` INT, `isbn` INT, `price` INT, `count` INT, `category` INT);

-- -----------------------------------------------------
-- Placeholder table for view `sum_by_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sum_by_order` (`oid` INT, `osum` INT);

-- -----------------------------------------------------
-- Placeholder table for view `orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orders` (`user_id` INT, `login` INT, `order_id` INT, `status` INT, `book_id` INT, `title` INT, `count` INT, `price` INT, `osum` INT, `delivery_id` INT);

-- -----------------------------------------------------
-- function concatauthors
-- -----------------------------------------------------

USE `books`;
DROP function IF EXISTS `concatauthors`;

DELIMITER $$
USE `books`$$
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
$$

DELIMITER ;

-- -----------------------------------------------------
-- View `books`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `books` ;
DROP TABLE IF EXISTS `books`;
USE `books`;
CREATE  OR REPLACE VIEW `books` AS SELECT book.id, book.title, concatauthors(book.id) as `authors`, book.isbn, book.price, book.count, category.title as category
  from book, category WHERE category.id = category_id;


-- -----------------------------------------------------
-- View `sum_by_order`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `sum_by_order` ;
DROP TABLE IF EXISTS `sum_by_order`;
USE `books`;
CREATE  OR REPLACE VIEW `sum_by_order` AS SELECT `order_id` `oid`, sum(`price` * `count`) `osum` FROM `book_has_order` GROUP BY `order_id`
;

-- -----------------------------------------------------
-- View `orders`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `orders` ;
DROP TABLE IF EXISTS `orders`;
USE `books`;
CREATE  OR REPLACE VIEW `orders` AS 
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
        `delivery_id`
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

USE `books`;

DELIMITER $$

USE `books`$$
DROP TRIGGER IF EXISTS `book_has_order_BEFORE_INSERT` $$
USE `books`$$
CREATE DEFINER = CURRENT_USER TRIGGER `books`.`book_has_order_BEFORE_INSERT` BEFORE INSERT ON `book_has_order` FOR EACH ROW
begin
	SET @price = (select price from book where id = NEW.`book_id`);
    SET @newcount = (select count from book where id = NEW.`book_id`) - NEW.count;
    SET NEW.price = @price;
    UPDATE book SET book.count = @newcount where id = NEW.`book_id`;
end$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data inserts
-- -----------------------------------------------------

-- -----------------------------------------------------
-- category
-- -----------------------------------------------------
INSERT INTO `category` (`id`,`title`) VALUES (1,'Fantasy');
INSERT INTO `category` (`id`,`title`) VALUES (2,'Action');
INSERT INTO `category` (`id`,`title`) VALUES (3,'Love novel');
INSERT INTO `category` (`id`,`title`) VALUES (4,'none');

-- -----------------------------------------------------
-- book
-- -----------------------------------------------------
INSERT INTO `book` (`id`,`title`,`isbn`,`price`,`count`,`category_id`,`cover`,`description`) VALUES (1,'Божественная комедия','ISBN-01234-0123',125,3,1,'NULL','');
INSERT INTO `book` (`id`,`title`,`isbn`,`price`,`count`,`category_id`,`cover`,`description`) VALUES (2,'Сказки','ISBN-01234-0124',300,5,2,'NULL','NULL');
INSERT INTO `book` (`id`,`title`,`isbn`,`price`,`count`,`category_id`,`cover`,`description`) VALUES (3,'И пришло разрушение','ISBN-01234-0125',245.5,1,2,'NULL','NULL');
INSERT INTO `book` (`id`,`title`,`isbn`,`price`,`count`,`category_id`,`cover`,`description`) VALUES (4,'Отец Горио','ISBN-01234-0126',46,3,2,'','');
INSERT INTO `book` (`id`,`title`,`isbn`,`price`,`count`,`category_id`,`cover`,`description`) VALUES (5,'Вымыслы','ISBN-01234-0127',118.3,4,2,'NULL','');
INSERT INTO `book` (`id`,`title`,`isbn`,`price`,`count`,`category_id`,`cover`,`description`) VALUES (6,'Декамерон','ISBN-01234-0128',148.7,2,3,'NULL','NULL');
INSERT INTO `book` (`id`,`title`,`isbn`,`price`,`count`,`category_id`,`cover`,`description`) VALUES (7,'Вопрос о воде и земле','ISBN-01234-0133',125,4,4,'NULL','');

-- -----------------------------------------------------
-- author
-- -----------------------------------------------------
INSERT INTO `author` (`id`,`title`) VALUES (1,'Данте Алигьери');
INSERT INTO `author` (`id`,`title`) VALUES (2,'Ханс Кристиан Андерсен');
INSERT INTO `author` (`id`,`title`) VALUES (3,'Чинуа Ачебе');
INSERT INTO `author` (`id`,`title`) VALUES (4,'Оноре де Бальзак');
INSERT INTO `author` (`id`,`title`) VALUES (5,'Хорхе Луис Борхес');
INSERT INTO `author` (`id`,`title`) VALUES (6,'Джованни Боккаччо');

-- -----------------------------------------------------
-- author_has_book
-- -----------------------------------------------------
INSERT INTO `author_has_book` (`author_id`,`book_id`) VALUES (1,1);
INSERT INTO `author_has_book` (`author_id`,`book_id`) VALUES (1,7);
INSERT INTO `author_has_book` (`author_id`,`book_id`) VALUES (2,2);
INSERT INTO `author_has_book` (`author_id`,`book_id`) VALUES (3,3);
INSERT INTO `author_has_book` (`author_id`,`book_id`) VALUES (4,4);
INSERT INTO `author_has_book` (`author_id`,`book_id`) VALUES (5,5);
INSERT INTO `author_has_book` (`author_id`,`book_id`) VALUES (6,6);

-- -----------------------------------------------------
-- user
-- -----------------------------------------------------
INSERT INTO `user` (`login`, `password`, `role`) VALUES ('admin', '8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918', 'admin');
INSERT INTO `user` (`login`, `password`, `role`) VALUES ('client', '948FE603F61DC036B5C596DC09FE3CE3F3D30DC90F024C85F3C82DB2CCAB679D', 'client');
