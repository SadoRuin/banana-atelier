-- MariaDB dump 10.19  Distrib 10.10.2-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: banana
-- ------------------------------------------------------
-- Server version	10.10.2-MariaDB-1:10.10.2+maria~ubu2204

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `my_art`
--

DROP TABLE IF EXISTS `my_art`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_art` (
  `user_seq` bigint(20) NOT NULL,
  `art_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`user_seq`,`art_seq`),
  KEY `FK_art_TO_my_art_1` (`art_seq`),
  CONSTRAINT `FK_art_TO_my_art_1` FOREIGN KEY (`art_seq`) REFERENCES `art` (`art_seq`) ON DELETE CASCADE,
  CONSTRAINT `FK_user_TO_my_art_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_art`
--

LOCK TABLES `my_art` WRITE;
/*!40000 ALTER TABLE `my_art` DISABLE KEYS */;
INSERT INTO `my_art` VALUES
(100,107),
(100,129),
(100,132),
(100,135),
(100,140),
(100,144),
(102,90),
(102,129),
(104,146),
(105,129),
(108,93),
(108,107),
(108,129),
(108,132),
(108,135),
(108,143),
(108,144),
(108,146),
(108,160),
(118,137),
(121,140),
(121,155),
(123,129),
(125,90),
(125,128),
(125,143),
(130,142),
(137,90),
(137,93),
(137,107),
(137,128),
(137,129),
(137,132),
(137,135),
(137,143),
(137,144),
(137,146),
(137,147);
/*!40000 ALTER TABLE `my_art` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16  8:56:03
