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
(83,'pyh1993@naver.com','$2a$10$DcageRHGxw9Iw4UKsJuvRuj.MRDHTgUQpUQnWGOmlUGJM2iFesjC6','테스트용','default_profile_4.png',0,'ROLE_USER'),
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

-- Dump completed on 2023-02-16  8:57:01
