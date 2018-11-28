-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2018 at 08:49 PM
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
-- Database: `banking_system_1235`
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
(1, 'eman', '1234', 169),
(2, 'mahmoud', '123', 197),
(3, 'Eman', '5p/XfOQlcqqRztWrRkUkjw==', 692),
(4, 'Mostafa', '5p/XfOQlcqqRztWrRkUkjw==', 10000),
(5, 'Eman Ashraf', 'BHhUI/ABhqzK+Sn5j4tBgQ==', 975),
(6, 'eman', '5p/XfOQlcqqRztWrRkUkjw==', 10),
(7, 'EMan', '5p/XfOQlcqqRztWrRkUkjw==', 22),
(8, 'ahmed', '5p/XfOQlcqqRztWrRkUkjw==', 100),
(9, 'ddddddd', '/F/WOT074/uEdIdVZQCDEg==', 456),
(10, '123', '5p/XfOQlcqqRztWrRkUkjw==', 123),
(11, 'eman', '5p/XfOQlcqqRztWrRkUkjw==', 835);

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
(7, 3, 'Create account with initial amount 100 LE, at 2018-11-24T14:44:01.176Z'),
(8, 4, 'Create account with initial amount 10000 LE, at 2018-11-24T14:49:24.969Z'),
(9, 3, 'Deposit 100 LE, at 2018-11-24T15:32:33.360Z'),
(10, 3, 'Withdraw 100 LE, at 2018-11-24T15:39:52.809Z'),
(11, 3, 'Withdraw 100 LE, at 2018-11-24T15:39:57.243Z'),
(12, 3, 'Deposit 1000 LE, at 2018-11-24T15:40:02.344Z'),
(13, 3, 'Transfer 10 LE to Account ID : 2, at 2018-11-24T15:48:05.664Z'),
(14, 3, 'Transfer 10 LE to Account ID : 2, at 2018-11-24T15:48:06.022Z'),
(15, 3, 'Transfer 10 LE to Account ID : 2, at 2018-11-24T15:49:30.449Z'),
(16, 3, 'Transfer 6 LE, at 2018-11-24T17:11:43.628Z'),
(17, 5, 'Create account with initial amount 999 LE, at 2018-11-24T19:18:28.431Z'),
(18, 5, 'Withdraw 20 LE, at 2018-11-24T19:19:10.416Z'),
(19, 5, 'Deposit 1 LE, at 2018-11-24T19:19:13.973Z'),
(20, 5, 'Deposit 1 LE, at 2018-11-24T19:19:14.117Z'),
(21, 5, 'Transfer 6 LE to Account ID : 2, at 2018-11-24T19:19:30.441Z'),
(22, 6, 'Create account with initial amount 10 LE, at 2018-11-25T22:58:34.133Z'),
(23, 7, 'Create account with initial amount 22 LE, at 2018-11-25T23:09:00.981Z'),
(24, 8, 'Create account with initial amount 100 LE, at 2018-11-25T23:11:10.283Z'),
(25, 9, 'Create account with initial amount 456 LE, at 2018-11-25T23:12:23.651Z'),
(26, 10, 'Create account with initial amount 123 LE, at 2018-11-25T23:15:26.847Z'),
(27, 3, 'Withdraw 100 LE, at 2018-11-25T23:26:32.893Z'),
(28, 3, 'Deposit 140 LE, at 2018-11-25T23:26:35.338Z'),
(29, 3, 'Deposit 140 LE, at 2018-11-25T23:26:41.873Z'),
(30, 3, 'Deposit 140 LE, at 2018-11-25T23:26:45.550Z'),
(31, 3, 'Withdraw 10 LE, at 2018-11-26T02:29:55.054Z'),
(32, 3, 'Withdraw 10 LE, at 2018-11-26T02:29:55.470Z'),
(33, 3, 'Deposit 30 LE, at 2018-11-26T02:29:58.005Z'),
(34, 3, 'Deposit 30 LE, at 2018-11-26T02:29:58.939Z'),
(35, 3, 'Deposit 30 LE, at 2018-11-26T02:29:59.505Z'),
(36, 3, 'Deposit 30 LE, at 2018-11-26T02:29:59.906Z'),
(37, 3, 'Deposit 30 LE, at 2018-11-26T02:30:03.294Z'),
(38, 3, 'Deposit -2 LE, at 2018-11-26T02:40:15.139Z'),
(39, 3, 'Deposit 10 LE, at 2018-11-26T02:44:30.945Z'),
(40, 3, 'Withdraw 10 LE, at 2018-11-26T03:05:28.012Z'),
(41, 3, 'Transfer 10 LE to Account ID : 2, at 2018-11-26T03:12:00.593Z'),
(42, 3, 'Transfer 5 LE to Account ID : 2, at 2018-11-26T03:25:02.282Z'),
(43, 3, 'Transfer 4 LE, at 2018-11-26T18:05:04.332Z'),
(44, 3, 'Withdraw 10 LE, at 2018-11-26T18:07:44.400Z'),
(45, 3, 'Transfer 22 LE to Account ID : 2, at 2018-11-26T18:11:46.703Z'),
(46, 11, 'Create account with initial amount 1111 LE, at 2018-11-26T18:30:20.012Z'),
(47, 11, 'Deposit 12 LE, at 2018-11-26T18:33:40.764Z'),
(48, 11, 'Deposit 12 LE, at 2018-11-26T18:33:40.841Z'),
(49, 11, 'Withdraw 100 LE, at 2018-11-26T18:35:09.314Z'),
(50, 11, 'Transfer 100 LE to Account ID : 2, at 2018-11-26T18:37:55.408Z'),
(51, 11, 'Transfer 100 LE, at 2018-11-26T18:40:13.069Z'),
(52, 3, 'Transfer 44 LE, at 2018-11-26T19:14:15.109Z'),
(53, 3, 'Transfer 44 LE, at 2018-11-26T19:14:15.249Z'),
(54, 3, 'Transfer 4 LE, at 2018-11-26T19:18:48.069Z'),
(55, 3, 'Transfer 4 LE, at 2018-11-26T19:18:51.535Z'),
(56, 3, 'Transfer 4 LE, at 2018-11-26T19:18:52.525Z'),
(57, 3, 'Transfer 4 LE, at 2018-11-26T19:18:52.935Z'),
(58, 3, 'Transfer 4 LE, at 2018-11-26T19:18:53.346Z'),
(59, 3, 'Transfer 123 LE, at 2018-11-26T19:24:58.285Z'),
(60, 3, 'Transfer 123 LE, at 2018-11-26T19:25:08.640Z'),
(61, 3, 'Transfer 12 LE, at 2018-11-26T19:27:01.278Z'),
(62, 3, 'Transfer 12 LE, at 2018-11-26T19:27:21.440Z'),
(63, 3, 'Transfer 122 LE, at 2018-11-26T19:30:42.952Z'),
(64, 3, 'Transfer 122 LE, at 2018-11-26T19:31:22.388Z'),
(65, 3, 'Transfer 11 LE, at 2018-11-26T19:32:31.748Z'),
(66, 3, 'Transfer 11 LE, at 2018-11-26T19:33:02.407Z'),
(67, 3, 'Transfer 11 LE, at 2018-11-26T19:34:26.777Z'),
(68, 3, 'Transfer 11 LE, at 2018-11-26T19:35:00.772Z'),
(69, 3, 'Transfer 1 LE, at 2018-11-26T19:36:14.776Z'),
(70, 3, 'Transfer 2 LE, at 2018-11-26T19:39:31.216Z');

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
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
