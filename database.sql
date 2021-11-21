-- MySQL dump 10.17  Distrib 10.3.22-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: mcd1
-- ------------------------------------------------------
-- Server version	10.3.22-MariaDB-1

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
-- Table structure for table `Absent`
--

DROP TABLE IF EXISTS `Absent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Absent` (
  `Id_etudiant` int(11) NOT NULL,
  `id_seance` int(11) NOT NULL,
  `Justifie` tinyint(1) NOT NULL,
  `Justification` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`Id_etudiant`,`id_seance`),
  KEY `Absent_Seance0_FK` (`id_seance`),
  CONSTRAINT `Absent_Etudiant_FK` FOREIGN KEY (`Id_etudiant`) REFERENCES `Etudiant` (`Id_etudiant`),
  CONSTRAINT `Absent_Seance0_FK` FOREIGN KEY (`id_seance`) REFERENCES `Seance` (`id_seance`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Absent`
--

LOCK TABLES `Absent` WRITE;
/*!40000 ALTER TABLE `Absent` DISABLE KEYS */;
INSERT INTO `Absent` VALUES (1,1,1,NULL),(2,1,1,NULL),(3,1,1,NULL),(4,1,1,NULL);
/*!40000 ALTER TABLE `Absent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Annee_universitaire`
--

DROP TABLE IF EXISTS `Annee_universitaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Annee_universitaire` (
  `Id_annee` int(11) NOT NULL AUTO_INCREMENT,
  `Date_debut` date NOT NULL,
  `Date_fin` date NOT NULL,
  `Libelle` varchar(45) NOT NULL,
  PRIMARY KEY (`Id_annee`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Annee_universitaire`
--

LOCK TABLES `Annee_universitaire` WRITE;
/*!40000 ALTER TABLE `Annee_universitaire` DISABLE KEYS */;
INSERT INTO `Annee_universitaire` VALUES (1,'2019-09-01','2020-07-31','2019/2020');
/*!40000 ALTER TABLE `Annee_universitaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Classe`
--

DROP TABLE IF EXISTS `Classe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Classe` (
  `id_classe` int(11) NOT NULL AUTO_INCREMENT,
  `Attribut` char(5) NOT NULL,
  PRIMARY KEY (`id_classe`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Classe`
--

LOCK TABLES `Classe` WRITE;
/*!40000 ALTER TABLE `Classe` DISABLE KEYS */;
INSERT INTO `Classe` VALUES (1,'G1'),(2,'G2'),(3,'G3'),(4,'G4'),(5,'G5');
/*!40000 ALTER TABLE `Classe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Departement`
--

DROP TABLE IF EXISTS `Departement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Departement` (
  `id_Departement` int(11) NOT NULL AUTO_INCREMENT,
  `Nom_departement` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_Departement`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Departement`
--

LOCK TABLES `Departement` WRITE;
/*!40000 ALTER TABLE `Departement` DISABLE KEYS */;
INSERT INTO `Departement` VALUES (1,'Réseau et communication'),(2,'Informatique et Aide à la decistion');
/*!40000 ALTER TABLE `Departement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Element`
--

DROP TABLE IF EXISTS `Element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Element` (
  `id_element` int(11) NOT NULL AUTO_INCREMENT,
  `Nom_Element` varchar(45) DEFAULT NULL,
  `Id_enseignant` int(11) NOT NULL,
  `id_module` int(11) NOT NULL,
  PRIMARY KEY (`id_element`),
  KEY `Element_Enseignant_FK` (`Id_enseignant`),
  KEY `Element_Module0_FK` (`id_module`),
  CONSTRAINT `Element_Enseignant_FK` FOREIGN KEY (`Id_enseignant`) REFERENCES `Enseignant` (`Id_enseignant`),
  CONSTRAINT `Element_Module0_FK` FOREIGN KEY (`id_module`) REFERENCES `Module` (`id_module`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Element`
--

LOCK TABLES `Element` WRITE;
/*!40000 ALTER TABLE `Element` DISABLE KEYS */;
INSERT INTO `Element` VALUES (1,'Systéme d\'exploitation',1,2),(2,'TCP/IP',1,1);
/*!40000 ALTER TABLE `Element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Emploi`
--

DROP TABLE IF EXISTS `Emploi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Emploi` (
  `id_emploi` int(11) NOT NULL AUTO_INCREMENT,
  `debut` date NOT NULL,
  `fin` date NOT NULL,
  `id_classe` int(11) NOT NULL,
  PRIMARY KEY (`id_emploi`),
  KEY `Emploi_Classe_FK` (`id_classe`),
  CONSTRAINT `Emploi_Classe_FK` FOREIGN KEY (`id_classe`) REFERENCES `Classe` (`id_classe`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Emploi`
--

LOCK TABLES `Emploi` WRITE;
/*!40000 ALTER TABLE `Emploi` DISABLE KEYS */;
INSERT INTO `Emploi` VALUES (1,'2019-09-01','2020-05-31',1);
/*!40000 ALTER TABLE `Emploi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Enseignant`
--

DROP TABLE IF EXISTS `Enseignant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Enseignant` (
  `Id_enseignant` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(20) DEFAULT NULL,
  `Prenom` varchar(20) DEFAULT NULL,
  `Tel` varchar(13) DEFAULT NULL,
  `Photo` varchar(100) DEFAULT NULL,
  `E_mail` varchar(45) DEFAULT NULL,
  `id_Departement` int(11) NOT NULL,
  `Id_user` int(11) NOT NULL,
  PRIMARY KEY (`Id_enseignant`),
  UNIQUE KEY `Enseignant_Users_AK` (`Id_user`),
  KEY `Enseignant_Departement_FK` (`id_Departement`),
  CONSTRAINT `Enseignant_Departement_FK` FOREIGN KEY (`id_Departement`) REFERENCES `Departement` (`id_Departement`),
  CONSTRAINT `Enseignant_Users0_FK` FOREIGN KEY (`Id_user`) REFERENCES `Users` (`Id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enseignant`
--

LOCK TABLES `Enseignant` WRITE;
/*!40000 ALTER TABLE `Enseignant` DISABLE KEYS */;
INSERT INTO `Enseignant` VALUES (1,'Bouzidi','Driss','0787638219','/images/2.jpg','test@test.ma',1,2);
/*!40000 ALTER TABLE `Enseignant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Etudiant`
--

DROP TABLE IF EXISTS `Etudiant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Etudiant` (
  `Id_etudiant` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(20) NOT NULL,
  `Prenom` varchar(20) NOT NULL,
  `Date_naissance` date NOT NULL,
  `Lieu_naissance` varchar(30) NOT NULL,
  `Genre` tinyint(1) NOT NULL,
  `CIN` varchar(10) NOT NULL,
  `Telephone` varchar(13) NOT NULL,
  `Photo` varchar(100) NOT NULL,
  `E_mail` varchar(45) NOT NULL,
  `CNE` varchar(15) NOT NULL,
  `id_classe` int(11) NOT NULL,
  `Id_user` int(11) NOT NULL,
  PRIMARY KEY (`Id_etudiant`),
  UNIQUE KEY `Etudiant_Users_AK` (`Id_user`),
  KEY `Etudiant_Classe_FK` (`id_classe`),
  CONSTRAINT `Etudiant_Classe_FK` FOREIGN KEY (`id_classe`) REFERENCES `Classe` (`id_classe`),
  CONSTRAINT `Etudiant_Users0_FK` FOREIGN KEY (`Id_user`) REFERENCES `Users` (`Id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Etudiant`
--

LOCK TABLES `Etudiant` WRITE;
/*!40000 ALTER TABLE `Etudiant` DISABLE KEYS */;
INSERT INTO `Etudiant` VALUES (1,'Fahd','Adni','1999-12-10','Khouribga',0,'Q336183','0687966920','/images/1.jpg','fahd.adni@gmail.com','F147005003',1,1),(2,'Khallouf','Faycal','1999-07-01','Khouribga',0,'Q336332','0687996920','/images/3.jpg','yousra.adni@gmail.com','F147005002',1,2),(3,'Mamor','Mohamed','1999-01-01','Khouribga',0,'test','test','test','test','F121331',1,4),(4,'Bouraya','Walid','1999-01-01','Khouribga',0,'tet','tet','tet','tet','F12131',1,5);
/*!40000 ALTER TABLE `Etudiant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Module`
--

DROP TABLE IF EXISTS `Module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Module` (
  `id_module` int(11) NOT NULL AUTO_INCREMENT,
  `Nom_module` varchar(45) DEFAULT NULL,
  `id_Semestre` int(11) NOT NULL,
  PRIMARY KEY (`id_module`),
  KEY `Module_Semestre_FK` (`id_Semestre`),
  CONSTRAINT `Module_Semestre_FK` FOREIGN KEY (`id_Semestre`) REFERENCES `Semestre` (`id_Semestre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Module`
--

LOCK TABLES `Module` WRITE;
/*!40000 ALTER TABLE `Module` DISABLE KEYS */;
INSERT INTO `Module` VALUES (1,'Réseau local',1),(2,'Systéme d\'exploitation',1),(3,'TEC',1),(4,'Anglais',1);
/*!40000 ALTER TABLE `Module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Note`
--

DROP TABLE IF EXISTS `Note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Note` (
  `id_note` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identifiant de la note',
  `Note` float NOT NULL,
  `type_evaluation` tinyint(1) NOT NULL COMMENT 'type_note est un boolean indiquant si la note est celle du controle ou de l''examen',
  `type_session` tinyint(1) NOT NULL COMMENT 'type_session est un boolean indiquant si la note est celle de la premi?re session ou du rattrapag',
  `date_ajout` date NOT NULL COMMENT 'date d''ajout de la note',
  `date_modif` date NOT NULL COMMENT 'date de la derni?re modification de la note',
  `observation` varchar(100) NOT NULL,
  `note_rachetage` float NOT NULL,
  `Id_etudiant` int(11) NOT NULL,
  `id_element` int(11) DEFAULT NULL,
  `id_validite` int(11) NOT NULL,
  PRIMARY KEY (`id_note`),
  KEY `Note_Etudiant_FK` (`Id_etudiant`),
  KEY `Note_Element0_FK` (`id_element`),
  KEY `Note_Validite1_FK` (`id_validite`),
  CONSTRAINT `Note_Element0_FK` FOREIGN KEY (`id_element`) REFERENCES `Element` (`id_element`),
  CONSTRAINT `Note_Etudiant_FK` FOREIGN KEY (`Id_etudiant`) REFERENCES `Etudiant` (`Id_etudiant`),
  CONSTRAINT `Note_Validite1_FK` FOREIGN KEY (`id_validite`) REFERENCES `Validite` (`id_validite`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Note`
--

LOCK TABLES `Note` WRITE;
/*!40000 ALTER TABLE `Note` DISABLE KEYS */;
/*!40000 ALTER TABLE `Note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Responsable`
--

DROP TABLE IF EXISTS `Responsable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Responsable` (
  `Id_enseignant` int(11) NOT NULL,
  `id_module` int(11) NOT NULL,
  PRIMARY KEY (`Id_enseignant`,`id_module`),
  KEY `Responsable_Module0_FK` (`id_module`),
  CONSTRAINT `Responsable_Enseignant_FK` FOREIGN KEY (`Id_enseignant`) REFERENCES `Enseignant` (`Id_enseignant`),
  CONSTRAINT `Responsable_Module0_FK` FOREIGN KEY (`id_module`) REFERENCES `Module` (`id_module`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Responsable`
--

LOCK TABLES `Responsable` WRITE;
/*!40000 ALTER TABLE `Responsable` DISABLE KEYS */;
/*!40000 ALTER TABLE `Responsable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Seance`
--

DROP TABLE IF EXISTS `Seance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Seance` (
  `id_seance` int(11) NOT NULL AUTO_INCREMENT,
  `debut` datetime NOT NULL,
  `fin` datetime NOT NULL,
  `type_seance` char(2) NOT NULL,
  `salle` char(3) NOT NULL,
  `id_emploi` int(11) NOT NULL,
  `Id_enseignant` int(11) NOT NULL,
  `id_element` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_seance`),
  KEY `Seance_Emploi_FK` (`id_emploi`),
  KEY `Seance_Enseignant0_FK` (`Id_enseignant`),
  KEY `id_element` (`id_element`),
  CONSTRAINT `Seance_Emploi_FK` FOREIGN KEY (`id_emploi`) REFERENCES `Emploi` (`id_emploi`),
  CONSTRAINT `Seance_Enseignant0_FK` FOREIGN KEY (`Id_enseignant`) REFERENCES `Enseignant` (`Id_enseignant`),
  CONSTRAINT `Seance_ibfk_1` FOREIGN KEY (`id_element`) REFERENCES `Element` (`id_element`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Seance`
--

LOCK TABLES `Seance` WRITE;
/*!40000 ALTER TABLE `Seance` DISABLE KEYS */;
INSERT INTO `Seance` VALUES (1,'2019-09-01 08:00:00','2019-09-01 10:00:00','CR','A1',1,1,1),(2,'2019-09-01 10:10:00','2019-09-01 12:10:00','TD','A1',1,1,1);
/*!40000 ALTER TABLE `Seance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Semestre`
--

DROP TABLE IF EXISTS `Semestre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Semestre` (
  `id_Semestre` int(11) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(45) NOT NULL,
  `Date_debut` date NOT NULL,
  `Date_fin` date NOT NULL,
  `Id_annee` int(11) NOT NULL,
  PRIMARY KEY (`id_Semestre`),
  KEY `Semestre_Annee_universitaire_FK` (`Id_annee`),
  CONSTRAINT `Semestre_Annee_universitaire_FK` FOREIGN KEY (`Id_annee`) REFERENCES `Annee_universitaire` (`Id_annee`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Semestre`
--

LOCK TABLES `Semestre` WRITE;
/*!40000 ALTER TABLE `Semestre` DISABLE KEYS */;
INSERT INTO `Semestre` VALUES (1,'S1-2020','2019-09-01','2020-01-15',1);
/*!40000 ALTER TABLE `Semestre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `Id_user` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(20) NOT NULL,
  `PWD` varchar(45) NOT NULL,
  `session` varchar(50) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`Id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'testuser','testuser','ASSAIO38J8S02','etudiant'),(2,'TesT2','TesT2','TesT2','enseignant'),(3,'TEST1','TEST1','TEST1','etudiant'),(4,'TesT2','TesT2','TesT2','etudiant'),(5,'TesT2','TesT2','TesT2','etudiant');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Validite`
--

DROP TABLE IF EXISTS `Validite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Validite` (
  `id_validite` int(11) NOT NULL AUTO_INCREMENT,
  `lib_validite` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_validite`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Validite`
--

LOCK TABLES `Validite` WRITE;
/*!40000 ALTER TABLE `Validite` DISABLE KEYS */;
/*!40000 ALTER TABLE `Validite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inscrit`
--

DROP TABLE IF EXISTS `inscrit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inscrit` (
  `Id_etudiant` int(11) NOT NULL,
  `Id_annee` int(11) NOT NULL,
  PRIMARY KEY (`Id_etudiant`,`Id_annee`),
  KEY `inscrit_Annee_universitaire0_FK` (`Id_annee`),
  CONSTRAINT `inscrit_Annee_universitaire0_FK` FOREIGN KEY (`Id_annee`) REFERENCES `Annee_universitaire` (`Id_annee`),
  CONSTRAINT `inscrit_Etudiant_FK` FOREIGN KEY (`Id_etudiant`) REFERENCES `Etudiant` (`Id_etudiant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscrit`
--

LOCK TABLES `inscrit` WRITE;
/*!40000 ALTER TABLE `inscrit` DISABLE KEYS */;
/*!40000 ALTER TABLE `inscrit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-19 17:48:49
