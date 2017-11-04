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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_auth` */

/*Table structure for table `t_child` */

DROP TABLE IF EXISTS `t_child`;

CREATE TABLE `t_child` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personId` varchar(18) DEFAULT NULL COMMENT '身份证',
  `childName` varchar(50) NOT NULL COMMENT '小孩名字',
  `parentId` int(11) DEFAULT NULL COMMENT '父母id',
  `birthday` timestamp NULL DEFAULT NULL COMMENT '生日',
  `sex` enum('male','female') DEFAULT NULL COMMENT '性别',
  `specialty` varchar(100) DEFAULT NULL COMMENT '特长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_child` */

insert  into `t_child`(`id`,`personId`,`childName`,`parentId`,`birthday`,`sex`,`specialty`) values (2,NULL,'boy1',6,NULL,'male',NULL);

/*Table structure for table `t_child_organ` */

DROP TABLE IF EXISTS `t_child_organ`;

CREATE TABLE `t_child_organ` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `childId` int(11) DEFAULT NULL COMMENT '小孩id',
  `organId` int(11) DEFAULT NULL COMMENT '机构id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_child_organ` */

/*Table structure for table `t_contact` */

DROP TABLE IF EXISTS `t_contact`;

CREATE TABLE `t_contact` (
  `id` int(11) NOT NULL,
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `telephone` varchar(14) DEFAULT NULL COMMENT '电话',
  `qq` varchar(20) DEFAULT NULL COMMENT 'qq',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信',
  `mail` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_contact` */

/*Table structure for table `t_event` */

DROP TABLE IF EXISTS `t_event`;

CREATE TABLE `t_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '事件id',
  `code` varchar(8) NOT NULL COMMENT '事件代码',
  `name` varchar(16) NOT NULL COMMENT '事件名称',
  `time` datetime NOT NULL COMMENT '创建这条数据的时间',
  `description` varchar(255) DEFAULT NULL COMMENT '事件描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_event` */

insert  into `t_event`(`id`,`code`,`name`,`time`,`description`) values (1,'1001','吃饭','2017-06-17 22:16:00','就是吃饭'),(2,'2001','睡觉','2017-06-17 22:16:14','就是睡觉这么简单');

/*Table structure for table `t_login` */

DROP TABLE IF EXISTS `t_login`;

CREATE TABLE `t_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `t_login` */

insert  into `t_login`(`id`,`username`,`password`) values (1,'genie','genie'),(10,'junitTestPersist','password'),(11,'junitTestMerge','password'),(12,'junitTestAttachDirty','password'),(13,'junitTestAttachClean','password'),(15,'junitTestMerge','password'),(16,'test1','test1'),(17,'test2','test2'),(18,'test3','test2');

/*Table structure for table `t_organ` */

DROP TABLE IF EXISTS `t_organ`;

CREATE TABLE `t_organ` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organName` varchar(50) DEFAULT NULL COMMENT '机构名',
  `parentOrganId` int(11) DEFAULT NULL COMMENT '父机构id',
  `contactId` int(11) DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_organ` */

/*Table structure for table `t_parent` */

DROP TABLE IF EXISTS `t_parent`;

CREATE TABLE `t_parent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personId` varchar(18) DEFAULT NULL,
  `parentName` varchar(50) DEFAULT NULL,
  `sex` enum('male','female') DEFAULT NULL,
  `contactId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `t_parent` */

insert  into `t_parent`(`id`,`personId`,`parentName`,`sex`,`contactId`) values (6,NULL,'parent1','male',1);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(16) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`roleName`,`description`) values (1,'administrator','admin');

/*Table structure for table `t_role_auth` */

DROP TABLE IF EXISTS `t_role_auth`;

CREATE TABLE `t_role_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `auth_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_auth` */

/*Table structure for table `t_userinfo` */

DROP TABLE IF EXISTS `t_userinfo`;

CREATE TABLE `t_userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `realName` varchar(50) DEFAULT NULL COMMENT '姓名',
  `personId` varchar(18) DEFAULT NULL COMMENT '身份证',
  `phone` varchar(14) DEFAULT NULL COMMENT '电话',
  `sex` enum('male','female') DEFAULT NULL COMMENT '性别',
  `loginId` int(11) DEFAULT NULL COMMENT '登陆id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_userinfo` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
