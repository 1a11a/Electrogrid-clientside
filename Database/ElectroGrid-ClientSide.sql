phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 07:32 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `consumptions`
--

CREATE TABLE `consumptions` (
  `ConsumptionID` Varchar(255) NOT NULL,
  `CustomerName` varchar(255) NOT NULL,
  `CustomerUsage` varchar(255) NOT NULL,
  `price` varchar(255) NOT NULL,
  `CustomerType` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consumptions`
--

INSERT INTO `consumptions` (`ConsumptionID`, `CustomerName`, `CustomerUsage`, `price`, `CustomerType`) VALUES
(2, 'Subhashana', '40 units', '400', 'Platinum'),
(3, 'Prabhavi', '21 units', '210', 'Silver'),
(4, 'Supun', '21 units', '210', 'silver');

-- --------------------------------------------------------
--
-- Indexes for dumped tables
--

--
-- Indexes for table `consumptions`
--
ALTER TABLE `consumptions`
  ADD PRIMARY KEY (`ConsumptionID`);

--
-- AUTO_INCREMENT for dumped tables

--
-- AUTO_INCREMENT for table `consumptions`
--
ALTER TABLE `consumptions`
  MODIFY `ConsumptionID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;