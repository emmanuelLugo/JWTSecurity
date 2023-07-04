/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.6.27-log : Database - jwt_database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jwt_database` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jwt_database`;

/*Table structure for table `sys_usuario` */

DROP TABLE IF EXISTS `sys_usuario`;

CREATE TABLE `sys_usuario` (
  `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN` varchar(60) NOT NULL,
  `SENHA` varchar(60) NOT NULL,
  `NOME_USUARIO` varchar(100) NOT NULL,
  `DT_ULTIMO_LOGIN` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_usuario` */

insert  into `sys_usuario`(`ID_USUARIO`,`LOGIN`,`SENHA`,`NOME_USUARIO`,`DT_ULTIMO_LOGIN`) values (1,'TEST','$2a$10$9i44gFaGK/3WCl8/u1WVROzwo1oxB0yKwP3lWuFixKJS1UyAPzq4q','USUARIO PRUEBA',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
