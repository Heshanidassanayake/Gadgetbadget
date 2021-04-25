-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 05:29 PM
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
-- Database: `gadgetbadget`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserId` int(20) NOT NULL,
  `UserFirstName` varchar(10) NOT NULL,
  `UserLastName` varchar(10) NOT NULL,
  `UserAddress` varchar(50) NOT NULL,
  `UserNIC` varchar(15) NOT NULL,
  `UserPhoneNumber` varchar(10) NOT NULL,
  `UserEmail` varchar(50) NOT NULL,
  `UserName` varchar(20) NOT NULL,
  `UserPassword` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserId`, `UserFirstName`, `UserLastName`, `UserAddress`, `UserNIC`, `UserPhoneNumber`, `UserEmail`, `UserName`, `UserPassword`) VALUES
(1, 'Piumi', 'Dewmini', 'Kandy', '233343546756', '0776595777', 'piumidewmini@gmail.com', 'Piumi', 'piu123'),
(2, 'Lochini', 'Wekadapola', 'Gampola', '233343564543', '0775695777', 'lochini123@gmail.com', 'Lochini', 'Lochi12');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserId` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
