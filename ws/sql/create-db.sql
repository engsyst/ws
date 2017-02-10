-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ws
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ws` ;

-- -----------------------------------------------------
-- Schema ws
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ws` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `ws` ;

-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `book` ;

CREATE TABLE IF NOT EXISTS `book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `isbn` VARCHAR(45) NULL,
  `price` DOUBLE NOT NULL DEFAULT 0,
  `count` INT NOT NULL DEFAULT 0,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC),
  INDEX `fk_book_category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_book_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `author` ;

CREATE TABLE IF NOT EXISTS `author` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `authir_idx` (`title` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(65) NOT NULL,
  `role` ENUM('client','admin') NOT NULL DEFAULT 'client',
  PRIMARY KEY (`id`),
  INDEX `login_idx` (`login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order` ;

CREATE TABLE IF NOT EXISTS `order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `no` INT NULL,
  `user_id` INT NOT NULL,
  `date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `book_has_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `book_has_order` ;

CREATE TABLE IF NOT EXISTS `book_has_order` (
  `book_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  `count` INT NOT NULL,
  `price` DOUBLE NOT NULL,
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


-- -----------------------------------------------------
-- Table `author_has_book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `author_has_book` ;

CREATE TABLE IF NOT EXISTS `author_has_book` (
  `author_id` INT NOT NULL,
  `book_id` INT NOT NULL,
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
ENGINE = InnoDB;

USE `ws`;

DELIMITER $$

USE `ws`$$
DROP TRIGGER IF EXISTS `book_has_order_AFTER_INSERT` $$
USE `ws`$$
CREATE DEFINER = CURRENT_USER TRIGGER `ws`.`book_has_order_AFTER_INSERT` AFTER INSERT ON `book_has_order` FOR EACH ROW
    begin
		SET @bid = book_id;
		SET @oid = order_id;
        SET @new_count = (select count from book where id = book_id) - count;
		SET @new_price = (select price from book where id = book_id);
        if (@new_count < 0) then 
			SIGNAL sqlstate '45001' set message_text = "Not enouth books!";
        end if;
        UPDATE book_has_order SET price = @new_price WHERE book_id = @bid and order_id = @oid;
        UPDATE book SET count = @new_count;
	end;$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
