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
-- Table structure for table `notice_notification`
--

DROP TABLE IF EXISTS `notice_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice_notification` (
  `notification_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `notification_content` varchar(100) DEFAULT NULL,
  `notification_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `notification_is_read` tinyint(1) NOT NULL DEFAULT 0,
  `user_seq` bigint(20) NOT NULL,
  `artist_seq` bigint(20) NOT NULL,
  `notice_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`notification_seq`),
  KEY `FK_user_TO_notice_notification_1` (`user_seq`),
  KEY `FK_artist_TO_notice_notification_1` (`artist_seq`),
  KEY `FK_notice_TO_notice_notification_1` (`notice_seq`),
  CONSTRAINT `FK_artist_TO_notice_notification_1` FOREIGN KEY (`artist_seq`) REFERENCES `artist` (`user_seq`) ON DELETE CASCADE,
  CONSTRAINT `FK_notice_TO_notice_notification_1` FOREIGN KEY (`notice_seq`) REFERENCES `notice` (`notice_seq`) ON DELETE CASCADE,
  CONSTRAINT `FK_user_TO_notice_notification_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice_notification`
--

LOCK TABLES `notice_notification` WRITE;
/*!40000 ALTER TABLE `notice_notification` DISABLE KEYS */;
INSERT INTO `notice_notification` VALUES
(1,'페페 작가가 새로운 공지를 게시했습니다.','2023-02-13 01:19:21',0,83,121,8),
(2,'페페 작가가 새로운 공지를 게시했습니다.','2023-02-13 01:19:21',0,122,121,8),
(3,'가루바나나 작가가 새로운 공지를 게시했습니다.','2023-02-13 01:20:37',0,83,122,9),
(4,'가루바나나 작가가 새로운 공지를 게시했습니다.','2023-02-13 01:20:37',0,121,122,9);
/*!40000 ALTER TABLE `notice_notification` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16  8:56:48
