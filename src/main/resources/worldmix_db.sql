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
-- Database: `worldmix_db`
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
  `name` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `description` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `parentid` int(11) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `description`, `parentid`) VALUES
(81, 'Cars', 'Vehicles', NULL),
(82, 'Services', 'Services', NULL),
(83, 'Accounting Services', 'Accounting Services', 82),
(84, 'Auto Services', 'Auto Services', 82),
(85, 'Building Services', 'Building Services', 82),
(86, 'Boat Services', 'Boat Services', 82),
(87, 'Aston Martin', 'Aston Martin', 81),
(88, 'Audi', 'Audi', 81),
(89, 'Bentley', 'Bentley', 81),
(90, 'BMW', 'BMW', 81),
(91, 'Cadillac', 'Cadillac', 81),
(92, 'Chevrolet', 'Chevrolet', 81),
(93, 'Chrysler', 'Chrysler', 81),
(94, 'Datsun', 'Datsun', 81),
(95, 'Dodge', 'Dodge', 81),
(97, 'GMC', 'GMC', 81),
(98, 'Grand Cherokee', 'Grand Cherokee', 81),
(99, 'Honda', 'Honda', 81),
(100, 'Hyundai', 'Hyundai', 81),
(101, 'Infiniti', 'Infiniti', 81),
(102, 'Jaguar', 'Jaguar', 81),
(103, 'Jeep', 'Jeep', 81),
(104, 'Kia', 'Kia', 81),
(105, 'Lamborghini', 'Lamborghini', 81),
(106, 'Land Rover', 'Land Rover', 81),
(107, 'Lexus', 'Lexus', 81),
(108, 'Lincoln', 'Lincoln', 81),
(109, 'Lotus', 'Lotus', 81),
(110, 'Maserati', 'Maserati', 81),
(111, 'Maybach', 'Maybach', 81),
(112, 'Mazda', 'Mazda', 81),
(113, 'Mercedes', 'Mercedes', 81),
(114, 'Mercedes-AMG', 'Mercedes-AMG', 81),
(115, 'Mercury', 'Mercury', 81),
(116, 'MINI', 'MINI', 81),
(117, 'Mitsubishi', 'Mitsubishi', 81),
(118, 'Nissan', 'Nissan', 81),
(119, 'Porsche', 'Porsche', 81),
(120, 'Rolls-Royce', 'Rolls-Royce', 81),
(121, 'Scion', 'Scion', 81),
(122, 'Subaru', 'Subaru', 81),
(123, 'Suzuki', 'Suzuki', 81),
(124, 'Toyota', 'Toyota', 81),
(125, 'Volkswagen', 'Volkswagen', 81),
(126, 'Volvo', 'Volvo', 81),
(127, 'Peugeot', 'Peugeot', 81),
(128, 'Daihatsu', 'Daihatsu', 81),
(129, 'Opel', 'Opel', 81),
(130, 'Ford', 'Ford', 81),
(131, 'Renault', 'Renault', 81),
(132, 'Iveco', 'Iveco', 81),
(133, 'Alpha Romeo', 'Alpha Romeo', 81),
(134, 'Cat', 'Cat', 81),
(135, 'Case', 'Case', 81),
(136, 'Citroen', 'Citroen', 81),
(137, 'Saturn', 'Saturn', 81),
(138, 'Oldsmobile', 'Oldsmobile', 81),
(139, 'Pontiac', 'Pontiac', 81),
(140, 'Bridal Services', 'Bridal Services', 82),
(141, 'Business Services', 'Business Services', 82),
(142, 'Car Rental Agencies', 'Car Rental Agencies', 82),
(143, 'Catering Services', 'Catering Services', 82),
(144, 'Children’s Services', 'Children’s Services', 81),
(145, 'Check-Cashing Services', 'Check-Cashing Services', 82),
(146, 'Cleaning Services', 'Cleaning Services', 82),
(147, 'Check-Cashing Services', 'Check-Cashing Services', 82),
(148, 'Consulting Services', 'Consulting Services', 82),
(149, 'Contractor Services', 'Contractor Services', 82),
(150, 'Copywriting & Proof Services', 'Copywriting & Proof Services', 82),
(151, 'Cover Letter/Resume Services', 'Cover Letter/Resume Services', 82),
(152, 'Dating Services', 'Dating Services', 82),
(153, 'Decorating Services', 'Decorating Services', 82),
(154, 'Designing Services', 'Designing Services', 82),
(155, 'DJ Businesses', 'DJ Businesses', 82),
(156, 'Dry Cleaning & Laundry Services', 'Dry Cleaning & Laundry Services', 82),
(157, 'Dry Cleaning Delivery', 'Dry Cleaning Delivery', 82),
(158, 'Editorial Services', 'Editorial Services', 82),
(159, 'Educational Services', 'Educational Services', 82),
(160, 'Electrical Services', 'Electrical Services', 82),
(161, 'Employment Services', 'Employment Services', 82),
(162, 'Environmental Services', 'Environmental Services', 82),
(163, 'Errand Services', 'Errand Services', 82),
(164, 'Event Planning', 'Event Planning', 82),
(165, 'Eye-Care Centers', 'Eye-Care Centers', 82),
(166, 'Financial Services', 'Financial Services', 82),
(167, 'Fitness Centers', 'Fitness Centers', 82),
(168, 'Hair Salons', 'Hair Salons', 82),
(169, 'Handyman Services', 'Handyman Services', 82),
(170, 'Health-Care Services', 'Health-Care Services', 82),
(171, 'Home-Improvement Services', 'Home-Improvement Services', 82),
(172, 'Lawn Care & Landscaping', 'Lawn Care & Landscaping', 82),
(173, 'Limousine Services', 'Limousine Services', 82),
(174, 'Maintenance Services', 'Maintenance Services', 82),
(175, 'Message Therapist', 'Message Therapist', 82),
(176, 'Moving Services', 'Moving Services', 82),
(177, 'Painting Services', 'Painting Services', 82),
(178, 'Personal-Care Services', 'Personal-Care Services', 82),
(179, 'Personal Chef', 'Personal Chef', 82),
(180, 'Pest Control Services', 'Pest Control Services', 82),
(181, 'Pet-Care Services', 'Pet-Care Services', 82),
(182, 'Photography Services', 'Photography Services', 82),
(183, 'Plant Maintenance Services', 'Plant Maintenance Services', 82),
(184, 'Plumbing Services', 'Plumbing Services', 82),
(185, 'Pool Services', 'Pool Services', 82),
(186, 'Postal & Business Centers', 'Postal & Business Centers', 82),
(187, 'Printing Services', 'Printing Services', 82),
(188, 'Private Investigation', 'Private Investigation', 82),
(189, 'Property Inspection', 'Property Inspection', 82),
(190, 'Property Management Services', 'Property Management Services', 82),
(191, 'Publishing Services', 'Publishing Services', 82),
(192, 'Real Estate Services', 'Real Estate Services', 82),
(193, 'Recreational Services', 'Recreational Services', 82),
(194, 'Referral Services', 'Referral Services', 82),
(195, 'Accommodation', 'Houses, Flats, Cottages', NULL),
(196, 'Harare', 'Area', 195),
(197, 'Avondale', 'Location', 196),
(198, 'House', 'Type', 197),
(199, 'Cottage', 'Type', 197),
(200, 'Flat', 'Type', 197),
(201, 'Room(s)', 'Type', 197),
(202, 'Accommodation (Rent/Lease)', 'Houses, Flats, Cottages', NULL),
(203, 'Harare', 'Area', 202),
(204, 'Avondale', 'Location', 203),
(205, 'House', 'Type', 204),
(206, 'Cottage', 'Type', 203),
(207, 'Cottage', 'Type', 205),
(208, 'Accommodation (Rent/Lease)', 'Houses, Flats, Cottages', NULL),
(209, 'Harare', 'Area', 208),
(210, 'Avondale', 'Location', 209),
(211, 'Adylinn', 'Area', 209),
(212, 'Alexandra Park', 'Area', 209),
(213, 'Amby', 'Area', 209),
(214, 'Arcadia', 'Area', 209),
(215, 'Ardbennie', 'Area', 209),
(216, 'Arlington', 'Area', 209),
(217, 'Ashbritte', 'Area', 209),
(218, 'Ashdown Park', 'Area', 209),
(219, 'Aspindale Park', 'Area', 209),
(220, 'Athlone', 'Area', 209),
(221, 'Avenues', 'Area', 209),
(222, 'Avondale West', 'Area', 209),
(223, 'Avonlea', 'Area', 209),
(224, 'Ballantyne Park', 'Area', 209),
(225, 'Belgravia', 'Area', 209),
(226, 'Belvedere', 'Area', 209),
(227, 'Beverley', 'Area', 209),
(228, 'Beverley West', 'Area', 209),
(229, 'Bloomingdale', 'Area', 209),
(230, 'Bluff Hill', 'Area', 209),
(231, 'Borrowdale', 'Area', 209),
(232, 'Braeside', 'Area', 209),
(233, 'Brooke Ridge', 'Area', 209),
(234, 'Budiriro', 'Area', 209),
(235, 'Carrick Creagh', 'Area', 209),
(236, 'Chadcombe', 'Area', 209),
(237, 'Chikurubi', 'Area', 209),
(238, 'Chiremba Park', 'Area', 209),
(239, 'Chisipite', 'Area', 209),
(240, 'City Centre', 'Area', 209),
(241, 'Civic Centre', 'Area', 209),
(242, 'Cold Comfort', 'Area', 209),
(243, 'Colne Valley', 'Area', 209),
(244, 'Colray', 'Area', 209),
(245, 'Coronation Park', 'Area', 209),
(246, 'Cotswold Hills', 'Area', 209),
(247, 'Cranbourne Park', 'Area', 209),
(248, 'Crowborough', 'Area', 209),
(249, 'Dawn Hill', 'Area', 209),
(250, 'Dzivarasekwa', 'Area', 209),
(251, 'Eastlea', 'Area', 209),
(252, 'Emerald Hill', 'Area', 209),
(253, 'Epworth', 'Area', 209),
(254, 'Gevstein Park', 'Area', 209),
(255, 'Glaudina', 'Area', 209),
(256, 'Glen Lorne', 'Area', 209),
(257, 'Glen Norah', 'Area', 209),
(258, 'Glen View', 'Area', 209),
(259, 'Glenwood', 'Area', 209),
(260, 'Graniteside', 'Area', 209),
(261, 'Green Grove', 'Area', 209),
(262, 'Greencroft', 'Area', 209),
(263, 'Greendale', 'Area', 209),
(264, 'Greystone Park', 'Area', 209),
(265, 'Grobbie Park', 'Area', 209),
(266, 'Groombridge', 'Area', 209),
(267, 'Gun Hill', 'Area', 209),
(268, 'Haig Park', 'Area', 209),
(269, 'Hatcliffe', 'Area', 209),
(270, 'Hatfield', 'Area', 209),
(271, 'Helensvale', 'Area', 209),
(272, 'Highfield', 'Area', 209),
(273, 'Highlands', 'Area', 209),
(274, 'Hillside', 'Area', 209),
(275, 'Hogerty Hill', 'Area', 209),
(276, 'Hopley', 'Area', 209),
(277, 'Houghton Park', 'Area', 209),
(278, 'Induna', 'Area', 209),
(279, 'Kambanje', 'Area', 209),
(280, 'Kambuzuma', 'Area', 209),
(281, 'Kensington', 'Area', 209),
(282, 'Kopje', 'Area', 209),
(283, 'Kuwadzana', 'Area', 209),
(284, 'Letombo Park', 'Area', 209),
(285, 'Lewisam', 'Area', 209),
(286, 'Lichendale', 'Area', 209),
(287, 'Little Norfolk', 'Area', 209),
(288, 'Lochinvar', 'Area', 209),
(289, 'Logan Park', 'Area', 209),
(290, 'Lorelei', 'Area', 209),
(291, 'Luna', 'Area', 209),
(292, 'Mabelreign', 'Area', 209),
(293, 'Mabvuku', 'Area', 209),
(294, 'Madokero', 'Area', 209),
(295, 'Mainway Meadows', 'Area', 209),
(296, 'Malvern', 'Area', 209),
(297, 'Mandara', 'Area', 209),
(298, 'Manidoda Park', 'Area', 209),
(299, 'Manresa', 'Area', 209),
(300, 'Marimba Park', 'Area', 209),
(301, 'Marlborough', 'Area', 209),
(302, 'Mayfield Park', 'Area', 209),
(303, 'Mbare', 'Area', 209),
(304, 'Meyrick Park', 'Area', 209),
(305, 'Midlands', 'Area', 209),
(306, 'Milton Park', 'Area', 209),
(307, 'Monovale', 'Area', 209),
(308, 'Mount Hampden', 'Area', 209),
(309, 'Mount Pleasant', 'Area', 209),
(310, 'Msasa Park', 'Area', 209),
(311, 'Mufakose', 'Area', 209),
(312, 'Mukuvisi Park', 'Area', 209),
(313, 'New Forest', 'Area', 209),
(314, 'Newlands', 'Area', 209),
(315, 'Nkwisi Park', 'Area', 209),
(316, 'Northwood', 'Area', 209),
(317, 'Meadowlands Park', 'Area', 209),
(318, 'Parktown', 'Area', 209),
(319, 'Philadelphia', 'Area', 209),
(320, 'Pomona', 'Area', 209),
(321, 'Prospect', 'Area', 209),
(322, 'Queensdale', 'Area', 209),
(323, 'Quinnington', 'Area', 209),
(324, 'Rhodesville', 'Area', 209),
(325, 'Ridgeview', 'Area', 209),
(326, 'Rietfontein', 'Area', 209),
(327, 'Ringley', 'Area', 209),
(328, 'Rolf Valley', 'Area', 209),
(329, 'Rugare', 'Area', 209),
(330, 'Runniville', 'Area', 209),
(331, 'Ryelands', 'Area', 209),
(332, 'Sanganai Park', 'Area', 209),
(333, 'Science Park', 'Area', 209),
(334, 'Sentosa', 'Area', 209),
(335, 'Shawasha Hills', 'Area', 209),
(336, 'Sherwood Park', 'Area', 209),
(337, 'Shortson', 'Area', 209),
(338, 'Southerton', 'Area', 209),
(339, 'St. Andrews Park', 'Area', 209),
(340, 'St. Martins', 'Area', 209),
(341, 'Strathaven', 'Area', 209),
(342, 'Sunningdale', 'Area', 209),
(343, 'Sunridge', 'Area', 209),
(344, 'Sunrise', 'Area', 209),
(345, 'Tafara', 'Area', 209),
(346, 'The Grange', 'Area', 209),
(347, 'Tynwald', 'Area', 209),
(348, 'Tynwald North', 'Area', 209),
(349, 'Tynwald South', 'Area', 209),
(350, 'Umwinsidale', 'Area', 209),
(351, 'Uplands', 'Area', 209),
(352, 'Vainona', 'Area', 209),
(353, 'Valencedene', 'Area', 209),
(354, 'Ventersburg', 'Area', 209),
(355, 'Warren Park', 'Area', 209),
(356, 'Warren Park D', 'Area', 209),
(357, 'Waterfalls', 'Area', 209),
(358, 'Westwood', 'Area', 209),
(359, 'Willowvale', 'Area', 209),
(360, 'Wilmington Park', 'Area', 209),
(361, 'Workington', 'Area', 209),
(362, 'House', 'Type', 210),
(363, 'Cottage', 'Type', 210),
(364, 'Flat', 'Type', 210),
(365, 'Room(s)', 'Type', 210),
(366, 'House', 'Type', 211),
(367, 'Cottage', 'Type', 211),
(368, 'Flat', 'Type', 211),
(369, 'Room(s)', 'Type', 211),
(370, 'House', 'Type', 212),
(371, 'Cottage', 'Type', 212),
(372, 'Flat', 'Type', 212),
(373, 'Room(s)', 'Type', 212),
(374, 'House', 'Type', 213),
(375, 'Flat', 'Type', 374),
(376, 'Flat', 'Type', 213),
(377, 'Cottage', 'Type', 213),
(378, 'Room(s)', 'Type', 213),
(379, 'House', 'Type', 214),
(380, 'Cottage', 'Type', 214),
(381, 'Flat', 'Type', 214),
(382, 'Room(s)', 'Type', 214),
(383, 'House', 'Type', 215),
(384, 'Cottage', 'Type', 215),
(385, 'Flat', 'Type', 215),
(386, 'Room(s)', 'Type', 215),
(387, 'House', 'Type', 216),
(388, 'Cottage', 'Type', 216),
(389, 'Flat', 'Type', 216),
(390, 'Room(s)', 'Type', 216),
(391, 'House', 'Type', 217),
(392, 'Cottage', 'Type', 217),
(393, 'Flat', 'Type', 217),
(394, 'Room(s)', 'Type', 217),
(395, 'House', 'Type', 218),
(396, 'Cottage', 'Type', 218),
(397, 'Flat', 'Type', 218),
(398, 'Room(s)', 'Type', 218),
(399, 'House', 'Type', 219),
(400, 'Cottage', 'Type', 219),
(401, 'Flat', 'Type', 219),
(402, 'Room(s)', 'Type', 219),
(403, 'House', 'Type', 220),
(404, 'Cottage', 'Type', 220),
(405, 'Flat', 'Type', 220),
(406, 'Room(s)', 'Type', 220);

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

INSERT INTO countries (id, name) VALUES
(1, 'Zimbabwe'),
(3, 'South Africa'),
(4, 'Zambia');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id` int(11) UNSIGNED NOT NULL,
  `user` int(11) UNSIGNED DEFAULT NULL,
  `category` int(11) UNSIGNED DEFAULT NULL,
  `selections` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `intent` tinyint(1) UNSIGNED DEFAULT NULL,
  `location` int(11) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`id`, `user`, `category`, `selections`, `intent`, `location`) VALUES
(1, 11, 5, '[Premium, Foreign Trade, SMS]', 0, 5),
(2, 2, 81, '[Premium, Foreign Trade]', 1, 5);

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
(2, 'Swipe', 1),
(3, 'Visa', 1),
(4, 'MasterCard', 1),
(5, 'M-Pesa', 1);

-- --------------------------------------------------------

--
-- Table structure for table `searchable`
--

CREATE TABLE `searchable` (
  `id` int(11) UNSIGNED NOT NULL,
  `value` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `listing` int(11) UNSIGNED DEFAULT NULL,
  `fields_id` int(11) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Dumping data for table `searchable`
--

INSERT INTO `searchable` (`id`, `value`, `listing`, `fields_id`) VALUES
(1, '100', 1, NULL),
(2, 'Individual', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `subscriber`
--

CREATE TABLE `subscriber` (
  `id` int(11) UNSIGNED NOT NULL,
  `number` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `username` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `key` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `country` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `fullnumber` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `active` int(11) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Dumping data for table `subscriber`
--

INSERT INTO `subscriber` (`id`, `number`, `username`, `password`, `key`, `country`, `fullnumber`, `token`, `active`) VALUES
(1, '775240298', 'Tapiwanashe', 'password', 'DJKQ', 'ZW', '263775240298', 'token', 1),
(2, '0778512351', 'Chris', 'password', 'FP9M', NULL, '0778512351', 'token', 1);

-- --------------------------------------------------------

--
-- Table structure for table `subscription`
--

CREATE TABLE `subscription` (
  `id` int(11) UNSIGNED NOT NULL,
  `category` int(11) UNSIGNED DEFAULT NULL,
  `user` int(11) UNSIGNED DEFAULT NULL,
  `intent` tinyint(1) UNSIGNED DEFAULT NULL,
  `number` varchar(191) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Dumping data for table `subscription`
--

INSERT INTO `subscription` (`id`, `category`, `user`, `intent`, `number`) VALUES
(1, 87, 1, 0, '775240298'),
(2, 89, 1, 0, '775240298'),
(3, 142, 1, 1, '775240298'),
(4, 194, 1, 0, '775240298'),
(5, 192, 1, 1, '775240298'),
(6, 200, 22, 0, '0773374304'),
(8, 90, 22, 0, '0773374304'),
(9, 198, 24, 1, '0776168743'),
(10, 89, 24, 0, '0776168743'),
(11, 89, 22, 1, '0773374304'),
(12, 87, 24, 0, '0776168743'),
(13, 87, 22, 1, '0773374304'),
(14, 198, 24, 0, '0776168743'),
(15, 198, 22, 0, '0773374304'),
(16, 201, 22, 0, '0773374304'),
(17, 201, 22, 1, '0773374304'),
(18, 217, 22, 0, '0773374304'),
(19, 369, 22, 0, '0773374304'),
(20, 362, 22, 1, '0773374304'),
(21, 363, 22, 0, '0773374304'),
(22, 396, 22, 1, '0773374304'),
(23, 372, 22, 0, '0773374304'),
(24, 90, 22, 0, '0773374304'),
(25, 90, 26, 1, '0776376207'),
(26, 90, 26, 0, '0776376207'),
(27, 370, 22, 0, '0773374304'),
(28, 365, 22, 0, '0773374304'),
(29, 362, 22, 0, '0773374304'),
(30, 362, 22, 1, '0773374304'),
(31, 87, 2, 0, '0778512351'),
(32, 87, 2, 0, '0778512351');

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
(6, 'Avondale', 5);

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
(3, 'Foreign Trade - $2', NULL, 1, 100, 1, 2, NULL),
(4, 'SMS Notification - $2', NULL, 1, 100, 1, 2, NULL),
(5, 'Premium - $2', NULL, 1, 100, 1, 2, NULL);

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
(5, 'Lusaka', 4),
(6, 'Kitwe', 4),
(7, 'Gweru', 1),
(8, 'Kwekwe', 1),
(10, 'Masvingo', 1),
(11, 'Chinhoyi', 1),
(12, 'Chirundu', 1);

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
-- Indexes for table `searchable`
--
ALTER TABLE `searchable`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_foreignkey_searchable_fields` (`fields_id`);

--
-- Indexes for table `subscriber`
--
ALTER TABLE `subscriber`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subscription`
--
ALTER TABLE `subscription`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=407;

--
-- AUTO_INCREMENT for table `countries`
--
ALTER TABLE `countries`
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
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `searchable`
--
ALTER TABLE `searchable`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `subscriber`
--
ALTER TABLE `subscriber`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `subscription`
--
ALTER TABLE `subscription`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `suburbs`
--
ALTER TABLE `suburbs`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tokens`
--
ALTER TABLE `tokens`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `towns`
--
ALTER TABLE `towns`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(16) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `searchable`
--
ALTER TABLE `searchable`
  ADD CONSTRAINT `c_fk_searchable_fields_id` FOREIGN KEY (`fields_id`) REFERENCES `fields` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

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
