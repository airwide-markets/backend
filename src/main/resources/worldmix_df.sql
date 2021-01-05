-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 30, 2020 at 11:58 PM
-- Server version: 5.7.29-cll-lve
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `worldmix_df`
--

-- --------------------------------------------------------

--
-- Table structure for table `acc_permissions`
--

CREATE TABLE `acc_permissions` (
  `ID` int(11) NOT NULL,
  `Lft` int(11) NOT NULL,
  `Rght` int(11) NOT NULL,
  `Title` char(64) COLLATE utf8_bin NOT NULL,
  `Description` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `acc_permissions`
--

INSERT INTO `acc_permissions` (`ID`, `Lft`, `Rght`, `Title`, `Description`) VALUES
(1, 0, 1, 'root', 'root'),
(2, 1, 2, 'read_div', 'User can view divisions'),
(3, 3, 4, 'update_div', 'User can edit divisions'),
(4, 5, 6, 'delete_div', 'User can delete divisions'),
(5, 7, 8, 'create_div', 'User can create divisions'),
(6, 9, 10, 'read_scores', 'User can view scoresheets'),
(7, 11, 12, 'update_scores', 'User can edit scoresheets'),
(8, 13, 14, 'delete_scores', 'User can delete scoresheets'),
(9, 15, 16, 'create_scores', 'User can create scoresheets'),
(10, 17, 18, 'read_outlets', 'User can view outlet information'),
(11, 19, 20, 'update_outlets', 'User can edit outlet information'),
(12, 21, 22, 'delete_outlets', 'User can delete outlet information'),
(13, 23, 24, 'create_outlets', 'User can create outlet information'),
(26, 49, 50, 'read_users', 'User can view users'),
(27, 51, 52, 'update_users', 'User can edit users'),
(28, 53, 54, 'delete_users', 'User can delete users'),
(29, 55, 56, 'create_users', 'User can create users'),
(30, 57, 58, 'read_config', 'User can view configuration settings'),
(31, 59, 60, 'update_config', 'User can edit configuration settings'),
(32, 61, 62, 'delete_config', 'User can delete configuration settings'),
(33, 63, 64, 'create_config', 'User can create configuration settings');

-- --------------------------------------------------------

--
-- Table structure for table `acc_rolepermissions`
--

CREATE TABLE `acc_rolepermissions` (
  `RoleID` int(11) NOT NULL,
  `PermissionID` int(11) NOT NULL,
  `AssignmentDate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `acc_rolepermissions`
--

INSERT INTO `acc_rolepermissions` (`RoleID`, `PermissionID`, `AssignmentDate`) VALUES
(1, 1, 1552715635),
(1, 2, 1552717335),
(1, 3, 1552717352),
(1, 4, 1552717335),
(1, 5, 1552717352),
(1, 6, 1552717352),
(1, 7, 1552717352),
(1, 8, 1552717352),
(1, 9, 1552717352),
(1, 10, 1552717352),
(1, 11, 1552717352),
(1, 12, 1552717352),
(1, 13, 1552717352),
(1, 26, 1552717352),
(1, 27, 1552717352),
(1, 28, 1552717352),
(1, 29, 1552717352),
(1, 30, 1552717353),
(1, 31, 1552717353),
(1, 32, 1552717353),
(1, 33, 1552717353),
(2, 12, 1512136282),
(2, 14, 1512136282),
(2, 15, 1512136282),
(2, 16, 1512136282),
(2, 17, 1512136282),
(2, 18, 1512136283),
(2, 19, 1512136283),
(2, 20, 1512136283),
(2, 21, 1512136283),
(2, 22, 1512136283),
(2, 23, 1512136283),
(2, 24, 1512136283),
(2, 25, 1512136283),
(2, 30, 1552884268),
(3, 2, 1512305684),
(3, 3, 1512305684),
(3, 4, 1512305684),
(3, 5, 1512305684),
(3, 6, 1513256370),
(3, 7, 1513256370),
(3, 8, 1513256370),
(3, 9, 1513256370),
(3, 10, 1513256370),
(3, 11, 1513256370),
(3, 12, 1513256370),
(3, 13, 1513256370),
(3, 14, 1513256370),
(3, 15, 1513256370),
(3, 16, 1513256370),
(3, 17, 1513256370),
(3, 18, 1513256370),
(3, 19, 1513256370),
(3, 20, 1513256370),
(3, 21, 1513256370),
(3, 22, 1513256370),
(3, 23, 1513256370),
(3, 24, 1513256370),
(3, 25, 1513256370),
(3, 29, 1513344163);

-- --------------------------------------------------------

--
-- Table structure for table `acc_roles`
--

CREATE TABLE `acc_roles` (
  `ID` int(11) NOT NULL,
  `Lft` int(11) NOT NULL,
  `Rght` int(11) NOT NULL,
  `Title` varchar(128) COLLATE utf8_bin NOT NULL,
  `Description` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `acc_roles`
--

INSERT INTO `acc_roles` (`ID`, `Lft`, `Rght`, `Title`, `Description`) VALUES
(1, 0, 3, 'root', 'root'),
(2, 1, 2, 'Standard', 'This is a user less than an admin');

-- --------------------------------------------------------

--
-- Table structure for table `acc_userroles`
--

CREATE TABLE `acc_userroles` (
  `UserID` int(11) NOT NULL,
  `RoleID` int(11) NOT NULL,
  `AssignmentDate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `acc_userroles`
--

INSERT INTO `acc_userroles` (`UserID`, `RoleID`, `AssignmentDate`) VALUES
(1, 1, 1552715635),
(2, 1, 1512122597),
(3, 1, 1526885949),
(3, 2, 1512123241),
(4, 1, 1529815418),
(4, 3, 1512138422),
(5, 1, 1530451859),
(5, 3, 1512150925),
(6, 3, 1512150990),
(7, 3, 1512317825),
(8, 1, 1513248949),
(9, 3, 1513249081),
(10, 3, 1513249387),
(11, 1, 1513249483),
(12, 3, 1513249532);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parentid` int(11) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `description`, `parentid`) VALUES
(1, 'Property', 'For all your accommodation needs', NULL),
(2, 'Jobs', 'For all your employment needs', NULL),
(3, 'Electronics', 'Get the best computers and gadgets here', NULL),
(4, 'Clothes', 'Get fantastic apparel', NULL),
(5, 'Houses', 'Houses to rent or to buy', 1),
(6, 'Industrial Properties', 'Where to place the next factory', 1),
(7, 'Flats', 'Plenty of flats available here', 5),
(8, 'Cottages', 'There are plenty of cottages available', 5),
(9, 'Stands', 'Start your dream home from scratch', 1),
(10, 'For Women', 'Hot clothes fresh from the designers', 4),
(11, 'For Men', 'Men Grab the best suits available right away', 4),
(12, 'Suits', 'Ace the party', 11),
(13, '2 Piece', 'Steal the occasion', 12),
(14, '3 Piece', 'This is it', 12),
(15, 'Costumes', 'Best Outfits for all you ladies', 10),
(16, 'Industrial Jobs', '', 2),
(17, 'Cement Jobs', '', 16);

-- --------------------------------------------------------

--
-- Table structure for table `countries`
--

CREATE TABLE `countries` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `countries`
--

INSERT INTO `countries` (`id`, `name`) VALUES
(1, 'Zimbabwe'),
(3, 'South Africa');

-- --------------------------------------------------------

--
-- Table structure for table `listings`
--

CREATE TABLE `listings` (
  `id` int(11) UNSIGNED NOT NULL,
  `country` tinyint(1) UNSIGNED DEFAULT NULL,
  `town` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `suburb` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contact` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `number` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `category` int(11) UNSIGNED DEFAULT NULL,
  `pmethods` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `added` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `listings`
--

INSERT INTO `listings` (`id`, `country`, `town`, `suburb`, `contact`, `number`, `description`, `category`, `pmethods`, `added`) VALUES
(2, 1, '2', '4', 'Tapiwanashe Maposhere', '0777333444', 'Come and be a doctor', 7, '2, ', '2019-05-03 06:56:42'),
(3, 1, '', '', 'Tapiwanashe Maposhere', '0777333222', 'For all your accommodation needs', 7, '4, ', '2019-05-03 06:57:28'),
(4, 1, '2', '5', 'Tapiwanashe Maposhere', '0775250298', 'Paying a lot', 17, '4, ', '2019-05-09 16:34:53');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id` int(11) UNSIGNED NOT NULL,
  `user` int(11) UNSIGNED DEFAULT NULL,
  `category` int(11) UNSIGNED DEFAULT NULL,
  `selections` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `intent` tinyint(1) UNSIGNED DEFAULT NULL,
  `location` int(11) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`id`, `user`, `category`, `selections`, `intent`, `location`) VALUES
(1, 10, 3, '[Premium, Foreign Trade, SMS]', 1, 5),
(2, 10, 3, '[Premium, Foreign Trade, SMS]', 0, 5);

-- --------------------------------------------------------

--
-- Table structure for table `pmethods`
--

CREATE TABLE `pmethods` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` tinyint(1) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `pmethods`
--

INSERT INTO `pmethods` (`id`, `name`, `uploader`) VALUES
(1, 'Ecocash', 1),
(2, 'Cash', 1),
(3, 'Transfer', 1),
(4, 'USD$', 1);

-- --------------------------------------------------------

--
-- Table structure for table `subscriber`
--

CREATE TABLE `subscriber` (
  `id` int(11) UNSIGNED NOT NULL,
  `number` double DEFAULT NULL,
  `username` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `key` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Dumping data for table `subscriber`
--

INSERT INTO `subscriber` (`id`, `number`, `username`, `key`) VALUES
(2, 2630775240298, 'Tapiwanashe', 'FXYZRO'),
(3, 263775240298, 'Tapiwanashe', 'MRXJSH');

-- --------------------------------------------------------

--
-- Table structure for table `subscriptions`
--

CREATE TABLE `subscriptions` (
  `id` int(11) UNSIGNED NOT NULL,
  `pollurl` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user` int(11) UNSIGNED DEFAULT NULL,
  `timecreated` datetime DEFAULT NULL,
  `reference` int(11) UNSIGNED DEFAULT NULL,
  `browseurl` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tokens_id` int(11) UNSIGNED DEFAULT NULL,
  `quantity` int(11) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `subscriptions`
--

INSERT INTO `subscriptions` (`id`, `pollurl`, `user`, `timecreated`, `reference`, `browseurl`, `tokens_id`, `quantity`) VALUES
(2, 'https://www.paynow.co.zw/Interface/CheckPayment/?guid=dd07355a-d1ca-4c61-952c-8dcbd17c85a3', 1, '2019-06-06 05:20:53', 1559791248, 'https://www.paynow.co.zw/Payment/ConfirmPayment/3161255', 4, 100);

-- --------------------------------------------------------

--
-- Table structure for table `suburbs`
--

CREATE TABLE `suburbs` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `towns_id` int(11) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `suburbs`
--

INSERT INTO `suburbs` (`id`, `name`, `towns_id`) VALUES
(1, 'Harare East', 4),
(2, 'Dzivarasekwa', 4),
(4, 'Avondale', 2),
(5, 'Greencroft', 2),
(6, 'Test Match', 5),
(7, 'Chikanga', 3),
(8, 'Dangamvura', 3);

-- --------------------------------------------------------

--
-- Table structure for table `tokens`
--

CREATE TABLE `tokens` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tokencount` tinyint(1) UNSIGNED DEFAULT NULL,
  `uploader` tinyint(1) UNSIGNED DEFAULT NULL,
  `minquantity` int(11) UNSIGNED DEFAULT NULL,
  `country` int(11) UNSIGNED DEFAULT NULL,
  `price` double DEFAULT NULL,
  `alter` tinyint(1) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `tokens`
--

INSERT INTO `tokens` (`id`, `name`, `tokencount`, `uploader`, `minquantity`, `country`, `price`, `alter`) VALUES
(4, 'Premium', NULL, 1, 10, 1, 10, NULL),
(5, 'Foreign Trade', NULL, 1, 100, 1, 5, NULL),
(6, 'SMS', NULL, 1, 10, 1, 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `towns`
--

CREATE TABLE `towns` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `countries_id` int(11) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `towns`
--

INSERT INTO `towns` (`id`, `name`, `countries_id`) VALUES
(2, 'Harare', 1),
(3, 'Mutare', 1),
(4, 'Bulawayo', 1),
(5, 'Pretoria', 3);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` int(11) UNSIGNED NOT NULL,
  `pollurl` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user` int(11) UNSIGNED DEFAULT NULL,
  `timecreated` datetime DEFAULT NULL,
  `reference` int(11) UNSIGNED DEFAULT NULL,
  `browseurl` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `pollurl`, `user`, `timecreated`, `reference`, `browseurl`) VALUES
(1, 'https://www.paynow.co.zw/Interface/CheckPayment/?guid=fc51975f-3a99-4798-af04-4bdfafbfb965', 10, '2019-06-06 04:24:48', 1559787888, 'https://www.paynow.co.zw/Payment/ConfirmPayment/3161199'),
(2, 'https://www.paynow.co.zw/Interface/CheckPayment/?guid=e82e4376-53cd-41a4-83a7-cd6a20984548', 10, '2019-06-06 04:26:36', 1559787996, 'https://www.paynow.co.zw/Payment/ConfirmPayment/3161200'),
(3, 'https://www.paynow.co.zw/Interface/CheckPayment/?guid=67e3c4f1-07c3-473e-88d4-845446d2099d', 10, '2019-06-06 04:32:09', 1559788329, 'https://www.paynow.co.zw/Payment/ConfirmPayment/3161203'),
(4, 'https://www.paynow.co.zw/Interface/CheckPayment/?guid=c0816b3a-b6ed-4a20-8865-b9fa8d28746d', 10, '2019-06-06 04:36:56', 1559788615, 'https://www.paynow.co.zw/Payment/ConfirmPayment/3161209'),
(5, 'https://www.paynow.co.zw/Interface/CheckPayment/?guid=03f4a5f9-6bae-40b3-88ff-71c3f1645dc2', 10, '2019-06-06 04:40:41', 1559788839, 'https://www.paynow.co.zw/Payment/ConfirmPayment/3161215');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(16) NOT NULL,
  `username` varchar(42) NOT NULL,
  `password` varchar(42) NOT NULL,
  `name` varchar(42) NOT NULL,
  `email` varchar(191) DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  `uploader` tinyint(1) UNSIGNED DEFAULT NULL,
  `number` varchar(191) DEFAULT NULL,
  `token` varchar(191) DEFAULT NULL,
  `active` int(11) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `name`, `email`, `status`, `uploader`, `number`, `token`, `active`) VALUES
(1, 'Tapiwanashe', 'password', 'Tapiwanashe Maposhere', 'tapiwanashe@seeit.co.zw', 1, NULL, '0717494105', '', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acc_permissions`
--
ALTER TABLE `acc_permissions`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Title` (`Title`),
  ADD KEY `Lft` (`Lft`),
  ADD KEY `Rght` (`Rght`);

--
-- Indexes for table `acc_rolepermissions`
--
ALTER TABLE `acc_rolepermissions`
  ADD PRIMARY KEY (`RoleID`,`PermissionID`);

--
-- Indexes for table `acc_roles`
--
ALTER TABLE `acc_roles`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Title` (`Title`),
  ADD KEY `Lft` (`Lft`),
  ADD KEY `Rght` (`Rght`);

--
-- Indexes for table `acc_userroles`
--
ALTER TABLE `acc_userroles`
  ADD PRIMARY KEY (`UserID`,`RoleID`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `listings`
--
ALTER TABLE `listings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pmethods`
--
ALTER TABLE `pmethods`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subscriber`
--
ALTER TABLE `subscriber`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subscriptions`
--
ALTER TABLE `subscriptions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_foreignkey_subscriptions_tokens` (`tokens_id`);

--
-- Indexes for table `suburbs`
--
ALTER TABLE `suburbs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_foreignkey_suburbs_towns` (`towns_id`);

--
-- Indexes for table `tokens`
--
ALTER TABLE `tokens`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `towns`
--
ALTER TABLE `towns`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_foreignkey_towns_countries` (`countries_id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `acc_permissions`
--
ALTER TABLE `acc_permissions`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `acc_roles`
--
ALTER TABLE `acc_roles`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `countries`
--
ALTER TABLE `countries`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `listings`
--
ALTER TABLE `listings`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pmethods`
--
ALTER TABLE `pmethods`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `subscriber`
--
ALTER TABLE `subscriber`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `subscriptions`
--
ALTER TABLE `subscriptions`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `suburbs`
--
ALTER TABLE `suburbs`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tokens`
--
ALTER TABLE `tokens`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `towns`
--
ALTER TABLE `towns`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(16) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `subscriptions`
--
ALTER TABLE `subscriptions`
  ADD CONSTRAINT `c_fk_subscriptions_tokens_id` FOREIGN KEY (`tokens_id`) REFERENCES `tokens` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `suburbs`
--
ALTER TABLE `suburbs`
  ADD CONSTRAINT `c_fk_suburbs_towns_id` FOREIGN KEY (`towns_id`) REFERENCES `towns` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `towns`
--
ALTER TABLE `towns`
  ADD CONSTRAINT `c_fk_towns_countries_id` FOREIGN KEY (`countries_id`) REFERENCES `countries` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
