/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 10.1.14-MariaDB : Database - geniedb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`geniedb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `geniedb`;

/*Table structure for table `t_auth` */

DROP TABLE IF EXISTS `t_auth`;

CREATE TABLE `t_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authName` varchar(16) NOT NULL,
  `authPath` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `t_auth` */

insert  into `t_auth`(`id`,`authName`,`authPath`,`description`) values (3,'testadminhtml','/html/admin.html',NULL),(4,'testuserhtml','/html/user.html',NULL),(5,'testindexhtml','/html/index.html',NULL),(6,'testuserdo','/user/.*',NULL);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(16) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`roleName`,`description`) values (1,'ROLE_ADMIN','管理员'),(2,'ROLE_ORDINARY','普通用户'),(3,'ROLE_USER','测试');

/*Table structure for table `t_role_auth` */

DROP TABLE IF EXISTS `t_role_auth`;

CREATE TABLE `t_role_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `auth_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_role_auth` */

insert  into `t_role_auth`(`id`,`role_id`,`auth_id`) values (3,1,3),(4,1,4),(5,2,4),(6,1,5),(7,2,5),(8,1,6);

/*Table structure for table `t_t_manageruser` */

DROP TABLE IF EXISTS `t_t_manageruser`;

CREATE TABLE `t_t_manageruser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `t_t_manageruser` */

insert  into `t_t_manageruser`(`id`,`username`,`password`) values (1,'genie','genie'),(10,'junitTestPersist','password'),(11,'junitTestMerge','password'),(12,'junitTestAttachDirty','password'),(13,'junitTestAttachClean','password'),(15,'junitTestMerge','password'),(16,'test1','test1'),(17,'test2','test2'),(18,'test3','test2'),(19,'genie2','genie2'),(21,'saveTest','saveTest2');

/*Table structure for table `t_t_manageruser_role` */

DROP TABLE IF EXISTS `t_t_manageruser_role`;

CREATE TABLE `t_t_manageruser_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_t_manageruser_role` */

insert  into `t_t_manageruser_role`(`id`,`login_id`,`role_id`) values (1,1,1),(2,19,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
