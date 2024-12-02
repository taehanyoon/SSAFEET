DROP SCHEMA IF EXISTS `ssafeet`;

CREATE DATABASE `ssafeet`;

USE `ssafeet`;

DROP TABLE IF EXISTS `places`;

CREATE TABLE `places` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `lat` DECIMAL(9,6) NOT NULL,
    `lng` DECIMAL(9,6) NOT NULL,
    `url` VARCHAR(100) NULL,
    `address` TEXT NOT NULL,
    `campus` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `place_id` INT NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `description` TEXT NULL,
    `price` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`place_id`) REFERENCES `places`(`id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `product_id` INT NULL,
    `phone` VARCHAR(100) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `campus` VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `signup_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `point` INT NOT NULL DEFAULT 0,
    `cumm_walk` INT NOT NULL DEFAULT 0,
    `curr_walk` INT NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`product_id`) REFERENCES `products`(`id`) ON DELETE SET NULL
);

DROP TABLE IF EXISTS `courses`;

CREATE TABLE `courses` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `start_time` TIMESTAMP NOT NULL,
    `end_time` TIMESTAMP NOT NULL,
    `submit_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `name` VARCHAR(100) NOT NULL,
    `content` TEXT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `reviews`;

CREATE TABLE `reviews` (
    `course_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `submit_time` TIMESTAMP NOT NULL,
    `rating` INT NOT NULL,
    `content` TEXT NOT NULL,
    PRIMARY KEY (`course_id`, `user_id`),
    FOREIGN KEY (`course_id`) REFERENCES `courses`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `gps`;

CREATE TABLE `gps` (
    `user_id` INT NOT NULL,
    `time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `lat` DECIMAL(9, 6) NOT NULL,
    `lng` DECIMAL(9, 6) NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `coupons`;

CREATE TABLE `coupons` (
    `product_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `redeem_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `used_time` TIMESTAMP NULL,
    FOREIGN KEY (`product_id`) REFERENCES `products`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);