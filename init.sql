DROP DATABASE IF EXISTS `bigpig`;
CREATE DATABASE IF NOT EXISTS `bigpig` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bigpig`;

CREATE TABLE IF NOT EXISTS `user` (
    `idUser` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL DEFAULT '0',
    `surname` varchar(100) NOT NULL DEFAULT '0',
    `username` varchar(100) NOT NULL DEFAULT '0',
    `password` varchar(100) NOT NULL DEFAULT '0',
    PRIMARY KEY (`idUser`)
);

CREATE TABLE IF NOT EXISTS `user_keys` (
    `publicK` varchar(69) NOT NULL DEFAULT '0',
    `idUser` int(11) NOT NULL,
    `validazioni` BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (`publicK`),
    KEY `idUser` (`idUser`),
    CONSTRAINT `fk_keys_users` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE
);