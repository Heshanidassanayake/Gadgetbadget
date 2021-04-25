-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 04:46 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `product`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `itemID` int(11) NOT NULL,
  `itemCode` int(11) NOT NULL,
  `itemName` varchar(30) NOT NULL,
  `itemType` varchar(20) NOT NULL,
  `itemCatogory` varchar(20) NOT NULL,
  `itemPrice` double NOT NULL,
  `itemDesc` varchar(100) NOT NULL,
  `Brand` varchar(20) NOT NULL,
  `Color` varchar(20) NOT NULL,
  `Size` varchar(20) NOT NULL,
  `Meterial` varchar(20) NOT NULL,
  `ItemLocation` varchar(50) NOT NULL,
  `itemQuntity` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`itemID`, `itemCode`, `itemName`, `itemType`, `itemCatogory`, `itemPrice`, `itemDesc`, `Brand`, `Color`, `Size`, `Meterial`, `ItemLocation`, `itemQuntity`) VALUES
(2, 3, 'laptop', 'nortbook', 'electonic', 10000, 'good condition', 'dell', 'silver', '15 cm', 'nikal', 'ithaly', ''),
(4, 2, 'harddisk', 'ssd', 'electronc', 3000, 'good condition', 'hitachi', 'black', '6 cm', 'plastic', 'Srilanka', ''),
(13, 11, 'Monitor', 'LED', 'Electronic', 101000, 'isp pannel 120hz refresh rate 1080*1980', 'Asus', 'Black', '20', 'plastic', 'Thaivan', '300'),
(16, 12, 'samsung', 'Mobile', 'phone', 100, 'good condgood condgood cond', 'aaa', 'black', '6', 'plastic', 'sril', 'sril'),
(17, 12, 'samsung', 'Mobile', 'phone', 100, 'good condgood condgood cond', 'aaa', 'black', '6', 'plastic', 'lanka', '1'),
(18, 11, 'headphone', 'headphone', 'audio', 1000, '100db wired leather design adn good qulity', 'pwomee', 'green', '10', 'plastic', 'USA', '100');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`itemID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `itemID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
