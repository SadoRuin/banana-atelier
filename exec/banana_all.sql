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
-- Table structure for table `art`
--

DROP TABLE IF EXISTS `art`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art` (
  `art_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `art_name` varchar(100) NOT NULL,
  `art_description` varchar(500) NOT NULL,
  `art_reg_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `art_hit` int(11) NOT NULL DEFAULT 0,
  `art_download_count` int(11) NOT NULL DEFAULT 0,
  `art_like_count` int(11) NOT NULL DEFAULT 0,
  `art_thumbnail` varchar(100) NOT NULL COMMENT '생성된 작품 썸네일 파일이름',
  `art_img` varchar(100) NOT NULL COMMENT '작품 이미지 파일이름',
  `is_digital` tinyint(1) NOT NULL DEFAULT 1,
  `is_sold` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'F : 기본 / T : 판매됨',
  `is_represent` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'F: 기본 / T: 대표작품 설정',
  `art_category_seq` bigint(20) NOT NULL,
  `user_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`art_seq`),
  KEY `FK_art_category_TO_art_1` (`art_category_seq`),
  KEY `FK_artist_TO_art_1` (`user_seq`),
  CONSTRAINT `FK_art_category_TO_art_1` FOREIGN KEY (`art_category_seq`) REFERENCES `art_category` (`art_category_seq`) ON DELETE CASCADE,
  CONSTRAINT `FK_artist_TO_art_1` FOREIGN KEY (`user_seq`) REFERENCES `artist` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `art`
--

LOCK TABLES `art` WRITE;
/*!40000 ALTER TABLE `art` DISABLE KEYS */;
INSERT INTO `art` VALUES
(78,'테스트','테스트','2023-02-10 02:44:18',17,1,0,'s_a41d054e-3060-4681-af8e-f03a89563eb8.PNG','7b13972e-4375-4287-ba51-f0b4da3b0178.PNG',0,0,1,1,121),
(88,'치얼스','당신의 눈동자에 건배','2023-02-11 13:45:14',6,3,0,'thumb_c5961a7f-5118-40a1-9622-3f322b6acd02.jpg','c5961a7f-5118-40a1-9622-3f322b6acd02.jpg',0,0,0,1,122),
(90,'imgTest','imgTest','2023-02-11 09:52:49',46,6,3,'thumb_b11c7797-bdcf-407c-8798-65311be39ed6.png','b11c7797-bdcf-407c-8798-65311be39ed6.png',0,0,0,1,104),
(93,'통','울지마','2023-02-11 10:03:44',22,1,2,'thumb_fa12f04d-61d3-4bef-812d-ecd4e27dbd02.png','fa12f04d-61d3-4bef-812d-ecd4e27dbd02.png',0,0,0,2,118),
(107,'햄터님','ㅈㅂㅈㅂ','2023-02-12 10:35:59',79,3,3,'thumb_68e26664-56a8-4ba7-bfd4-3eb8bec63e0e.png','68e26664-56a8-4ba7-bfd4-3eb8bec63e0e.png',0,1,1,2,108),
(128,'클레이','찰흙','2023-02-13 00:30:10',74,4,2,'thumb_8d79504d-6da5-499e-86ba-ebfdbe2750d7.jpg','8d79504d-6da5-499e-86ba-ebfdbe2750d7.jpg',0,0,0,7,102),
(129,'무룽','집순이 무룽이는 집에 가고 싶어','2023-02-13 01:59:18',40,0,6,'thumb_25dd481b-6953-4a23-8245-af9ef5603b7d.png','25dd481b-6953-4a23-8245-af9ef5603b7d.png',0,0,1,1,104),
(132,'니시노야','다들 하이큐 좋아하시나요? 니시노야를 그려보았습니다 모작입니다 판매는 안돼요','2023-02-13 12:56:29',87,3,3,'thumb_013e7140-dedc-4b21-92fb-4f72f3318f40.jpg','013e7140-dedc-4b21-92fb-4f72f3318f40.jpg',0,0,1,1,100),
(135,'부기','부기부기','2023-02-13 14:03:21',18,0,3,'thumb_3c0a816f-501e-4030-ad90-3473b19974ba.png','3c0a816f-501e-4030-ad90-3473b19974ba.png',0,0,0,5,108),
(137,'happy coding','해피 코딩입니다~','2023-02-14 00:33:56',19,1,1,'thumb_617b5b20-b46e-4568-a136-1a766226bb27.png','617b5b20-b46e-4568-a136-1a766226bb27.png',0,0,0,4,128),
(140,'써니','우리집 댕댕','2023-02-14 07:28:10',17,0,2,'thumb_a72b3b9f-efc0-4282-b7f6-f9347625ae19.jpg','a72b3b9f-efc0-4282-b7f6-f9347625ae19.jpg',0,0,0,1,121),
(142,'수채화','수채화입니다아악','2023-02-14 13:09:47',15,0,1,'thumb_aea45302-73e7-40cb-93a1-a9a7987acf3e.jpg','aea45302-73e7-40cb-93a1-a9a7987acf3e.jpg',0,0,1,2,125),
(143,'예술','ㄷ','2023-02-14 15:36:01',20018,3433,5645,'thumb_a5bcaa79-372f-4ef7-9f5a-9da7194247a2.png','a5bcaa79-372f-4ef7-9f5a-9da7194247a2.png',0,0,1,6,125),
(144,'마음껏 핥으라구','마음껏 핥으라구\n그런데 우리 ㅅ줄바꿈이 안돼요 ㅇ\n어떡해요','2023-02-14 17:13:08',44,0,3,'thumb_0cda01c6-edc6-4cc7-bd72-0e6df613213a.png','0cda01c6-edc6-4cc7-bd72-0e6df613213a.png',0,0,1,2,108),
(146,'안녕하세요','안녕하세요..?\n지구를 정복하러 왔습니다\n지 구 조 아','2023-02-15 03:58:04',14,0,3,'thumb_b1364d28-ad24-43fb-89a1-7cb0638635c5.jpg','b1364d28-ad24-43fb-89a1-7cb0638635c5.jpg',0,0,1,5,108),
(147,'쿠로미','귀여운 쿠로미쨩-! ㅋ ㅋ','2023-02-15 15:20:39',37,1,1,'thumb_2eaba6e4-02dc-47bd-920c-0bf78bf82ef4.jpg','2eaba6e4-02dc-47bd-920c-0bf78bf82ef4.jpg',0,0,0,7,100),
(149,'우엑','힘들어욤','2023-02-15 18:42:36',6,0,0,'thumb_6a2190d8-49ec-409e-ae96-7f1e1e3bda84.png','6a2190d8-49ec-409e-ae96-7f1e1e3bda84.png',0,0,0,4,108),
(150,'하늘','하늘이 맑다...','2023-02-16 00:08:33',1,0,0,'thumb_c3561e52-f58c-4856-b414-164d43297419.jpg','c3561e52-f58c-4856-b414-164d43297419.jpg',0,0,0,1,137),
(151,'커피','신문이 영어다.\n커피가 맛이 없어졌다.','2023-02-16 00:09:05',6,0,0,'thumb_aac68146-395c-4b7b-81f0-5f1bc1fedc30.jpg','aac68146-395c-4b7b-81f0-5f1bc1fedc30.jpg',0,0,0,1,137),
(152,'고래','고래의 꼬리다','2023-02-16 00:09:32',1,0,0,'thumb_bae12a20-8bb5-4066-bcaf-3f562aa5e2d6.jpg','bae12a20-8bb5-4066-bcaf-3f562aa5e2d6.jpg',0,0,1,5,137),
(155,'귤','귤 입니다','2023-02-16 03:52:40',9,0,1,'thumb_77d76e3e-7cef-483a-bc38-ce26379cf0ac.jpeg','77d76e3e-7cef-483a-bc38-ce26379cf0ac.jpeg',0,0,0,3,141),
(156,'피카츄','내가 그린 피카츄~','2023-02-16 13:52:14',2,0,0,'thumb_001a2269-80d7-47f9-a490-c0e286095baa.jpg','001a2269-80d7-47f9-a490-c0e286095baa.jpg',0,0,0,2,145),
(157,'라이츄','내가 그린 라이츄~','2023-02-16 13:56:39',3,0,0,'thumb_d5f015f8-6fcf-4c8e-a466-63e9de2b476f.png','d5f015f8-6fcf-4c8e-a466-63e9de2b476f.png',0,0,0,2,145),
(158,'파이리','내가 그린 파이리~','2023-02-16 13:57:01',3,0,0,'thumb_c47a4a48-b13d-40e9-b1d3-79c35dbcdc6b.jpg','c47a4a48-b13d-40e9-b1d3-79c35dbcdc6b.jpg',0,0,0,2,145),
(159,'꼬북이','내가 그린 꼬북꼬북~','2023-02-16 13:57:28',2,0,0,'thumb_95069b21-265a-4390-a722-a850982b5b11.jpeg','95069b21-265a-4390-a722-a850982b5b11.jpeg',0,0,1,2,145),
(160,'바나나','손나 바나나','2023-02-16 05:37:08',2,0,1,'thumb_582aa0f4-867f-47af-80af-170ae1aa4bc2.jpg','582aa0f4-867f-47af-80af-170ae1aa4bc2.jpg',0,0,0,3,121),
(161,'캠벨 수프캔','맛있겠다','2023-02-16 06:03:24',41,0,0,'thumb_72a4a084-3ebb-4fdf-8a89-4bdea9ec222e.jpg','72a4a084-3ebb-4fdf-8a89-4bdea9ec222e.jpg',0,0,0,3,121),
(162,'마를린 먼로','이것이 팝아트','2023-02-16 06:03:48',2,0,0,'thumb_b4a1848f-a133-4428-aea2-94e2edfbb1e7.jpg','b4a1848f-a133-4428-aea2-94e2edfbb1e7.jpg',0,0,0,3,121),
(163,'하트하고 있는 짱구','짱구배경화면','2023-02-16 15:26:41',1,1,0,'thumb_9e96579e-7f06-4c66-81f4-8fc5c9f0a421.jpg','9e96579e-7f06-4c66-81f4-8fc5c9f0a421.jpg',0,0,0,2,152),
(164,'흰둥이와 짱구','짱구배경화면','2023-02-16 15:27:49',1,0,0,'thumb_8118d31f-bb5e-4d0c-b2e7-61b6d55acef8.png','8118d31f-bb5e-4d0c-b2e7-61b6d55acef8.png',0,0,0,2,152),
(165,'액션가면 짱구','짱구배경화면','2023-02-16 15:28:12',0,0,0,'thumb_a52d24cf-9ccc-4e55-9f23-8bb73be696af.jpg','a52d24cf-9ccc-4e55-9f23-8bb73be696af.jpg',0,0,0,2,152),
(166,'문어 짱구','짱구배경화면','2023-02-16 15:28:30',1,0,0,'thumb_9bcc84df-a461-423e-905b-90e1585d39f5.jpg','9bcc84df-a461-423e-905b-90e1585d39f5.jpg',0,0,0,2,152),
(167,'햄스터와 짱구','짱구배경화면','2023-02-16 15:29:11',0,0,0,'thumb_a175b49f-57da-4be0-8818-100d041ea069.jpg','a175b49f-57da-4be0-8818-100d041ea069.jpg',0,0,0,2,152),
(168,'유치원 가는 짱구','짱구배경화면','2023-02-16 15:29:45',0,0,0,'thumb_5b77e509-dba4-490e-9cbd-155c34b2886c.jpg','5b77e509-dba4-490e-9cbd-155c34b2886c.jpg',0,0,0,2,152),
(169,'하늘을 나는 짱구','짱구배경화면','2023-02-16 15:30:09',3,0,0,'thumb_c807fe3b-74de-4930-86ea-9f2fbad265c4.jpg','c807fe3b-74de-4930-86ea-9f2fbad265c4.jpg',0,0,0,2,152),
(170,'랍스터 짱구','짱구배경화면','2023-02-16 15:30:39',0,0,0,'thumb_999f0eef-6e22-4969-8d10-037dd608e15a.png','999f0eef-6e22-4969-8d10-037dd608e15a.png',0,0,0,2,152),
(171,'하트 패턴 짱구','짱구배경화면','2023-02-16 15:31:17',0,0,0,'thumb_dc66f792-08f2-487f-9f73-239363b8150e.jpg','dc66f792-08f2-487f-9f73-239363b8150e.jpg',0,0,0,2,152),
(172,'인생네컷','인생네컷 추천 포즈를 취하는 패트와 매트','2023-02-16 15:47:12',6,0,0,'thumb_8a58f966-f2ae-459c-ad55-9d11dbda0f3f.jpg','8a58f966-f2ae-459c-ad55-9d11dbda0f3f.jpg',0,0,1,7,153),
(173,'어깨동무를 하는 패트와 매트 ','어깨동무하는 모습은 어떤가요? ','2023-02-16 15:48:08',4,0,0,'thumb_474331fc-5615-4651-9ba8-64dd278732a2.jpg','474331fc-5615-4651-9ba8-64dd278732a2.jpg',0,0,0,7,153),
(174,'악수하는 패트와 매트 ','악수하는 모습 패트와 매트  ','2023-02-16 15:48:51',4,0,0,'thumb_31d4b0cd-de81-4314-b230-9e05ab2c985b.jpg','31d4b0cd-de81-4314-b230-9e05ab2c985b.jpg',0,0,0,7,153),
(175,'각자노는 패트와 매트 ','보기 좋네요 ','2023-02-16 15:49:21',3,0,0,'thumb_0fa4bf3c-d186-4482-9621-116da761ed78.jpg','0fa4bf3c-d186-4482-9621-116da761ed78.jpg',0,0,0,7,153),
(177,'폭주족 루피 ','넘 무섭네 ','2023-02-16 16:04:04',2,0,0,'thumb_0ee9425f-3a81-4f19-83fc-068be086c1f7.jpg','0ee9425f-3a81-4f19-83fc-068be086c1f7.jpg',0,0,0,3,154),
(178,'한대 맞은 루피 ','왜 때려 ㅠ','2023-02-16 16:04:23',0,0,0,'thumb_60ccbcee-b251-41e9-91c8-ae52306b0ff2.jpg','60ccbcee-b251-41e9-91c8-ae52306b0ff2.jpg',0,0,0,3,154),
(179,'폭탄 머리 루피','깜짝이야!!','2023-02-16 16:04:44',2,0,0,'thumb_f1dbb484-1d69-4933-9ab6-091e05eb5ee0.jpg','f1dbb484-1d69-4933-9ab6-091e05eb5ee0.jpg',0,0,0,3,154),
(180,'놀란 루피','소름!!','2023-02-16 16:13:23',0,0,0,'thumb_30c05875-289c-427d-b69e-bc3d270a1335.jpg','30c05875-289c-427d-b69e-bc3d270a1335.jpg',0,0,0,3,154),
(183,'서빙 춘식','알바는 즐거워~!','2023-02-16 16:53:05',0,0,0,'thumb_294b366b-b970-4a5a-9a27-e43cd700a41b.jpg','294b366b-b970-4a5a-9a27-e43cd700a41b.jpg',0,0,0,1,105),
(184,'라이언과 춘식','폴라로이드 한장 찰칵','2023-02-16 16:53:35',1,0,0,'thumb_e6ef4cea-71a3-47e9-b676-66f40bad46dc.jpg','e6ef4cea-71a3-47e9-b676-66f40bad46dc.jpg',0,0,0,1,105),
(185,'다이어트 춘식','고구마 마음껏 먹고 싶다','2023-02-16 16:54:00',3,0,0,'thumb_2d19a1f0-45b5-44ab-b251-69065fbc67ef.jpg','2d19a1f0-45b5-44ab-b251-69065fbc67ef.jpg',0,0,0,1,105),
(186,'꿀잠 춘식','꿀잠자 춘식아~!','2023-02-16 16:54:37',1,0,0,'thumb_f144a13c-502d-40b0-aa98-f27baf322775.jpg','f144a13c-502d-40b0-aa98-f27baf322775.jpg',0,0,0,1,105),
(187,'열공 춘식','공부는 하는거니 춘식아~?','2023-02-16 16:55:14',4,0,0,'thumb_aff55bea-3dc3-4988-94b6-0987cde05509.jpg','aff55bea-3dc3-4988-94b6-0987cde05509.jpg',0,0,0,1,105);
/*!40000 ALTER TABLE `art` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `art_category`
--

DROP TABLE IF EXISTS `art_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_category` (
  `art_category_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `art_category_name` varchar(100) NOT NULL,
  PRIMARY KEY (`art_category_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `art_category`
--

LOCK TABLES `art_category` WRITE;
/*!40000 ALTER TABLE `art_category` DISABLE KEYS */;
INSERT INTO `art_category` VALUES
(1,'일러스트레이션'),
(2,'캐릭터 디자인'),
(3,'디지털 아트'),
(4,'타이포그래피'),
(5,'포토그래피'),
(6,'파인아트'),
(7,'공예');
/*!40000 ALTER TABLE `art_category` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `auction`
--

DROP TABLE IF EXISTS `auction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auction` (
  `curation_art_seq` bigint(20) NOT NULL,
  `auction_start_price` int(11) NOT NULL,
  `auction_gap` int(11) NOT NULL,
  `auction_start_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `auction_end_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `auction_paid_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `auction_status_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `auction_end_price` int(11) NOT NULL DEFAULT 0,
  `auction_status` varchar(10) NOT NULL DEFAULT 'INIT',
  `user_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`curation_art_seq`),
  KEY `FK_user_TO_auction_1` (`user_seq`),
  CONSTRAINT `FK_curation_art_TO_auction_1` FOREIGN KEY (`curation_art_seq`) REFERENCES `curation_art` (`curation_art_seq`) ON DELETE CASCADE,
  CONSTRAINT `FK_user_TO_auction_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction`
--

LOCK TABLES `auction` WRITE;
/*!40000 ALTER TABLE `auction` DISABLE KEYS */;
/*!40000 ALTER TABLE `auction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auction_bid_log`
--

DROP TABLE IF EXISTS `auction_bid_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auction_bid_log` (
  `auction_bid_log_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `auction_bid_price` int(11) NOT NULL DEFAULT 0,
  `auction_bid_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `user_seq` bigint(20) NOT NULL,
  `curation_art_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`auction_bid_log_seq`),
  KEY `FK_user_TO_auction_bid_log_1` (`user_seq`),
  KEY `FK_auction_TO_auction_bid_log_1` (`curation_art_seq`),
  CONSTRAINT `FK_auction_TO_auction_bid_log_1` FOREIGN KEY (`curation_art_seq`) REFERENCES `auction` (`curation_art_seq`) ON DELETE CASCADE,
  CONSTRAINT `FK_user_TO_auction_bid_log_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_bid_log`
--

LOCK TABLES `auction_bid_log` WRITE;
/*!40000 ALTER TABLE `auction_bid_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `auction_bid_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auction_join`
--

DROP TABLE IF EXISTS `auction_join`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auction_join` (
  `user_seq` bigint(20) NOT NULL,
  `curation_art_seq` bigint(20) NOT NULL,
  `auction_join_time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`user_seq`,`curation_art_seq`),
  KEY `FK_curation_art_TO_auction_join_1` (`curation_art_seq`),
  CONSTRAINT `FK_curation_art_TO_auction_join_1` FOREIGN KEY (`curation_art_seq`) REFERENCES `curation_art` (`curation_art_seq`) ON DELETE CASCADE,
  CONSTRAINT `FK_user_TO_auction_join_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_join`
--

LOCK TABLES `auction_join` WRITE;
/*!40000 ALTER TABLE `auction_join` DISABLE KEYS */;
/*!40000 ALTER TABLE `auction_join` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curation`
--

LOCK TABLES `curation` WRITE;
/*!40000 ALTER TABLE `curation` DISABLE KEYS */;
INSERT INTO `curation` VALUES
(53,'2023-02-16 18:23:01','2023-02-16 13:59:33','제가 그린 포켓몬 자랑할게요!','피카츄, 라이츄, 파이리, 꼬북이','INIT',0,3,'001a2269-80d7-47f9-a490-c0e286095baa.jpg','thumb_001a2269-80d7-47f9-a490-c0e286095baa.jpg',145),
(55,'2023-02-23 15:25:00','2023-02-16 06:26:43','고래는 생선이다','생선은 헤엄을 치기 때문이다.','INIT',0,1,'bae12a20-8bb5-4066-bcaf-3f562aa5e2d6.jpg','thumb_bae12a20-8bb5-4066-bcaf-3f562aa5e2d6.jpg',137),
(56,'2023-02-16 15:37:01','2023-02-16 15:35:54','짱구 배경화면 구경하세요~!','짱구 배경화면 모음집입니다! 구경하고 가세요~!','ON',0,1,'9e96579e-7f06-4c66-81f4-8fc5c9f0a421.jpg','thumb_9e96579e-7f06-4c66-81f4-8fc5c9f0a421.jpg',152),
(57,'2023-02-15 15:37:01','2023-02-16 15:53:59','내 피, 땀, 눈물','함들게 만든 작품입니다. 가격 잘 쳐주세요~!','END',0,1,'8a58f966-f2ae-459c-ad55-9d11dbda0f3f.jpg','thumb_8a58f966-f2ae-459c-ad55-9d11dbda0f3f.jpg',153),
(58,'2023-02-16 16:16:01','2023-02-16 16:15:42','루피 저장소','또 뭘 만들어볼까요??','ON',0,1,'0ee9425f-3a81-4f19-83fc-068be086c1f7.jpg','thumb_0ee9425f-3a81-4f19-83fc-068be086c1f7.jpg',154),
(59,'2023-02-16 16:59:01','2023-02-16 16:57:40','춘식이 모음집','보기만 해도 귀엽지요?','ON',0,0,'294b366b-b970-4a5a-9a27-e43cd700a41b.jpg','thumb_294b366b-b970-4a5a-9a27-e43cd700a41b.jpg',105),
(60,'2023-02-19 20:49:00','2023-02-16 09:03:04','귀여운 햄터 구경오세요','제가 좋아하는 햄터입니다\n귀여워요\n한 번 감상해주세요','INIT',0,0,'0cda01c6-edc6-4cc7-bd72-0e6df613213a.png','thumb_0cda01c6-edc6-4cc7-bd72-0e6df613213a.png',108);
/*!40000 ALTER TABLE `curation` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
(67,500000,10000,0,187,59),
(68,10000,1000,0,144,60),
(69,50000,1000,0,146,60);
/*!40000 ALTER TABLE `curation_art` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curation_bookmark`
--

DROP TABLE IF EXISTS `curation_bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curation_bookmark` (
  `curation_seq` bigint(20) NOT NULL,
  `user_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`curation_seq`,`user_seq`),
  KEY `FK9k260ukj71xlhjh02yj9gie3q` (`user_seq`),
  CONSTRAINT `FK24xdejc7811rcycwjvvx2g4cg` FOREIGN KEY (`curation_seq`) REFERENCES `curation` (`curation_seq`) ON DELETE CASCADE,
  CONSTRAINT `FK9k260ukj71xlhjh02yj9gie3q` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curation_bookmark`
--

LOCK TABLES `curation_bookmark` WRITE;
/*!40000 ALTER TABLE `curation_bookmark` DISABLE KEYS */;
INSERT INTO `curation_bookmark` VALUES
(53,137),
(53,146),
(53,151),
(55,137),
(56,151),
(57,151),
(58,108);
/*!40000 ALTER TABLE `curation_bookmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curation_notification`
--

DROP TABLE IF EXISTS `curation_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curation_notification` (
  `notification_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `notification_content` varchar(100) DEFAULT NULL,
  `notification_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `notification_is_read` tinyint(1) NOT NULL DEFAULT 0,
  `user_seq` bigint(20) NOT NULL,
  `artist_seq` bigint(20) NOT NULL,
  `curation_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`notification_seq`),
  KEY `FK_user_TO_curation_notification_1` (`user_seq`),
  KEY `FK_artist_TO_curation_notification_1` (`artist_seq`),
  KEY `FK_curation_TO_curation_notification_1` (`curation_seq`),
  CONSTRAINT `FK_artist_TO_curation_notification_1` FOREIGN KEY (`artist_seq`) REFERENCES `artist` (`user_seq`) ON DELETE CASCADE,
  CONSTRAINT `FK_curation_TO_curation_notification_1` FOREIGN KEY (`curation_seq`) REFERENCES `curation` (`curation_seq`) ON DELETE CASCADE,
  CONSTRAINT `FK_user_TO_curation_notification_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curation_notification`
--

LOCK TABLES `curation_notification` WRITE;
/*!40000 ALTER TABLE `curation_notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `curation_notification` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `my_artist`
--

DROP TABLE IF EXISTS `my_artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_artist` (
  `user_seq` bigint(20) NOT NULL,
  `artist_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`user_seq`,`artist_seq`),
  KEY `FK_artist_TO_my_artist_1` (`artist_seq`),
  CONSTRAINT `FK_artist_TO_my_artist_1` FOREIGN KEY (`artist_seq`) REFERENCES `artist` (`user_seq`) ON DELETE CASCADE,
  CONSTRAINT `FK_user_TO_my_artist_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_artist`
--

LOCK TABLES `my_artist` WRITE;
/*!40000 ALTER TABLE `my_artist` DISABLE KEYS */;
INSERT INTO `my_artist` VALUES
(83,121),
(83,122),
(100,102),
(100,104),
(100,105),
(100,108),
(102,108),
(104,105),
(105,102),
(105,107),
(108,100),
(108,118),
(121,100),
(121,122),
(122,121),
(123,100),
(123,104),
(123,105),
(125,102),
(125,104),
(125,108),
(125,118),
(125,122),
(125,128),
(130,125);
/*!40000 ALTER TABLE `my_artist` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
(26,'제목','내용','2023-02-16 04:16:05',100),
(27,'','','2023-02-16 13:35:43',100);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nickname` varchar(12) NOT NULL,
  `profile_img` varchar(100) NOT NULL DEFAULT 'default_profile_1.png' COMMENT '프로필 이미지 파일 이름',
  `artist_like_count` int(11) NOT NULL DEFAULT 0,
  `role` varchar(20) NOT NULL COMMENT 'ADMIN, USER, ARTIST',
  PRIMARY KEY (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES
(83,'ssafy@ruu.kr','$2a$10$DcageRHGxw9Iw4UKsJuvRuj.MRDHTgUQpUQnWGOmlUGJM2iFesjC6','테스트용','default_profile_4.png',0,'ROLE_USER'),
(100,'dam2.dev@gmail.com','$2a$10$5UNn40tS119pCjg9PWQIRetzGEv9RzUx5ooVrZ7ikYuDz68pjmAq2','발빠짐주희','default_profile_4.png',2,'ROLE_ARTIST'),
(102,'sin10071@naver.com','$2a$10$GWMUVUM8.IfHIunbCAW3YuRwqIdUww0Sf5sdCy7mLvC76xZuQdy..','seonho','default_profile_3.png',3,'ROLE_ARTIST'),
(104,'ggg@gmail.com','$2a$10$ON9/tnS6jiMbjG.87dg1BeJc75vC9u/EfdYrqzdM/XTbGS283r4yC','ggg','default_profile_2.png',2,'ROLE_ARTIST'),
(105,'hhhh@gmail.com','$2a$10$gVq/YS4Tkrsp0fa5ERkR/uAxtsyl2X3od4/Yq5G1trY.MCr.qBf.6','hhhh','default_profile_4.png',1,'ROLE_ARTIST'),
(107,'a@gmail.com','$2a$10$cu2v8C5DJV/w2HEU08zbsuY1l7A1BKDI72hTODur5GxoB/FhesEBS','a','default_profile_4.png',1,'ROLE_ARTIST'),
(108,'qri98@naver.com','$2a$10$tpFhMH6XPPUcKsq4orTDC.iy.iNn9sCAcpWzx8FTR.OcrtAgM6zx2','규레기','default_profile_1.png',3,'ROLE_ARTIST'),
(118,'shpark097754@gmail.com','$2a$10$mhudh1GK1HZGADEmqW871.lsi2q5IPpY7ZlkusfP.AamfnmA8hEze','박시현','default_profile_3.png',2,'ROLE_ARTIST'),
(121,'bananatest@ruu.kr','$2a$10$aX3tyK.jNA3NX48SZ8TrEu7C6mYOj1UO7sMlKs9Z9XKi34dgQxQQu','페페','69a32511-3bb7-4618-99bb-c6ff510d2032.jpg',0,'ROLE_ARTIST'),
(122,'garubanana@ruu.kr','$2a$10$z6i2e7JW6n5epsbqKU9tXeWjfI5FgRwvnFPBI.LTIDs8jzKCH0wp6','가루바나나','83ed3387-aa24-4125-ba05-e2e2a77a809e.jpg',1,'ROLE_ARTIST'),
(123,'sonyujin596@gmail.com','$2a$10$YyDDTo3GNcXzZxMCDYCfYeG6rVSmKHza83TA6iDdp9rEMeIvlyt4S','yuyu','default_profile_1.png',0,'ROLE_USER'),
(124,'b@ruu.kr','$2a$10$a64NJzcFiwSSb5j.05peCOn5ygBGfWpj2SKRkNoT8GemKyWrdYV6m','b','default_profile_3.png',0,'ROLE_USER'),
(125,'shpark0913@naver.com','$2a$10$Q2rl8uK2932KSXBKoDi1RekZNNMQTHww8UEZT9NOg54ni0UreZDKu','으아악','default_profile_2.png',1,'ROLE_ARTIST'),
(126,'newseul34@gmail.com','$2a$10$hBZAI9LMp2Smy5B2Iws5s.HjqoE8o7CEnQJd4fk/Pn0t/sMSbaG.q','슬기코치','default_profile_1.png',0,'ROLE_USER'),
(127,'hi@ruu.kr','$2a$10$T9F5eY.u.trUz/ANg95SS.7giPU4oOTLXyr0uMsJkb4zRTuMoKxVm','hi','default_profile_3.png',0,'ROLE_USER'),
(128,'sjb378@me.com','$2a$10$.LNnA856CBaRiU6bQOMTfemi7x.GdfzmMizqZzzGrLJKSqKT3lsom','test','default_profile_1.png',1,'ROLE_ARTIST'),
(129,'devjunmo@gmail.com','$2a$10$MK3p.iZHd3Np3LOG5ryGq.wLIZ34ey5y0fFIMs0CzZYFS1tXgxvLW','바규난킬러','default_profile_1.png',0,'ROLE_USER'),
(130,'qri1114@gmail.com','$2a$10$ATT.xjweaMDsM401MMln6uVshsyeIlcNtRG22JAfv3hOtEByhPUfO','미묭구','default_profile_4.png',0,'ROLE_USER'),
(137,'shpark0913@khu.ac.kr','$2a$10$1zIahxpo58w1BNaMPy4gAOj8ntIdVOXapoUaLNOATKyW5x0mY2Rv.','감나무','default_profile_3.png',0,'ROLE_ARTIST'),
(138,'chohm00790083@gmail.com','$2a$10$5ZEYY7nEy6ArwzMjMPLiheUZHb8DEY7PAZacG/Lq2HOR2eKh4IA.K','의문의중국인개발자','default_profile_4.png',0,'ROLE_USER'),
(139,'qwerty@ruu.kr','$2a$10$ZHCWI77dlFIJRU2AVZv2he7sVjyiapJwxegAhX8yuliVxCJZQ8JVC','qwerty','default_profile_1.png',0,'ROLE_USER'),
(140,'simon@ruu.kr','$2a$10$VynSaeoLX7oqw5ywYiHr2ub7noLSGw2Yt974C.BDMuTLUDmfGyoje','강시몬 컨설턴트','c90bb92d-d01a-4916-a272-1519e250d560.png',0,'ROLE_USER'),
(141,'cxzlkjhgfdsa@naver.com','$2a$10$PPRSgd2WAN09nKD6CdEc2exml98Yv5GrKjDcZTtRiVdQfrQsyX0m2','나양준모요','default_profile_2.png',0,'ROLE_ARTIST'),
(142,'coach09@ruu.kr','$2a$10$26IcdnJWhIRc8qdm/MdWeuFBw5DXjs01BXjiJEGncYf/cgWbFxocG','황승주 코치','default_profile_4.png',0,'ROLE_USER'),
(143,'coach05@ruu.kr','$2a$10$qZEyGgEAYLqWhSRbXCIZzOZNTspNoEwW4ZXOXupF.F9/ptCaUiYq6','신슬기 코치','default_profile_1.png',0,'ROLE_USER'),
(145,'jinjin@ruu.kr','$2a$10$mKZ5MWjScUiYjsJoPog2euhJjULrLJPaonjhuu6QmH2Un5..reLo2','jinjin','default_profile_1.png',0,'ROLE_ARTIST'),
(146,'yujin@ruu.kr','$2a$10$0jKM8f4wsjbaDUIb26wu3.hYxXRc9TDFgW0X8axo8ULJZxjtJC7J.','yujin','default_profile_1.png',0,'ROLE_USER'),
(147,'jin@ruu.kr','$2a$10$CeN914tuGxXopicspeaAq.o5yYcwxROx3FMqHFJtu5mIu6G4DOT.q','jin','default_profile_1.png',0,'ROLE_USER'),
(148,'son@ruu.kr','$2a$10$B4KnItw.2UGEBpVJIg7VZuHViWFcNszKa.oN8zaF6oY8ohSLN4NOW','son','default_profile_4.png',0,'ROLE_USER'),
(149,'yu@ruu.kr','$2a$10$KIrUAoi8RKlROFCvFI7At..MZiTsY48tQ2oLnrxk39Mo.eYG8UxLG','yu','default_profile_2.png',0,'ROLE_USER'),
(150,'sonyujin@ruu.kr','$2a$10$HgEQtdZU2O1RaPUeNa/v9edaIKfTkhPCmbNoYix5N9t8ASDrrbNVe','sonyujin','default_profile_3.png',0,'ROLE_USER'),
(151,'apple@ruu.kr','$2a$10$IOztQwPFRJBlcJKi8asjE.RwRjqa7QiKEqwn6/wDxY9aD2YkATO8q','apple','default_profile_3.png',0,'ROLE_USER'),
(152,'zzang@ruu.kr','$2a$10$/9vqFPfEzHZeIY4s5cVoaehurLLpfWOM/COK.LkoeFrG2D5E2SGZ2','짱구엄마','default_profile_3.png',0,'ROLE_ARTIST'),
(153,'patmat@ruu.kr','$2a$10$h7ftl5FezADTfMpEGbB8S.zCxq95BlgY94wucg4AchV7SlF.BzfU2','패트와 매트 처돌이','default_profile_2.png',0,'ROLE_ARTIST'),
(154,'roopy@ruu.kr','$2a$10$.UhIs1wFkUF.pRLiaIhA6Oc.ZvMI4HHY8xI4KUe8ezZyeVT6lB6XS','루피 컬렉터','default_profile_3.png',0,'ROLE_ARTIST'),
(155,'xkhg0611x@naver.com','$2a$10$47VqcxCXyhrHImOsnc/4I.T07UUZKriJSUZ4VQWqwSKUbSFkWghvC','하상재','default_profile_1.png',0,'ROLE_USER');
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

-- Dump completed on 2023-02-16 13:41:02
