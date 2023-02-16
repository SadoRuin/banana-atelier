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
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artist` (
  `user_seq` bigint(20) NOT NULL,
  `instagram_link` varchar(100) DEFAULT NULL,
  `twitter_link` varchar(100) DEFAULT NULL,
  `youtube_link` varchar(100) DEFAULT NULL,
  `blog_link` varchar(100) DEFAULT NULL,
  `artist_intro` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`user_seq`),
  CONSTRAINT `FK_user_TO_artist_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES
(100,NULL,NULL,NULL,NULL,NULL),
(102,'','','','','안녕하세요. 작가 seonho입니다.'),
(104,NULL,NULL,NULL,NULL,NULL),
(105,NULL,NULL,NULL,NULL,NULL),
(107,NULL,NULL,NULL,NULL,NULL),
(108,'','','','','안녕하세요. 작가 규레기입니다.'),
(118,NULL,NULL,NULL,NULL,NULL),
(121,'아싸라 안함','해로운새','유튭프리미엄 비싸','그런거 없음','술 한잔 마셨습니다... 작품이 별로여도 좋습니다. 하지만 월식이 하나만은 기억해주세요 진심을 다해 전합니다. 최서을 다했고 열심히 했습니다 저의 진심이 느껴지길 바랍니다 고맙읍니다...'),
(122,'','','','','바나나를 가루로 드셔보시겠습니까?'),
(123,NULL,NULL,NULL,NULL,NULL),
(125,'','','','','안녕하세요. 작가 으아악입니다.'),
(128,'','','','','안녕하세요. 작가 test입니다.'),
(137,'','','','','안녕하세요. 작가 감나무입니다.'),
(141,'','','','','안녕하세요. 작가 나양준모요입니다.'),
(145,'','','','','안녕하세요. 작가 jinjin입니다.'),
(152,'','','','','안녕하세요. 작가 짱구엄마입니다.'),
(153,'','','','','안녕하세요. 작가 패트와 매트 처돌이입니다.'),
(154,'','','','','안녕하세요. 작가 루피 컬렉터입니다.');
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16  8:53:20
