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
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `notice_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `notice_title` varchar(100) NOT NULL,
  `notice_content` text DEFAULT NULL,
  `notice_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `user_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`notice_seq`),
  KEY `FK_artist_TO_notice_1` (`user_seq`),
  CONSTRAINT `FK_artist_TO_notice_1` FOREIGN KEY (`user_seq`) REFERENCES `artist` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES
(1,'수정테스트','수정수정','2023-02-08 17:03:41',104),
(4,'공지사항 제목ㅎ','공지사항 내용입니다ㅎㅎ','2023-02-08 17:06:57',104),
(6,'gg','gg','2023-02-08 19:43:50',105),
(7,'dfdfd','ggdfd','2023-02-08 19:43:53',105),
(8,'공지사항 테스트','공지사항 내용','2023-02-12 16:16:39',121),
(9,'공지사항','내용','2023-02-12 16:20:18',122),
(10,'공지제목','공지내용','2023-02-13 05:00:32',108),
(11,'공지제목22','공지내용22','2023-02-13 05:04:48',108),
(12,'수정테스트','수정수정','2023-02-13 16:47:56',104),
(14,'수정테스트','수정수정','2023-02-13 16:57:27',107),
(15,'제목입니다2','내용입니다2','2023-02-13 16:57:34',107),
(16,'공지내용','공지내용','2023-02-13 14:47:42',108),
(17,'','내용','2023-02-13 15:46:56',108),
(18,'공지사항제목22222222222','ㄷㄷㄷㄷㄷㄷㄷ','2023-02-13 15:48:51',108),
(19,'공지사항제목33333','ㅈㄷㄹㅈㄷㄹㅈ','2023-02-13 15:49:28',108),
(20,'[일정] 굿즈 수요 조사 안내','안녕하세요 굿즈를 제작 전 수요 조사를 하려고 합니다','2023-02-14 00:54:52',104),
(21,'열마리오리','는 sheep duck','2023-02-14 11:47:34',108),
(22,'공지사항제목','ㄱㅈㄷㄱㄷㅎㄱㄷㅎ','2023-02-14 17:55:22',108),
(23,'ㅇㄹㅇ','ㄹㅇㄹ','2023-02-15 04:14:25',100),
(24,'dd','dd','2023-02-15 15:47:49',100),
(26,'제목','내용','2023-02-16 04:16:05',100);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16  8:56:35
