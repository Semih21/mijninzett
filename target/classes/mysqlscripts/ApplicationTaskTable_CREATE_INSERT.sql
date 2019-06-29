CREATE TABLE `mijn_inzet`.`taskapplication` (
  `taskId` INT NOT NULL,
  `userId` INT NOT NULL,
  `applicationDate` DATE NOT NULL,
  `unsubscribeDate` DATE NULL,
  `availableHours` INT NOT NULL,
  `roleName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`taskId`, `userId`, `applicationDate`)); VARCHAR(45) NOT NULL,
  PRIMARY KEY (`taskId`, `userId`, `applicationDate`));


INSERT INTO taskapplication (`taskId`,`userId`,`applicationDate`,`unsubscribeDate`,`availableHours',`roleName`) VALUES(1,1,'2019-01-01','0000-00-00',8,'Docent');
INSERT INTO taskapplication (`taskId`,`userId`,`applicationDate`,`unsubscribeDate`,`availableHours',`roleName`) VALUES(2,1,'2019-02-01','0000-00-00',12,'Docent');
INSERT INTO taskapplication (`taskId`,`userId`,`applicationDate`,`unsubscribeDate`,`availableHours',`roleName`) VALUES(3,1,'2018-11-01','0000-00-00',4,'Docent');
INSERT INTO taskapplication (`taskId`,`userId`,`applicationDate`,`unsubscribeDate`,`availableHours',`roleName`) VALUES(4,2,'2019-02-04','0000-00-00',24,'Coordinator');
INSERT INTO taskapplication (`taskId`,`userId`,`applicationDate`,`unsubscribeDate`,`availableHours',`roleName`) VALUES(5,2,'2017-02-03','0000-00-00',100,'Coordinator');
