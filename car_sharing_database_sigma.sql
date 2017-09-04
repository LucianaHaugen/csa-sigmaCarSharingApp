-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: sigma
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_bookings`
--

DROP TABLE IF EXISTS `tbl_bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_bookings` (
  `bookingId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `reg` varchar(40) DEFAULT NULL,
  `time_of_booking` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `starting_date` date DEFAULT NULL,
  `starting_time` time DEFAULT NULL,
  `ending_date` date DEFAULT NULL,
  `ending_time` time DEFAULT NULL,
  `errand` varchar(255) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookingId`),
  KEY `fk_user_booking` (`userId`),
  CONSTRAINT `fk_user_booking` FOREIGN KEY (`userId`) REFERENCES `tbl_users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_bookings`
--

LOCK TABLES `tbl_bookings` WRITE;
/*!40000 ALTER TABLE `tbl_bookings` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_users`
--

DROP TABLE IF EXISTS `tbl_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) DEFAULT NULL,
  `approved` tinyint(1) DEFAULT NULL,
  `total_bookings_year` int(11) DEFAULT NULL,
  `total_distance_year` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_users`
--

LOCK TABLES `tbl_users` WRITE;
/*!40000 ALTER TABLE `tbl_users` DISABLE KEYS */;
INSERT INTO `tbl_users` VALUES (1,'luh',1,0,0),(2,'nig',0,0,0),(3,'maj',1,1,200),(4,'roå',1,5,300),(5,'jem',1,1,500);
/*!40000 ALTER TABLE `tbl_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_vehicles`
--

DROP TABLE IF EXISTS `tbl_vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_vehicles` (
  `vehicleId` int(11) NOT NULL AUTO_INCREMENT,
  `reg` varchar(40) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `mileage` int(11) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `equipment` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `fuel` varchar(255) DEFAULT NULL,
  `site` varchar(255) DEFAULT NULL,
  `responsible` varchar(255) DEFAULT NULL,
  `vehicle_bild` varbinary(8000) DEFAULT NULL,
  PRIMARY KEY (`vehicleId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_vehicles`
--

LOCK TABLES `tbl_vehicles` WRITE;
/*!40000 ALTER TABLE `tbl_vehicles` DISABLE KEYS */;
INSERT INTO `tbl_vehicles` VALUES (1,'LFC 860',NULL,NULL,'Station wagon','GPS-tracked','Volvo V60 DRIVe','Diesel','Gbg','Christian Björkman',NULL),(2,'ERF 209',NULL,NULL,'','','Volvo V60','Diesel','Vxö','Daniel Björkman',NULL),(3,'NEX 816',NULL,NULL,'Station wagon','GPS-tracked','Volvo V60 DRIVe','Diesel','Södt','Rickard Gustafsson',NULL),(4,'',NULL,NULL,'SportCombi','Dragkrok','Saab 9-5 Bio Power','Etanol E85','Kstd','',NULL),(5,'MYJ 078',NULL,NULL,'Station wagon','GPS-tracked','Volvo V60 DRIVe','Diesel','Sthm','Patrik Almström',NULL);
/*!40000 ALTER TABLE `tbl_vehicles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-23 15:19:14
