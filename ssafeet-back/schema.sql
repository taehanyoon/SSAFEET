DROP SCHEMA IF EXISTS `ssafeet`;

CREATE DATABASE `ssafeet`;

USE `ssafeet`;

DROP TABLE IF EXISTS `places`;

CREATE TABLE `places` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `lat` DECIMAL(9,6) NOT NULL,
    `lng` DECIMAL(9,6) NOT NULL,
    `phone` VARCHAR(100) NULL,
    `description` TEXT NOT NULL,
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
    `id` INT NOT NULL,
    `product_id` INT NULL,
    `phone` VARCHAR(100) NOT NULL UNIQUE,
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

INSERT INTO `places` (`name`, `lat`, `lng`, `phone`, `description`, `campus`) 
VALUES 
('카페 루스터', 37.500362, 127.039387, '02-508-5101', '전 메뉴 20% 할인', '서울'),
('아우어', 37.500765, 127.040653, '0507-1416-0425', '전 메뉴 10% 할인', '서울'),
('바른커피', 37.502419, 127.039383, '02-555-2932', '전 메뉴 15% 할인', '서울'),
('대전커피하우스', 36.355496, 127.298458, '042-123-4567', '전 메뉴 25% 할인', '대전'),
('대전베이커리카페', 36.356000, 127.297800, '042-765-4321', '아메리카노 무료 제공', '대전'),
('구미브런치', 36.107129, 128.416221, '054-123-4567', '전 메뉴 20% 할인', '구미'),
('구미도넛샵', 36.107500, 128.415800, '054-765-4321', '도넛 1개 무료', '구미'),
('광주카페라운지', 35.204191, 126.807166, '062-123-4567', '전 메뉴 30% 할인', '광주'),
('광주아이스크림카페', 35.204500, 126.806800, '062-765-4321', '아이스크림 1개 무료', '광주'),
('부울경티하우스', 35.096288, 128.853833, '051-123-4567', '티 메뉴 1+1', '부울경'),
('부울경샌드위치카페', 35.096700, 128.853400, '051-765-4321', '샌드위치 10% 할인', '부울경');

INSERT INTO `products` (`place_id`, `name`, `description`, `price`) 
VALUES 
-- Products for place_id = 1 (카페 루스터)
(1, '아메리카노', 'p1.jpg', 2500),
(1, '라떼', 'p2.jpg', 3500),
(1, '프라푸치노', 'p3.jpg', 4000),

-- Products for place_id = 2 (아우어)
(2, '아메리카노', 'p4.jpg', 2500),
(2, '카푸치노', 'p5.jpg', 3700),

-- Products for place_id = 3 (바른커피)
(3, '아이스아메리카노', 'p6.jpg', 2800),
(3, '바닐라라떼', 'p7.jpg', 3900),

-- Products for place_id = 4 (대전커피하우스)
(4, '핸드드립커피', 'p8.jpg', 5000),
(4, '에스프레소', 'p9.jpg', 3000),

-- Products for place_id = 5 (대전베이커리카페)
(5, '크로와상', 'p10.jpg', 3500),
(5, '초코머핀', 'p11.jpg', 4000),

-- Products for place_id = 6 (구미브런치)
(6, '오믈렛', 'p12.jpg', 6000),
(6, '팬케이크', 'p13.jpg', 5000),

-- Products for place_id = 7 (구미도넛샵)
(7, '글레이즈드 도넛', 'p14.jpg', 2000),
(7, '초콜릿 도넛', 'p15.jpg', 2500),

-- Products for place_id = 8 (광주카페라운지)
(8, '카라멜 마끼아또', 'p16.jpg', 4500),
(8, '콜드브루', 'p17.jpg', 4000),

-- Products for place_id = 9 (광주아이스크림카페)
(9, '바닐라 아이스크림', 'p18.jpg', 3000),
(9, '초콜릿 아이스크림', 'p19.jpg', 3500),

-- Products for place_id = 10 (부울경티하우스)
(10, '녹차', 'p20.jpg', 2500),
(10, '홍차', 'p21.jpg', 2500),

-- Products for place_id = 11 (부울경샌드위치카페)
(11, 'BLT 샌드위치', 'p22.jpg', 5500),
(11, '치킨 샌드위치', 'p23.jpg', 6000);

INSERT INTO `users` (`id`, `product_id`, `phone`, `name`, `campus`, `password`, `point`, `cumm_walk`, `curr_walk`) 
VALUES 
(1211111, 1, '01011111111', '김싸피', '서울', '111111', 3205, 243205, 2503);

INSERT INTO `coupons` (`product_id`, `user_id`) 
VALUES 
(1, 1211111),
(2, 1211111),
(3, 1211111);