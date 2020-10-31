CREATE DATABASE  IF NOT EXISTS `chernevonlinestore` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `chernevonlinestore`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: chernevonlinestore
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brands` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `name_ru` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (0,'Adidas','Adidas'),(1,'Puma','Puma'),(2,'New Balance','New Balance');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `name_ru` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (0,'sport equipment','спорт. инвентарь'),(1,'clothes','одежда');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_statuses`
--

DROP TABLE IF EXISTS `order_statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_statuses` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `name_ru` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_statuses`
--

LOCK TABLES `order_statuses` WRITE;
/*!40000 ALTER TABLE `order_statuses` DISABLE KEYS */;
INSERT INTO `order_statuses` VALUES (0,'accepted','принят'),(1,'confirmed','подтвержден'),(2,'formed','оформлен'),(3,'sent','отправлен'),(4,'completed','завершен'),(5,'canceled','отменен');
/*!40000 ALTER TABLE `order_statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordered_products`
--

DROP TABLE IF EXISTS `ordered_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordered_products` (
  `product_id` int DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `count` varchar(45) DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  KEY `product_idx` (`product_id`),
  KEY `order_idx` (`order_id`),
  CONSTRAINT `order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordered_products`
--

LOCK TABLES `ordered_products` WRITE;
/*!40000 ALTER TABLE `ordered_products` DISABLE KEYS */;
INSERT INTO `ordered_products` VALUES (4,20,'3',1),(1,12,'2',1),(1,12,'2',2),(2,120,'3',2),(4,20,'2',3),(1,12,'1',3),(2,120,'1',3),(4,20,'2',4),(1,12,'1',4),(2,120,'1',4),(2,120,'1',5),(4,20,'2',5),(1,12,'1',5),(2,120,'1',6),(1,12,'1',6),(4,20,'1',6),(2,120,'1',7),(4,20,'2',7),(2,120,'1',8),(4,20,'1',8),(1,12,'1',8),(1,12,'2',9),(1,12,'1',10),(4,20,'1',10),(2,120,'2',11),(4,20,'1',11),(1,12,'2',11),(2,120,'2',12),(4,20,'1',12),(1,12,'2',12),(1,12,'1',13),(2,120,'1',13),(1,12,'1',14);
/*!40000 ALTER TABLE `ordered_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_id` int DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status_idx` (`status_id`),
  KEY `user_idx` (`user_id`),
  CONSTRAINT `status` FOREIGN KEY (`status_id`) REFERENCES `order_statuses` (`id`),
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,0,'',15,'2020-10-16 07:34:21'),(2,0,'',15,'2020-10-16 07:37:14'),(3,0,'',15,'2020-10-16 07:42:10'),(4,0,'',15,'2020-10-16 07:51:23'),(5,0,'',15,'2020-10-16 08:04:44'),(6,0,'',15,'2020-10-16 08:15:31'),(7,0,'',15,'2020-10-16 08:24:34'),(8,0,'',15,'2020-10-16 08:32:27'),(9,0,'',15,'2020-10-16 08:44:21'),(10,0,'',15,'2020-10-16 09:31:54'),(11,0,'',15,'2020-10-16 09:42:03'),(12,0,'',15,'2020-10-16 11:24:14'),(13,0,'',15,'2020-10-16 12:54:04'),(14,0,'',15,'2020-10-21 23:32:34');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `price` bigint DEFAULT NULL,
  `image_path` varchar(45) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `brand_id` int DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id_idx` (`category_id`),
  KEY `brand_id_idx` (`brand_id`),
  CONSTRAINT `brand_id` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Ball',12,'images/ball.jpg',0,0,NULL),(2,'Sport uniform',120,'images/sport_uniform_adidas.jpg',1,0,NULL),(3,'Sneakers',30,'images/sneakers.jpg',1,1,NULL),(4,'Backpack',20,'images/backpack_adidas.jpg',0,0,NULL),(5,'New Balance 574',45,'images/new-balance-574-v2-classic.jpg',1,2,NULL),(6,'New Balance 452',40,'images/new-balance-452-v1-classic.jpg',1,2,NULL),(7,'Adidas Gazelle',35,'images/adidas-gazelle-BB5476.jpg',1,0,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (0,'admin'),(1,'user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `f_name` varchar(45) DEFAULT NULL,
  `l_name` varchar(45) DEFAULT NULL,
  `login` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `user_pic_path` varchar(128) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (13,'Kostiantyn','Chernev','user12112212','example@domain.end','+38 012 345 6789','123','images/userpicsuser12112212.png',1),(14,'Kostiantyn','Chernev','user121112','example@domain.end','+38 012 345 6789','123','images/userpics\\user121112.png',1),(15,'Kostiantyn','Chernev','user121212','example@domain.end','+38 012 345 6789','123','images/userpics\\user121212.jpg',1),(17,'Kostiantyn','Chernev','user11111','example@domain.end','+38 012 345 6789','123','images/userpics\\user11111.jpg',1),(18,'Kostiantyn','Chernev','useruser','example@domain.end','+38 012 345 6789','123','images/userpics\\useruser.png',1),(21,'Kostiantyn','Chernev','user123332','example@domain.end','+38 012 345 6789','123','images\\userpics\\default.png',1),(22,'Kostiantyn','Chernev','user114143412','example@domain.end','+38 012 345 6789','123','images\\userpics\\default.png',1),(23,'Admin','Admin','admin','example@domain.end','+38 012 345 6789','admin','images\\userpics\\default.png',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-30 14:18:41
