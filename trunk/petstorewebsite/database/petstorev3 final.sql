-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.30-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema petstorev3
--

CREATE DATABASE IF NOT EXISTS petstorev3;
USE petstorev3;

--
-- Definition of table `customer_dim`
--

DROP TABLE IF EXISTS `customer_dim`;
CREATE TABLE `customer_dim` (
  `customerid` int(11) NOT NULL,
  `category` varchar(45) DEFAULT NULL,
  `customertype` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_dim`
--

/*!40000 ALTER TABLE `customer_dim` DISABLE KEYS */;
INSERT INTO `customer_dim` (`customerid`,`category`,`customertype`) VALUES 
 (1,'home',0),
 (2,'home',0),
 (3,'home',0),
 (4,NULL,1),
 (5,NULL,1),
 (6,'home',0),
 (7,NULL,1);
/*!40000 ALTER TABLE `customer_dim` ENABLE KEYS */;


--
-- Definition of table `customerbus`
--

DROP TABLE IF EXISTS `customerbus`;
CREATE TABLE `customerbus` (
  `bcid` int(11) NOT NULL,
  `companyname` varchar(45) DEFAULT NULL,
  `company_desc` varchar(100) DEFAULT NULL,
  `industry` varchar(45) DEFAULT NULL,
  `annual_income` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`bcid`) USING BTREE,
  KEY `customeridbus` (`bcid`) USING BTREE,
  CONSTRAINT `customeridbus` FOREIGN KEY (`bcid`) REFERENCES `customers` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customerbus`
--

/*!40000 ALTER TABLE `customerbus` DISABLE KEYS */;
INSERT INTO `customerbus` (`bcid`,`companyname`,`company_desc`,`industry`,`annual_income`) VALUES 
 (4,'Vick Inc','Vick Inc provides companies with animal food',NULL,NULL),
 (5,'McCoy Corporation','Mccoy corporation is a private business',NULL,NULL),
 (7,'Tim Heating Company','They need food for their guard dogs',NULL,NULL);
/*!40000 ALTER TABLE `customerbus` ENABLE KEYS */;


--
-- Definition of table `customerhome`
--

DROP TABLE IF EXISTS `customerhome`;
CREATE TABLE `customerhome` (
  `hcid` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `preference` varchar(100) DEFAULT NULL,
  `marriage` tinyint(1) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`hcid`) USING BTREE,
  KEY `idcustomerhome` (`hcid`) USING BTREE,
  CONSTRAINT `idcustomerhome` FOREIGN KEY (`hcid`) REFERENCES `customers` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customerhome`
--

/*!40000 ALTER TABLE `customerhome` DISABLE KEYS */;
INSERT INTO `customerhome` (`hcid`,`age`,`preference`,`marriage`,`gender`) VALUES 
 (1,23,'Food for small dogs',0,1),
 (2,45,'Has fish',1,0),
 (3,21,'Food for cat',0,0),
 (6,34,'cat bed',1,1),
 (11,24,'cat supply',0,1);
/*!40000 ALTER TABLE `customerhome` ENABLE KEYS */;


--
-- Definition of table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `streetaddr` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `ctype` tinyint(1) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customers`
--

/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` (`cid`,`streetaddr`,`city`,`state`,`zip`,`ctype`,`lastname`,`firstname`,`phone`) VALUES 
 (1,'1234 centre ave.','Pittsburgh','PA','15232',0,'Mical','Spring',NULL),
 (2,'3237 Longhorn Way','Arlington','TX','87649',0,'McCoy','Colt',NULL),
 (3,'6778 Hokie Lane','Blacksburgh','VA','56723',0,'Vick','Michael',NULL),
 (4,'314 Nothing Street','Pittsburgh','PA','15234',1,'Bradford','Sam',NULL),
 (5,'3237 Buckeye Street','Cleveland','OH','45634',1,'Smith','Troy',NULL),
 (6,'8767 Gator Lane','Gainsville','FL','20984',0,'Tebow','Tim',NULL),
 (7,'8 Boilermaker Way','West Lafayette','IN','47906',1,'Orton','Kyle',NULL),
 (11,'304 S Graham St','Pittsburgh','PA','15232',0,'zhao','jeff','412-482-2222');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;


--
-- Definition of table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `storeid` int(11) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `streetaddr` varchar(45) DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  PRIMARY KEY (`eid`),
  KEY `employeestore` (`storeid`),
  CONSTRAINT `employeestore` FOREIGN KEY (`storeid`) REFERENCES `stores` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee`
--

/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`eid`,`storeid`,`firstname`,`lastname`,`title`,`email`,`streetaddr`,`salary`,`city`,`state`,`zip`,`startdate`) VALUES 
 (1,2,'Govind','Seshadri','sales','govse@email.com','14271 Corporate Drive',NULL,'Garden Grove','CA','92843','2008-05-21'),
 (2,1,'Sarah','Jones','Cashier','sjones@petstorev3.com','3727 Sutherland Drive','45000.00','Pittsburgh','PA','15213','2008-12-12'),
 (3,1,'Mark','Ingrahm','Manager','mingrahm@petstorev3.com','7463 Kiffin lane','55000.00','Pittbsurgh','PA','15234','2008-01-13'),
 (4,7,'Shane','Battier','Cashier','sbattier@petstorev3.com','786 Beach Ave','43000.00','Miami','FL','33127','2006-02-14'),
 (5,7,'Richard','Pen','Manager','rpen@petstorev3.com','8 Palm Ave','54000.00','Miami','FL','33128','2007-03-14'),
 (6,9,'Shawn','Penn','Chashier','spenn@petstorev3.com','76 long lane','45000.00','Dallas','TX','75612','2007-04-03'),
 (7,9,'Ram','Roder','Manager','rroder@petstorev3.com','5 horn street','55000.00','Dallas','TX','75613','2007-05-08'),
 (8,11,'Rob','Zombie','Cashier','rzombie@petstorev3.com','45 Bam Road','48000.00','Albany','CA','94710','2008-06-12'),
 (9,11,'Steve','Slayer','Manager','sslayer@petstorev3.com','23 Red Road','58000.00','Albany','CA','94711','2008-05-23'),
 (10,4,'Dell','Parker','Cashier','sparker@petstorev3.com','37 Penn Hwy','41000.00','Monroeville','PA','15416','2007-07-24'),
 (11,4,'Dusty','Baker','Manager','sbaker@petstorev3.com','67 Penn Road','47000.00','Monroeville','PA','16415','2007-07-12');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


--
-- Definition of table `factsale`
--

DROP TABLE IF EXISTS `factsale`;
CREATE TABLE `factsale` (
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
-- Dumping data for table `factsale`
--

/*!40000 ALTER TABLE `factsale` DISABLE KEYS */;
/*!40000 ALTER TABLE `factsale` ENABLE KEYS */;


--
-- Definition of table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
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
-- Dumping data for table `inventory`
--

/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` (`storeid`,`productid`,`quantity`) VALUES 
 (1,2,23),
 (1,5,111),
 (2,2,99),
 (2,5,75),
 (3,1,20);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;


--
-- Definition of table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail` (
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
-- Dumping data for table `orderdetail`
--

/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` (`oid`,`pid`,`quantity`) VALUES 
 (1,1,2),
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
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;


--
-- Definition of table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
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
-- Dumping data for table `orders`
--

/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`oid`,`sid`,`cid`,`salesmanid`,`odate`,`totalamount`) VALUES 
 (1,1,1,1,'2008-09-10','1050.00'),
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
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


--
-- Definition of table `product_dim`
--

DROP TABLE IF EXISTS `product_dim`;
CREATE TABLE `product_dim` (
  `productid` int(11) NOT NULL,
  `category` varchar(45) NOT NULL,
  `price` decimal(6,2) NOT NULL,
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_dim`
--

/*!40000 ALTER TABLE `product_dim` DISABLE KEYS */;
INSERT INTO `product_dim` (`productid`,`category`,`price`) VALUES 
 (1,'Beds','35.66'),
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
/*!40000 ALTER TABLE `product_dim` ENABLE KEYS */;


--
-- Definition of table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(45) DEFAULT NULL,
  `uniprice` decimal(10,2) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`pid`,`pname`,`uniprice`,`category`) VALUES 
 (1,'dogbed','35.66','Beds'),
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
/*!40000 ALTER TABLE `products` ENABLE KEYS */;


--
-- Definition of table `regions`
--

DROP TABLE IF EXISTS `regions`;
CREATE TABLE `regions` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(45) DEFAULT NULL,
  `managerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `manager` (`managerid`),
  CONSTRAINT `manager` FOREIGN KEY (`managerid`) REFERENCES `employee` (`eid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `regions`
--

/*!40000 ALTER TABLE `regions` DISABLE KEYS */;
INSERT INTO `regions` (`rid`,`rname`,`managerid`) VALUES 
 (8,'NorthWest',NULL),
 (9,'Center',NULL),
 (10,'SouthWest',NULL),
 (11,'SouthEast',NULL),
 (12,'NorthEast',NULL),
 (14,'Great Pittsburgh Area',1);
/*!40000 ALTER TABLE `regions` ENABLE KEYS */;


--
-- Definition of table `store_dim`
--

DROP TABLE IF EXISTS `store_dim`;
CREATE TABLE `store_dim` (
  `storeid` int(11) NOT NULL,
  `regionid` int(11) NOT NULL,
  PRIMARY KEY (`storeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `store_dim`
--

/*!40000 ALTER TABLE `store_dim` DISABLE KEYS */;
INSERT INTO `store_dim` (`storeid`,`regionid`) VALUES 
 (1,8),
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
/*!40000 ALTER TABLE `store_dim` ENABLE KEYS */;


--
-- Definition of table `stores`
--

DROP TABLE IF EXISTS `stores`;
CREATE TABLE `stores` (
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
-- Dumping data for table `stores`
--

/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` (`sid`,`regionid`,`street`,`city`,`state`,`zip`,`manager`,`phone`,`name`) VALUES 
 (1,8,'3500 Mountain View Dr','West Mifflin','PA','15122',1,'412-653-7071','MountainView Store'),
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
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;


--
-- Definition of table `time_dim`
--

DROP TABLE IF EXISTS `time_dim`;
CREATE TABLE `time_dim` (
  `timedimid` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `weekinyear` int(11) NOT NULL,
  `dayinyear` int(11) NOT NULL,
  `holiday` tinyint(1) NOT NULL,
  PRIMARY KEY (`timedimid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `time_dim`
--

/*!40000 ALTER TABLE `time_dim` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_dim` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
