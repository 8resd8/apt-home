-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ssafyhome
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafyhome` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ssafyhome` ;

-- -----------------------------------------------------
-- Table `ssafyhome`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyhome`.`member` (
    `mid` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `salt` VARCHAR(45) NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    `phone_num` VARCHAR(45) NULL,
    `name` VARCHAR(45) NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `last_login` DATETIME NULL,
    PRIMARY KEY (`mid`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyhome`.`broker`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyhome`.`broker` (
    `bid` VARCHAR(255) NOT NULL,
    `office_name` VARCHAR(45) NULL,
    `broker_name` VARCHAR(45) NOT NULL,
    `phone_num` VARCHAR(45) NOT NULL,
    `address` VARCHAR(45) NOT NULL,
    `license_num` VARCHAR(45) NOT NULL,
    `password` VARCHAR(255) NULL,
    `salt` VARCHAR(45) NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    `last_login` DATETIME NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`bid`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyhome`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyhome`.`reservation` (
    `rid` BIGINT NOT NULL AUTO_INCREMENT,
    `member_id` VARCHAR(255) NOT NULL,
    `broker_id` VARCHAR(255) NOT NULL,
    `start_time` DATETIME NULL,
    `end_time` DATETIME NULL,
    `status` CHAR(10) NULL,
    `client_memo` VARCHAR(255) NULL,
    `broker_memo` VARCHAR(255) NULL,
    `broker_name` VARCHAR(45) NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`rid`),
    INDEX `fk_reservation_member_idx` (`member_id` ASC) VISIBLE,
    INDEX `fk_reservation_broker1_idx` (`broker_id` ASC) VISIBLE,
    CONSTRAINT `fk_reservation_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `ssafyhome`.`member` (`mid`)
                                                              ON DELETE NO ACTION
                                                              ON UPDATE NO ACTION,
    CONSTRAINT `fk_reservation_broker1`
    FOREIGN KEY (`broker_id`)
    REFERENCES `ssafyhome`.`broker` (`bid`)
                                                              ON DELETE NO ACTION
                                                              ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyhome`.`houseinfos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyhome`.`houseinfos` (
    `apt_seq` VARCHAR(20) NOT NULL,
    `sgg_cd` VARCHAR(5) NULL DEFAULT NULL,
    `umd_cd` VARCHAR(5) NULL DEFAULT NULL,
    `umd_nm` VARCHAR(20) NULL DEFAULT NULL,
    `jibun` VARCHAR(10) NULL DEFAULT NULL,
    `road_nm_sgg_cd` VARCHAR(5) NULL DEFAULT NULL,
    `road_nm` VARCHAR(20) NULL DEFAULT NULL,
    `road_nm_bonbun` VARCHAR(10) NULL DEFAULT NULL,
    `road_nm_bubun` VARCHAR(10) NULL DEFAULT NULL,
    `apt_nm` VARCHAR(40) NULL DEFAULT NULL,
    `build_year` INT NULL DEFAULT NULL,
    `latitude` VARCHAR(45) NULL DEFAULT NULL,
    `longitude` VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`apt_seq`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafyhome`.`broker_estate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyhome`.`estate` (
    `eid` BIGINT NOT NULL AUTO_INCREMENT,
    `broker_id` VARCHAR(255) NOT NULL,
    `apt_seq` VARCHAR(20) NOT NULL,
    `type` CHAR(10) NOT NULL,
    `status` CHAR(10) NOT NULL DEFAULT '판매중',
    `floor` INT NOT NULL,
    `total_floor` INT NOT NULL,
    `area` DOUBLE NOT NULL,
    `amount` VARCHAR(45) NOT NULL,
    `desc` TEXT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`eid`),
    INDEX `fk_broker_estate_houseinfos1_idx` (`apt_seq` ASC) VISIBLE,
    INDEX `fk_broker_estate_broker1_idx` (`broker_id` ASC) VISIBLE,
    CONSTRAINT `fk_broker_estate_houseinfos1`
    FOREIGN KEY (`apt_seq`)
    REFERENCES `ssafyhome`.`houseinfos` (`apt_seq`)
                                                              ON DELETE NO ACTION
                                                              ON UPDATE NO ACTION,
    CONSTRAINT `fk_broker_estate_broker1`
    FOREIGN KEY (`broker_id`)
    REFERENCES `ssafyhome`.`broker` (`bid`)
                                                              ON DELETE NO ACTION
                                                              ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyhome`.`reservation_estate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyhome`.`reservation_estate` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `reservation_id` BIGINT NOT NULL,
    `estate_id` BIGINT NOT NULL,
    `memo` TEXT NULL,
    INDEX `fk_reservation_estate_reservation1_idx` (`reservation_id` ASC) VISIBLE,
    INDEX `fk_reservation_estate_broker_estate1_idx` (`estate_id` ASC) VISIBLE,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_reservation_estate_reservation1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `ssafyhome`.`reservation` (`rid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_reservation_estate_broker_estate1`
    FOREIGN KEY (`estate_id`)
    REFERENCES `ssafyhome`.`estate` (`eid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyhome`.`favorite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyhome`.`favorite` (
    `fid` BIGINT NOT NULL AUTO_INCREMENT,
    `member_id` VARCHAR(255) NOT NULL,
    `estate_id` BIGINT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`fid`),
    INDEX `fk_bookmark_member1_idx` (`member_id` ASC) VISIBLE,
    INDEX `fk_bookmark_broker_estate1_idx` (`estate_id` ASC) VISIBLE,
    CONSTRAINT `fk_bookmark_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `ssafyhome`.`member` (`mid`)
                                                              ON DELETE NO ACTION
                                                              ON UPDATE NO ACTION,
    CONSTRAINT `fk_bookmark_broker_estate1`
    FOREIGN KEY (`estate_id`)
    REFERENCES `ssafyhome`.`estate` (`eid`)
                                                              ON DELETE NO ACTION
                                                              ON UPDATE NO ACTION)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ssafyhome`.`dongcodes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyhome`.`dongcodes` (
                                                       `dong_code` VARCHAR(10) NOT NULL,
    `sido_name` VARCHAR(30) NULL DEFAULT NULL,
    `gugun_name` VARCHAR(30) NULL DEFAULT NULL,
    `dong_name` VARCHAR(30) NULL DEFAULT NULL,
    PRIMARY KEY (`dong_code`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafyhome`.`housedeals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyhome`.`housedeals` (
    `no` INT NOT NULL AUTO_INCREMENT,
    `apt_seq` VARCHAR(20) NULL DEFAULT NULL,
    `apt_dong` VARCHAR(40) NULL DEFAULT NULL,
    `floor` VARCHAR(3) NULL DEFAULT NULL,
    `deal_year` INT NULL DEFAULT NULL,
    `deal_month` INT NULL DEFAULT NULL,
    `deal_day` INT NULL DEFAULT NULL,
    `exclu_use_ar` DECIMAL(7,2) NULL DEFAULT NULL,
    `deal_amount` VARCHAR(10) NULL DEFAULT NULL,
    PRIMARY KEY (`no`),
    INDEX `apt_seq_to_house_info_idx` (`apt_seq` ASC) VISIBLE,
    CONSTRAINT `apt_seq_to_house_info`
    FOREIGN KEY (`apt_seq`)
    REFERENCES `ssafyhome`.`houseinfos` (`apt_seq`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 7084512
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;