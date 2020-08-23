CREATE SCHEMA `company` DEFAULT CHARACTER SET utf8 ;

USE `company`;

CREATE TABLE `employees`
(
  `id`         int(11) NOT NULL AUTO_INCREMENT,
  `last_name`  varchar(64)   DEFAULT NULL,
  `first_name` varchar(64)   DEFAULT NULL,
  `email`      varchar(64)   DEFAULT NULL,
  `department` varchar(64)   DEFAULT NULL,
  `salary`     DECIMAL(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
