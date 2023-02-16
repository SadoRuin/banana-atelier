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
-- Table structure for table `curation`
--

DROP TABLE IF EXISTS `curation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curation` (
  `curation_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `curation_start_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `curation_end_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `curation_name` varchar(100) NOT NULL,
  `curation_summary` varchar(1000) DEFAULT NULL,
  `curation_status` varchar(10) NOT NULL DEFAULT 'INIT',
  `curation_hit` int(11) NOT NULL DEFAULT 0,
  `curation_bm_cnt` int(11) NOT NULL DEFAULT 0,
  `curation_img` varchar(100) NOT NULL DEFAULT 'no_img',
  `curation_thumbnail` varchar(100) NOT NULL DEFAULT 'no_thumbnail',
  `user_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`curation_seq`),
  KEY `FK_artist_TO_curation_1` (`user_seq`),
  CONSTRAINT `FK_artist_TO_curation_1` FOREIGN KEY (`user_seq`) REFERENCES `artist` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curation`
--

LOCK TABLES `curation` WRITE;
/*!40000 ALTER TABLE `curation` DISABLE KEYS */;
INSERT INTO `curation` VALUES
(53,'2023-02-16 18:23:01','2023-02-16 13:59:33','제가 그린 포켓몬 자랑할게요!','피카츄, 라이츄, 파이리, 꼬북이','INIT',0,1,'001a2269-80d7-47f9-a490-c0e286095baa.jpg','thumb_001a2269-80d7-47f9-a490-c0e286095baa.jpg',145),
(55,'2023-02-23 15:25:00','2023-02-16 06:26:43','고래는 생선이다','생선은 헤엄을 치기 때문이다.','INIT',0,0,'bae12a20-8bb5-4066-bcaf-3f562aa5e2d6.jpg','thumb_bae12a20-8bb5-4066-bcaf-3f562aa5e2d6.jpg',137),
(56,'2023-02-16 15:37:01','2023-02-16 15:35:54','짱구 배경화면 구경하세요~!','짱구 배경화면 모음집입니다! 구경하고 가세요~!','ON',0,1,'9e96579e-7f06-4c66-81f4-8fc5c9f0a421.jpg','thumb_9e96579e-7f06-4c66-81f4-8fc5c9f0a421.jpg',152),
(57,'2023-02-15 15:37:01','2023-02-16 15:53:59','내 피, 땀, 눈물','함들게 만든 작품입니다. 가격 잘 쳐주세요~!','END',0,1,'8a58f966-f2ae-459c-ad55-9d11dbda0f3f.jpg','thumb_8a58f966-f2ae-459c-ad55-9d11dbda0f3f.jpg',153),
(58,'2023-02-16 16:16:01','2023-02-16 16:15:42','루피 저장소','또 뭘 만들어볼까요??','ON',0,1,'0ee9425f-3a81-4f19-83fc-068be086c1f7.jpg','thumb_0ee9425f-3a81-4f19-83fc-068be086c1f7.jpg',154),
(59,'2023-02-16 16:59:01','2023-02-16 16:57:40','춘식이 모음집','보기만 해도 귀엽지요?','ON',0,0,'294b366b-b970-4a5a-9a27-e43cd700a41b.jpg','thumb_294b366b-b970-4a5a-9a27-e43cd700a41b.jpg',105);
/*!40000 ALTER TABLE `curation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16  8:54:43
