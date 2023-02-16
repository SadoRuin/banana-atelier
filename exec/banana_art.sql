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
(107,'햄터님','ㅈㅂㅈㅂ','2023-02-12 10:35:59',78,3,3,'thumb_68e26664-56a8-4ba7-bfd4-3eb8bec63e0e.png','68e26664-56a8-4ba7-bfd4-3eb8bec63e0e.png',0,1,1,2,108),
(128,'클레이','찰흙','2023-02-13 00:30:10',74,4,2,'thumb_8d79504d-6da5-499e-86ba-ebfdbe2750d7.jpg','8d79504d-6da5-499e-86ba-ebfdbe2750d7.jpg',0,0,0,7,102),
(129,'무룽','집순이 무룽이는 집에 가고 싶어','2023-02-13 01:59:18',39,0,6,'thumb_25dd481b-6953-4a23-8245-af9ef5603b7d.png','25dd481b-6953-4a23-8245-af9ef5603b7d.png',0,0,1,1,104),
(132,'니시노야','다들 하이큐 좋아하시나요? 니시노야를 그려보았습니다 모작입니다 판매는 안돼요','2023-02-13 12:56:29',79,3,3,'thumb_013e7140-dedc-4b21-92fb-4f72f3318f40.jpg','013e7140-dedc-4b21-92fb-4f72f3318f40.jpg',0,0,1,1,100),
(135,'부기','부기부기','2023-02-13 14:03:21',18,0,3,'thumb_3c0a816f-501e-4030-ad90-3473b19974ba.png','3c0a816f-501e-4030-ad90-3473b19974ba.png',0,0,0,5,108),
(137,'happy coding','해피 코딩입니다~','2023-02-14 00:33:56',19,1,1,'thumb_617b5b20-b46e-4568-a136-1a766226bb27.png','617b5b20-b46e-4568-a136-1a766226bb27.png',0,0,0,4,128),
(140,'써니','우리집 댕댕','2023-02-14 07:28:10',16,0,2,'thumb_a72b3b9f-efc0-4282-b7f6-f9347625ae19.jpg','a72b3b9f-efc0-4282-b7f6-f9347625ae19.jpg',0,0,0,1,121),
(142,'수채화','수채화입니다아악','2023-02-14 13:09:47',14,0,1,'thumb_aea45302-73e7-40cb-93a1-a9a7987acf3e.jpg','aea45302-73e7-40cb-93a1-a9a7987acf3e.jpg',0,0,1,2,125),
(143,'예술','ㄷ','2023-02-14 15:36:01',20016,3433,5645,'thumb_a5bcaa79-372f-4ef7-9f5a-9da7194247a2.png','a5bcaa79-372f-4ef7-9f5a-9da7194247a2.png',0,0,1,6,125),
(144,'마음껏 핥으라구','마음껏 핥으라구\n그런데 우리 ㅅ줄바꿈이 안돼요 ㅇ\n어떡해요','2023-02-14 17:13:08',44,0,3,'thumb_0cda01c6-edc6-4cc7-bd72-0e6df613213a.png','0cda01c6-edc6-4cc7-bd72-0e6df613213a.png',0,0,1,2,108),
(146,'안녕하세요','안녕하세요..?\n지구를 정복하러 왔습니다\n지 구 조 아','2023-02-15 03:58:04',14,0,3,'thumb_b1364d28-ad24-43fb-89a1-7cb0638635c5.jpg','b1364d28-ad24-43fb-89a1-7cb0638635c5.jpg',0,0,1,5,108),
(147,'쿠로미','귀여운 쿠로미쨩-! ㅋ ㅋ','2023-02-15 15:20:39',34,0,1,'thumb_2eaba6e4-02dc-47bd-920c-0bf78bf82ef4.jpg','2eaba6e4-02dc-47bd-920c-0bf78bf82ef4.jpg',0,0,0,7,100),
(149,'우엑','힘들어욤','2023-02-15 18:42:36',5,0,0,'thumb_6a2190d8-49ec-409e-ae96-7f1e1e3bda84.png','6a2190d8-49ec-409e-ae96-7f1e1e3bda84.png',0,0,0,4,108),
(150,'하늘','하늘이 맑다...','2023-02-16 00:08:33',1,0,0,'thumb_c3561e52-f58c-4856-b414-164d43297419.jpg','c3561e52-f58c-4856-b414-164d43297419.jpg',0,0,0,1,137),
(151,'커피','신문이 영어다.\n커피가 맛이 없어졌다.','2023-02-16 00:09:05',5,0,0,'thumb_aac68146-395c-4b7b-81f0-5f1bc1fedc30.jpg','aac68146-395c-4b7b-81f0-5f1bc1fedc30.jpg',0,0,0,1,137),
(152,'고래','고래의 꼬리다','2023-02-16 00:09:32',1,0,0,'thumb_bae12a20-8bb5-4066-bcaf-3f562aa5e2d6.jpg','bae12a20-8bb5-4066-bcaf-3f562aa5e2d6.jpg',0,0,1,5,137),
(155,'귤','귤 입니다','2023-02-16 03:52:40',9,0,1,'thumb_77d76e3e-7cef-483a-bc38-ce26379cf0ac.jpeg','77d76e3e-7cef-483a-bc38-ce26379cf0ac.jpeg',0,0,0,3,141),
(156,'피카츄','내가 그린 피카츄~','2023-02-16 13:52:14',2,0,0,'thumb_001a2269-80d7-47f9-a490-c0e286095baa.jpg','001a2269-80d7-47f9-a490-c0e286095baa.jpg',0,0,0,2,145),
(157,'라이츄','내가 그린 라이츄~','2023-02-16 13:56:39',2,0,0,'thumb_d5f015f8-6fcf-4c8e-a466-63e9de2b476f.png','d5f015f8-6fcf-4c8e-a466-63e9de2b476f.png',0,0,0,2,145),
(158,'파이리','내가 그린 파이리~','2023-02-16 13:57:01',2,0,0,'thumb_c47a4a48-b13d-40e9-b1d3-79c35dbcdc6b.jpg','c47a4a48-b13d-40e9-b1d3-79c35dbcdc6b.jpg',0,0,0,2,145),
(159,'꼬북이','내가 그린 꼬북꼬북~','2023-02-16 13:57:28',1,0,0,'thumb_95069b21-265a-4390-a722-a850982b5b11.jpeg','95069b21-265a-4390-a722-a850982b5b11.jpeg',0,0,1,2,145),
(160,'바나나','손나 바나나','2023-02-16 05:37:08',2,0,1,'thumb_582aa0f4-867f-47af-80af-170ae1aa4bc2.jpg','582aa0f4-867f-47af-80af-170ae1aa4bc2.jpg',0,0,0,3,121),
(161,'캠벨 수프캔','맛있겠다','2023-02-16 06:03:24',40,0,0,'thumb_72a4a084-3ebb-4fdf-8a89-4bdea9ec222e.jpg','72a4a084-3ebb-4fdf-8a89-4bdea9ec222e.jpg',0,0,0,3,121),
(162,'마를린 먼로','이것이 팝아트','2023-02-16 06:03:48',2,0,0,'thumb_b4a1848f-a133-4428-aea2-94e2edfbb1e7.jpg','b4a1848f-a133-4428-aea2-94e2edfbb1e7.jpg',0,0,0,3,121),
(163,'하트하고 있는 짱구','짱구배경화면','2023-02-16 15:26:41',1,1,0,'thumb_9e96579e-7f06-4c66-81f4-8fc5c9f0a421.jpg','9e96579e-7f06-4c66-81f4-8fc5c9f0a421.jpg',0,0,0,2,152),
(164,'흰둥이와 짱구','짱구배경화면','2023-02-16 15:27:49',1,0,0,'thumb_8118d31f-bb5e-4d0c-b2e7-61b6d55acef8.png','8118d31f-bb5e-4d0c-b2e7-61b6d55acef8.png',0,0,0,2,152),
(165,'액션가면 짱구','짱구배경화면','2023-02-16 15:28:12',0,0,0,'thumb_a52d24cf-9ccc-4e55-9f23-8bb73be696af.jpg','a52d24cf-9ccc-4e55-9f23-8bb73be696af.jpg',0,0,0,2,152),
(166,'문어 짱구','짱구배경화면','2023-02-16 15:28:30',0,0,0,'thumb_9bcc84df-a461-423e-905b-90e1585d39f5.jpg','9bcc84df-a461-423e-905b-90e1585d39f5.jpg',0,0,0,2,152),
(167,'햄스터와 짱구','짱구배경화면','2023-02-16 15:29:11',0,0,0,'thumb_a175b49f-57da-4be0-8818-100d041ea069.jpg','a175b49f-57da-4be0-8818-100d041ea069.jpg',0,0,0,2,152),
(168,'유치원 가는 짱구','짱구배경화면','2023-02-16 15:29:45',0,0,0,'thumb_5b77e509-dba4-490e-9cbd-155c34b2886c.jpg','5b77e509-dba4-490e-9cbd-155c34b2886c.jpg',0,0,0,2,152),
(169,'하늘을 나는 짱구','짱구배경화면','2023-02-16 15:30:09',1,0,0,'thumb_c807fe3b-74de-4930-86ea-9f2fbad265c4.jpg','c807fe3b-74de-4930-86ea-9f2fbad265c4.jpg',0,0,0,2,152),
(170,'랍스터 짱구','짱구배경화면','2023-02-16 15:30:39',0,0,0,'thumb_999f0eef-6e22-4969-8d10-037dd608e15a.png','999f0eef-6e22-4969-8d10-037dd608e15a.png',0,0,0,2,152),
(171,'하트 패턴 짱구','짱구배경화면','2023-02-16 15:31:17',0,0,0,'thumb_dc66f792-08f2-487f-9f73-239363b8150e.jpg','dc66f792-08f2-487f-9f73-239363b8150e.jpg',0,0,0,2,152),
(172,'인생네컷','인생네컷 추천 포즈를 취하는 패트와 매트','2023-02-16 15:47:12',4,0,0,'thumb_8a58f966-f2ae-459c-ad55-9d11dbda0f3f.jpg','8a58f966-f2ae-459c-ad55-9d11dbda0f3f.jpg',0,0,1,7,153),
(173,'어깨동무를 하는 패트와 매트 ','어깨동무하는 모습은 어떤가요? ','2023-02-16 15:48:08',3,0,0,'thumb_474331fc-5615-4651-9ba8-64dd278732a2.jpg','474331fc-5615-4651-9ba8-64dd278732a2.jpg',0,0,0,7,153),
(174,'악수하는 패트와 매트 ','악수하는 모습 패트와 매트  ','2023-02-16 15:48:51',3,0,0,'thumb_31d4b0cd-de81-4314-b230-9e05ab2c985b.jpg','31d4b0cd-de81-4314-b230-9e05ab2c985b.jpg',0,0,0,7,153),
(175,'각자노는 패트와 매트 ','보기 좋네요 ','2023-02-16 15:49:21',2,0,0,'thumb_0fa4bf3c-d186-4482-9621-116da761ed78.jpg','0fa4bf3c-d186-4482-9621-116da761ed78.jpg',0,0,0,7,153),
(177,'폭주족 루피 ','넘 무섭네 ','2023-02-16 16:04:04',2,0,0,'thumb_0ee9425f-3a81-4f19-83fc-068be086c1f7.jpg','0ee9425f-3a81-4f19-83fc-068be086c1f7.jpg',0,0,0,3,154),
(178,'한대 맞은 루피 ','왜 때려 ㅠ','2023-02-16 16:04:23',0,0,0,'thumb_60ccbcee-b251-41e9-91c8-ae52306b0ff2.jpg','60ccbcee-b251-41e9-91c8-ae52306b0ff2.jpg',0,0,0,3,154),
(179,'폭탄 머리 루피','깜짝이야!!','2023-02-16 16:04:44',0,0,0,'thumb_f1dbb484-1d69-4933-9ab6-091e05eb5ee0.jpg','f1dbb484-1d69-4933-9ab6-091e05eb5ee0.jpg',0,0,0,3,154),
(180,'놀란 루피','소름!!','2023-02-16 16:13:23',0,0,0,'thumb_30c05875-289c-427d-b69e-bc3d270a1335.jpg','30c05875-289c-427d-b69e-bc3d270a1335.jpg',0,0,0,3,154),
(183,'서빙 춘식','알바는 즐거워~!','2023-02-16 16:53:05',0,0,0,'thumb_294b366b-b970-4a5a-9a27-e43cd700a41b.jpg','294b366b-b970-4a5a-9a27-e43cd700a41b.jpg',0,0,0,1,105),
(184,'라이언과 춘식','폴라로이드 한장 찰칵','2023-02-16 16:53:35',0,0,0,'thumb_e6ef4cea-71a3-47e9-b676-66f40bad46dc.jpg','e6ef4cea-71a3-47e9-b676-66f40bad46dc.jpg',0,0,0,1,105),
(185,'다이어트 춘식','고구마 마음껏 먹고 싶다','2023-02-16 16:54:00',1,0,0,'thumb_2d19a1f0-45b5-44ab-b251-69065fbc67ef.jpg','2d19a1f0-45b5-44ab-b251-69065fbc67ef.jpg',0,0,0,1,105),
(186,'꿀잠 춘식','꿀잠자 춘식아~!','2023-02-16 16:54:37',0,0,0,'thumb_f144a13c-502d-40b0-aa98-f27baf322775.jpg','f144a13c-502d-40b0-aa98-f27baf322775.jpg',0,0,0,1,105),
(187,'열공 춘식','공부는 하는거니 춘식아~?','2023-02-16 16:55:14',2,0,0,'thumb_aff55bea-3dc3-4988-94b6-0987cde05509.jpg','aff55bea-3dc3-4988-94b6-0987cde05509.jpg',0,0,0,1,105);
/*!40000 ALTER TABLE `art` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16  8:52:39
