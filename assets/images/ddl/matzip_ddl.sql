-- MySQL dump 10.19  Distrib 10.3.39-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: mat_zip
-- ------------------------------------------------------
-- Server version	10.3.39-MariaDB-0ubuntu0.20.04.2-log

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
-- Table structure for table `active_level`
--

DROP TABLE IF EXISTS `active_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `active_level` (
  `active_level_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '활동등급고유번호',
  `active_level_name` varchar(150) NOT NULL COMMENT '활동등급명칭',
  `active_level_standard` int(11) NOT NULL COMMENT '활동등급기준',
  PRIMARY KEY (`active_level_seq`),
  UNIQUE KEY `active_level_UK` (`active_level_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='활동등급';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `board_category`
--

DROP TABLE IF EXISTS `board_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board_category` (
  `board_category_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '게시판카테고리고유번호',
  `board_category_name` varchar(150) NOT NULL COMMENT '게시판카테고리이름',
  PRIMARY KEY (`board_category_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='게시판카테고리';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `business_license`
--

DROP TABLE IF EXISTS `business_license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_license` (
  `business_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '사업자고유번호',
  `user_seq` bigint(20) NOT NULL COMMENT '회원고유번호',
  `business_name` varchar(100) NOT NULL COMMENT '상호명',
  `business_number` char(10) NOT NULL COMMENT '사업자등록번호',
  `business_address` varchar(100) DEFAULT NULL COMMENT '주소',
  `business_verification_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT '인증날짜',
  `business_verification_status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '인증상태',
  PRIMARY KEY (`business_seq`) USING BTREE,
  KEY `buisiness_license_users_FK` (`user_seq`),
  CONSTRAINT `buisiness_license_users_FK` FOREIGN KEY (`user_seq`) REFERENCES `users` (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='사업자등록증';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `favorite_board`
--

DROP TABLE IF EXISTS `favorite_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite_board` (
  `favorite_board_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '즐겨찾기게시판고유번호',
  `user_seq` bigint(20) NOT NULL COMMENT '회원고유번호',
  `board_category_seq` bigint(20) NOT NULL COMMENT '게시판카테고리고유번호',
  PRIMARY KEY (`favorite_board_seq`),
  UNIQUE KEY `favorite_board_UK` (`board_category_seq`,`user_seq`),
  KEY `favorite_board_users_FK` (`user_seq`),
  CONSTRAINT `favorite_board_board_category_FK` FOREIGN KEY (`board_category_seq`) REFERENCES `board_category` (`board_category_seq`),
  CONSTRAINT `favorite_board_users_FK` FOREIGN KEY (`user_seq`) REFERENCES `users` (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='즐겨찾기게시판';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follow` (
  `following_user_seq` bigint(20) NOT NULL COMMENT '팔로잉고유번호',
  `followed_user_seq` bigint(20) NOT NULL COMMENT '팔로워고유번호',
  PRIMARY KEY (`following_user_seq`,`followed_user_seq`),
  KEY `follow_users_FK2` (`followed_user_seq`),
  CONSTRAINT `follow_users_FK1` FOREIGN KEY (`following_user_seq`) REFERENCES `users` (`user_seq`),
  CONSTRAINT `follow_users_FK2` FOREIGN KEY (`followed_user_seq`) REFERENCES `users` (`user_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='팔로우';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like` (
  `like_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '좋아요고유번호',
  `like_user_seq` bigint(20) NOT NULL COMMENT '회원고유번호',
  `post_seq` bigint(20) DEFAULT NULL COMMENT '게시글고유번호',
  `post_comment_seq` bigint(20) DEFAULT NULL COMMENT '게시글댓글고유번호',
  `list_seq` bigint(20) DEFAULT NULL COMMENT '리스트고유번호',
  `list_comment_seq` bigint(20) DEFAULT NULL COMMENT '리스트댓글고유번호',
  `review_seq` bigint(20) DEFAULT NULL COMMENT '맛집리뷰고유번호',
  PRIMARY KEY (`like_seq`),
  KEY `like_users_FK` (`like_user_seq`),
  KEY `like_post_FK` (`post_seq`),
  KEY `like_post_comment_FK` (`post_comment_seq`),
  KEY `like_lists_FK` (`list_seq`),
  KEY `like_list_comment_FK` (`list_comment_seq`),
  KEY `like_review_FK` (`review_seq`),
  CONSTRAINT `like_list_comment_FK` FOREIGN KEY (`list_comment_seq`) REFERENCES `list_comment` (`list_comment_seq`),
  CONSTRAINT `like_lists_FK` FOREIGN KEY (`list_seq`) REFERENCES `lists` (`list_seq`),
  CONSTRAINT `like_post_FK` FOREIGN KEY (`post_seq`) REFERENCES `post` (`post_seq`),
  CONSTRAINT `like_post_comment_FK` FOREIGN KEY (`post_comment_seq`) REFERENCES `post_comment` (`post_comment_seq`),
  CONSTRAINT `like_review_FK` FOREIGN KEY (`review_seq`) REFERENCES `review` (`review_seq`),
  CONSTRAINT `like_users_FK` FOREIGN KEY (`like_user_seq`) REFERENCES `users` (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='좋아요';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `list_comment`
--

DROP TABLE IF EXISTS `list_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `list_comment` (
  `list_comment_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '리스트댓글고유번호',
  `list_seq` bigint(20) NOT NULL COMMENT '리스트고유번호',
  `list_comment_user_seq` bigint(20) NOT NULL COMMENT '회원고유번호',
  `list_comment_content` varchar(1000) NOT NULL COMMENT '댓글내용',
  `list_comment_status` varchar(8) NOT NULL DEFAULT 'active' COMMENT '댓글상태',
  `list_comment_created_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '댓글작성시간',
  `list_comment_updated_time` datetime DEFAULT NULL COMMENT '댓글수정시간',
  `list_comment_deleted_time` datetime DEFAULT NULL COMMENT '댓글삭제시간',
  PRIMARY KEY (`list_comment_seq`),
  KEY `list_comment_lists_FK` (`list_seq`),
  KEY `list_comment_users_FK` (`list_comment_user_seq`),
  CONSTRAINT `list_comment_lists_FK` FOREIGN KEY (`list_seq`) REFERENCES `lists` (`list_seq`),
  CONSTRAINT `list_comment_users_FK` FOREIGN KEY (`list_comment_user_seq`) REFERENCES `users` (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='리스트댓글';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `list_matzip`
--

DROP TABLE IF EXISTS `list_matzip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `list_matzip` (
  `list_matzip_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '리스트맛집고유번호',
  `list_seq` bigint(20) NOT NULL COMMENT '리스트고유번호',
  `restaurant_seq` bigint(20) NOT NULL COMMENT '음식점고유번호',
  `list_matzip_comment` varchar(1000) DEFAULT NULL COMMENT '리스트맛집코멘트',
  PRIMARY KEY (`list_matzip_seq`),
  KEY `list_matzip_lists_FK` (`list_seq`),
  KEY `list_matzip_restaurant_FK` (`restaurant_seq`),
  CONSTRAINT `list_matzip_lists_FK` FOREIGN KEY (`list_seq`) REFERENCES `lists` (`list_seq`),
  CONSTRAINT `list_matzip_restaurant_FK` FOREIGN KEY (`restaurant_seq`) REFERENCES `restaurant` (`restaurant_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='리스트맛집';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lists`
--

DROP TABLE IF EXISTS `lists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lists` (
  `list_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '리스트고유번호',
  `list_user_seq` bigint(20) NOT NULL COMMENT '회원고유번호',
  `list_title` varchar(100) NOT NULL COMMENT '리스트제목',
  `list_content` varchar(1000) NOT NULL COMMENT '리스트내용',
  `list_status` varchar(8) NOT NULL DEFAULT 'active' COMMENT '리스트상태',
  `list_created_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '리스트작성시간',
  `list_updated_time` datetime DEFAULT NULL COMMENT '리스트수정시간',
  `list_deleted_time` datetime DEFAULT NULL COMMENT '리스트삭제시간',
  `list_level` int(11) NOT NULL COMMENT '리스트우선순위',
  PRIMARY KEY (`list_seq`),
  KEY `lists_users_FK` (`list_user_seq`),
  CONSTRAINT `lists_users_FK` FOREIGN KEY (`list_user_seq`) REFERENCES `users` (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='리스트';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message_block`
--

DROP TABLE IF EXISTS `message_block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_block` (
  `message_block_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '차단고유번호',
  `message_block_request_user_seq` bigint(20) NOT NULL COMMENT '차단요청자',
  `message_blocked_user_seq` bigint(20) NOT NULL COMMENT '차단대상자',
  PRIMARY KEY (`message_block_seq`),
  KEY `message_block_users_FK` (`message_block_request_user_seq`),
  KEY `message_block_users_FK_1` (`message_blocked_user_seq`),
  CONSTRAINT `message_block_users_FK` FOREIGN KEY (`message_block_request_user_seq`) REFERENCES `users` (`user_seq`),
  CONSTRAINT `message_block_users_FK_1` FOREIGN KEY (`message_blocked_user_seq`) REFERENCES `users` (`user_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='쪽지사용자차단';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `message_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '쪽지고유번호',
  `message_send_user_seq` bigint(20) NOT NULL COMMENT '발신회원고유번호',
  `message_receive_user_seq` bigint(20) NOT NULL COMMENT '수신회원고유번호',
  `message_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT '발송일',
  `message_content` varchar(1000) NOT NULL COMMENT '쪽지내용',
  `message_recipient_read_yn` char(1) NOT NULL DEFAULT 'N' COMMENT '수신회원읽음여부',
  `message_sender_deleted_yn` char(1) NOT NULL DEFAULT 'N' COMMENT '발신회원삭제여부',
  `message_recipient_deleted_yn` char(1) NOT NULL DEFAULT 'N' COMMENT '수신회원삭제여부',
  PRIMARY KEY (`message_seq`),
  KEY `messages_users_FK1` (`message_send_user_seq`),
  KEY `messages_users_FK2` (`message_receive_user_seq`),
  CONSTRAINT `messages_users_FK1` FOREIGN KEY (`message_send_user_seq`) REFERENCES `users` (`user_seq`),
  CONSTRAINT `messages_users_FK2` FOREIGN KEY (`message_receive_user_seq`) REFERENCES `users` (`user_seq`),
  CONSTRAINT `message_CK` CHECK (`message_recipient_read_yn` in ('Y','N')),
  CONSTRAINT `message_CK1` CHECK (`message_sender_deleted_yn` in ('Y','N')),
  CONSTRAINT `message_CK2` CHECK (`message_recipient_deleted_yn` in ('Y','N'))
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='쪽지';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `penalty`
--

DROP TABLE IF EXISTS `penalty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `penalty` (
  `penalty_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '패널티고유번호',
  `penalty_user_seq` bigint(20) NOT NULL COMMENT '회원고유번호',
  `penalty_start_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT '시작일',
  `penalty_end_date` datetime NOT NULL COMMENT '종료일',
  `penalty_type` varchar(15) NOT NULL DEFAULT 'ban' COMMENT '패널티종류',
  `penalty_reason_content` varchar(1000) DEFAULT NULL COMMENT '패널티사유내용',
  PRIMARY KEY (`penalty_seq`),
  KEY `penalty_users_FK` (`penalty_user_seq`),
  CONSTRAINT `penalty_users_FK` FOREIGN KEY (`penalty_user_seq`) REFERENCES `users` (`user_seq`),
  CONSTRAINT `penalty_CK` CHECK (`penalty_type` in ('ban','permanent'))
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='패널티';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `post_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '게시글고유번호',
  `post_user_seq` bigint(20) NOT NULL COMMENT '회원고유번호',
  `board_category_seq` bigint(20) NOT NULL COMMENT '게시판카테고리고유번호',
  `restaurant_seq` bigint(20) DEFAULT NULL,
  `list_seq` bigint(20) DEFAULT NULL COMMENT '리스트고유번호',
  `post_title` varchar(1500) NOT NULL COMMENT '게시글제목',
  `post_content` text NOT NULL COMMENT '게시글내용',
  `post_status` varchar(8) NOT NULL DEFAULT 'active' COMMENT '게시글 상태',
  `post_created_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '게시글작성시간',
  `post_updated_time` datetime DEFAULT NULL COMMENT '게시글수정시간',
  `post_deleted_time` datetime DEFAULT NULL COMMENT '게시글삭제시간',
  PRIMARY KEY (`post_seq`),
  KEY `post_users_FK` (`post_user_seq`),
  KEY `post_lists_FK` (`list_seq`),
  KEY `post_board_category_FK` (`board_category_seq`),
  KEY `fk_restaurant` (`restaurant_seq`),
  CONSTRAINT `fk_restaurant` FOREIGN KEY (`restaurant_seq`) REFERENCES `restaurant` (`restaurant_seq`),
  CONSTRAINT `post_board_category_FK` FOREIGN KEY (`board_category_seq`) REFERENCES `board_category` (`board_category_seq`),
  CONSTRAINT `post_lists_FK` FOREIGN KEY (`list_seq`) REFERENCES `lists` (`list_seq`),
  CONSTRAINT `post_users_FK` FOREIGN KEY (`post_user_seq`) REFERENCES `users` (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='게시글';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `post_comment`
--

DROP TABLE IF EXISTS `post_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_comment` (
  `post_comment_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '게시글댓글고유번호',
  `post_seq` bigint(20) NOT NULL COMMENT '게시글고유번호',
  `post_comment_user_seq` bigint(20) NOT NULL COMMENT '회원고유번호',
  `post_comment_content` varchar(1000) NOT NULL COMMENT '댓글내용',
  `post_comment_status` varchar(8) NOT NULL DEFAULT 'active' COMMENT '댓글상태',
  `post_comment_created_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '댓글작성시간',
  `post_comment_updated_time` datetime DEFAULT NULL COMMENT '댓글수정시간',
  `post_comment_deleted_time` datetime DEFAULT NULL COMMENT '댓글삭제시간',
  PRIMARY KEY (`post_comment_seq`),
  KEY `post_comment_users_FK` (`post_comment_user_seq`),
  KEY `post_comment_post_FK` (`post_seq`),
  CONSTRAINT `post_comment_post_FK` FOREIGN KEY (`post_seq`) REFERENCES `post` (`post_seq`),
  CONSTRAINT `post_comment_users_FK` FOREIGN KEY (`post_comment_user_seq`) REFERENCES `users` (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='게시글댓글';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `post_image`
--

DROP TABLE IF EXISTS `post_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_image` (
  `post_image_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '이미지고유번호',
  `post_seq` bigint(20) NOT NULL COMMENT '게시글고유번호',
  `post_image_filepath` varchar(300) NOT NULL COMMENT '파일저장명',
  `post_image_name` varchar(300) NOT NULL COMMENT '이미지명',
  `post_image_url` varchar(300) NOT NULL COMMENT '파일URL',
  `post_image_created_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '이미지등록시간',
  `post_image_deleted_yn` char(1) NOT NULL DEFAULT 'N' COMMENT '이미지삭제여부',
  PRIMARY KEY (`post_image_seq`),
  KEY `post_image_post_FK` (`post_seq`),
  CONSTRAINT `post_image_post_FK` FOREIGN KEY (`post_seq`) REFERENCES `post` (`post_seq`),
  CONSTRAINT `post_image_CK` CHECK (`post_image_deleted_yn` in ('Y','N'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='게시글이미지';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `post_tag`
--

DROP TABLE IF EXISTS `post_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_tag` (
  `post_tag_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '게시글태그고유번호',
  `tag_seq` bigint(20) NOT NULL COMMENT '태그고유번호',
  `post_seq` bigint(20) NOT NULL COMMENT '게시글고유번호',
  PRIMARY KEY (`post_tag_seq`),
  KEY `post_tag_post_FK` (`post_seq`),
  KEY `post_tag_tags_FK` (`tag_seq`),
  CONSTRAINT `post_tag_post_FK` FOREIGN KEY (`post_seq`) REFERENCES `post` (`post_seq`),
  CONSTRAINT `post_tag_tags_FK` FOREIGN KEY (`tag_seq`) REFERENCES `tags` (`tag_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='게시글태그';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reasons`
--

DROP TABLE IF EXISTS `reasons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reasons` (
  `reason_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '신고사유코드',
  `reason_name` varchar(100) NOT NULL COMMENT '신고사유',
  PRIMARY KEY (`reason_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='신고사유';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `report_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '신고고유번호',
  `reporter_user_seq` bigint(20) NOT NULL COMMENT '신고회원고유번호',
  `reported_user_seq` bigint(20) NOT NULL COMMENT '신고당한회원고유번호',
  `penalty_seq` bigint(20) DEFAULT NULL COMMENT '패널티고유번호',
  `report_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '신고시간',
  `report_content` varchar(1000) DEFAULT NULL COMMENT '신고내용',
  `report_finished_time` datetime DEFAULT NULL COMMENT '처리완료시간',
  `report_status` varchar(7) NOT NULL DEFAULT 'wait' COMMENT '처리상태',
  `post_seq` bigint(20) DEFAULT NULL COMMENT '게시글고유번호',
  `post_comment_seq` bigint(20) DEFAULT NULL COMMENT '게시글댓글고유번호',
  `list_seq` bigint(20) DEFAULT NULL COMMENT '리스트고유번호',
  `list_comment_seq` bigint(20) DEFAULT NULL COMMENT '리스트댓글고유번호',
  `review_seq` bigint(20) DEFAULT NULL COMMENT '맛집리뷰고유번호',
  `message_seq` bigint(20) DEFAULT NULL COMMENT '쪽지고유번호',
  PRIMARY KEY (`report_seq`),
  KEY `report_users_FK` (`reporter_user_seq`),
  KEY `report_users_FK_1` (`reported_user_seq`),
  KEY `report_penalty_FK` (`penalty_seq`),
  KEY `report_post_FK` (`post_seq`),
  KEY `report_post_comment_FK` (`post_comment_seq`),
  KEY `report_lists_FK` (`list_seq`),
  KEY `report_list_comment_FK` (`list_comment_seq`),
  KEY `report_review_FK` (`review_seq`),
  KEY `report_messages_FK` (`message_seq`),
  CONSTRAINT `report_list_comment_FK` FOREIGN KEY (`list_comment_seq`) REFERENCES `list_comment` (`list_comment_seq`),
  CONSTRAINT `report_lists_FK` FOREIGN KEY (`list_seq`) REFERENCES `lists` (`list_seq`),
  CONSTRAINT `report_messages_FK` FOREIGN KEY (`message_seq`) REFERENCES `messages` (`message_seq`),
  CONSTRAINT `report_penalty_FK` FOREIGN KEY (`penalty_seq`) REFERENCES `penalty` (`penalty_seq`),
  CONSTRAINT `report_post_FK` FOREIGN KEY (`post_seq`) REFERENCES `post` (`post_seq`),
  CONSTRAINT `report_post_comment_FK` FOREIGN KEY (`post_comment_seq`) REFERENCES `post_comment` (`post_comment_seq`),
  CONSTRAINT `report_review_FK` FOREIGN KEY (`review_seq`) REFERENCES `review` (`review_seq`),
  CONSTRAINT `report_users_FK` FOREIGN KEY (`reporter_user_seq`) REFERENCES `users` (`user_seq`),
  CONSTRAINT `report_users_FK_1` FOREIGN KEY (`reported_user_seq`) REFERENCES `users` (`user_seq`),
  CONSTRAINT `report_CK1` CHECK (`report_status` in ('wait','none','penalty'))
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='신고';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `report_reason`
--

DROP TABLE IF EXISTS `report_reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_reason` (
  `report_reason_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '사유별신고고유번호',
  `report_seq` bigint(20) DEFAULT NULL COMMENT '신고고유번호',
  `reason_seq` bigint(20) NOT NULL COMMENT '신고사유코드',
  PRIMARY KEY (`report_reason_seq`),
  KEY `report_reason_report_FK` (`report_seq`),
  KEY `report_reason_reasons_FK` (`reason_seq`),
  CONSTRAINT `report_reason_reasons_FK` FOREIGN KEY (`reason_seq`) REFERENCES `reasons` (`reason_seq`),
  CONSTRAINT `report_reason_report_FK` FOREIGN KEY (`report_seq`) REFERENCES `report` (`report_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='사유별신고';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary table structure for view `report_view`
--

DROP TABLE IF EXISTS `report_view`;
/*!50001 DROP VIEW IF EXISTS `report_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `report_view` AS SELECT
 1 AS `report_seq`,
  1 AS `category`,
  1 AS `seq`,
  1 AS `reporter_user_seq`,
  1 AS `reported_user_seq`,
  1 AS `report_content`,
  1 AS `penalty_seq`,
  1 AS `report_status`,
  1 AS `report_time`,
  1 AS `report_finished_time` */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant` (
  `restaurant_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '음식점고유번호',
  `restaurant_title` varchar(100) NOT NULL COMMENT '음식점이름',
  `restaurant_address` varchar(100) NOT NULL COMMENT '음식점주소',
  `restaurant_phone` varchar(50) DEFAULT NULL COMMENT '음식점전화번호',
  `restaurant_star` decimal(5,2) DEFAULT NULL COMMENT '음식점별점',
  PRIMARY KEY (`restaurant_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='음식점';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `review_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '맛집리뷰고유번호',
  `review_user_seq` bigint(20) NOT NULL COMMENT '회원고유번호',
  `restaurant_seq` bigint(20) NOT NULL COMMENT '음식점고유번호',
  `review_content` varchar(1000) NOT NULL COMMENT '맛집리뷰내용',
  `review_status` varchar(8) NOT NULL DEFAULT 'active' COMMENT '맛집리뷰상태',
  `review_created_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '맛집리뷰작성시간',
  `review_updated_time` datetime DEFAULT NULL COMMENT '맛집리뷰수정시간',
  `review_deleted_time` datetime DEFAULT NULL COMMENT '맛집리뷰삭제시간',
  `review_star` decimal(5,2) NOT NULL COMMENT '리뷰별점',
  PRIMARY KEY (`review_seq`),
  KEY `review_restaurant_FK` (`restaurant_seq`),
  KEY `review_users_FK` (`review_user_seq`),
  CONSTRAINT `review_restaurant_FK` FOREIGN KEY (`restaurant_seq`) REFERENCES `restaurant` (`restaurant_seq`),
  CONSTRAINT `review_users_FK` FOREIGN KEY (`review_user_seq`) REFERENCES `users` (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='맛집리뷰';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `review_image`
--

DROP TABLE IF EXISTS `review_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review_image` (
  `review_image_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '이미지고유번호',
  `review_seq` bigint(20) NOT NULL COMMENT '맛집리뷰고유번호',
  `review_image_path` varchar(100) NOT NULL COMMENT '이미지경로',
  PRIMARY KEY (`review_image_seq`),
  KEY `review_image_review_FK` (`review_seq`),
  CONSTRAINT `review_image_review_FK` FOREIGN KEY (`review_seq`) REFERENCES `review` (`review_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='맛집 리뷰 이미지';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `tag_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '태그고유번호',
  `tag_name` varchar(100) NOT NULL COMMENT '태그명',
  PRIMARY KEY (`tag_seq`),
  UNIQUE KEY `tag_UK` (`tag_name`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='태그';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_activity`
--

DROP TABLE IF EXISTS `user_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_activity` (
  `activity_user_seq` bigint(20) NOT NULL COMMENT '회원고유번호',
  `active_level_seq` bigint(20) NOT NULL COMMENT '활동등급고유번호',
  `activity_point` int(11) NOT NULL DEFAULT 0 COMMENT '활동포인트',
  `influencer_yn` char(1) NOT NULL DEFAULT 'N' COMMENT '인기회원여부',
  PRIMARY KEY (`activity_user_seq`),
  KEY `user_activity_active_level_FK` (`active_level_seq`),
  CONSTRAINT `user_activity_active_level_FK` FOREIGN KEY (`active_level_seq`) REFERENCES `active_level` (`active_level_seq`),
  CONSTRAINT `user_activity_users_FK` FOREIGN KEY (`activity_user_seq`) REFERENCES `users` (`user_seq`),
  CONSTRAINT `user_activity_CK` CHECK (`influencer_yn` in ('Y','N'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='회원활동';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_seq` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '회원고유번호',
  `user_email` varchar(50) NOT NULL COMMENT '이메일',
  `user_password` varchar(100) DEFAULT NULL COMMENT '비밀번호',
  `user_name` varchar(100) NOT NULL COMMENT '회원명',
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '휴대폰번호',
  `user_nickname` varchar(50) NOT NULL COMMENT '닉네임',
  `user_social_yn` char(1) NOT NULL DEFAULT 'N' COMMENT '소셜회원여부',
  `user_social_site` varchar(6) DEFAULT NULL COMMENT '소셜사이트',
  `social_token` varchar(300) DEFAULT NULL COMMENT '소셜토큰',
  `user_auth` varchar(6) NOT NULL DEFAULT 'user' COMMENT '권한',
  `penalty_yn` char(1) NOT NULL DEFAULT 'N' COMMENT '패널티여부',
  `business_verified_yn` char(1) DEFAULT 'N' COMMENT '사업자인증여부',
  `user_status` varchar(8) NOT NULL DEFAULT 'active' COMMENT '가입상태',
  `user_reg_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT '가입일',
  `user_delete_date` datetime DEFAULT NULL COMMENT '탈퇴일',
  `pw_reset_token` varchar(100) DEFAULT NULL COMMENT '비밀번호초기화토큰',
  `pw_token_due_time` datetime DEFAULT NULL COMMENT '토큰만료시간',
  PRIMARY KEY (`user_seq`),
  UNIQUE KEY `user_UK` (`user_nickname`),
  CONSTRAINT `user_CK` CHECK (`user_social_yn` in ('Y','N')),
  CONSTRAINT `user_CK1` CHECK (`user_social_site` in ('kakao','naver','google')),
  CONSTRAINT `user_CK2` CHECK (`business_verified_yn` in ('Y','N')),
  CONSTRAINT `user_CK3` CHECK (`user_auth` in ('user','admin')),
  CONSTRAINT `user_CK4` CHECK (`user_status` in ('active','inactive')),
  CONSTRAINT `user_CK5` CHECK (`penalty_yn` in ('Y','N'))
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='회원';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Final view structure for view `report_view`
--

/*!50001 DROP VIEW IF EXISTS `report_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `report_view` AS select `report`.`report_seq` AS `report_seq`,'post' AS `category`,`report`.`post_seq` AS `seq`,`report`.`reporter_user_seq` AS `reporter_user_seq`,`report`.`reported_user_seq` AS `reported_user_seq`,`report`.`report_content` AS `report_content`,`report`.`penalty_seq` AS `penalty_seq`,`report`.`report_status` AS `report_status`,`report`.`report_time` AS `report_time`,`report`.`report_finished_time` AS `report_finished_time` from `report` where `report`.`post_seq` is not null union all select `report`.`report_seq` AS `report_seq`,'post_comment' AS `category`,`report`.`post_comment_seq` AS `seq`,`report`.`reporter_user_seq` AS `reporter_user_seq`,`report`.`reported_user_seq` AS `reported_user_seq`,`report`.`report_content` AS `report_content`,`report`.`penalty_seq` AS `penalty_seq`,`report`.`report_status` AS `report_status`,`report`.`report_time` AS `report_time`,`report`.`report_finished_time` AS `report_finished_time` from `report` where `report`.`post_comment_seq` is not null union all select `report`.`report_seq` AS `report_seq`,'list' AS `category`,`report`.`list_seq` AS `seq`,`report`.`reporter_user_seq` AS `reporter_user_seq`,`report`.`reported_user_seq` AS `reported_user_seq`,`report`.`report_content` AS `report_content`,`report`.`penalty_seq` AS `penalty_seq`,`report`.`report_status` AS `report_status`,`report`.`report_time` AS `report_time`,`report`.`report_finished_time` AS `report_finished_time` from `report` where `report`.`list_seq` is not null union all select `report`.`report_seq` AS `report_seq`,'list_comment' AS `category`,`report`.`list_comment_seq` AS `seq`,`report`.`reporter_user_seq` AS `reporter_user_seq`,`report`.`reported_user_seq` AS `reported_user_seq`,`report`.`report_content` AS `report_content`,`report`.`penalty_seq` AS `penalty_seq`,`report`.`report_status` AS `report_status`,`report`.`report_time` AS `report_time`,`report`.`report_finished_time` AS `report_finished_time` from `report` where `report`.`list_comment_seq` is not null union all select `report`.`report_seq` AS `report_seq`,'review' AS `category`,`report`.`review_seq` AS `seq`,`report`.`reporter_user_seq` AS `reporter_user_seq`,`report`.`reported_user_seq` AS `reported_user_seq`,`report`.`report_content` AS `report_content`,`report`.`penalty_seq` AS `penalty_seq`,`report`.`report_status` AS `report_status`,`report`.`report_time` AS `report_time`,`report`.`report_finished_time` AS `report_finished_time` from `report` where `report`.`review_seq` is not null union all select `report`.`report_seq` AS `report_seq`,'message' AS `category`,`report`.`message_seq` AS `seq`,`report`.`reporter_user_seq` AS `reporter_user_seq`,`report`.`reported_user_seq` AS `reported_user_seq`,`report`.`report_content` AS `report_content`,`report`.`penalty_seq` AS `penalty_seq`,`report`.`report_status` AS `report_status`,`report`.`report_time` AS `report_time`,`report`.`report_finished_time` AS `report_finished_time` from `report` where `report`.`message_seq` is not null order by `report_time` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-04  9:30:29
