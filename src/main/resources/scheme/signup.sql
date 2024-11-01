CREATE TABLE IF NOT EXISTS `ssafyhome`.`member` (
    `id` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `salt` VARCHAR(45) NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    `phone_num` VARCHAR(45) NULL,
    `name` VARCHAR(45) NULL,
    `created_at` DATETIME NULL,
    `updated_at` DATETIME NULL,
    `last_login` DATETIME NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `ssafyhome`.`broker` (
    `id` VARCHAR(255) NOT NULL,
    `office_name` VARCHAR(45) NULL,
    `name` VARCHAR(45) NOT NULL,
    `phone_num` VARCHAR(45) NOT NULL,
    `address` VARCHAR(45) NOT NULL,
    `license_num` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NULL,
    `salt` VARCHAR(45) NOT NULL,
    `last_login` DATETIME NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;

