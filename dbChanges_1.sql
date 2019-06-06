SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema InvoiceManagment
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema InvoiceManagment
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `InvoiceManagment` DEFAULT CHARACTER SET utf8mb4;
SET collation_connection=utf8mb4_unicode_ci;
USE `InvoiceManagment` ;
SET @change_number =1;
SET @version='version 1.0';
SET @applied=getdate();
SET @applied_by='MARKO KOVACEC';
SET @description='SQL skripta za nalogo PPJ';
SET @file='dbChanges_1.sql';

-- -----------------------------------------------------
-- Table `Article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Article` (
  `article_id` BINARY(16) NOT NULL,
  `barcode` VARCHAR(14) NOT NULL,
  `name` VARCHAR(1000) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `vat` DECIMAL(5,2) NOT NULL,
  `stock` INT NOT NULL,
  `deleted` TINYINT(1) NOT NULL,
  `created` TIMESTAMP NOT NULL,
  `modified` TIMESTAMP NULL,
  PRIMARY KEY (`article_id`)
  )
ENGINE = InnoDB;

DELIMITER //
CREATE TRIGGER update_article_modified
BEFORE UPDATE
	ON Article FOR EACH ROW
BEGIN
	DECLARE newtime DATETIME;
    SELECT getdate() into newtime;
    INSERT INTO Article (modified) VALUES (newtime);
END; //
DELIMITER ;

-- -----------------------------------------------------
-- Table `InternalArticle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InternalArticle` (
  `internal_article_id` BINARY(16) NOT NULL,
  `internal_id` VARCHAR(4) NOT NULL,
  `name` VARCHAR(1000) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `vat` DECIMAL(5,2) NOT NULL,
  `stock` INT NOT NULL,
  `deleted` TINYINT(1) NOT NULL,
  `created` TIMESTAMP NOT NULL,
  `modified` TIMESTAMP NULL,
  PRIMARY KEY (`internal_article_id`))
ENGINE = InnoDB;
DELIMITER //
CREATE TRIGGER update_inter_article_modified
BEFORE UPDATE
	ON InternalArticle FOR EACH ROW
BEGIN
	DECLARE newtime DATETIME;
    SELECT getdate() into newtime;
    INSERT INTO InternalArticle (modified) VALUES (newtime);
END; //
DELIMITER ;


-- -----------------------------------------------------
-- Table `Company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Company` (
  `company_id` BINARY(16) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `tax_number` VARCHAR(45) NOT NULL,
  `registration_number` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(30) NOT NULL,
  `taxpayer` TINYINT(1) NOT NULL,
  `address` VARCHAR(150) NOT NULL,
  `deleted` TINYINT(1) NOT NULL,
  `created` TIMESTAMP NOT NULL,
  `modified` TIMESTAMP NULL,
  PRIMARY KEY (`company_id`))
ENGINE = InnoDB;
DELIMITER //
CREATE TRIGGER update_company_modified
BEFORE UPDATE
	ON Company FOR EACH ROW
BEGIN
	DECLARE newtime DATETIME;
    SELECT getdate() into newtime;
    INSERT INTO Company (modified) VALUES (newtime);
END; //
DELIMITER ;


-- -----------------------------------------------------
-- Table `Invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Invoice` (
  `invoice_id` BINARY(16) NOT NULL,
  `total` DECIMAL(10,2) NOT NULL,
  `total_vat` DECIMAL(10,2) NOT NULL,
  `deleted` TINYINT(1) NOT NULL,
  `created` DATETIME NOT NULL,
  `modified` DATETIME NULL,
  `issuer_id` BINARY(16) NOT NULL,
  `customer_id` BINARY(16) NULL,
  PRIMARY KEY (`invoice_id`),
  INDEX `customer_id_idx` (`customer_id` ASC),
  INDEX `issuer_id_idx` (`issuer_id` ASC),
  CONSTRAINT `issuer_id`
    FOREIGN KEY (`issuer_id`)
    REFERENCES `Company` (`company_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `Company` (`company_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
DELIMITER //
CREATE TRIGGER update_invoice_modified
BEFORE UPDATE
	ON Invoice FOR EACH ROW
BEGIN
	DECLARE newtime DATETIME;
    SELECT getdate() into newtime;
    INSERT INTO Invoice (modified) VALUES (newtime);
END; //
DELIMITER ;

-- -----------------------------------------------------
-- Table `SchemaVersion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SchemaVersion` (
  `schema_version_id` BINARY(16) NOT NULL,
  `change_number` INT NOT NULL,
  `version` VARCHAR(30) NOT NULL,
  `applied` DATETIME NOT NULL,
  `applied_by` VARCHAR(45) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `file` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`schema_version_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Invoice_has_Article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Invoice_has_Article` (
  `invoice_id` BINARY(16) NULL,
  `article_id` BINARY(16) NULL,
  `quantity` INT NOT NULL,
  INDEX `article_id_idx1` (`article_id` ASC),
  INDEX `invoice_id_idx` (`invoice_id` ASC),
  CONSTRAINT `invoice_id`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `Invoice` (`invoice_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `article_id`
    FOREIGN KEY (`article_id`)
    REFERENCES `Article` (`article_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Invoice_has_InternalArticle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Invoice_has_InternalArticle` (
  `invoice_id` BINARY(16) NULL,
  `internal_article_id` BINARY(16) NULL,
  `quantity` INT NOT NULL,
  INDEX `invoice_id_idx` (`invoice_id` ASC),
  INDEX `internal_article_id_idx` (`internal_article_id` ASC),
  CONSTRAINT `invoice_idl`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `Invoice` (`invoice_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `internal_article_id`
    FOREIGN KEY (`internal_article_id`)
    REFERENCES `InternalArticle` (`internal_article_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `InvoiceManagment` ;

-- -----------------------------------------------------
-- Placeholder table for view `view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `view1` (`id` INT);

-- -----------------------------------------------------
-- View `view1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `view1`;
USE `InvoiceManagment`;

INSERT INTO Article (deleted) VALUES (0);
INSERT INTO InternalArticle (deleted) VALUES (0);
INSERT INTO Invoice (deleted) VALUES (0);
INSERT INTO Company (deleted) VALUES (0);

INSERT INTO SchemaVersion (change_number, version, applied, applied_by, description, file) VALUES (change_number, version, applied, applied_by, description, file);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
