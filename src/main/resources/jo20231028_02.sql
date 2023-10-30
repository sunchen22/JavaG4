

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `advertisement`
--


DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `advertisement` (
  `advertisementID` int NOT NULL AUTO_INCREMENT,
  `dinerID` int DEFAULT NULL,
  `advertisementBlob` longblob,
  `advertisementName` varchar(50),
  `advertisementUpTime` datetime DEFAULT NULL,
  `advertisementDownTime` datetime DEFAULT NULL,
  `advertisementDuringTime` int DEFAULT NULL,
  `advertisementStatus` varchar(20) not null,
  PRIMARY KEY (`advertisementID`),
  KEY `dinerID` (`dinerID`),
  CONSTRAINT `advertisement_ibfk_1` FOREIGN KEY (`dinerID`) REFERENCES `dinerinfo` (`dinerID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
DELIMITER //
CREATE TRIGGER calculate_days
BEFORE INSERT ON advertisement FOR EACH ROW
BEGIN
  SET NEW.advertisementDuringTime = DATEDIFF(NEW.advertisementDownTime, NEW.advertisementUpTime);
END;
//
DELIMITER ;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
INSERT INTO `advertisement` (`advertisementID`, `dinerID`, `advertisementBlob`, `advertisementName`, `advertisementUpTime`, `advertisementDownTime`, `advertisementStatus`) 
VALUES 
(1,1,NULL,'特惠早餐活動','2023-09-09 00:00:00','2023-10-09 00:00:00','Submitted'),
(2,2,NULL,'冬季限定美食','2023-09-09 00:00:00','2023-10-09 00:00:00','Submitted'),
(3,3,NULL,'夏日冰飲下半價','2023-09-09 00:00:00','2023-10-09 00:00:00','Submitted'),
(4,4,NULL,'週末家庭套餐','2023-09-09 00:00:00','2023-10-09 00:00:00','Submitted'),
(5,5,NULL,'午夜宵夜特輯','2023-09-09 00:00:00','2023-10-09 00:00:00','Submitted');

/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buildinginfo`
--

DROP TABLE IF EXISTS `buildinginfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buildinginfo` (
  `buildingID` int NOT NULL AUTO_INCREMENT,
  `buildingName` varchar(15) DEFAULT NULL,
  `buildingAddress` varchar(30) DEFAULT NULL,
  `buildingStatus` int DEFAULT 1,
  PRIMARY KEY (`buildingID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buildinginfo`
--

LOCK TABLES `buildinginfo` WRITE;
/*!40000 ALTER TABLE `buildinginfo` DISABLE KEYS */;
INSERT INTO `buildinginfo` VALUES (1,'AA大樓','台北市松山區八德路二段346巷3弄2號',1),(2,'BB大樓','台北市松山區南京東路四段133巷4弄1號',1),(3,'CC大樓','台北市松山區八德路三段155巷1號',1),(4,'dd大樓','台北市松山區敦化南路1段5號3樓',1),(5,'ee大樓','台北市松山區長安東路二段215號',1);
/*!40000 ALTER TABLE `buildinginfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `businesshours`
--

DROP TABLE IF EXISTS `businesshours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `businesshours` (
  `dinerOpenHoursID` int NOT NULL AUTO_INCREMENT,
  `dinerID` int NOT NULL,
  `dayOfWeek` varchar(10),
  `openTime` time DEFAULT NULL,
  `closeTime` time DEFAULT NULL,
  `openStatus` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`dinerOpenHoursID`),
  UNIQUE KEY `unique_openDay` (`dinerID`,`dayOfWeek`),
  CONSTRAINT `businesshours_ibfk_1` FOREIGN KEY (`dinerID`) REFERENCES `dinerinfo` (`dinerID`),
  CONSTRAINT `businesshours_chk_1` CHECK ((`openStatus` in (_utf8mb4'open',_utf8mb4'closed'))),
  CONSTRAINT `CHECK_businessHours` CHECK ((`openTime` < `closeTime`)),
  CONSTRAINT `CHECK_closeTime` CHECK ((`closeTime` in (_utf8mb4'9:30:00',_utf8mb4'10:00:00',_utf8mb4'10:30:00',_utf8mb4'11:00:00',_utf8mb4'11:30:00',_utf8mb4'12:00:00',_utf8mb4'12:30:00',_utf8mb4'13:00:00',_utf8mb4'13:30:00',_utf8mb4'14:00:00',_utf8mb4'14:30:00',_utf8mb4'15:00:00',_utf8mb4'15:30:00',_utf8mb4'16:00:00',_utf8mb4'16:30:00',_utf8mb4'17:00:00',_utf8mb4'17:30:00',_utf8mb4'18:00:00',_utf8mb4'18:30:00',_utf8mb4'19:00:00',_utf8mb4'19:30:00',_utf8mb4'20:00:00',_utf8mb4'20:30:00',_utf8mb4'21:00:00'))),
  CONSTRAINT `CHECK_openTime` CHECK ((`openTime` in (_utf8mb4'9:00:00',_utf8mb4'9:30:00',_utf8mb4'10:00:00',_utf8mb4'10:30:00',_utf8mb4'11:00:00',_utf8mb4'11:30:00',_utf8mb4'12:00:00',_utf8mb4'12:30:00',_utf8mb4'13:00:00',_utf8mb4'13:30:00',_utf8mb4'14:00:00',_utf8mb4'14:30:00',_utf8mb4'15:00:00',_utf8mb4'15:30:00',_utf8mb4'16:00:00',_utf8mb4'16:30:00',_utf8mb4'17:00:00',_utf8mb4'17:30:00',_utf8mb4'18:00:00',_utf8mb4'18:30:00',_utf8mb4'19:00:00',_utf8mb4'19:30:00',_utf8mb4'20:00:00',_utf8mb4'20:30:00')))
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `businesshours`
--

LOCK TABLES `businesshours` WRITE;
/*!40000 ALTER TABLE `businesshours` DISABLE KEYS */;
INSERT INTO `businesshours` VALUES (1,1,'Monday','09:00:00','18:00:00','Open'),(2,1,'Tuesday','09:00:00','18:00:00','Open'),(3,1,'Wednesday','09:00:00','18:00:00','Open'),(4,1,'Thursday','09:00:00','18:00:00','Open'),(5,1,'Friday','09:00:00','18:00:00','Open'),(6,2,'Monday','09:00:00','19:00:00','Open'),(7,2,'Tuesday','09:00:00','19:00:00','Open'),(8,2,'Wednesday','09:00:00','19:00:00','Open'),(9,2,'Thursday','09:00:00','19:00:00','Open'),(10,2,'Friday','09:00:00','19:00:00','Open'),(11,3,'Monday','10:00:00','20:00:00','Open'),(12,3,'Tuesday','10:00:00','20:00:00','Open'),(13,3,'Wednesday','10:00:00','20:00:00','Open'),(14,3,'Thursday','10:00:00','20:00:00','Open'),(15,3,'Friday','10:00:00','20:00:00','Open'),(16,4,'Monday','11:00:00','21:00:00','Open'),(17,4,'Tuesday','11:00:00','21:00:00','Open'),(18,4,'Wednesday','11:00:00','21:00:00','Open'),(19,4,'Thursday','11:00:00','21:00:00','Open'),(20,4,'Friday','11:00:00','21:00:00','Open'),(21,5,'Monday','09:00:00','17:00:00','Open'),(22,5,'Tuesday','09:00:00','17:00:00','Open'),(23,5,'Wednesday','09:00:00','17:00:00','Open'),(24,5,'Thursday','09:00:00','17:00:00','Open'),(25,5,'Friday','09:00:00','17:00:00','Open');
/*!40000 ALTER TABLE `businesshours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentlike`
--

DROP TABLE IF EXISTS `commentlike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commentlike` (
  `commentLikeID` int NOT NULL AUTO_INCREMENT,
  `commentID` int NOT NULL,
  `userID` int NOT NULL,
  `likeCount` int NOT NULL,
  PRIMARY KEY (`commentLikeID`),
  UNIQUE KEY `commentID` (`commentID`,`userID`),
  KEY `userID` (`userID`),
  CONSTRAINT `commentlike_ibfk_1` FOREIGN KEY (`commentID`) REFERENCES `dinerratingcomment` (`commentID`),
  CONSTRAINT `commentlike_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `userinfo` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentlike`
--

LOCK TABLES `commentlike` WRITE;
/*!40000 ALTER TABLE `commentlike` DISABLE KEYS */;
INSERT INTO `commentlike` VALUES (1,1,1,1),(2,2,2,1),(3,3,1,0),(4,3,3,1),(5,4,4,1);
/*!40000 ALTER TABLE `commentlike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumerform`
--

DROP TABLE IF EXISTS `consumerform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumerform` (
  `consumerFormID` int NOT NULL AUTO_INCREMENT,
  `userID` int DEFAULT NULL,
  `consumerFormTime` datetime NOT NULL,
  `consumerFormType` varchar(10) NOT NULL,
  `consumerFormMail` varchar(50) NOT NULL,
  `consumerFormPhone` varchar(10) NOT NULL,
  `consumerFormContent` longtext NOT NULL,
  `consumerFormReplyStatus` varchar(10) NOT NULL,
  `consumerFormReplyContent` longtext,
  `consumerFormReplyTime` datetime DEFAULT NULL,
  `empID` int DEFAULT NULL,
  PRIMARY KEY (`consumerFormID`),
  KEY `userID` (`userID`),
  KEY `empID` (`empID`),
  CONSTRAINT `consumerform_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `userinfo` (`userID`),
  CONSTRAINT `consumerform_ibfk_2` FOREIGN KEY (`empID`) REFERENCES `webempadmin` (`empID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumerform`
--

LOCK TABLES `consumerform` WRITE;
/*!40000 ALTER TABLE `consumerform` DISABLE KEYS */;
INSERT INTO `consumerform` VALUES (1,1,'2023-09-08 10:00:00','餐點品質','user1@email.com','1234567890','餐點品質有待改善，請協助改進。','未回覆',NULL,NULL,2),(2,2,'2023-09-07 14:30:00','建議','ususerinfouserinfoer2@email.com','9876543210','我有一個關於網站的建議，請聽聽。','已回覆','感謝您的建議，我們會考慮。','2023-09-07 15:45:00',2),(3,1,'2023-09-06 09:15:00','其他意見','user3@email.com','1112223333','我有一些其他意見，希望能分享給您。','已回覆','您的問題已解答，請參考回覆內容。','2023-09-06 10:00:00',4),(4,4,'2023-09-05 16:20:00','付款問題','user3@email.com','5556667777','我在付款時遇到問題，請幫助解決。','未回覆',NULL,NULL,2),(5,1,'2023-09-04 11:45:00','未收到商品','user5@email.com','7778889999','我訂購的商品還未送達，請查明原因。','已回覆','我們將儘快處理您的訂單，請稍後。','2023-09-04 12:30:00',1);
/*!40000 ALTER TABLE `consumerform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dinerform`
--

DROP TABLE IF EXISTS `dinerform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dinerform` (
  `dinerFormID` int NOT NULL AUTO_INCREMENT,
  `dinerID` int NOT NULL,
  `dinerFormTime` datetime NOT NULL,
  `dinerFormType` varchar(10) NOT NULL,
  `dinerFormContent` longtext NOT NULL,
  `dinerFormReplyStatus` varchar(10) NOT NULL,
  `dinerFormReplyContent` longtext,
  `dinerFormReplyTime` datetime DEFAULT NULL,
  `empID` int DEFAULT NULL,
  PRIMARY KEY (`dinerFormID`),
  KEY `dinerID` (`dinerID`),
  KEY `empID` (`empID`),
  CONSTRAINT `dinerform_ibfk_1` FOREIGN KEY (`dinerID`) REFERENCES `dinerinfo` (`dinerID`),
  CONSTRAINT `dinerform_ibfk_2` FOREIGN KEY (`empID`) REFERENCES `webempadmin` (`empID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dinerform`
--

LOCK TABLES `dinerform` WRITE;
/*!40000 ALTER TABLE `dinerform` DISABLE KEYS */;
INSERT INTO `dinerform` VALUES (1,5,'2023-09-08 10:00:00','訂單問題','我的訂單出現了問題，請協助處理。','未回覆',NULL,NULL,2),(2,3,'2023-09-07 14:30:00','帳號與密碼問題','我無法登錄我的帳號，請幫我恢復訪問權限。','已完成','我們已經重置您的密碼，請嘗試重新登錄。','2023-09-07 15:45:00',3),(3,3,'2023-09-06 09:15:00','款項問題','我付款時遇到問題，請確認付款狀態。','未回覆',NULL,NULL,4),(4,2,'2023-09-05 16:20:00','操作問題','我在使用應用程式時遇到了一些操作問題，請指導。','已完成','我們已提供操作指導，請查收您的郵件。','2023-09-05 17:00:00',5),(5,5,'2023-09-04 11:45:00','其他問題','我有一些其他問題和建議，希望提供回饋。','未回覆',NULL,NULL,1);
/*!40000 ALTER TABLE `dinerform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dinerinfo`
--

DROP TABLE IF EXISTS `dinerinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dinerinfo` (
  `dinerID` int NOT NULL AUTO_INCREMENT,
  `dinerName` varchar(50) NOT NULL,
  `dinerPassword` varchar(15) DEFAULT NULL,
  `dinerRegisterTime` datetime DEFAULT NULL,
  `dinerTaxID` varchar(8) NOT NULL,
  `dinerContact` varchar(30) NOT NULL,
  `dinerPhone` varchar(10) NOT NULL,
  `dinerEmail` varchar(30) NOT NULL,
  `dinerAddress` varchar(30) NOT NULL,
  `dinerBank` varchar(3) NOT NULL,
  `dinerAccount` varchar(16) NOT NULL,
  `dinerAccountName` varchar(30) NOT NULL,
  `dinerType` varchar(1) NOT NULL,
  `dinerStatus` varchar(15) DEFAULT 'Submitted',
  `dinerOrderThreshold` int DEFAULT NULL,
  `dinerBlob` longblob,
  `dinerUpdate` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`dinerID`),
  UNIQUE KEY `unique_TaxID` (`dinerTaxID`),
  UNIQUE KEY `unique_Phone` (`dinerPhone`),
  UNIQUE KEY `unique_Email` (`dinerEmail`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dinerinfo`
--

LOCK TABLES `dinerinfo` WRITE;
/*!40000 ALTER TABLE `dinerinfo` DISABLE KEYS */;
INSERT INTO dinerinfo (dinerID, dinerName, dinerPassword, dinerRegisterTime, dinerTaxID, dinerContact, dinerPhone, dinerEmail, dinerAddress, dinerBank, dinerAccount, dinerAccountName, dinerType, dinerStatus, dinerOrderThreshold)
VALUES 
(1, '美味小館', 'Passw_01', '2023-09-03 12:34:56', '12345670', '王大明', '0912333678', 'windgofast999@gmail.com', '台北市松山區南京東路3段337號', '100', '123456789012', '王大明', 'M', 'Active', 100),
(2, '鮮味屋', 'Passw_02', '2023-09-07 14:56:23', '77777789', '李小芳', '0923456781', 'xianwei@example.com', '台北市松山區南京東路3段333號', '200', '234567890123', '李小芳', 'X', 'Active', 200),
(3, '滋味坊', 'Passw_03', '2023-09-13 10:12:45', '29384765', '陳志強', '0934567892', 'ziwei@example.com', '台北市松山區南京東路3段319號', '101', '345678901234', '陳志強', 'M', 'Active', 300),
(4, '珍煮樓', 'Passw_04', '2023-09-20 15:25:36', '88883726', '張麗君', '0945678904', 'xiangchun@example.com', '台北市松山區南京東路3段311號', '300', '456789012345', '張麗君', 'D', 'Active', 400),
(5, 'Happy Hours', 'Passw_05', '2023-09-28 16:47:58', '20398876', '楊建中', '0956789016', 'weidao@example.com', '台北市松山區南京東路3段307號', '123', '567890123456', '楊建中', 'X', 'Active', 500);
/*!40000 ALTER TABLE `dinerinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dinernews`
--

DROP TABLE IF EXISTS `dinernews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dinernews` (
  `dinerNewsID` int NOT NULL AUTO_INCREMENT,
  `dinerNewsContent1` varchar(100) DEFAULT NULL,
  `dinerNewsContent2` varchar(100) DEFAULT NULL,
  `dinerNewsContent3` varchar(100) DEFAULT NULL,
  `empID` int DEFAULT NULL,
  `dinerNewsReleaseTime` date not NULL,
  `dinerNewsReviseTime` date not NULL,
  `dinerNewsStatus` int not null,
  PRIMARY KEY (`dinerNewsID`),
  KEY `empID` (`empID`),
  CONSTRAINT `dinernews_ibfk_1` FOREIGN KEY (`empID`) REFERENCES `webempadmin` (`empID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dinernews`
--

LOCK TABLES `dinernews` WRITE;
/*!40000 ALTER TABLE `dinernews` DISABLE KEYS */;
INSERT INTO `dinernews` VALUES (1,'這是第一筆假資料','這是第一筆假資料','這是第一筆假資料',1,'2023-09-09','2023-10-09',1),(2,'這是第二筆假資料','這是第二筆假資料','這是第二筆假資料',2,'2023-09-09','2023-10-09',1),(3,'這是第三筆假資料','這是第三筆假資料','這是第三筆假資料',3,'2023-09-09','2023-10-09',1),(4,'這是第四筆假資料','這是第四筆假資料','這是第四筆假資料',4,'2023-09-09','2023-10-09',1),(5,'這是第五筆假資料','這是第五筆假資料','這是第五筆假資料',5,'2023-09-09','2023-10-09',1);
/*!40000 ALTER TABLE `dinernews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dinerratingcomment`
--

DROP TABLE IF EXISTS `dinerratingcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dinerratingcomment` (
  `commentID` int NOT NULL AUTO_INCREMENT,
  `dinerID` int NOT NULL,
  `userID` int NOT NULL,
  `dinerRating` int NOT NULL,
  `userCommentContent` varchar(100) DEFAULT NULL,
  `userCommentTime` datetime NOT NULL,
  `dinerReplyContent` varchar(100) DEFAULT NULL,
  `dinerReplyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`commentID`),
  KEY `userID` (`userID`),
  CONSTRAINT `dinerratingcomment_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `userinfo` (`userID`),
  CONSTRAINT `dinerratingcomment_ibfk_2` FOREIGN KEY (`dinerID`) REFERENCES `dinerinfo` (`dinerID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dinerratingcomment`
--

LOCK TABLES `dinerratingcomment` WRITE;
/*!40000 ALTER TABLE `dinerratingcomment` DISABLE KEYS */;
INSERT INTO `dinerratingcomment` VALUES (1,1,1,4,'口感還不錯！','2023-05-15 13:45:00','感謝您的正面評價！','2023-05-15 14:00:00'),(2,2,2,5,'真的很好吃！','2023-04-12 14:30:00','很高興您喜歡我們的食物！','2023-04-12 15:00:00'),(3,3,3,3,'普普通通，還可以接受。','2023-05-01 16:20:00','感謝您的建議，我們會繼續努力改進。','2023-05-01 17:00:00'),(4,4,4,2,'不太合我的口味。','2023-03-20 18:10:00','很抱歉您這次的體驗不佳，我們會注意。','2023-03-20 18:30:00'),(5,5,5,4,'份量足夠，味道也不錯！','2023-04-25 12:05:00','感謝您的支持！','2023-04-25 12:30:00');
/*!40000 ALTER TABLE `dinerratingcomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite` (
  `favoriteID` int NOT NULL AUTO_INCREMENT,
  `userID` int NOT NULL,
  `dinerID` int NOT NULL,
  `favoriteTime` datetime NOT NULL,
  PRIMARY KEY (`favoriteID`),
  UNIQUE KEY `userID` (`userID`,`dinerID`),
  KEY `dinerID` (`dinerID`),
  CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `userinfo` (`userID`),
  CONSTRAINT `favorite_ibfk_2` FOREIGN KEY (`dinerID`) REFERENCES `dinerinfo` (`dinerID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
INSERT INTO `favorite` VALUES (1,1,1,'2023-06-15 13:45:00'),(2,1,3,'2023-06-16 09:30:00'),(3,2,1,'2023-06-16 10:20:00'),(4,1,2,'2023-06-17 12:10:00'),(5,2,4,'2023-06-17 15:05:00');
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grouporder`
--

DROP TABLE IF EXISTS `grouporder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grouporder` (
  `groupOrderID` int NOT NULL AUTO_INCREMENT,
  `dinerID` int NOT NULL,
  `buildingID` int NOT NULL,
  `orderStatus` varchar(3) NOT NULL,
  `groupOrderCreateTime` datetime NOT NULL,
  `groupOrderSubmitTime` datetime NOT NULL,
  `holderID` int NOT NULL,
  `groupTotalPrice` int DEFAULT NULL,
  `deliveredBlob` longblob,
  PRIMARY KEY (`groupOrderID`),
  KEY `GroupOrder_dinerID_FK` (`dinerID`),
  KEY `GroupOrder_buildingID_FK` (`buildingID`),
  KEY `GroupOrder_holderID_FK` (`holderID`),
  CONSTRAINT `GroupOrder_buildingID_FK` FOREIGN KEY (`buildingID`) REFERENCES `buildinginfo` (`buildingID`),
  CONSTRAINT `GroupOrder_dinerID_FK` FOREIGN KEY (`dinerID`) REFERENCES `dinerinfo` (`dinerID`),
  CONSTRAINT `GroupOrder_holderID_FK` FOREIGN KEY (`holderID`) REFERENCES `userinfo` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grouporder`
--

LOCK TABLES `grouporder` WRITE;
/*!40000 ALTER TABLE `grouporder` DISABLE KEYS */;
INSERT INTO `grouporder` VALUES (1,1,2,'1','2023-10-08 09:01:00','2023-10-08 11:00:00',3,90,NULL),(2,2,1,'2','2023-10-08 09:09:00','2023-10-08 11:30:00',2,70,NULL),(3,3,5,'3','2023-10-08 10:15:00','2023-10-08 12:00:00',1,100,NULL),(4,4,5,'5','2023-10-09 09:34:00','2023-10-09 11:30:00',4,60,NULL),(5,5,4,'7','2023-10-09 10:47:00','2023-10-09 12:30:00',5,40,NULL);
/*!40000 ALTER TABLE `grouporder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productID` int NOT NULL AUTO_INCREMENT,
  `dinerID` int NOT NULL,
  `productName` varchar(15) NOT NULL,
  `productPrice` int NOT NULL,
  `productTypeID` int NOT NULL,
  `productDailyStock` int NOT NULL,
  `productReleaseTime` datetime NOT NULL,
  `productBlob1` longblob,
  `productBlob2` longblob,
  `productBlob3` longblob,
  `productRemark` varchar(500) DEFAULT NULL,
  `productStatus` varchar(5) NOT NULL DEFAULT '上架中',
  PRIMARY KEY (`productID`),
  UNIQUE KEY `unique_productName` (`dinerID`,`productName`),
  KEY `productTypeID` (`productTypeID`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`productTypeID`) REFERENCES `producttype` (`productTypeID`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`dinerID`) REFERENCES `dinerinfo` (`dinerID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES 
(1,1,'滷肉飯',70,1,100,'2023-09-12 16:18:20',null,null,null,'香醇豐滿，絲絲入扣的滷肉，濃郁滋味。','上架中'),
(2,1,'陽春麵',60,2,110,'2023-09-12 16:18:20',null,null,null,'簡單清爽，飽滿口感。','上架中'),
(3,1,'雞腿飯',100,1,120,'2023-09-12 16:18:20',null,null,null,'鮮嫩多汁的雞腿，搭配香飯，一口難忘。','上架中'),
(4,1,'珍珠奶茶',60,5,130,'2023-09-12 16:18:20',null,null,null,'黑糖珍珠，奶茶細膩，甜美滋味交融。','上架中'),
(5,1,'綠茶',40,5,140,'2023-09-12 16:18:20',null,null,null,'清新宜人，天然抗氧，回甘持久。','上架中'),
(6,2,'牛肉麵',80,2,100,'2023-09-12 16:18:20',null,null,null,'濃郁牛肉湯底，美味十足。','上架中'),
(7,2,'蛋炒飯',65,1,110,'2023-09-12 16:18:20',null,null,null,'香氣四溢，簡單好吃。','上架中'),
(8,2,'炸雞翅',50,3,120,'2023-09-12 16:18:20',null,null,null,'酥脆外皮，多汁炸雞翅，下飯美味。','上架中'),
(9,2,'柳橙汁',30,5,130,'2023-09-12 16:18:20',null,null,null,'新鮮柳橙，清涼果汁，消暑首選。','上架中'),
(10,3,'炸魷魚',75,3,100,'2023-09-12 16:18:20',null,null,null,'鮮嫩魷魚，香氣四溢。','上架中'),
(11,3,'炸雞排',55,3,110,'2023-09-12 16:18:20',null,null,null,'香脆炸雞，下飯美味。','上架中'),
(12,3,'草莓冰淇淋',40,4,120,'2023-09-12 16:18:20',null,null,null,'新鮮草莓，甜蜜享受。','上架中'),
(13,3,'檸檬汁',35,5,130,'2023-09-12 16:18:20',null,null,null,'清涼果汁，消暑首選。','上架中'),
(14,4,'拉麵',70,2,110,'2023-09-12 16:18:20',null,null,null,'麵條彈牙，湯底濃郁，滿足味蕾。','上架中'),
(15,4,'炸魚柳',55,3,120,'2023-09-12 16:18:20',null,null,null,'酥脆魚柳，下飯好選擇。','上架中'),
(16,4,'草莓奶昔',35,5,130,'2023-09-12 16:18:20',null,null,null,'新鮮草莓，濃郁奶昔，冰涼享受。','上架中'),
(17,5,'奶茶',30,5,100,'2023-09-12 16:18:20',null,null,null,'香濃牛奶，茶香四溢，下午茶首選。','上架中'),
(18,5,'巧克力布朗尼',50,4,130,'2023-09-12 16:18:20',null,null,null,'濃郁巧克力，濕潤布朗尼，甜蜜誘惑。','上架中'),
(19,5,'蘋果派',45,4,100,'2023-09-12 16:18:20',null,null,null,'蘋果香氣，金黃酥皮，經典甜點。','上架中'),
(20,5,'柳橙汁',30,5,110,'2023-09-12 16:18:20',null,null,null,'新鮮橙汁，清爽消暑。','上架中');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producttype`
--

DROP TABLE IF EXISTS `producttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producttype` (
  `productTypeID` int NOT NULL AUTO_INCREMENT,
  `productTypeDes` varchar(5) NOT NULL,
  PRIMARY KEY (`productTypeID`),
  UNIQUE KEY `productTypeDes` (`productTypeDes`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producttype`
--

LOCK TABLES `producttype` WRITE;
/*!40000 ALTER TABLE `producttype` DISABLE KEYS */;
INSERT INTO `producttype` VALUES (1,'飯類'),(2,'麵類'),(3,'炸物'),(4,'甜點'),(5,'飲料');
/*!40000 ALTER TABLE `producttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productvary`
--

DROP TABLE IF EXISTS `productvary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productvary` (
  `productVaryID` int NOT NULL AUTO_INCREMENT,
  `productID` int NOT NULL,
  `productVaryDes` varchar(5) NOT NULL,
  `productVaryPrice` int NOT NULL,
  `varyTypeID` int NOT NULL,
  PRIMARY KEY (`productVaryID`),
  UNIQUE KEY `unique_productVaryDes` (`productID`,`productVaryDes`),
  KEY `varyTypeID` (`varyTypeID`),
  CONSTRAINT `productvary_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`),
  CONSTRAINT `productvary_ibfk_2` FOREIGN KEY (`varyTypeID`) REFERENCES `varytype` (`varyTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productvary`
--

LOCK TABLES `productvary` WRITE;
/*!40000 ALTER TABLE `productvary` DISABLE KEYS */;
INSERT INTO `productvary` VALUES (1,1,'小份',0,1),(2,1,'大份',10,1),(3,1,'加滷蛋',10,7),(4,1,'無',0,7),(5,2,'小份',0,1),
(6,2,'大份',10,1),(7,3,'無',0,7),(8,3,'加荷包蛋',15,7),(9,4,'去冰',0,5),(10,4,'少冰',0,5),
(11,4,'正常冰',0,5),(12,4,'溫',0,5),(13,4,'半糖',0,6),(14,4,'正常糖',0,6),(15,4,'無糖',0,6),
(16,5,'去冰',0,5),(17,5,'少冰',0,5),(18,5,'正常冰',0,5),(19,5,'溫',0,5),(20,5,'半糖',0,6),
(21,5,'正常糖',0,6),(22,5,'無糖',0,6),(23,6,'無',0,7),(24,6,'加酸菜',0,7),(25,7,'無',0,7),
(26,7,'加火腿',5,7),(27,9,'無糖',0,6),(28,9,'正常糖',0,6),(29,10,'不辣',0,6),(30,10,'辣',0,6),
(31,11,'不辣',0,4),(32,11,'辣',0,4),(33,13,'無',0,4),(34,13,'加蜂蜜',5,4),(35,14,'無',0,7),
(36,14,'加叉燒',10,7),(37,15,'小份',10,1),(38,15,'大份',15,1),(39,17,'無',10,7),(40,17,'加珍珠',5,7);
/*!40000 ALTER TABLE `productvary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userfaq`
--

DROP TABLE IF EXISTS `userfaq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userfaq` (
  `userFAQID` int NOT NULL AUTO_INCREMENT,
  `userFAQTitle` varchar(50) NOT NULL,
  `userFAQContent` longtext NOT NULL,
  `userFAQReleaseTime` datetime NOT NULL,
  `userFAQReviseTime` datetime DEFAULT NULL,
  `empID` int NOT NULL,
  PRIMARY KEY (`userFAQID`),
  UNIQUE KEY `userFAQTitle` (`userFAQTitle`),
  KEY `empID` (`empID`),
  CONSTRAINT `userfaq_ibfk_1` FOREIGN KEY (`empID`) REFERENCES `webempadmin` (`empID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userfaq`
--

LOCK TABLES `userfaq` WRITE;
/*!40000 ALTER TABLE `userfaq` DISABLE KEYS */;
INSERT INTO `userfaq` VALUES (1,'常見問題1','這是第一個常見問題的內容。','2023-09-08 10:00:00',NULL,1),(2,'常見問題2','這是第二個常見問題的內容。','2023-09-07 14:30:00','2023-09-07 15:45:00',5),(3,'常見問題3','這是第三個常見問題的內容。','2023-09-06 09:15:00',NULL,5),(4,'常見問題4','這是第四個常見問題的內容。','2023-09-05 16:20:00',NULL,5),(5,'常見問題5','這是第五個常見問題的內容。','2023-09-04 11:45:00','2023-09-04 12:30:00',2);
/*!40000 ALTER TABLE `userfaq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userinfo` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userAccount` varchar(50) NOT NULL,
  `userPassword` varchar(15) NOT NULL,
  `userPhone` varchar(15) NOT NULL,
  `userName` varchar(15) NOT NULL,
  `userRegisterTime` datetime NOT NULL,
  `userNickName` varchar(15) DEFAULT NULL,
  `buildingID` int DEFAULT NULL,
  `userBirthday` date NOT NULL,
  `userBlob` longblob,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userAccount` (`userAccount`),
  KEY `buildingID` (`buildingID`),
  CONSTRAINT `userinfo_ibfk_1` FOREIGN KEY (`buildingID`) REFERENCES `buildinginfo` (`buildingID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (1,'jumbooo0307@gmail.com','Password1','0934567891','SUNSUN','2023-01-01 01:13:37','nickname1',1,'1911-01-01',NULL),(2,'girlnofear@gmail.com','123','0934567892','白雅禎','2023-02-02 02:13:37','Jane',2,'1922-02-02',NULL),(3,'m80s8j10@gmail.com','123','0934567893','剪子臻','2023-03-07 22:13:37','子臻',1,'1933-03-03',NULL),(4,'kindofyouknow1775@gmail.com','Password4','0934567894','APPLE','2023-04-07 22:13:37','nickname4',2,'1944-04-04',NULL),(5,'pkm08960@gmail.com','Password5','0934567895','憲崇','2023-05-07 22:13:37','nickname5',3,'1955-05-05',NULL),(6,'pig@yahoo.com.tw','Password2','0934567892','Name6','2023-02-02 02:13:37','nickname6',2,'1922-02-02',NULL);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usernews`
--

DROP TABLE IF EXISTS `usernews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usernews` (
  `userNewsID` int NOT NULL AUTO_INCREMENT,
  `userNewsContent` varchar(100) NOT NULL,
  `empID` int NOT NULL,
  `userNewsReleaseTime` datetime DEFAULT NULL,
  `userNewsReviseTime` datetime DEFAULT NULL,
  `userNewsStatus` int NOT NULL default '0',
  PRIMARY KEY (`userNewsID`),
  UNIQUE KEY `userNewsContent` (`userNewsContent`),
  KEY `empID` (`empID`),
  CONSTRAINT `usernews_ibfk_1` FOREIGN KEY (`empID`) REFERENCES `webempadmin` (`empID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usernews`
--

LOCK TABLES `usernews` WRITE;
/*!40000 ALTER TABLE `usernews` DISABLE KEYS */;
INSERT INTO `usernews` VALUES (1,'這是第一篇用戶新聞',1,'2023-09-08 10:00:00','2023-09-08 10:00:00',0),(2,'這是第二篇用戶新聞',2,'2023-09-07 14:30:00','2023-09-07 15:45:00',1),(3,'這是第三篇用戶新聞',1,'2023-09-06 09:15:00','2023-09-06 09:15:00',2),(4,'這是第四篇用戶新聞',1,'2023-09-05 16:20:00','2023-09-06 09:15:00',0),(5,'這是第五篇用戶新聞',5,'2023-09-04 11:45:00','2023-09-04 12:30:00',1),(6,'這是第6篇用戶新聞',1,'2023-09-06 09:15:00','2023-09-06 09:15:00',1),(7,'這是第7篇用戶新聞',1,'2023-09-05 16:20:00','2023-09-06 09:15:00',0);
/*!40000 ALTER TABLE `usernews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userorderdetail`
--

DROP TABLE IF EXISTS `userorderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userorderdetail` (
  `userOrderItemID` int NOT NULL AUTO_INCREMENT,
  `productID` int NOT NULL,
  `productQuantity` int NOT NULL,
  `userItemPrice` int DEFAULT NULL,
  `userID` int NOT NULL,
  `groupOrderID` int NOT NULL,
  `userPaymentTime` datetime NOT NULL,
  PRIMARY KEY (`userOrderItemID`),
  KEY `UserOrderDetail_productID_FK` (`productID`),
  KEY `UserOrderDetail_userID_FK` (`userID`),
  KEY `UserOrderDetail_groupOrderID_FK` (`groupOrderID`),
  CONSTRAINT `UserOrderDetail_groupOrderID_FK` FOREIGN KEY (`groupOrderID`) REFERENCES `grouporder` (`groupOrderID`),
  CONSTRAINT `UserOrderDetail_productID_FK` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`),
  CONSTRAINT `UserOrderDetail_userID_FK` FOREIGN KEY (`userID`) REFERENCES `userinfo` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userorderdetail`
--

LOCK TABLES `userorderdetail` WRITE;
/*!40000 ALTER TABLE `userorderdetail` DISABLE KEYS */;
INSERT INTO `userorderdetail` VALUES (1,1,1,90,3,1,'2023-10-08 10:23:00'),(2,2,1,70,2,2,'2023-10-08 11:13:00'),(3,3,1,100,1,3,'2023-10-08 11:07:00'),(4,4,1,60,4,4,'2023-10-09 10:11:00'),(5,5,1,40,5,5,'2023-10-09 11:13:00');
/*!40000 ALTER TABLE `userorderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userorderdetailvary`
--

DROP TABLE IF EXISTS `userorderdetailvary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userorderdetailvary` (
  `userOrderDetailVaryID` int NOT NULL AUTO_INCREMENT,
  `userOrderItemID` int NOT NULL,
  `productVaryID1` int NOT NULL,
  `productVaryID2` int DEFAULT NULL,
  `productVaryID3` int DEFAULT NULL,
  `productVaryID4` int DEFAULT NULL,
  PRIMARY KEY (`userOrderDetailVaryID`),
  KEY `userOrderDetailVary_userOrderItemID_FK` (`userOrderItemID`),
  KEY `userOrderDetailVary_productVaryID1_FK` (`productVaryID1`),
  KEY `userOrderDetailVary_productVaryID2_FK` (`productVaryID2`),
  KEY `userOrderDetailVary_productVaryID3_FK` (`productVaryID3`),
  KEY `userOrderDetailVary_productVaryID4_FK` (`productVaryID4`),
  CONSTRAINT `userOrderDetailVary_productVaryID1_FK` FOREIGN KEY (`productVaryID1`) REFERENCES `productvary` (`productVaryID`),
  CONSTRAINT `userOrderDetailVary_productVaryID2_FK` FOREIGN KEY (`productVaryID2`) REFERENCES `productvary` (`productVaryID`),
  CONSTRAINT `userOrderDetailVary_productVaryID3_FK` FOREIGN KEY (`productVaryID3`) REFERENCES `productvary` (`productVaryID`),
  CONSTRAINT `userOrderDetailVary_productVaryID4_FK` FOREIGN KEY (`productVaryID4`) REFERENCES `productvary` (`productVaryID`),
  CONSTRAINT `userOrderDetailVary_userOrderItemID_FK` FOREIGN KEY (`userOrderItemID`) REFERENCES `userorderdetail` (`userOrderItemID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userorderdetailvary`
--

LOCK TABLES `userorderdetailvary` WRITE;
/*!40000 ALTER TABLE `userorderdetailvary` DISABLE KEYS */;
INSERT INTO `userorderdetailvary` VALUES (1,1,2,3,null,null),(2,2,6,null,null,null),(3,3,null,null,null,null),(4,4,9,13,null,null), (5,5,17,21,null,null);
/*!40000 ALTER TABLE `userorderdetailvary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `varytype`
--

DROP TABLE IF EXISTS `varytype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `varytype` (
  `varyTypeID` int NOT NULL AUTO_INCREMENT,
  `varyType` varchar(5) NOT NULL,
  PRIMARY KEY (`varyTypeID`),
  UNIQUE KEY `varyType` (`varyType`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `varytype`
--

LOCK TABLES `varytype` WRITE;
/*!40000 ALTER TABLE `varytype` DISABLE KEYS */;
INSERT INTO `varytype` VALUES (1,'份量'),(2,'加飯'),(3,'加麵'),(4,'加辣'),(5,'冰塊'),(6,'甜度'),(7,'加料');
/*!40000 ALTER TABLE `varytype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `webempadmin`
--

DROP TABLE IF EXISTS `webempadmin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `webempadmin` (
  `empID` int NOT NULL AUTO_INCREMENT,
  `empName` varchar(100) NOT NULL,
  `empPassword` varchar(100) NOT NULL,
  `empArriveDate` date NOT NULL,
  `empAdminAuthorization` varchar(100) NOT NULL,
  `empBlob` LONGBLOB,
  `empStatus` tinyint(1) NOT NULL default '0',
  PRIMARY KEY (`empID`),
  UNIQUE KEY `empName` (`empName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `webempadmin`
--

LOCK TABLES `webempadmin` WRITE;
/*!40000 ALTER TABLE `webempadmin` DISABLE KEYS */;
INSERT INTO `webempadmin` VALUES (1,'abab','1234','2023-09-08','Admin', NULL, 0),(2,'Jane Smith','pw0202','2023-09-07','Admin', NULL, 0),(3,'Bob Johnson','pw0303','2023-09-06','manager', NULL, 0),(4,'Alice Brown','pw0404','2023-09-05','manager', NULL, 0),(5,'Eva Wilson','pw0505','2023-09-04','Admin', NULL, 1);
/*!40000 ALTER TABLE `webempadmin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
