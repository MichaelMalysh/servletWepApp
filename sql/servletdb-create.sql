-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema servletdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema servletdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `servletdb` DEFAULT CHARACTER SET utf8 ;
USE `servletdb` ;

-- -----------------------------------------------------
-- Table `servletdb`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servletdb`.`roles` ;

CREATE TABLE IF NOT EXISTS `servletdb`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servletdb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servletdb`.`users` ;

CREATE TABLE IF NOT EXISTS `servletdb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `first_name` VARCHAR(90) NULL,
  `last_name` VARCHAR(90) NULL,
  `budget` INT NULL DEFAULT 0,
  `local_name` VARCHAR(90) NULL,
  `roles_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_users_roles1_idx` (`roles_id` ASC) INVISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_users_roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `servletdb`.`roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servletdb`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servletdb`.`category` ;

CREATE TABLE IF NOT EXISTS `servletdb`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servletdb`.`publication`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servletdb`.`publication` ;

CREATE TABLE IF NOT EXISTS `servletdb`.`publication` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `price` INT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_publication_category_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_publication_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `servletdb`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servletdb`.`statuses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servletdb`.`statuses` ;

CREATE TABLE IF NOT EXISTS `servletdb`.`statuses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servletdb`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servletdb`.`order` ;

CREATE TABLE IF NOT EXISTS `servletdb`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bill` INT NOT NULL DEFAULT 0,
  `users_id` INT NOT NULL,
  `statuses_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_order_statuses1_idx` (`statuses_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `servletdb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_statuses1`
    FOREIGN KEY (`statuses_id`)
    REFERENCES `servletdb`.`statuses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`order_has_publication`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servletdb`.`order_has_publication` ;

CREATE TABLE IF NOT EXISTS `servletdb`.`order_has_publication` (
  `order_id` INT NOT NULL,
  `publication_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `publication_id`),
  INDEX `fk_order_has_publication_publication1_idx` (`publication_id` ASC) VISIBLE,
  INDEX `fk_order_has_publication_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_publication_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `servletdb`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_publication_publication1`
    FOREIGN KEY (`publication_id`)
    REFERENCES `servletdb`.`publication` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `servletdb`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `servletdb`;
INSERT INTO `servletdb`.`roles` (`id`, `name`) VALUES (1, 'admin');
INSERT INTO `servletdb`.`roles` (`id`, `name`) VALUES (2, 'reader');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`statuses`
-- -----------------------------------------------------
START TRANSACTION;
USE `servletdb`;
INSERT INTO `servletdb`.`statuses` (`id`, `name`) VALUES (1, 'opened');
INSERT INTO `servletdb`.`statuses` (`id`, `name`) VALUES (2, 'confirmed');
INSERT INTO `servletdb`.`statuses` (`id`, `name`) VALUES (3, 'paid');
INSERT INTO `servletdb`.`statuses` (`id`, `name`) VALUES (4, 'closed');

COMMIT;

