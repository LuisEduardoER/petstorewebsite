-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.39


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema petstore
--

CREATE DATABASE IF NOT EXISTS petstore;
USE petstore;

--
-- Definition of table `petstore`.`customer_dim`
--

DROP TABLE IF EXISTS `petstore`.`customer_dim`;
CREATE TABLE  `petstore`.`customer_dim` (
  `customerid` int(11) NOT NULL,
  `category` varchar(45) DEFAULT NULL,
  `customertype` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `petstore`.`customer_dim`
--

/*!40000 ALTER TABLE `customer_dim` DISABLE KEYS */;
LOCK TABLES `customer_dim` WRITE;
INSERT INTO `petstore`.`customer_dim` VALUES  (1,NULL,0),
 (2,NULL,0),
 (3,NULL,0),
 (4,NULL,1),
 (5,NULL,1),
 (6,NULL,0),
 (7,NULL,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `customer_dim` ENABLE KEYS */;


--
-- Definition of table `petstore`.`customerbus`
--

DROP TABLE IF EXISTS `petstore`.`customerbus`;
CREATE TABLE  `petstore`.`customerbus` (
  `hcid` int(11) NOT NULL,
  `companyname` varchar(45) DEFAULT NULL,
  `company_desc` varchar(100) DEFAULT NULL,
  `industry` varchar(45) DEFAULT NULL,
  `annual_income` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`hcid`),
  KEY `customeridhome` (`hcid`),
  CONSTRAINT `customeridhome` FOREIGN KEY (`hcid`) REFERENCES `customers` (`cid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `petstore`.`customerbus`
--

/*!40000 ALTER TABLE `customerbus` DISABLE KEYS */;
LOCK TABLES `customerbus` WRITE;
INSERT INTO `petstore`.`customerbus` VALUES  (1,'McCoy Corporation','Mccoy corporation is a private business',NULL,NULL),
 (2,'Vick Inc','Vick Inc provides companies with animal food',NULL,NULL),
 (5,'Tim Heating Company','They need food for their guard dogs',NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `customerbus` ENABLE KEYS */;


--
-- Definition of table `petstore`.`customerhome`
--

DROP TABLE IF EXISTS `petstore`.`customerhome`;
CREATE TABLE  `petstore`.`customerhome` (
  `bcid` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `preference` varchar(100) DEFAULT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `marriage` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`bcid`),
  KEY `bcidcustomer` (`bcid`),
  CONSTRAINT `bcidcustomer` FOREIGN KEY (`bcid`) REFERENCES `customers` (`cid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `petstore`.`customerhome`
--

/*!40000 ALTER TABLE `customerhome` DISABLE KEYS */;
LOCK TABLES `customerhome` WRITE;
INSERT INTO `petstore`.`customerhome` VALUES  (3,23,'Food for small dogs','Male',NULL),
 (4,45,'Has fish','Male',NULL),
 (6,21,'Food for cat','Male',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `customerhome` ENABLE KEYS */;


--
-- Definition of table `petstore`.`customers`
--

DROP TABLE IF EXISTS `petstore`.`customers`;
CREATE TABLE  `petstore`.`customers` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `streetaddr` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `ctype` tinyint(1) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `phone` int(45) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `petstore`.`customers`
--

/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
LOCK TABLES `customers` WRITE;
INSERT INTO `petstore`.`customers` VALUES  (1,'1234 centre ave.','Pittsburgh','PA','15232',0,NULL,NULL,NULL),
 (2,'3237 Longhorn Way','Arlington','TX','87649',0,'McCoy','Colt',NULL),
 (3,'6778 Hokie Lane','Blacksburgh','VA','56723',0,'Vick','Michael',NULL),
 (4,'314 Nothing Street','Pittsburgh','PA','15234',1,'Bradford','Sam',NULL),
 (5,'3237 Buckeye Street','Cleveland','OH','45634',1,'Smith','Troy',NULL),
 (6,'8767 Gator Lane','Gainsville','FL','20984',0,'Tebow','Tim',NULL),
 (7,'8 Boilermaker Way','West Lafayette','IN','47906',1,'Orton','Kyle',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;


--
-- Definition of table `petstore`.`employee`
--

DROP TABLE IF EXISTS `petstore`.`employee`;
CREATE TABLE  `petstore`.`employee` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `storeid` int(11) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `streetaddr` varchar(45) DEFAULT NULL,
  `salary` decimal(10,0) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  PRIMARY KEY (`eid`),
  KEY `employeestore` (`storeid`),
  CONSTRAINT `employeestore` FOREIGN KEY (`storeid`) REFERENCES `stores` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `petstore`.`employee`
--

/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
LOCK TABLES `employee` WRITE;
INSERT INTO `petstore`.`employee` VALUES  (1,2,'Govind','Seshadri','sales','govse@email.com','14271 Corporate Drive',NULL,'Garden Grove','CA','92843','2008-05-21'),
 (2,1,'Sarah','Jones','Cashier','sjones@petstore.com','3727 Sutherland Drive','45000','Pittsburgh','PA','15213','2008-12-12'),
 (3,1,'Mark','Ingrahm','Manager','mingrahm@petstore.com','7463 Kiffin lane','55000','Pittbsurgh','PA','15234','2008-01-13'),
 (4,7,'Shane','Battier','Cashier','sbattier@petstore.com','786 Beach Ave','43000','Miami','FL','33127','2006-02-14'),
 (5,7,'Richard','Pen','Manager','rpen@petstore.com','8 Palm Ave','54000','Miami','FL','33128','2007-03-14'),
 (6,9,'Shawn','Penn','Chashier','spenn@petstore.com','76 long lane','45000','Dallas','TX','75612','2007-04-03'),
 (7,9,'Ram','Roder','Manager','rroder@petstore.com','5 horn street','55000','Dallas','TX','75613','2007-05-08'),
 (8,11,'Rob','Zombie','Cashier','rzombie@petstore.com','45 Bam Road','48000','Albany','CA','94710','2008-06-12'),
 (9,11,'Steve','Slayer','Manager','sslayer@petstore.com','23 Red Road','58000','Albany','CA','94711','2008-05-23'),
 (10,4,'Dell','Parker','Cashier','sparker@petstore.com','37 Penn Hwy','41000','Monroeville','PA','15416','2007-07-24'),
 (11,4,'Dusty','Baker','Manager','sbaker@petstore.com','67 Penn Road','47000','Monroeville','PA','16415','2007-07-12');
UNLOCK TABLES;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


--
-- Definition of table `petstore`.`factsale`
--

DROP TABLE IF EXISTS `petstore`.`factsale`;
CREATE TABLE  `petstore`.`factsale` (
  `customerid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `storeid` int(11) NOT NULL,
  `timeid` int(11) NOT NULL,
  `sale` decimal(6,2) NOT NULL,
  PRIMARY KEY (`productid`,`storeid`,`timeid`,`customerid`),
  KEY `factsaleproduct` (`productid`),
  KEY `factsalestore` (`storeid`),
  KEY `factsaletime` (`timeid`),
  KEY `factsalecustomer` (`customerid`),
  CONSTRAINT `factsalecustomer` FOREIGN KEY (`customerid`) REFERENCES `customer_dim` (`customerid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `factsaleproduct` FOREIGN KEY (`productid`) REFERENCES `product_dim` (`productid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `factsalestore` FOREIGN KEY (`storeid`) REFERENCES `store_dim` (`storeid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `factsaletime` FOREIGN KEY (`timeid`) REFERENCES `time_dim` (`timedimid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `petstore`.`factsale`
--

/*!40000 ALTER TABLE `factsale` DISABLE KEYS */;
LOCK TABLES `factsale` WRITE;
INSERT INTO `petstore`.`factsale` VALUES  (7,5,4,5,'0.00');
UNLOCK TABLES;
/*!40000 ALTER TABLE `factsale` ENABLE KEYS */;


--
-- Definition of table `petstore`.`inventory`
--

DROP TABLE IF EXISTS `petstore`.`inventory`;
CREATE TABLE  `petstore`.`inventory` (
  `storeid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `quantity` int(45) DEFAULT NULL,
  PRIMARY KEY (`storeid`,`productid`) USING BTREE,
  KEY `inventorystore` (`storeid`),
  KEY `inventoryproduct` (`productid`),
  CONSTRAINT `inventoryproduct` FOREIGN KEY (`productid`) REFERENCES `products` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `inventorystore` FOREIGN KEY (`storeid`) REFERENCES `stores` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `petstore`.`inventory`
--

/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
LOCK TABLES `inventory` WRITE;
INSERT INTO `petstore`.`inventory` VALUES  (1,2,23),
 (1,5,111),
 (2,2,99),
 (2,5,75),
 (3,1,20);
UNLOCK TABLES;
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;


--
-- Definition of table `petstore`.`orderdetail`
--

DROP TABLE IF EXISTS `petstore`.`orderdetail`;
CREATE TABLE  `petstore`.`orderdetail` (
  `oid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `quantity` int(45) DEFAULT NULL,
  PRIMARY KEY (`oid`,`pid`),
  KEY `orderiddetail` (`oid`),
  KEY `productid` (`pid`),
  CONSTRAINT `orderiddetail` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `productid` FOREIGN KEY (`pid`) REFERENCES `products` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `petstore`.`orderdetail`
--

/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
LOCK TABLES `orderdetail` WRITE;
INSERT INTO `petstore`.`orderdetail` VALUES  (1,1,2),
 (1,5,1),
 (1,26,1),
 (9,8,1),
 (9,21,1),
 (10,26,5),
 (11,2,2),
 (12,16,2),
 (14,17,1),
 (15,10,4),
 (16,18,1),
 (17,10,1),
 (18,1,14),
 (18,16,1),
 (19,15,1),
 (20,14,2),
 (21,13,3),
 (22,12,1),
 (23,11,3),
 (24,10,1),
 (25,9,2),
 (26,8,1),
 (27,7,1),
 (28,3,1),
 (29,6,1),
 (30,5,1),
 (31,1,1),
 (31,2,2),
 (31,25,1),
 (31,26,1),
 (32,24,1),
 (33,23,2),
 (34,22,1),
 (35,20,1),
 (35,21,1),
 (36,20,1),
 (37,18,2),
 (37,19,1),
 (38,17,1),
 (39,16,1),
 (40,15,1),
 (41,14,1),
 (42,13,2),
 (43,12,1),
 (44,11,1),
 (45,10,1),
 (45,14,1),
 (46,9,1),
 (46,15,2),
 (47,8,1),
 (47,16,1),
 (48,7,1),
 (48,17,1),
 (49,6,1),
 (49,18,3),
 (50,1,10),
 (50,3,4),
 (50,5,1),
 (51,26,2),
 (52,25,1),
 (53,24,1),
 (54,23,1),
 (55,22,1),
 (56,22,1),
 (57,21,1),
 (58,18,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;


--
-- Definition of table `petstore`.`orders`
--

DROP TABLE IF EXISTS `petstore`.`orders`;
CREATE TABLE  `petstore`.`orders` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `salesmanid` int(11) DEFAULT NULL,
  `odate` date NOT NULL,
  `totalamount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `customerid` (`cid`),
  KEY `storeidorder` (`sid`),
  CONSTRAINT `customerid` FOREIGN KEY (`cid`) REFERENCES `customers` (`cid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `storeidorder` FOREIGN KEY (`sid`) REFERENCES `stores` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `petstore`.`orders`
--

/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
LOCK TABLES `orders` WRITE;
INSERT INTO `petstore`.`orders` VALUES  (1,1,1,1,'2008-09-10','1050.00'),
 (9,1,7,11,'2008-10-10','37.00'),
 (10,1,7,11,'2009-01-24','12.00'),
 (11,1,2,4,'2008-10-11','21.00'),
 (12,1,2,2,'2009-01-01','9.00'),
 (13,1,2,4,'2009-02-13','5.00'),
 (14,14,6,1,'2008-12-10','10.00'),
 (15,14,5,1,'2009-01-02','3.00'),
 (16,3,7,8,'2008-12-24','100.00'),
 (17,3,6,8,'2008-12-24','13.00'),
 (18,3,5,8,'2008-12-24','17.00'),
 (19,18,6,1,'2009-02-03','54.00'),
 (20,13,5,10,'2009-02-01','99.00'),
 (21,13,2,9,'2009-08-01','140.00'),
 (22,13,5,2,'2009-08-05','501.00'),
 (23,12,5,4,'2009-05-07','46.00'),
 (24,12,1,9,'2009-06-13','19.00'),
 (25,12,4,9,'2009-04-21','44.00'),
 (26,12,3,9,'2009-04-11','56.00'),
 (27,11,6,6,'2009-06-06','67.00'),
 (28,11,6,1,'2009-04-04','44.00'),
 (29,10,1,8,'2009-09-21','9.00'),
 (30,9,3,4,'2009-03-19','29.00'),
 (31,8,3,9,'2009-11-11','11.00'),
 (32,7,5,1,'2009-10-11','10.00'),
 (33,7,5,2,'2009-10-11','2.00'),
 (34,6,4,9,'2009-07-16','16.00'),
 (35,5,2,4,'2008-10-11','20.81'),
 (36,5,2,2,'2009-01-01','9.21'),
 (37,5,2,4,'2009-02-13','5.33'),
 (38,14,3,1,'2008-10-30','9.99'),
 (39,14,5,1,'2009-01-02','3.13'),
 (40,5,7,8,'2008-12-24','100.00'),
 (41,5,6,8,'2008-12-24','12.67'),
 (42,5,5,8,'2008-12-24','16.54'),
 (43,3,6,1,'2009-02-03','54.21'),
 (44,14,5,10,'2009-02-01','99.10'),
 (45,2,2,9,'2009-08-01','140.34'),
 (46,14,5,2,'2009-08-05','501.03'),
 (47,2,5,4,'2009-05-07','46.00'),
 (48,2,1,9,'2009-06-13','18.51'),
 (49,14,4,9,'2009-04-21','43.90'),
 (50,14,3,9,'2009-04-11','55.98'),
 (51,1,6,6,'2009-06-06','66.66'),
 (52,1,6,1,'2009-04-04','44.44'),
 (53,14,1,8,'2009-09-21','9.21'),
 (54,7,3,4,'2009-03-19','29.21'),
 (55,6,3,9,'2009-11-11','11.11'),
 (56,8,5,1,'2009-10-11','10.11'),
 (57,3,5,2,'2009-10-11','1.58'),
 (58,2,4,9,'2009-07-16','16.07');
UNLOCK TABLES;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


--
-- Definition of table `petstore`.`product_dim`
--

DROP TABLE IF EXISTS `petstore`.`product_dim`;
CREATE TABLE  `petstore`.`product_dim` (
  `productid` int(11) NOT NULL,
  `category` varchar(45) NOT NULL,
  `price` decimal(6,2) NOT NULL,
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `petstore`.`product_dim`
--

/*!40000 ALTER TABLE `product_dim` DISABLE KEYS */;
LOCK TABLES `product_dim` WRITE;
INSERT INTO `petstore`.`product_dim` VALUES  (1,'Beds','35.66'),
 (2,'Canned food','4.99'),
 (3,'Beds','12.99'),
 (5,'Dry Food','2.99'),
 (6,'Canned food','1.90'),
 (7,'Dry food','9.70'),
 (8,'Food pouches','22.99'),
 (9,'Dry food','9.70'),
 (10,'Food pouches','18.99'),
 (11,'Canned food','1.90'),
 (12,'Food rolls','4.47'),
 (13,'Food rolls','4.47'),
 (14,'Food toppings','6.99'),
 (15,'Food toppings','3.99'),
 (16,'Canned food','0.45'),
 (17,'Canned food','1.33'),
 (18,'Dry food','8.97'),
 (19,'Dry food','8.97'),
 (20,'Food pouches','0.62'),
 (21,'Food pouches','0.88'),
 (22,'Treats','5.49'),
 (23,'Treats','9.99'),
 (24,'Beds','35.99'),
 (25,'Beds','60.29'),
 (26,'Beds','40.29');
UNLOCK TABLES;
/*!40000 ALTER TABLE `product_dim` ENABLE KEYS */;


--
-- Definition of table `petstore`.`products`
--

DROP TABLE IF EXISTS `petstore`.`products`;
CREATE TABLE  `petstore`.`products` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(45) DEFAULT NULL,
  `uniprice` decimal(10,2) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `petstore`.`products`
--

/*!40000 ALTER TABLE `products` DISABLE KEYS */;
LOCK TABLES `products` WRITE;
INSERT INTO `petstore`.`products` VALUES  (1,'dogbed','35.66','Beds'),
 (2,'catfood','4.99','Canned food'),
 (3,'catbed','12.99','Beds'),
 (5,'dogfood','2.99','Dry Food'),
 (6,'Natural Canned Adult Dog Food','1.90','Canned food'),
 (7,'Natural Brown Rice & Chicken Adult Dog Food','9.70','Dry food'),
 (8,'Mixables for Medium to Large Dogs','22.99','Food pouches'),
 (9,'Natural Brown Rice & Chicken Adult Dog Food','9.70','Dry food'),
 (10,'Mixables for Small to Medium Dogs','18.99','Food pouches'),
 (11,'Natural Canned Adult Cat Food','1.90','Canned food'),
 (12,'Natural Balance Dog Food Rolls Beef Formula','4.47','Food rolls'),
 (13,'Natural Balance Dog Food Rolls Lamb Formula','4.47','Food rolls'),
 (14,'Dinner Party Delicious Protein Sprinkle','6.99','Food toppings'),
 (15,'Savory Sauce Adult Dog Food','3.99','Food toppings'),
 (16,'Ground Canned Food for Cats','0.45','Canned food'),
 (17,'Wilderness Canned Cat Food','1.33','Canned food'),
 (18,'Natural Ultramix Indoor Feline','8.97','Dry food'),
 (19,'Adult Lamb & Rice Cat Food','8.97','Dry food'),
 (20,'Market Select Moist Cat Food in Gravy','0.62','Food pouches'),
 (21,'Wholesome Goodness Cat Cuisine in Broth','0.88','Food pouches'),
 (22,'Snausages Party Sack','5.49','Treats'),
 (23,'Meaty Tips Treats For Dogs','9.99','Treats'),
 (24,'Travel Dog Bed','35.99','Beds'),
 (25,'Tartan Plaid Berber Napper in Gray','60.29','Beds'),
 (26,'Tartan Plaid Berber Blanket in Red','40.29','Beds');
UNLOCK TABLES;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;


--
-- Definition of table `petstore`.`regions`
--

DROP TABLE IF EXISTS `petstore`.`regions`;
CREATE TABLE  `petstore`.`regions` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(45) DEFAULT NULL,
  `managerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `manager` (`managerid`),
  CONSTRAINT `manager` FOREIGN KEY (`managerid`) REFERENCES `employee` (`eid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `petstore`.`regions`
--

/*!40000 ALTER TABLE `regions` DISABLE KEYS */;
LOCK TABLES `regions` WRITE;
INSERT INTO `petstore`.`regions` VALUES  (8,'NorthWest',NULL),
 (9,'Center',NULL),
 (10,'SouthWest',NULL),
 (11,'SouthEast',NULL),
 (12,'NorthEast',NULL),
 (14,'Great Pittsburgh Area',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `regions` ENABLE KEYS */;


--
-- Definition of table `petstore`.`store_dim`
--

DROP TABLE IF EXISTS `petstore`.`store_dim`;
CREATE TABLE  `petstore`.`store_dim` (
  `storeid` int(11) NOT NULL,
  `regionid` int(11) NOT NULL,
  PRIMARY KEY (`storeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `petstore`.`store_dim`
--

/*!40000 ALTER TABLE `store_dim` DISABLE KEYS */;
LOCK TABLES `store_dim` WRITE;
INSERT INTO `petstore`.`store_dim` VALUES  (1,8),
 (2,8),
 (3,8),
 (4,8),
 (5,8),
 (6,8),
 (7,11),
 (8,11),
 (9,9),
 (10,9),
 (11,10),
 (12,10),
 (13,8),
 (14,8),
 (18,8);
UNLOCK TABLES;
/*!40000 ALTER TABLE `store_dim` ENABLE KEYS */;


--
-- Definition of table `petstore`.`stores`
--

DROP TABLE IF EXISTS `petstore`.`stores`;
CREATE TABLE  `petstore`.`stores` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `regionid` int(11) DEFAULT NULL,
  `street` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `zip` varchar(45) NOT NULL,
  `manager` int(11) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  KEY `managerstore` (`manager`),
  KEY `storeregion` (`regionid`),
  CONSTRAINT `managerstore` FOREIGN KEY (`manager`) REFERENCES `employee` (`eid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `storeregion` FOREIGN KEY (`regionid`) REFERENCES `regions` (`rid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `petstore`.`stores`
--

/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
LOCK TABLES `stores` WRITE;
INSERT INTO `petstore`.`stores` VALUES  (1,8,'3500 Mountain View Dr','West Mifflin','PA','15122',1,'412-653-7071','MountainView Store'),
 (2,8,'3739 William Penn Hwy','Monroeville','PA','15146',1,'412-372-8424','Monroeville Store'),
 (3,8,'1134 Northway Mall','Pittsburgh','PA','15237',1,'412-364-2720','Ross Township Store'),
 (4,8,'3739 William Penn Hwy','Monroeville','PA','15146',1,'412-372-8424','Monroeville Store'),
 (5,8,'400 Mill Creek Dr','Secaucus','NJ','07094',1,'201-583-0861','Secaucus Store'),
 (6,8,'1520 Forest Ave','Staten Island','NJ','10302',1,'718-273-3874','Staten Island Store'),
 (7,11,'3101 N Miami Ave, Suite 110','Miami','FL','33127',1,'305-573-8026','Miami-Midtown Store'),
 (8,11,'8241 W Flagler St Ste 101','Miami','FL','33144',1,'786-275-8300','Miami Flagler Store'),
 (9,9,'5500 Greenville Ave','Dallas','TX','75206',1,'469-232-2030','Dallas Store'),
 (10,9,'12100 Inwood Rd','Dallas','TX','75244',1,'972-239-3554','Dallas N Toolway Store'),
 (11,10,'1001 Eastshore Hwy','Albany','CA','94710',1,'510-524-1518','Albany Store'),
 (12,10,'15555 E 14th St','San Leandro','CA','94578',1,'510-317-1880','San Leandro Store'),
 (13,8,'100 108th Ave NE','Bellevue','WA','98004',1,'425-454-1054','Bellevue Store'),
 (14,8,'1203 N Landing Way','Renton','WA','98055',1,'425-204-1759','Rento Store'),
 (18,8,'304 S Center Ave','Pittsburgh','PA','11111',1,'412-482-1111','petsmart');
UNLOCK TABLES;
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;


--
-- Definition of table `petstore`.`time_dim`
--

DROP TABLE IF EXISTS `petstore`.`time_dim`;
CREATE TABLE  `petstore`.`time_dim` (
  `timedimid` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `weekinyear` int(11) NOT NULL,
  `dayinyear` int(11) NOT NULL,
  `holiday` tinyint(1) NOT NULL,
  PRIMARY KEY (`timedimid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `petstore`.`time_dim`
--

/*!40000 ALTER TABLE `time_dim` DISABLE KEYS */;
LOCK TABLES `time_dim` WRITE;
INSERT INTO `petstore`.`time_dim` VALUES  (1,'2009-01-01 00:00:00',1,1,1),
 (2,'2009-01-02 00:00:00',1,2,0),
 (3,'2009-01-03 00:00:00',1,3,0),
 (4,'2009-01-04 00:00:00',1,4,0),
 (5,'2009-01-05 00:00:00',1,5,0),
 (6,'2009-01-06 00:00:00',1,6,0),
 (7,'2009-01-07 00:00:00',1,7,0),
 (8,'2009-01-08 00:00:00',2,8,0),
 (9,'2009-01-09 00:00:00',2,9,0),
 (10,'2009-01-10 00:00:00',2,10,0),
 (11,'2009-01-11 00:00:00',2,11,0),
 (12,'2009-01-12 00:00:00',2,12,0),
 (13,'2009-01-13 00:00:00',2,13,0),
 (14,'2009-01-14 00:00:00',2,14,0),
 (15,'2009-01-15 00:00:00',3,15,0),
 (16,'2009-01-16 00:00:00',3,16,0),
 (17,'2009-01-17 00:00:00',3,17,0),
 (18,'2009-01-18 00:00:00',3,18,0),
 (19,'2009-01-19 00:00:00',3,19,0),
 (20,'2009-01-20 00:00:00',3,20,0),
 (21,'2009-01-21 00:00:00',3,21,0),
 (22,'2009-01-22 00:00:00',4,22,0),
 (23,'2009-01-23 00:00:00',4,23,0),
 (24,'2009-01-24 00:00:00',4,24,0),
 (25,'2009-01-25 00:00:00',4,25,0),
 (26,'2009-01-26 00:00:00',4,26,0),
 (27,'2009-01-27 00:00:00',4,27,0),
 (28,'2009-01-28 00:00:00',4,28,0),
 (29,'2009-01-29 00:00:00',5,29,0),
 (30,'2009-01-30 00:00:00',5,30,0),
 (31,'2009-01-31 00:00:00',5,31,0),
 (32,'2009-02-01 00:00:00',5,32,0),
 (33,'2009-02-02 00:00:00',5,33,0),
 (34,'2009-02-03 00:00:00',5,34,0),
 (35,'2009-02-04 00:00:00',5,35,0),
 (36,'2009-02-05 00:00:00',6,36,0),
 (37,'2009-02-06 00:00:00',6,37,0),
 (38,'2009-02-07 00:00:00',6,38,0),
 (39,'2009-02-08 00:00:00',6,39,0),
 (40,'2009-02-09 00:00:00',6,40,0),
 (41,'2009-02-11 00:00:00',6,41,0),
 (42,'2009-02-12 00:00:00',6,42,0),
 (43,'2009-02-13 00:00:00',7,43,0),
 (44,'2009-02-14 00:00:00',7,44,0),
 (45,'2009-02-15 00:00:00',7,45,0),
 (46,'2009-02-16 00:00:00',7,46,0),
 (47,'2009-02-17 00:00:00',7,47,0),
 (48,'2009-02-18 00:00:00',7,48,0),
 (49,'2009-02-19 00:00:00',7,49,0),
 (50,'2009-02-20 00:00:00',8,50,0),
 (51,'2009-02-21 00:00:00',8,51,0),
 (52,'2009-02-22 00:00:00',8,52,0),
 (53,'2009-02-23 00:00:00',8,53,0),
 (54,'2009-02-24 00:00:00',8,54,0),
 (55,'2009-02-25 00:00:00',8,55,0),
 (56,'2009-02-26 00:00:00',8,56,0),
 (57,'2009-02-27 00:00:00',9,57,0),
 (58,'2009-02-28 00:00:00',9,58,0),
 (59,'2009-03-01 00:00:00',9,59,0),
 (60,'2009-03-02 00:00:00',9,60,0),
 (61,'2009-03-03 00:00:00',9,61,0),
 (62,'2009-03-04 00:00:00',9,62,0),
 (63,'2009-03-05 00:00:00',9,63,0),
 (64,'2009-03-06 00:00:00',10,64,0),
 (65,'2009-03-07 00:00:00',10,65,0),
 (66,'2009-03-08 00:00:00',10,66,0),
 (67,'2009-03-09 00:00:00',10,67,0),
 (68,'2009-03-11 00:00:00',10,68,0),
 (69,'2009-03-12 00:00:00',10,69,0),
 (70,'2009-03-13 00:00:00',10,70,0),
 (71,'2009-03-14 00:00:00',11,71,0),
 (72,'2009-03-15 00:00:00',11,72,0),
 (73,'2009-03-16 00:00:00',11,73,0),
 (74,'2009-03-17 00:00:00',11,74,0),
 (75,'2009-03-18 00:00:00',11,75,0),
 (76,'2009-03-19 00:00:00',11,76,0),
 (77,'2009-03-20 00:00:00',11,77,0),
 (78,'2009-03-21 00:00:00',12,78,0),
 (79,'2009-03-22 00:00:00',12,79,0),
 (80,'2009-03-23 00:00:00',12,80,0),
 (81,'2009-03-24 00:00:00',12,81,0),
 (82,'2009-03-25 00:00:00',12,82,0),
 (83,'2009-03-26 00:00:00',12,83,0),
 (84,'2009-03-27 00:00:00',12,84,0),
 (85,'2009-03-28 00:00:00',13,85,0),
 (86,'2009-03-29 00:00:00',13,86,0),
 (87,'2009-03-30 00:00:00',13,87,0),
 (88,'2009-03-31 00:00:00',5,88,0),
 (89,'2009-04-01 00:00:00',13,89,0),
 (90,'2009-04-02 00:00:00',13,90,0),
 (91,'2009-04-03 00:00:00',13,91,0),
 (92,'2009-04-04 00:00:00',14,92,0),
 (93,'2009-04-05 00:00:00',14,93,0),
 (94,'2009-04-06 00:00:00',14,94,0),
 (95,'2009-04-07 00:00:00',14,95,0),
 (96,'2009-04-08 00:00:00',14,96,0),
 (97,'2009-04-09 00:00:00',14,97,0),
 (98,'2009-04-11 00:00:00',14,98,0),
 (99,'2009-04-12 00:00:00',15,99,0),
 (100,'2009-04-13 00:00:00',15,100,0),
 (101,'2009-04-14 00:00:00',15,101,0),
 (102,'2009-04-15 00:00:00',15,102,0),
 (103,'2009-04-16 00:00:00',15,103,0),
 (104,'2009-04-17 00:00:00',15,104,0),
 (105,'2009-04-18 00:00:00',15,105,0),
 (106,'2009-04-19 00:00:00',16,106,0),
 (107,'2009-04-20 00:00:00',16,107,0),
 (108,'2009-04-21 00:00:00',16,108,0),
 (109,'2009-04-22 00:00:00',16,109,0),
 (110,'2009-04-23 00:00:00',16,110,0),
 (111,'2009-04-24 00:00:00',16,111,0),
 (112,'2009-04-25 00:00:00',16,112,0),
 (113,'2009-04-26 00:00:00',17,113,0),
 (114,'2009-04-27 00:00:00',17,114,0),
 (115,'2009-04-28 00:00:00',17,115,0),
 (116,'2009-04-29 00:00:00',17,116,0),
 (117,'2009-04-30 00:00:00',17,117,0),
 (118,'2009-05-01 00:00:00',17,118,0),
 (119,'2009-05-02 00:00:00',17,119,0),
 (120,'2009-05-03 00:00:00',18,120,0),
 (121,'2009-05-04 00:00:00',18,121,0),
 (122,'2009-05-05 00:00:00',18,122,0),
 (123,'2009-05-06 00:00:00',18,123,0),
 (124,'2009-05-07 00:00:00',19,124,0),
 (125,'2009-05-08 00:00:00',19,125,0),
 (126,'2009-05-09 00:00:00',19,126,0),
 (127,'2009-05-10 00:00:00',19,127,0),
 (128,'2009-05-11 00:00:00',19,128,0),
 (129,'2009-05-12 00:00:00',19,129,0),
 (130,'2009-05-13 00:00:00',19,130,0),
 (131,'2009-05-14 00:00:00',20,131,0),
 (132,'2009-05-15 00:00:00',20,132,0),
 (133,'2009-05-16 00:00:00',20,133,0),
 (134,'2009-05-17 00:00:00',20,134,0),
 (135,'2009-05-18 00:00:00',20,135,0),
 (136,'2009-05-19 00:00:00',20,136,0),
 (137,'2009-05-20 00:00:00',20,137,0),
 (138,'2009-05-21 00:00:00',21,138,0),
 (139,'2009-05-22 00:00:00',21,139,0),
 (140,'2009-05-23 00:00:00',21,140,0),
 (141,'2009-05-24 00:00:00',21,141,0),
 (142,'2009-05-25 00:00:00',21,142,0),
 (143,'2009-05-26 00:00:00',21,143,0),
 (144,'2009-05-27 00:00:00',21,144,0),
 (145,'2009-05-28 00:00:00',22,145,0),
 (146,'2009-05-29 00:00:00',22,146,0),
 (147,'2009-05-30 00:00:00',22,147,0),
 (148,'2009-05-31 00:00:00',22,148,0),
 (149,'2009-06-01 00:00:00',22,149,0),
 (150,'2009-06-02 00:00:00',22,150,0),
 (151,'2009-06-03 00:00:00',22,151,0),
 (152,'2009-06-04 00:00:00',23,152,0),
 (153,'2009-06-05 00:00:00',23,153,0),
 (154,'2009-06-06 00:00:00',23,154,0),
 (155,'2009-06-07 00:00:00',23,155,0),
 (156,'2009-06-08 00:00:00',23,156,0),
 (157,'2009-06-09 00:00:00',23,157,0),
 (158,'2009-06-10 00:00:00',23,158,0),
 (159,'2009-06-11 00:00:00',24,159,0),
 (160,'2009-06-12 00:00:00',24,160,0),
 (161,'2009-06-13 00:00:00',24,161,0),
 (162,'2009-06-14 00:00:00',24,162,0),
 (163,'2009-06-15 00:00:00',24,163,0),
 (164,'2009-06-16 00:00:00',24,164,0),
 (165,'2009-06-17 00:00:00',24,165,0),
 (166,'2009-06-18 00:00:00',25,166,0),
 (167,'2009-06-19 00:00:00',25,167,0),
 (168,'2009-06-20 00:00:00',25,168,0),
 (169,'2009-06-21 00:00:00',25,169,0),
 (170,'2009-06-22 00:00:00',25,170,0),
 (171,'2009-06-23 00:00:00',25,171,0),
 (172,'2009-06-24 00:00:00',25,172,0),
 (173,'2009-06-25 00:00:00',26,173,0),
 (174,'2009-06-26 00:00:00',26,174,0),
 (175,'2009-06-27 00:00:00',26,175,0),
 (176,'2009-06-28 00:00:00',26,176,0),
 (177,'2009-06-29 00:00:00',26,177,0),
 (178,'2009-06-20 00:00:00',26,178,0),
 (179,'2009-07-01 00:00:00',27,179,0),
 (180,'2009-07-02 00:00:00',28,180,0),
 (181,'2009-07-03 00:00:00',28,181,0),
 (182,'2009-07-04 00:00:00',28,182,0),
 (183,'2009-07-05 00:00:00',28,183,0),
 (184,'2009-07-06 00:00:00',28,184,0),
 (185,'2009-07-07 00:00:00',28,185,0),
 (186,'2009-07-08 00:00:00',28,186,0),
 (187,'2009-07-09 00:00:00',29,187,0),
 (188,'2009-07-10 00:00:00',29,188,0),
 (189,'2009-07-11 00:00:00',29,189,0),
 (190,'2009-07-12 00:00:00',29,190,0),
 (191,'2009-07-13 00:00:00',29,191,0),
 (192,'2009-07-14 00:00:00',29,192,0),
 (193,'2009-07-15 00:00:00',29,193,0),
 (194,'2009-07-16 00:00:00',30,194,0),
 (195,'2009-07-17 00:00:00',30,195,0),
 (196,'2009-07-18 00:00:00',30,196,0),
 (197,'2009-07-19 00:00:00',30,197,0),
 (198,'2009-07-20 00:00:00',31,198,0),
 (199,'2009-07-21 00:00:00',31,199,0),
 (200,'2009-07-22 00:00:00',31,200,0),
 (201,'2009-07-23 00:00:00',31,201,0),
 (202,'2009-07-24 00:00:00',31,202,0),
 (203,'2009-07-25 00:00:00',31,203,0),
 (204,'2009-07-26 00:00:00',31,204,0),
 (205,'2009-07-27 00:00:00',31,205,0),
 (206,'2009-07-28 00:00:00',31,206,0),
 (207,'2009-07-29 00:00:00',31,207,0),
 (208,'2009-07-30 00:00:00',32,208,0),
 (209,'2009-07-31 00:00:00',32,209,0),
 (210,'2009-08-01 00:00:00',32,210,0),
 (211,'2009-08-02 00:00:00',32,211,0),
 (212,'2009-08-03 00:00:00',33,212,0),
 (213,'2009-08-04 00:00:00',33,213,0),
 (214,'2009-08-05 00:00:00',33,214,0),
 (215,'2009-08-06 00:00:00',33,215,0),
 (216,'2009-08-07 00:00:00',33,216,0),
 (217,'2009-08-08 00:00:00',33,217,0),
 (218,'2009-08-09 00:00:00',33,218,0),
 (219,'2009-08-10 00:00:00',34,219,0),
 (220,'2009-08-11 00:00:00',34,220,0),
 (221,'2009-08-12 00:00:00',34,221,0),
 (222,'2009-08-13 00:00:00',34,222,0),
 (223,'2009-08-14 00:00:00',34,223,0),
 (224,'2009-08-15 00:00:00',34,224,0),
 (225,'2009-08-16 00:00:00',34,225,0),
 (226,'2009-08-17 00:00:00',35,226,0),
 (227,'2009-08-18 00:00:00',35,227,0),
 (228,'2009-08-19 00:00:00',35,228,0),
 (229,'2009-08-20 00:00:00',35,229,0),
 (230,'2009-08-21 00:00:00',35,230,0),
 (231,'2009-08-22 00:00:00',35,231,0),
 (232,'2009-08-23 00:00:00',35,232,0),
 (233,'2009-08-24 00:00:00',36,233,0),
 (234,'2009-08-25 00:00:00',36,234,0),
 (235,'2009-08-26 00:00:00',36,235,0),
 (236,'2009-08-27 00:00:00',36,236,0),
 (237,'2009-08-28 00:00:00',36,237,0),
 (238,'2009-08-29 00:00:00',36,238,0),
 (239,'2009-08-30 00:00:00',36,239,0),
 (240,'2009-08-31 00:00:00',37,240,0),
 (241,'2009-09-01 00:00:00',37,241,0),
 (242,'2009-09-02 00:00:00',37,242,0),
 (243,'2009-09-03 00:00:00',37,243,0),
 (244,'2009-09-04 00:00:00',37,244,0),
 (245,'2009-09-05 00:00:00',37,245,0),
 (246,'2009-09-06 00:00:00',37,246,0),
 (247,'2009-09-07 00:00:00',38,247,0),
 (248,'2009-09-08 00:00:00',38,248,0),
 (249,'2009-09-09 00:00:00',38,249,0),
 (250,'2009-09-10 00:00:00',38,250,0),
 (251,'2009-09-11 00:00:00',38,251,0),
 (252,'2009-09-12 00:00:00',38,252,0),
 (253,'2009-09-13 00:00:00',38,253,0),
 (254,'2009-09-14 00:00:00',39,254,0),
 (255,'2009-09-15 00:00:00',39,255,0),
 (256,'2009-09-16 00:00:00',39,256,0),
 (257,'2009-09-17 00:00:00',39,257,0),
 (258,'2009-09-18 00:00:00',39,258,0),
 (259,'2009-09-29 00:00:00',39,259,0),
 (260,'2009-09-20 00:00:00',39,230,0),
 (261,'2009-09-21 00:00:00',40,231,0),
 (262,'2009-09-22 00:00:00',40,232,0),
 (263,'2009-09-23 00:00:00',40,233,0),
 (264,'2009-09-24 00:00:00',40,234,0),
 (265,'2009-09-25 00:00:00',40,235,0),
 (266,'2009-09-26 00:00:00',40,236,0),
 (267,'2009-09-27 00:00:00',40,237,0),
 (268,'2009-09-28 00:00:00',41,238,0),
 (269,'2009-09-29 00:00:00',41,239,0),
 (270,'2009-09-30 00:00:00',41,240,0),
 (271,'2009-10-01 00:00:00',41,271,0),
 (272,'2009-10-02 00:00:00',41,272,0),
 (273,'2009-10-03 00:00:00',41,273,0),
 (274,'2009-10-04 00:00:00',41,274,0),
 (275,'2009-10-05 00:00:00',42,275,0),
 (276,'2009-10-06 00:00:00',42,276,0),
 (277,'2009-10-07 00:00:00',42,277,0),
 (278,'2009-10-08 00:00:00',42,278,0),
 (279,'2009-10-09 00:00:00',42,279,0),
 (280,'2009-10-10 00:00:00',42,280,0),
 (281,'2009-10-11 00:00:00',42,281,0),
 (282,'2009-10-12 00:00:00',43,282,0),
 (283,'2009-10-13 00:00:00',43,283,0),
 (284,'2009-10-14 00:00:00',43,284,0),
 (285,'2009-10-15 00:00:00',43,285,0),
 (286,'2009-10-16 00:00:00',43,286,0),
 (287,'2009-10-17 00:00:00',43,287,0),
 (288,'2009-10-18 00:00:00',43,288,0),
 (289,'2009-10-29 00:00:00',44,289,0),
 (290,'2009-10-20 00:00:00',44,290,0),
 (291,'2009-10-21 00:00:00',44,291,0),
 (292,'2009-10-22 00:00:00',44,292,0),
 (293,'2009-10-23 00:00:00',44,293,0),
 (294,'2009-10-24 00:00:00',44,294,0),
 (295,'2009-10-25 00:00:00',44,295,0),
 (296,'2009-10-26 00:00:00',45,296,0),
 (297,'2009-10-27 00:00:00',45,297,0),
 (298,'2009-10-28 00:00:00',45,298,0),
 (299,'2009-10-29 00:00:00',45,299,0),
 (300,'2009-10-30 00:00:00',45,300,0),
 (301,'2009-10-31 00:00:00',45,301,0),
 (302,'2009-11-01 00:00:00',45,302,0),
 (303,'2009-11-02 00:00:00',46,303,0),
 (304,'2009-11-03 00:00:00',46,304,0),
 (305,'2009-11-04 00:00:00',46,305,0),
 (306,'2009-11-05 00:00:00',46,306,0),
 (307,'2009-11-06 00:00:00',46,307,0),
 (308,'2009-11-07 00:00:00',46,308,0),
 (309,'2009-11-08 00:00:00',46,309,0),
 (310,'2009-11-09 00:00:00',47,310,0),
 (311,'2009-11-10 00:00:00',47,311,0),
 (312,'2009-11-11 00:00:00',47,312,0),
 (313,'2009-11-12 00:00:00',47,313,0),
 (314,'2009-11-13 00:00:00',47,314,0),
 (315,'2009-11-14 00:00:00',47,315,0),
 (316,'2009-11-15 00:00:00',47,316,0),
 (317,'2009-11-16 00:00:00',48,317,0),
 (318,'2009-11-17 00:00:00',48,318,0),
 (319,'2009-11-18 00:00:00',48,319,0),
 (320,'2009-11-19 00:00:00',48,320,0),
 (321,'2009-11-20 00:00:00',48,321,0),
 (322,'2009-11-21 00:00:00',48,322,0),
 (323,'2009-11-22 00:00:00',48,323,0),
 (324,'2009-11-23 00:00:00',49,324,0),
 (325,'2009-11-24 00:00:00',49,325,0),
 (326,'2009-11-25 00:00:00',49,326,0),
 (327,'2009-11-26 00:00:00',49,327,0),
 (328,'2009-11-27 00:00:00',49,328,0),
 (329,'2009-11-28 00:00:00',49,329,0),
 (330,'2009-11-29 00:00:00',49,330,0),
 (331,'2009-11-30 00:00:00',50,331,0),
 (332,'2009-12-01 00:00:00',50,332,0),
 (333,'2009-12-02 00:00:00',50,333,0),
 (334,'2009-12-03 00:00:00',50,334,0),
 (335,'2009-12-04 00:00:00',50,335,0),
 (336,'2009-12-05 00:00:00',50,336,0),
 (337,'2009-12-06 00:00:00',50,337,0),
 (338,'2009-12-07 00:00:00',51,338,0),
 (339,'2009-12-08 00:00:00',51,339,0),
 (340,'2009-12-09 00:00:00',51,340,0),
 (341,'2009-12-10 00:00:00',51,341,0),
 (342,'2009-12-11 00:00:00',51,342,0),
 (343,'2009-12-12 00:00:00',51,343,0),
 (344,'2009-12-13 00:00:00',51,344,0),
 (345,'2009-12-14 00:00:00',52,345,0),
 (346,'2009-12-15 00:00:00',52,346,0),
 (347,'2009-12-16 00:00:00',52,347,0),
 (348,'2009-12-17 00:00:00',52,348,0),
 (349,'2009-12-18 00:00:00',52,349,0),
 (350,'2009-12-19 00:00:00',52,350,0),
 (351,'2009-12-20 00:00:00',52,351,0),
 (352,'2009-12-21 00:00:00',52,352,0),
 (353,'2009-12-22 00:00:00',52,353,0),
 (354,'2009-12-23 00:00:00',52,354,0),
 (355,'2009-12-24 00:00:00',52,355,0),
 (356,'2009-12-25 00:00:00',52,356,0),
 (357,'2009-12-26 00:00:00',52,357,0),
 (358,'2009-12-27 00:00:00',52,358,0),
 (359,'2009-12-28 00:00:00',52,359,0),
 (360,'2009-12-29 00:00:00',52,360,0),
 (361,'2009-12-30 00:00:00',52,361,0),
 (362,'2009-12-31 00:00:00',52,362,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `time_dim` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
