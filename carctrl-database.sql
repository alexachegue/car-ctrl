-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 07, 2025 at 12:39 AM
-- Server version: 8.0.39
-- PHP Version: 8.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carctrl-database`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `adminid` int NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`adminid`, `password`, `username`) VALUES
(1, 'pw', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `alerts`
--

CREATE TABLE `alerts` (
  `alert_id` int NOT NULL,
  `provider_id` int NOT NULL,
  `user_id` int NOT NULL,
  `appointment_id` int NOT NULL,
  `alert_type` enum('primary','success','warning','danger') COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `dismissible` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `alerts`
--

INSERT INTO `alerts` (`alert_id`, `provider_id`, `user_id`, `appointment_id`, `alert_type`, `message`, `created_at`, `dismissible`) VALUES
(3, 3, 88899, 20, 'primary', 'Appointment submitted and is pending confirmation.', '2025-05-04 10:25:04', 1),
(4, 9, 88914, 21, 'primary', 'Appointment submitted and is pending confirmation.', '2025-05-04 12:40:18', 1),
(5, 11, 88914, 22, 'primary', 'Appointment submitted and is pending confirmation.', '2025-05-04 12:42:52', 1),
(6, 1, 88899, 23, 'primary', 'Appointment submitted and is pending confirmation.', '2025-05-05 16:36:57', 1),
(7, 8, 88916, 24, 'primary', 'Appointment submitted and is pending confirmation.', '2025-05-06 12:16:11', 1),
(8, 11, 88916, 25, 'primary', 'Appointment submitted and is pending confirmation.', '2025-05-06 12:52:33', 1),
(9, 1, 88917, 26, 'primary', 'Appointment submitted and is pending confirmation.', '2025-05-06 16:41:52', 1),
(10, 4, 88899, 17, 'success', 'Your appointment has been scheduled.', '2025-05-06 20:36:53', 1);

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appointment_id` int NOT NULL,
  `user_id` int NOT NULL,
  `service_id` int NOT NULL,
  `car_id` int NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `appointment_date` datetime NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` enum('Scheduled','Pending','Cancelled') COLLATE utf8mb4_general_ci DEFAULT 'Pending',
  `provider_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`appointment_id`, `user_id`, `service_id`, `car_id`, `description`, `appointment_date`, `created_at`, `status`, `provider_id`) VALUES
(13, 88913, 6, 23, 'it only needs a swap', '2025-05-03 10:50:00', '2025-05-02 23:46:10', 'Pending', 3),
(14, 88913, 1, 23, '', '2025-05-01 08:00:00', '2025-05-02 23:52:50', 'Pending', 1),
(15, 88913, 12, 23, 'i need them replaced to better fit the troca', '2025-05-08 17:10:00', '2025-05-03 05:07:10', 'Pending', 5),
(16, 88890, 17, 11, 'i want a new look.', '2025-05-02 14:40:00', '2025-05-03 17:34:45', 'Pending', 5),
(17, 88899, 9, 24, 'i need it to sound louder and smooth.', '2025-05-01 16:30:00', '2025-05-07 00:36:52', 'Scheduled', 4),
(18, 88899, 12, 24, 'i want it all to match nicely.', '2025-04-30 08:00:00', '2025-05-03 19:51:33', 'Pending', 5),
(19, 88899, 3, 24, 'im gonna need its yearly one done.', '2025-05-07 11:20:00', '2025-05-03 22:19:04', 'Pending', 7),
(20, 88899, 6, 24, 'it needs a new one.', '2025-05-03 12:30:00', '2025-05-04 14:25:04', 'Pending', 3),
(21, 88914, 4, 25, 'it\'s been moving to the side funny.', '2025-05-02 15:40:00', '2025-05-04 16:40:18', 'Pending', 9),
(22, 88914, 6, 25, 'i\'m looking to exchange the battery.', '2025-05-06 14:45:00', '2025-05-04 16:42:52', 'Pending', 11),
(23, 88899, 1, 24, '', '2025-05-09 07:40:00', '2025-05-05 20:36:57', 'Pending', 1),
(24, 88916, 3, 29, 'just needs an inspection.', '2025-05-12 15:20:00', '2025-05-06 16:16:11', 'Pending', 8),
(25, 88916, 7, 29, 'can you look at it.', '2025-05-04 14:55:00', '2025-05-06 16:52:33', 'Pending', 11),
(26, 88917, 2, 30, '', '2025-05-05 18:45:00', '2025-05-06 20:41:52', 'Pending', 1);

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `car_id` int NOT NULL,
  `user_id` int NOT NULL,
  `model` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `make` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `year` int NOT NULL,
  `color` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`car_id`, `user_id`, `model`, `make`, `year`, `color`) VALUES
(3, 88890, '325i', 'bmw', 2004, 'silver'),
(11, 88890, 'g35', 'infiniti', 2003, 'black'),
(12, 88892, 'sonata', 'hyundai', 2012, 'grey'),
(14, 88906, 'grand cherokee ', 'jeep', 2021, 'white'),
(15, 88906, 'wrangler', 'jeep', 2018, 'blue'),
(21, 88912, 'colorado', 'chevrolet', 2005, 'light blue'),
(22, 88912, 'altima', 'nissan', 2016, 'dark blue'),
(23, 88913, 'dakota', 'dodge', 2010, 'black'),
(24, 88899, 'corvette', 'chevrolet', 2025, 'red'),
(25, 88914, 'accord', 'honda', 2019, 'black'),
(26, 88915, 'civic', 'honda', 2012, 'silver'),
(29, 88916, 'is300', 'lexus', 2004, 'silver'),
(30, 88917, 'accord', 'honda', 2018, 'white');

-- --------------------------------------------------------

--
-- Table structure for table `providers`
--

CREATE TABLE `providers` (
  `provider_id` int NOT NULL,
  `providername` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `operating_hours` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `bio` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` enum('ACTIVE','BUSY','OFFLINE') COLLATE utf8mb4_general_ci DEFAULT 'ACTIVE',
  `active_date` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `providers`
--

INSERT INTO `providers` (`provider_id`, `providername`, `password`, `email`, `phone`, `address`, `operating_hours`, `bio`, `status`, `active_date`) VALUES
(1, 'RoutinePro1', 'pass123', 'routine1@gmail.com', '123-456-7890', '123 Main St', '9 AM - 5 PM', 'Routine maintenance expert.', 'ACTIVE', '2023-01-01 00:00:00'),
(3, 'RepairGuy1', 'pass123', 'repair1@outlook.com', '345-678-9012', '345 Pine St', '8 AM - 4 PM', 'Brake and engine repair specialist.', 'ACTIVE', '2023-01-03 00:00:00'),
(4, 'RepairGuy2', 'pass123', 'repair2@mail.com', '456-789-0123', '456 Birch St', '11 AM - 7 PM', 'Fixing everything under the hood.', 'ACTIVE', '2023-01-04 00:00:00'),
(5, 'ModKing1', 'pass123', 'mod1@gmail.com', '567-890-1234', '567 Cedar St', '12 PM - 8 PM', 'Custom mods and visual upgrades.', 'ACTIVE', '2023-01-05 00:00:00'),
(6, 'ModKing2', 'pass123', 'mod2@yahoo.com', '678-901-2345', '678 Elm St', '1 PM - 9 PM', 'Performance tuning and wraps.', 'ACTIVE', '2023-01-06 00:00:00'),
(7, 'RoutinePro2', 'pass123', 'routine2@icloud.com', '234-567-8901', '234 Oak St', '10 AM - 6 PM', 'Keeping your car in top shape.', 'ACTIVE', '2023-01-02 00:00:00'),
(8, 'QuickFixPros', 'pass123', 'quickfix@shop.com', '999-111-2222', '101 Maple Ave', '9 AM - 5 PM', 'Fast and affordable repairs.', 'ACTIVE', '2023-01-07 00:00:00'),
(9, 'TuneTechAuto', 'pass123', 'tunetech@auto.com', '888-222-3333', '202 Walnut Rd', '8 AM - 4 PM', 'Engine tuning and performance.', 'ACTIVE', '2023-01-08 00:00:00'),
(10, 'EliteMods', 'pass123', 'elite@mods.com', '777-333-4444', '303 Spruce St', '10 AM - 6 PM', 'Elite custom modifications.', 'ACTIVE', '2023-01-09 00:00:00'),
(11, 'SafeRideGarage', 'pass123', 'saferide@garage.com', '666-444-5555', '404 Willow Ln', '7 AM - 3 PM', 'Safety-focused repair services.', 'ACTIVE', '2023-01-10 00:00:00'),
(12, 'EcoTune', 'pass123', 'eco@tune.com', '555-555-6666', '505 Cherry Blvd', '9 AM - 6 PM', 'Eco-friendly and efficient upgrades.', 'ACTIVE', '2023-01-11 00:00:00'),
(13, 'alexa', 'a', 'a@icloud.com', '3337771111', NULL, NULL, NULL, NULL, '2025-05-06 01:19:31'),
(14, 'JaysAuto', 'pw', 'email@gmail.com', '3338889999', NULL, NULL, NULL, NULL, '2025-05-06 12:32:38'),
(15, 'newmechanic', 'pw', 'mail@email.com', '000001111', NULL, NULL, NULL, 'ACTIVE', '2025-05-06 15:40:47');

-- --------------------------------------------------------

--
-- Table structure for table `provider_services`
--

CREATE TABLE `provider_services` (
  `provider_id` int NOT NULL,
  `service_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `provider_services`
--

INSERT INTO `provider_services` (`provider_id`, `service_id`) VALUES
(1, 1),
(8, 1),
(1, 2),
(9, 2),
(7, 3),
(8, 3),
(7, 4),
(9, 4),
(8, 5),
(3, 6),
(11, 6),
(3, 7),
(11, 7),
(4, 8),
(12, 8),
(4, 9),
(11, 9),
(9, 10),
(10, 11),
(5, 12),
(10, 12),
(10, 13),
(6, 14),
(12, 14),
(12, 15),
(6, 16),
(12, 16),
(5, 17),
(12, 17);

-- --------------------------------------------------------

--
-- Table structure for table `replies`
--

CREATE TABLE `replies` (
  `reply_id` int NOT NULL,
  `message` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `review_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `review_id` int NOT NULL,
  `appointment_id` int NOT NULL,
  `user_id` int NOT NULL,
  `provider_id` int DEFAULT NULL,
  `rating` int NOT NULL,
  `description` text COLLATE utf8mb4_general_ci,
  `provider_response` text COLLATE utf8mb4_general_ci,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`review_id`, `appointment_id`, `user_id`, `provider_id`, `rating`, `description`, `provider_response`, `created_at`) VALUES
(5, 16, 88890, 5, 1, 'I like the choices he made.', 'hackedd', '2025-05-03 17:35:32'),
(8, 21, 88914, 9, 2, 'he fixed it but took longer than expected.', 'im sorry i\'ll consider that next time.', '2025-05-04 16:40:54'),
(9, 17, 88899, 4, 5, 'he did good.', 'thanks.', '2025-05-05 20:35:10'),
(10, 25, 88916, 11, 5, 'he did a speedy and excellent job.', NULL, '2025-05-06 16:52:51'),
(11, 26, 88917, 1, 3, 'he did alright.', NULL, '2025-05-06 20:42:09');

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `service_id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `category` enum('MAINTENANCE','REPAIR','MODIFICATION') COLLATE utf8mb4_general_ci NOT NULL,
  `provider_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`service_id`, `name`, `description`, `category`, `provider_id`) VALUES
(1, 'Oil Change', 'Regular oil and filter change.', 'MAINTENANCE', NULL),
(2, 'Tire Rotation', 'Extend tire life and improve performance with routine rotations.', 'MAINTENANCE', NULL),
(3, 'Inspection', 'Comprehensive vehicle inspection to ensure safety and reliability.', 'MAINTENANCE', NULL),
(4, 'Alignment & Suspension', 'Ensure a smooth and safe ride with expert alignment and suspension services.', 'MAINTENANCE', NULL),
(5, 'Brakes', 'Ensure your safety with comprehensive brake services, including pad replacement, rotor inspection, and hydraulic system maintenance.', 'REPAIR', NULL),
(6, 'Battery Replacement', 'Replacing old or dead car battery.', 'REPAIR', NULL),
(7, 'Transmission', 'Get smooth shifting and reliable performance with expert transmission repairs, fluid changes, and diagnostics.', 'REPAIR', NULL),
(8, 'AC & Heating', 'Restore comfort to your vehicle with expert climate control diagnostics and repairs, ensuring reliable heating and cooling year-round.', 'REPAIR', 5),
(9, 'Exhaust System', 'Improve fuel efficiency and reduce emissions with thorough inspection and repair of your vehicle’s exhaust components.', 'REPAIR', 5),
(10, 'Engine', 'Keep your engine running at peak performance with professional diagnostics, repairs, and part replacements for all engine components.', 'REPAIR', NULL),
(11, 'Suspension', 'Enjoy a smoother ride with suspension system services, including shock, strut, and alignment adjustments to ensure optimal handling.', 'REPAIR', NULL),
(12, 'Custom Wheels & Rims', 'Upgrade your vehicle’s style and performance with sleek, durable wheels and rims tailored to your aesthetic and driving needs.', 'MODIFICATION', NULL),
(13, 'Custom Paint Jobs & Vinyl Wraps', 'Stand out on the road with premium paint jobs or vinyl wraps, offering bold colors, matte finishes, or unique graphics.', 'MODIFICATION', NULL),
(14, 'Suspension Lowering or Lifting', 'Customize your vehicle\'s height for better looks or off-road capability with expert suspension adjustments.', 'MODIFICATION', NULL),
(15, 'Cold Air Intake', 'Boost horsepower and throttle response with a high-performance cold air intake system designed for your engine.', 'MODIFICATION', NULL),
(16, 'ECU Tuning', 'Unlock your engine’s full potential with precision tuning that enhances fuel economy, torque, and acceleration.', 'MODIFICATION', NULL),
(17, 'Body Kits', 'Transform your vehicle’s appearance with stylish body kits, including bumpers, skirts, and spoilers tailored for performance and flair.', 'MODIFICATION', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `date_joined` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `profile_picture` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `email`, `phone_number`, `date_joined`, `profile_picture`) VALUES
(88890, 'jugo', '2004', 'jr@cloud.com', '3337779999', '2025-03-09 00:00:00', 'jugovq.png'),
(88892, 'bec', 'baconegg', 'swiss@yahoo.com', '7778881111', '2025-04-01 00:00:00', NULL),
(88899, 'tomcat', 'passi', 'mail@mail.go', '771', '2025-04-21 01:35:15', NULL),
(88906, 'liljeep', 'jeep', 'sinoes@icloud.com', '0001112299', '2025-04-22 14:14:39', NULL),
(88912, 'maytest', 'may', 'may@hotmail.gov', '3367775555', '2025-05-02 14:08:13', NULL),
(88913, 'alex', 'a', 'a@cloud.org', '000000001', '2025-05-02 23:38:17', NULL),
(88914, 'alexac', 'a', 'a_cheguesan@uncg.edu', '3360003355', '2025-05-04 16:38:19', NULL),
(88915, 'drpepper', '1234', 'drp@gmail.com', '1112223344', '2025-05-06 01:52:29', 'bmw_1m.jpg'),
(88916, 'may6user', 'pw', 'may@icloud.com', '77799915500', '2025-05-06 15:53:21', 'is300.png'),
(88917, 'newuser1', 'pw1', 'email@mail.com', '111111990', '2025-05-06 20:40:53', 'honda_accord.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`adminid`);

--
-- Indexes for table `alerts`
--
ALTER TABLE `alerts`
  ADD PRIMARY KEY (`alert_id`),
  ADD KEY `fk_alert_provider` (`provider_id`),
  ADD KEY `fk_alert_user` (`user_id`),
  ADD KEY `fk_alert_appointment` (`appointment_id`);

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appointment_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `car_id` (`car_id`),
  ADD KEY `FK5iltr7k9pows18hk8nc101vc1` (`service_id`),
  ADD KEY `fk_appointment_provider` (`provider_id`);

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`car_id`),
  ADD UNIQUE KEY `car_id` (`car_id`),
  ADD KEY `car-user` (`user_id`);

--
-- Indexes for table `providers`
--
ALTER TABLE `providers`
  ADD PRIMARY KEY (`provider_id`);

--
-- Indexes for table `provider_services`
--
ALTER TABLE `provider_services`
  ADD PRIMARY KEY (`provider_id`,`service_id`),
  ADD KEY `service_id` (`service_id`);

--
-- Indexes for table `replies`
--
ALTER TABLE `replies`
  ADD PRIMARY KEY (`reply_id`),
  ADD UNIQUE KEY `UK5k4ny6mmjc6sr2xn3hwx0v580` (`review_id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`review_id`),
  ADD KEY `fk_review_appointment` (`appointment_id`),
  ADD KEY `fk_review_user` (`user_id`),
  ADD KEY `FKaa3msm4d8q6stmoft1kvp3kc8` (`provider_id`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`service_id`),
  ADD KEY `FK3q3h24v1nc2gg6rarhoj41yeb` (`provider_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `adminid` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `alerts`
--
ALTER TABLE `alerts`
  MODIFY `alert_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appointment_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `car`
--
ALTER TABLE `car`
  MODIFY `car_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `providers`
--
ALTER TABLE `providers`
  MODIFY `provider_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `replies`
--
ALTER TABLE `replies`
  MODIFY `reply_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `review_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `service_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88918;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `alerts`
--
ALTER TABLE `alerts`
  ADD CONSTRAINT `fk_alert_appointment` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`appointment_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_alert_provider` FOREIGN KEY (`provider_id`) REFERENCES `providers` (`provider_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_alert_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`),
  ADD CONSTRAINT `FK5iltr7k9pows18hk8nc101vc1` FOREIGN KEY (`service_id`) REFERENCES `services` (`service_id`),
  ADD CONSTRAINT `fk_appointment_provider` FOREIGN KEY (`provider_id`) REFERENCES `providers` (`provider_id`) ON DELETE SET NULL;

--
-- Constraints for table `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `car-user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `provider_services`
--
ALTER TABLE `provider_services`
  ADD CONSTRAINT `provider_services_ibfk_1` FOREIGN KEY (`provider_id`) REFERENCES `providers` (`provider_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `provider_services_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `services` (`service_id`) ON DELETE CASCADE;

--
-- Constraints for table `replies`
--
ALTER TABLE `replies`
  ADD CONSTRAINT `FKpgwpj3gkxsfl1lve2hopa3s4s` FOREIGN KEY (`review_id`) REFERENCES `review` (`review_id`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `fk_review_appointment` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`appointment_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_review_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKaa3msm4d8q6stmoft1kvp3kc8` FOREIGN KEY (`provider_id`) REFERENCES `providers` (`provider_id`);

--
-- Constraints for table `services`
--
ALTER TABLE `services`
  ADD CONSTRAINT `FK3q3h24v1nc2gg6rarhoj41yeb` FOREIGN KEY (`provider_id`) REFERENCES `providers` (`provider_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
