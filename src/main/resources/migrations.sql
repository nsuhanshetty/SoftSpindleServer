---liquibase formatted sql
---changeset author:abhiramk version:1
CREATE TABLE customers (
  id int(11) NOT NULL AUTO_INCREMENT,
  customer_name varchar(100) NOT NULL,
  mobile_no varchar(30) NOT NULL,
  phone_no varchar(30) NOT NULL,
  address varchar(30) NOT NULL,
  email varchar(30) NOT NULL,
  image varchar(30) NOT NULL,
  created_at datetime DEFAULT NULL,
  updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);


---changeset
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(255) NOT NULL,
  `created_at` date DEFAULT NULL,
  `promise_date` date DEFAULT NULL,
  `price` float DEFAULT NULL,
  `current_payment` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;