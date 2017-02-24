ALTER TABLE `ItemsColor` DROP FOREIGN KEY `color`;
ALTER TABLE `Cart` DROP FOREIGN KEY `cartitems`;
ALTER TABLE `Cart` DROP FOREIGN KEY `cartusers`;
ALTER TABLE `Size` DROP FOREIGN KEY `sizeitems`;

ALTER TABLE `Users`DROP PRIMARY KEY;
ALTER TABLE `Items`DROP PRIMARY KEY;

DROP TABLE `Users`;
DROP TABLE `Items`;
DROP TABLE `ItemsColor`;
DROP TABLE `Cart`;
DROP TABLE `Size`;

CREATE TABLE `Users` (
`userid` int NOT NULL AUTO_INCREMENT,
`username` char(55) NOT NULL,
`useremail` char(105) NOT NULL,
`userpassword` char(105) NOT NULL,
PRIMARY KEY (`userid`) 
);

CREATE TABLE `Items` (
`itemid` int NOT NULL AUTO_INCREMENT,
`itemname` char(55) NOT NULL,
`itemdescription` char(255) NOT NULL,
`itemcost` decimal(10,2) NOT NULL,
`itemviews` int NULL,
PRIMARY KEY (`itemid`) 
);

CREATE TABLE `ItemsColor` (
`itemid` int NOT NULL,
`color` char(15) NOT NULL
);

CREATE TABLE `Cart` (
`userid` int NOT NULL,
`itemid` int NOT NULL
);

CREATE TABLE `Size` (
`itemid` int NOT NULL,
`size` int NOT NULL
);


ALTER TABLE `ItemsColor` ADD CONSTRAINT `color` FOREIGN KEY (`itemid`) REFERENCES `Items` (`itemid`);
ALTER TABLE `Cart` ADD CONSTRAINT `cartitems` FOREIGN KEY (`itemid`) REFERENCES `Items` (`itemid`);
ALTER TABLE `Cart` ADD CONSTRAINT `cartusers` FOREIGN KEY (`userid`) REFERENCES `Users` (`userid`);
ALTER TABLE `Size` ADD CONSTRAINT `sizeitems` FOREIGN KEY (`itemid`) REFERENCES `Items` (`itemid`);

