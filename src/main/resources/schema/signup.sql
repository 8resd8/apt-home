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

CREATE TABLE IF NOT EXISTS `ssafyhome`.`broker` (
    `bid` VARCHAR(255) NOT NULL,
    `office_name` VARCHAR(45) NULL,
    `broker_name` VARCHAR(45) NOT NULL, -- name->broker_name
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