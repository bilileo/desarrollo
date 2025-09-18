CREATE DATABASE  IF NOT EXISTS `desarrollo` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `desarrollo`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: desarrollo
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `id_Administrador` int NOT NULL,
  `mail_Administrador` varchar(45) DEFAULT NULL,
  `pass_Administrador` varchar(45) DEFAULT NULL,
  `user_Administrador` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_Administrador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (777,'admin','admin1234','SAUAP_Exam');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignado`
--

DROP TABLE IF EXISTS `asignado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asignado` (
  `Id_profesor` int NOT NULL,
  `Id_ua` int NOT NULL,
  `HrsClase` tinyint DEFAULT NULL,
  `HrsTaller` tinyint DEFAULT NULL,
  `HrsLab` tinyint DEFAULT NULL,
  `Lunes` tinyblob,
  `Martes` tinyblob,
  `Miercoles` tinyblob,
  `Jueves` tinyblob,
  `Viernes` tinyblob,
  PRIMARY KEY (`Id_profesor`,`Id_ua`),
  KEY `ID_ua_idx` (`Id_ua`),
  CONSTRAINT `ID_p` FOREIGN KEY (`Id_profesor`) REFERENCES `profesor` (`ID`),
  CONSTRAINT `ID_ua` FOREIGN KEY (`Id_ua`) REFERENCES `unidadaprendizaje` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignado`
--

LOCK TABLES `asignado` WRITE;
/*!40000 ALTER TABLE `asignado` DISABLE KEYS */;
INSERT INTO `asignado` VALUES (4,1010,2,1,2,_binary '\0\0',_binary '\0\0\0',_binary '\0\0',_binary '\0\0',_binary '\0 \0'),(6,1010,2,1,2,_binary '\0\0',_binary '\0\0',_binary '\0\0',_binary '\0\0',_binary '\0\0'),(6,1011,1,2,1,_binary '\0\0Ä',_binary '\0\0 ',_binary '\0\0\0',_binary '\0\0',_binary '\0\0');
/*!40000 ALTER TABLE `asignado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesor` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL DEFAULT '',
  `ApellidoP` varchar(50) NOT NULL DEFAULT '',
  `ApellidoM` varchar(50) NOT NULL DEFAULT '',
  `RFC` varchar(13) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (3,'Jorge','Tan','Guan','TAGJ01051379M'),(4,'Oscar','Tan','Guan','TAGO050523NT9'),(6,'mariel','torres','camacho','tocm020113mmm');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidadaprendizaje`
--

DROP TABLE IF EXISTS `unidadaprendizaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidadaprendizaje` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL DEFAULT '',
  `HrsClase` tinyint DEFAULT '0',
  `HrsTaller` tinyint DEFAULT '0',
  `HrsLab` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  CONSTRAINT `unidadaprendizaje_chk_1` CHECK ((`HrsClase` between 0 and 4)),
  CONSTRAINT `unidadaprendizaje_chk_2` CHECK ((`HrsTaller` between 0 and 4)),
  CONSTRAINT `unidadaprendizaje_chk_3` CHECK ((`HrsLab` between 0 and 4))
) ENGINE=InnoDB AUTO_INCREMENT=1012 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidadaprendizaje`
--

LOCK TABLES `unidadaprendizaje` WRITE;
/*!40000 ALTER TABLE `unidadaprendizaje` DISABLE KEYS */;
INSERT INTO `unidadaprendizaje` VALUES (1008,'Programacion Orientada a Objetos',2,3,4),(1009,'Desarrollo 2',2,1,2),(1010,'Programaci√≥n de Lenguaje Interpretados',2,1,2),(1011,'Calculo Integral',1,2,1);
/*!40000 ALTER TABLE `unidadaprendizaje` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-17 20:10:03
