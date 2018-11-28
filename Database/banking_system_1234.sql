-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2018 at 08:48 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banking_system_1234`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `ID` int(11) NOT NULL,
  `full_name` varchar(50) CHARACTER SET utf32 NOT NULL,
  `password` varchar(50) CHARACTER SET utf32 NOT NULL,
  `balance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`ID`, `full_name`, `password`, `balance`) VALUES
(1, 'eman', '1234', 513),
(2, 'mahmoud', '123', 54);

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `ID` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `transaction` varchar(255) CHARACTER SET utf32 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`ID`, `client_id`, `transaction`) VALUES
(1, 1, 'hii'),
(2, 1, 'hii'),
(3, 1, '5ra'),
(4, 1, '5ra'),
(5, 1, '12'),
(6, 1, 'Deposit 15 LE'),
(7, 2, 'Someone deposited 6 LE, at 2018-11-24T16:49:19.358Z'),
(8, 2, 'Someone deposited 6 LE, at 2018-11-24T17:11:43.483Z'),
(9, 2, 'Someone deposited 4 LE, at 2018-11-26T18:05:04.090Z'),
(10, 1, 'Someone deposited 100 LE, at 2018-11-26T18:40:12.949Z'),
(11, 4, 'Someone deposited 44 LE, at 2018-11-26T19:14:14.961Z'),
(12, 4, 'Someone deposited 44 LE, at 2018-11-26T19:14:15.178Z'),
(13, 1111111111, 'Someone deposited 4 LE, at 2018-11-26T19:18:47.974Z'),
(14, 1111111111, 'Someone deposited 4 LE, at 2018-11-26T19:18:51.397Z'),
(15, 1111111111, 'Someone deposited 4 LE, at 2018-11-26T19:18:52.125Z'),
(16, 1111111111, 'Someone deposited 4 LE, at 2018-11-26T19:18:52.727Z'),
(17, 1111111111, 'Someone deposited 4 LE, at 2018-11-26T19:18:53.087Z'),
(18, 4, 'Someone deposited 123 LE, at 2018-11-26T19:24:58.221Z'),
(19, 4, 'Someone deposited 123 LE, at 2018-11-26T19:25:08.597Z'),
(20, 11, 'Someone deposited 12 LE, at 2018-11-26T19:27:01.213Z'),
(21, 2, 'Someone deposited 12 LE, at 2018-11-26T19:27:21.316Z'),
(22, 1, 'Someone deposited 122 LE, at 2018-11-26T19:30:42.885Z'),
(23, 1, 'Someone deposited 122 LE, at 2018-11-26T19:31:22.326Z'),
(24, 11, 'Someone deposited 11 LE, at 2018-11-26T19:32:31.644Z'),
(25, 11, 'Someone deposited 11 LE, at 2018-11-26T19:33:02.319Z'),
(26, 11, 'Someone deposited 11 LE, at 2018-11-26T19:34:26.702Z'),
(27, 11, 'Someone deposited 11 LE, at 2018-11-26T19:35:00.667Z'),
(28, 111, 'Someone deposited 1 LE, at 2018-11-26T19:36:14.684Z'),
(29, 2, 'Someone deposited 2 LE, at 2018-11-26T19:39:31.115Z');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
