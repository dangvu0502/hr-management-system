CREATE DATABASE  IF NOT EXISTS `hr_system_v2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hr_system_v2`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: hr_system_v2
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `absence`
--

DROP TABLE IF EXISTS `absence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `absence` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `absence_type` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `request_date` datetime DEFAULT NULL,
  `from` datetime DEFAULT NULL,
  `to` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `absence`
--

LOCK TABLES `absence` WRITE;
/*!40000 ALTER TABLE `absence` DISABLE KEYS */;
/*!40000 ALTER TABLE `absence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `id` int NOT NULL,
  `Slug` varchar(45) DEFAULT NULL,
  `Thumnail_image` varchar(255) DEFAULT NULL,
  `Tittle` varchar(45) DEFAULT NULL,
  `Brieft` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `Category` int DEFAULT NULL,
  `Content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `Author` varchar(45) DEFAULT NULL,
  `PublishDate` datetime DEFAULT NULL,
  `Flag` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL,
  `Category_Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='			';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  `type` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group` (
  `code` varchar(45) NOT NULL,
  `manager_id` int DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `parent_group_code` varchar(45) DEFAULT NULL,
  `delete` tinyint DEFAULT '0',
  `update_date` date DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES ('G1',NULL,'Group 1',NULL,NULL,NULL,0,NULL),('G2',NULL,'Group 2',NULL,NULL,NULL,0,NULL),('G3',NULL,'Group 3',NULL,NULL,NULL,0,NULL),('G4',NULL,'Group 4',NULL,NULL,NULL,0,NULL),('G5',NULL,'Group 5',NULL,NULL,NULL,0,NULL),('G6',1,'Group 6',1,NULL,NULL,0,NULL);
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `code` varchar(45) NOT NULL,
  `group_code` varchar(45) DEFAULT NULL,
  `manager_id` int DEFAULT NULL,
  `project_name` varchar(45) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `description` varchar(2048) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `effort` int DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES ('DML','G4',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('ESH','G6',106,NULL,NULL,NULL,NULL,NULL,NULL),('HRM','G6',106,NULL,NULL,NULL,NULL,NULL,NULL),('LOL','G6',107,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `int` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `request_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `support_type_id` int DEFAULT NULL,
  `in_charge_staff` int DEFAULT NULL,
  `in_charge_group` varchar(45) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`int`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(2,'Manager'),(3,'Hr'),(4,'Staff');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setting` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `value` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` tinyint DEFAULT '0',
  `order` int DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES (1,'role','admin',0,1,NULL),(2,'role','manager',0,2,NULL),(3,'role','hr',0,3,NULL),(4,'role','staff',0,4,NULL),(5,'post status','draft',0,1,NULL),(6,'post status','deleted',0,2,NULL),(7,'post status','published',0,3,NULL),(8,'request status','submitted',0,1,NULL),(9,'request status','assigned',0,2,NULL),(10,'request status','completed',0,3,NULL),(11,'request status','closed',0,4,NULL),(12,'absence type','absence',0,1,NULL),(13,'absence type','late arriving',0,2,NULL),(14,'absence type','soon leaving',0,3,NULL),(15,'absence request status','submitted',0,1,NULL),(16,'absence request status','approved',0,2,NULL),(17,'absence request status','rejected',0,3,NULL),(18,'timesheet process','training',0,1,NULL),(19,'timesheet process','requirement',0,2,NULL),(20,'timesheet process','design',0,3,NULL),(21,'timesheet process','coding',0,4,NULL),(22,'timesheet process','testing',0,5,NULL),(23,'timesheet process','supporting',0,6,NULL),(24,'timesheet status','submitted',0,1,NULL),(25,'timesheet status','approved',0,2,NULL),(26,'timesheet status','rejected',0,3,NULL),(27,'staff status','registered',0,1,NULL),(28,'staff status','verified',0,2,NULL),(29,'staff status','active',0,3,NULL),(30,'staff status','inactive',0,4,NULL),(31,'project roles','PM',0,1,NULL),(32,'project roles','designer',0,2,NULL),(33,'project roles','developer',0,3,NULL),(34,'project roles','tester',0,4,NULL),(35,'project roles','QA',0,5,NULL);
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `support group`
--

DROP TABLE IF EXISTS `support group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `support group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `support_type_id` int DEFAULT NULL,
  `group_code` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `support group`
--

LOCK TABLES `support group` WRITE;
/*!40000 ALTER TABLE `support group` DISABLE KEYS */;
/*!40000 ALTER TABLE `support group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `support type`
--

DROP TABLE IF EXISTS `support type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `support type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `delete` tinyint DEFAULT NULL,
  `incharge group` varchar(45) DEFAULT NULL,
  `description` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `support type`
--

LOCK TABLES `support type` WRITE;
/*!40000 ALTER TABLE `support type` DISABLE KEYS */;
/*!40000 ALTER TABLE `support type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timesheet`
--

DROP TABLE IF EXISTS `timesheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timesheet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `process` int DEFAULT NULL,
  `duration` varchar(45) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `work_result` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `reject_reason` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `project_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timesheet`
--

LOCK TABLES `timesheet` WRITE;
/*!40000 ALTER TABLE `timesheet` DISABLE KEYS */;
INSERT INTO `timesheet` VALUES (8,'Design','2022-02-14',5,'1:40',3,'Very GOOD','test ng??y 27-02',106,'HRM'),(11,'Gaming','2022-02-20',3,'5:00',2,'Good','',106,'ESH'),(12,'Team Building','2022-02-01',3,'2:00',2,'Very Good','',106,'HRM'),(14,'Write SRS','2022-03-03',4,'1:00',3,'Good','',106,'HRM'),(16,'Write Screen-flow ','2022-02-01',1,'3:00',2,'Bad','',106,'HRM'),(42,'Test new date format','2022-02-10',1,'2:00',2,NULL,'',106,'ESH'),(43,'Test format date 2','2022-02-11',2,'2:00',2,NULL,'',106,'ESH'),(47,'Timesheet ?????u ti??n c???a danghai','2022-02-16',3,'2:00',2,'','',107,'HRM'),(49,'Timesheet 25-03','2022-02-11',3,'2:00',3,'','',106,'ESH'),(50,'ng??y 27-02','2022-02-27',1,'2:00',1,'test ng??y 27 th??ng 2',NULL,107,'ESH');
/*!40000 ALTER TABLE `timesheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `gender` tinyint DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `dob` datetime DEFAULT '2001-01-01 00:00:00',
  `address` varchar(255) DEFAULT NULL,
  `role_id` int DEFAULT '4',
  `project_role_id` int DEFAULT NULL,
  `supervisor_id` int DEFAULT NULL,
  `group_code` varchar(45) DEFAULT NULL,
  `status` tinyint DEFAULT '0',
  `verified` tinyint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (106,'v??  ????ng','dangvu','Xuq6t7Fr/Dg=','dangvu0502@gmail.com','0858690289',1,NULL,'2001-01-01 00:00:00',NULL,3,1,NULL,'G6',1,1),(107,'v?? h???i ????ng','danghai','Xuq6t7Fr/Dg=','dangvhhe150264@fpt.edu.vn','0858690289',1,NULL,'2001-01-01 00:00:00',NULL,2,3,NULL,'G6',1,1),(108,'v?? h???i ????ng','vudang',NULL,NULL,NULL,NULL,NULL,'2001-01-01 00:00:00',NULL,4,NULL,NULL,'G4',0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-27 20:39:45
