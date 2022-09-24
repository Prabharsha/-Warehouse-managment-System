DROP DATABASE IF EXISTS Warehouse;
CREATE DATABASE IF NOT EXISTS Warehouse;
SHOW DATABASES;
USE Warehouse;
#=========================================

DROP TABLE IF EXISTS branch;
CREATE TABLE IF NOT EXISTS branch
(
    branchID      VARCHAR(20) NOT NULL,
    branchName    VARCHAR(40) NOT NULL,
    branchAddress VARCHAR(60),
    branchContact VARCHAR(12),
    branchEmail   VARCHAR(40),
    CONSTRAINT PRIMARY KEY (branchID)
);

SHOW TABLES;
DESC branch;
#==========================================

DROP TABLE IF EXISTS supplier;
CREATE TABLE IF NOT EXISTS supplier
(
    supplierID      VARCHAR(20) NOT NULL,
    supplierName    VARCHAR(50),
    supplierAddress VARCHAR(50),
    supplierContact VARCHAR(12),
    amountToBePayed DOUBLE(99, 2) DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY (supplierID)
);
SHOW TABLES;
DESC supplier;
#==========================================

DROP TABLE IF EXISTS employee;
CREATE TABLE IF NOT EXISTS employee
(
    employeeNIC     VARCHAR(20) NOT NULL,
    employeeName    VARCHAR(50),
    employeeAddress VARCHAR(50),
    employeeContact VARCHAR(12),
    employeeJobType VARCHAR(40),
    PRIMARY KEY (employeeNIC)
);
SHOW TABLES;
DESC employee;
#=======================================

DROP TABLE IF EXISTS item;
CREATE TABLE IF NOT EXISTS item
(
    itemID          VARCHAR(10) NOT NULL,
    itemDescription VARCHAR(40),
    itemQNT         INT    DEFAULT 0,
    itemUnitPrice   DOUBLE DEFAULT 0.00,
    supplierID      VARCHAR(20) NOT NULL,
    CONSTRAINT PRIMARY KEY (itemID),
    CONSTRAINT FOREIGN KEY (supplierID) REFERENCES supplier (supplierID) ON DELETE CASCADE ON UPDATE CASCADE
);

#=======================================

DROP TABLE IF EXISTS `orderID`;
CREATE TABLE IF NOT EXISTS `orderID`
(
    orderID VARCHAR(10),
    CONSTRAINT PRIMARY KEY (orderID)
);

#=======================================

DROP TABLE IF EXISTS `Order`;
CREATE TABLE IF NOT EXISTS `Order`
(
    orderID   VARCHAR(10),
    branchID  VARCHAR(20),
    orderDate DATE,
    CONSTRAINT PRIMARY KEY (orderID),
    CONSTRAINT FOREIGN KEY (branchID) REFERENCES branch (branchID) ON DELETE CASCADE ON UPDATE CASCADE

);

SHOW TABLES;
DESC `Order`;

#=========================================
DROP TABLE IF EXISTS deliveryTeam;
CREATE TABLE IF NOT EXISTS deliveryTeam
(
    teamID      VARCHAR(10) NOT NULL,
    teamName    VARCHAR(30),
    teamContact VARCHAR(12),
    teamAddress VARCHAR(40),
    CONSTRAINT PRIMARY KEY (teamID)
);

SHOW TABLES;
DESC deliveryTeam;

#=========================================

DROP TABLE IF EXISTS orderDetails;
CREATE TABLE IF NOT EXISTS orderDetails
(
    orderID         VARCHAR(10),
    itemID          VARCHAR(20),#F
    itemDescription VARCHAR(40),
    itemQNT         INT,
    CONSTRAINT PRIMARY KEY (orderID, itemID),
    CONSTRAINT FOREIGN KEY (itemID) REFERENCES item (itemID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (orderID) REFERENCES `Order` (orderID) ON DELETE CASCADE ON UPDATE CASCADE
);

SHOW TABLES;
DESC orderDetails;

#========================================

DROP TABLE IF EXISTS payment;
CREATE TABLE IF NOT EXISTS payment
(
    paymentID  VARCHAR(10) NOT NULL,
    supplierID VARCHAR(20) NOT NULL,
    date       DATE,
    amount     DOUBLE DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY (paymentID),
    CONSTRAINT FOREIGN KEY (supplierID) REFERENCES supplier (supplierID)
);
SHOW TABLES;
DESC payment;
#=========================================

DROP TABLE IF EXISTS salary;
CREATE TABLE IF NOT EXISTS salary
(
    employeeNIC VARCHAR(20) NOT NULL,
    amount      DOUBLE DEFAULT 0.00,
    month       VARCHAR(8),
    CONSTRAINT PRIMARY KEY (employeeNIC, month),
    CONSTRAINT FOREIGN KEY (employeeNIC) REFERENCES employee (employeeNIC)
);

SHOW TABLES;
DESC salary;
#=========================================

DROP TABLE IF EXISTS retunedItems;
CREATE TABLE IF NOT EXISTS returnedItems
(
    returnID VARCHAR(10),
    itemID   VARCHAR(10) NOT NULL,
    branchID VARCHAR(20) NOT NULL,
    date     DATE,
    CONSTRAINT PRIMARY KEY (returnID),
    CONSTRAINT FOREIGN KEY (branchID) REFERENCES branch (branchID),
    CONSTRAINT FOREIGN KEY (itemID) REFERENCES item (itemID)
);

SHOW TABLES;
DESC returnedItems;
#========================================

DROP TABLE IF EXISTS logInDetails;
CREATE TABLE IF NOT EXISTS logInDetailS
(
    userName     VARCHAR(4) NOT NULL,
    UserPassword VARCHAR(4) NOT NULL,
    userType     VARCHAR(4) NOT NULL,
    CONSTRAINT PRIMARY KEY (userName)
);

SHOW TABLES;
DESC logInDetailS;
#========================================

DROP TABLE IF EXISTS C;
CREATE TABLE IF NOT EXISTS `dispatchedOrders`
(
    orderID        VARCHAR(10),
    branchID       VARCHAR(20),
    teamName       VARCHAR(30),
    orderDate      VARCHAR(11),
    dispatchedDate VARCHAR(11),

    CONSTRAINT PRIMARY KEY (orderID, branchID),
    CONSTRAINT FOREIGN KEY (orderID) REFERENCES orderID (orderID),
    CONSTRAINT FOREIGN KEY (branchID) REFERENCES branch (branchID)
);
SHOW TABLES;
DESC `dispatchedOrders`;

#========================================

DROP TABLE IF EXISTS `dispatchedOrderDetails`;
CREATE TABLE IF NOT EXISTS `dispatchedOrderDetails`
(
    orderID         VARCHAR(10),
    itemID          VARCHAR(20),
    itemDEscription VARCHAR(50),
    itemQNT         INT,

    CONSTRAINT PRIMARY KEY (orderID, itemID),
    CONSTRAINT FOREIGN KEY (orderID) REFERENCES orderID (orderID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (itemID) REFERENCES item (itemID) ON DELETE CASCADE ON UPDATE CASCADE

);
SHOW TABLES;
DESC dispatchedOrderDetails;

#==========================================




