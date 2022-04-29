DROP TABLE IF EXISTS `products`;

CREATE TABLE `products`
(
    `id`              int unsigned NOT NULL AUTO_INCREMENT,
    `main_cat`        varchar(255) DEFAULT NULL,
    `title`           varchar(5000) DEFAULT NULL,
    `asin`            varchar(20) DEFAULT NULL ,
    `category`        json DEFAULT NULL,
    `imageURLHighRes` json DEFAULT NULL,
    `price`           varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- INSERT INTO `products` (main_cat, title, asin, category, imageURLHighRes, price)
-- VALUES
--     ("\"\"", "\"title in init.sql\"", "\"DF4324\"", "[]", "[\"https:\\\\aaa\",\"https:\\\\bbb\"]", "\"$199\"");