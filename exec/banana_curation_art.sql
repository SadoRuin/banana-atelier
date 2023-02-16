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
-- Table structure for table `curation_art`
--

DROP TABLE IF EXISTS `curation_art`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curation_art` (
  `curation_art_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `auction_start_price` int(11) NOT NULL DEFAULT 0,
  `auction_gap` int(11) NOT NULL DEFAULT 0,
  `auction_people_cnt` int(11) NOT NULL DEFAULT 0,
  `art_seq` bigint(20) NOT NULL,
  `curation_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`curation_art_seq`),
  KEY `FK_art_TO_curation_art_1` (`art_seq`),
  KEY `FK_curation_TO_curation_art_1` (`curation_seq`),
  CONSTRAINT `FK_art_TO_curation_art_1` FOREIGN KEY (`art_seq`) REFERENCES `art` (`art_seq`) ON DELETE CASCADE,
  CONSTRAINT `FK_curation_TO_curation_art_1` FOREIGN KEY (`curation_seq`) REFERENCES `curation` (`curation_seq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curation_art`
--

LOCK TABLES `curation_art` WRITE;
/*!40000 ALTER TABLE `curation_art` DISABLE KEYS */;
INSERT INTO `curation_art` VALUES
(40,1000,50,0,156,53),
(41,0,0,0,157,53),
(42,0,0,0,158,53),
(43,500,10,0,159,53),
(45,10000000,100,0,152,55),
(46,1000,50,0,163,56),
(47,1000,50,0,164,56),
(48,1000,50,0,165,56),
(49,1000,50,0,166,56),
(50,1000,50,0,167,56),
(51,1000,50,0,168,56),
(52,1000,50,0,169,56),
(53,1000,50,0,170,56),
(54,1000,50,0,171,56),
(55,50000,1000,0,172,57),
(56,50000,1000,0,173,57),
(57,50000,1000,0,174,57),
(58,50000,1000,0,175,57),
(59,50000,10000,0,177,58),
(60,500000,10000,0,178,58),
(61,500000,10000,0,179,58),
(62,500000,10000,0,180,58),
(63,50000,10000,0,183,59),
(64,500000,10000,0,184,59),
(65,500000,10000,0,185,59),
(66,500000,10000,0,186,59),
(67,500000,10000,0,187,59);
/*!40000 ALTER TABLE `curation_art` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16  8:55:03
