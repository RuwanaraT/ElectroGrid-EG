-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 24, 2022 at 10:43 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
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
-- Table structure for table `administrator`
--

CREATE TABLE `administrator` (
  `AdminID` varchar(11) NOT NULL,
  `Full Name` varchar(50) NOT NULL,
  `NIC` varchar(10) NOT NULL,
  `Mobile Phone` int(10) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Salary` float NOT NULL,
  `NoOfWorkingHours` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`AdminID`, `Full Name`, `NIC`, `Mobile Phone`, `Email`, `Salary`, `NoOfWorkingHours`, `username`, `password`) VALUES
('AD001', 'Kamal Rathnayake', '456456456V', 779879876, 'kamal@gmail.com', 450000, 8, 'kamal123', '123#4'),
('AD002', 'Saman Kumara', '98098098V', 980980987, 'saman@gmail.com', 32000, 8, 'saman@124', 'uyt56@');

-- --------------------------------------------------------

--
-- Table structure for table `card`
--

CREATE TABLE `card` (
  `cardNumber` int(11) NOT NULL,
  `acntNumber` int(11) NOT NULL,
  `expiry` varchar(10) NOT NULL,
  `CVC` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `card`
--

INSERT INTO `card` (`cardNumber`, `acntNumber`, `expiry`, `CVC`) VALUES
(456321369, 789653, '02/22', 963),
(478956328, 478112328, '07/24', 178),
(789654213, 145632, '05/25', 789);

-- --------------------------------------------------------

--
-- Table structure for table `complaints`
--

CREATE TABLE `complaints` (
  `ComplaintID` int(11) NOT NULL,
  `accountNumber` char(10) NOT NULL,
  `ContactNo` char(10) NOT NULL,
  `ComplaintType` varchar(40) NOT NULL,
  `Details` varchar(200) NOT NULL,
  `Date` date NOT NULL,
  `Status` varchar(20) NOT NULL DEFAULT 'unresolved'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `complaints`
--

INSERT INTO `complaints` (`ComplaintID`, `accountNumber`, `ContactNo`, `ComplaintType`, `Details`, `Date`, `Status`) VALUES
(9, '0145782900', '0776543299', 'Interrupted Service', 'Power supply is discontinued despite payment of bills', '2022-03-19', 'resolved'),
(10, '123456709', '0711525099', 'Damaged Equipment', 'Meter and cables burnt out due to lightening.', '2022-04-27', 'unresolved'),
(11, '234678091', '0776541230', 'Incorrect Billing', 'Accumulated billing of several months recorded in bill despite continuous payment of bills', '2022-05-11', 'unresolved'),
(15, '0981235889', '0761256890', 'Incorrect Billing', 'Payment for March, 2021 not recorded in most recent bill. Payments were made on time.', '2022-09-12', '\"resolved\"'),
(16, '0981235889', '0761256890', 'Incorrect Billing', 'Payment for March, 2021 not recorded in most recent bill. Payments were made on time.', '2022-09-12', 'unresolved');

-- --------------------------------------------------------

--
-- Table structure for table `devicestable`
--

CREATE TABLE `devicestable` (
  `deviceID` int(11) NOT NULL,
  `device` varchar(50) NOT NULL,
  `powerUsage` decimal(20,2) NOT NULL,
  `hours` int(5) NOT NULL,
  `noOfdevices` int(11) NOT NULL,
  `DailypowerConsumption` decimal(20,2) NOT NULL,
  `MonthlyPowerConsumption` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `devicestable`
--

INSERT INTO `devicestable` (`deviceID`, `device`, `powerUsage`, `hours`, `noOfdevices`, `DailypowerConsumption`, `MonthlyPowerConsumption`) VALUES
(1, 'Fan Heater', '180.00', 5, 2, '1800.00', '54.00'),
(2, 'Printer', '200.00', 2, 1, '400.00', '12.00'),
(3, 'Computer', '500.00', 2, 2, '2000.00', '60.00'),
(4, 'Table fan', '200.00', 2, 1, '400.00', '12.00'),
(5, 'Lamp', '200.00', 2, 1, '400.00', '12.00'),
(7, 'Air Condiitioner', '1000.00', 10, 5, '50000.00', '1500.00'),
(8, 'Electric heater', '500.00', 2, 1, '1000.00', '30.00'),
(9, 'Electric water bottle', '500.00', 2, 2, '2000.00', '60.00'),
(10, 'Vaccum Cleaner', '200.00', 2, 2, '800.00', '24.00'),
(11, 'Tv recorder', '500.00', 2, 1, '1000.00', '30.00'),
(14, 'Electric Iron', '500.00', 2, 1, '1000.00', '30.00'),
(15, 'Television', '180.00', 10, 1, '1800.00', '54.00'),
(16, 'Celing fan', '100.00', 10, 1, '1000.00', '30.00'),
(17, 'Refrigerator', '500.00', 2, 1, '1000.00', '30.00'),
(20, 'Printer', '200.00', 5, 5, '5000.00', '150.00'),
(21, 'Router', '300.00', 20, 2, '12000.00', '360.00');

-- --------------------------------------------------------

--
-- Table structure for table `ebill`
--

CREATE TABLE `ebill` (
  `billID` int(11) NOT NULL,
  `eaNumber` int(11) NOT NULL,
  `cusName` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `billingDate` varchar(100) NOT NULL,
  `tType` varchar(100) NOT NULL DEFAULT 'Domestic',
  `dDates` varchar(100) NOT NULL DEFAULT '30 days',
  `conn` varchar(100) NOT NULL DEFAULT '30A',
  `amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ebill`
--

INSERT INTO `ebill` (`billID`, `eaNumber`, `cusName`, `address`, `billingDate`, `tType`, `dDates`, `conn`, `amount`) VALUES
(2, 34567, 'K. Ranasinghe', 'No:138/A, Lotus road, Gampaha.', '30/03/2022', 'Domestic', '30 days', '30A', 1578.49),
(3, 25436, 'C.Alwis', 'No:10, Galle road, Weligama.', '30/03/2022', 'Domestic', '30 days', '30A', 12435.87),
(4, 46057, 'L.Dissanayake', 'No:138/A, Awissawella road, Kosgama.', '30/03/2022', 'Domestic', '30 days', '30A', 3457.98);

--
-- Triggers `ebill`
--
DELIMITER $$
CREATE TRIGGER `tgr_deleteEBill` BEFORE DELETE ON `ebill` FOR EACH ROW INSERT INTO previous_ebill VALUES(OLD.billID, OLD.eaNumber, OLD.cusName, OLD.address, OLD.billingDate, OLD.tType, OLD.dDates, OLD.conn, OLD.amount, NOW())
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `interrupt`
--

CREATE TABLE `interrupt` (
  `InterruptID` int(11) NOT NULL,
  `interruptCode` varchar(10) NOT NULL,
  `Date` date NOT NULL,
  `Duration` decimal(5,2) NOT NULL,
  `Start_time` time(6) NOT NULL,
  `End_time` time(6) NOT NULL,
  `Region` varchar(30) NOT NULL,
  `Reason` varchar(50) NOT NULL,
  `AdminID` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `interrupt`
--

INSERT INTO `interrupt` (`InterruptID`, `interruptCode`, `Date`, `Duration`, `Start_time`, `End_time`, `Region`, `Reason`, `AdminID`) VALUES
(1, 'IN001', '0000-00-00', '1.00', '08:30:00.000000', '09:30:00.000000', 'Colombo', 'Break Down', 'AD002'),
(2, 'IN002', '2022-09-04', '2.00', '08:00:00.000000', '10:00:00.000000', 'Colombo', 'Lack of Resources', 'AD001'),
(4, 'IN003', '0000-00-00', '1.00', '02:30:00.000000', '03:30:00.000000', 'Colombo', 'On Demand', 'AD001'),
(5, 'IN004', '0000-00-00', '1.00', '11:00:00.000000', '12:00:00.000000', 'Kurunegala', 'Break Down', 'AD002'),
(12, 'IN007', '2022-09-04', '2.00', '09:00:00.000000', '11:00:00.000000', 'Kandy', 'Break Down', 'AD002'),
(13, 'IN008', '2022-04-22', '3.00', '13:00:00.000000', '16:00:00.000000', 'Kaduwela', 'Break Down', 'AD001'),
(15, 'IN009', '2022-04-23', '1.00', '20:00:00.000000', '21:00:00.000000', 'Badulla', 'Break Down', 'AD001'),
(16, 'IN010', '2022-04-24', '2.00', '20:00:00.000000', '22:00:00.000000', 'Kegalle', 'On Demand', 'AD001');

--
-- Triggers `interrupt`
--
DELIMITER $$
CREATE TRIGGER `backup_Interrupt` AFTER DELETE ON `interrupt` FOR EACH ROW insert into interrupt_backup VALUES(old.InterruptID,old.interruptCode,old.Date,old.Duration,old.Start_time,old.End_time,old.Region,old.Reason,old.AdminID)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `interrupt_backup`
--

CREATE TABLE `interrupt_backup` (
  `InterruptID` int(11) NOT NULL,
  `interruptCode` varchar(10) NOT NULL,
  `Date` varchar(30) NOT NULL,
  `Duration` decimal(5,2) NOT NULL,
  `Start_time` varchar(20) NOT NULL,
  `End_time` varchar(20) NOT NULL,
  `Region` varchar(30) NOT NULL,
  `Reason` varchar(50) NOT NULL,
  `AdminID` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `interrupt_backup`
--

INSERT INTO `interrupt_backup` (`InterruptID`, `interruptCode`, `Date`, `Duration`, `Start_time`, `End_time`, `Region`, `Reason`, `AdminID`) VALUES
(8, 'IN006', '23/04/2022', '1.00', '9:00', '10:00', 'Kandy', 'Break Down', ''),
(9, 'IN006', '21/04/2022', '2.00', '9:00', '11:00', 'Kandy', 'Break Down', ''),
(10, 'IN006', '22/04/2022', '2.00', '9:00', '11:00', 'Malabe', 'On Demand', ''),
(6, 'IN005', '0000-00-00', '2.00', '09:00:00.000000', '11:00:00.000000', 'Kandy', 'Break Down', ''),
(11, 'IN006', '0000-00-00', '2.00', '09:00:00.000000', '11:00:00.000000', 'Malabe', 'On Demand', 'AD001');

-- --------------------------------------------------------

--
-- Table structure for table `pay`
--

CREATE TABLE `pay` (
  `paymentID` int(11) NOT NULL,
  `acntNumber` int(11) NOT NULL,
  `billID` int(11) NOT NULL,
  `payAmount` double NOT NULL,
  `cardNumber` int(11) NOT NULL,
  `expiry` varchar(10) NOT NULL,
  `CVC` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pay`
--

INSERT INTO `pay` (`paymentID`, `acntNumber`, `billID`, `payAmount`, `cardNumber`, `expiry`, `CVC`, `date`) VALUES
(1, 789653, 511, 5000, 456321369, '02/22', 963, '2022-04-20 14:30:18'),
(2, 145632, 147, 3500, 789654213, '05/25', 789, '2022-04-21 03:01:01'),
(3, 456398, 963, 720, 478956328, '05/23', 112, '2022-04-21 05:14:53'),
(4, 789653, 555, 450, 456321369, '02/22', 963, '2022-04-21 05:29:47'),
(5, 23659631, 423, 8000, 456698852, '07/24', 559, '2022-04-23 02:45:13');

-- --------------------------------------------------------

--
-- Table structure for table `previous_ebill`
--

CREATE TABLE `previous_ebill` (
  `billID` int(11) NOT NULL,
  `eaNumber` int(11) NOT NULL,
  `cusName` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `billingDate` varchar(100) NOT NULL,
  `tType` varchar(100) NOT NULL,
  `dDates` varchar(100) NOT NULL,
  `conn` varchar(100) NOT NULL,
  `amount` double NOT NULL,
  `delTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `previous_ebill`
--

INSERT INTO `previous_ebill` (`billID`, `eaNumber`, `cusName`, `address`, `billingDate`, `tType`, `dDates`, `conn`, `amount`, `delTime`) VALUES
(4, 25436, 'C.Alwis', 'No:10, Galle road, Weligama.', '30/03/2022', 'Domestic', '30 days', '30A', 12435.87, '2022-04-11 18:58:29'),
(1, 27949, 'R.Perera', 'No:12/B, Temple road, Maharagama.', '30/03/2022', 'Domestic', '30 days', '30A', 1443.79, '2022-04-11 23:13:31'),
(5, 46057, 'L.Dissanayake', 'No:138/A, Awissawella road, Kosgama.', '30/03/2022', 'Domestic', '30 days', '30A', 2543.21, '2022-04-16 16:49:18'),
(5, 12345, 'N.Kalanaka', 'No:14/B, School Lane, Kadawatha', '30/03/2022', 'Domestic', '30 days', '30A', 2500, '2022-04-22 21:31:25');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `acctNumber` int(11) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `NIC` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `mobileNumber` int(11) NOT NULL,
  `landpNumber` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `confirmPassword` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrator`
--
ALTER TABLE `administrator`
  ADD PRIMARY KEY (`AdminID`);

--
-- Indexes for table `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`cardNumber`);

--
-- Indexes for table `complaints`
--
ALTER TABLE `complaints`
  ADD PRIMARY KEY (`ComplaintID`);

--
-- Indexes for table `devicestable`
--
ALTER TABLE `devicestable`
  ADD PRIMARY KEY (`deviceID`);

--
-- Indexes for table `ebill`
--
ALTER TABLE `ebill`
  ADD PRIMARY KEY (`billID`);

--
-- Indexes for table `interrupt`
--
ALTER TABLE `interrupt`
  ADD PRIMARY KEY (`InterruptID`),
  ADD KEY `Admin_FK` (`AdminID`);

--
-- Indexes for table `pay`
--
ALTER TABLE `pay`
  ADD PRIMARY KEY (`paymentID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `complaints`
--
ALTER TABLE `complaints`
  MODIFY `ComplaintID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `devicestable`
--
ALTER TABLE `devicestable`
  MODIFY `deviceID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `ebill`
--
ALTER TABLE `ebill`
  MODIFY `billID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `interrupt`
--
ALTER TABLE `interrupt`
  MODIFY `InterruptID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `pay`
--
ALTER TABLE `pay`
  MODIFY `paymentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
