-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : sam. 13 jan. 2024 à 10:32
-- Version du serveur : 8.0.35-0ubuntu0.22.04.1
-- Version de PHP : 8.1.2-1ubuntu2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `GestionCompteBancaire`
--

-- --------------------------------------------------------

--
-- Structure de la table `account`
--

CREATE TABLE `account` (
  `id` int NOT NULL,
  `number` int NOT NULL,
  `balance` double NOT NULL,
  `status` varchar(10) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'actif',
  `customer_id` int NOT NULL,
  `banker_id` int NOT NULL,
  `date_` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `account`
--

INSERT INTO `account` (`id`, `number`, `balance`, `status`, `customer_id`, `banker_id`, `date_`) VALUES
(1, 245857, 6000000, 'actif', 1, 1, '2023-12-05 22:04:06'),
(3, 245858, 0, 'actif', 2, 1, '2023-12-06 01:23:26'),
(4, 245859, 100050, 'actif', 3, 1, '2023-12-06 01:28:41'),
(5, 245860, 2900, 'actif', 4, 1, '2023-12-06 01:30:40'),
(6, 245861, 0, 'actif', 5, 1, '2023-12-07 21:08:52'),
(8, 245862, 0, 'actif', 6, 1, '2023-12-13 22:25:12');

-- --------------------------------------------------------

--
-- Structure de la table `banker`
--

CREATE TABLE `banker` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `firstname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `gender` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `position` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `date_` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `banker`
--

INSERT INTO `banker` (`id`, `name`, `firstname`, `gender`, `address`, `phone`, `position`, `email`, `password`, `date_`) VALUES
(1, 'Doe', 'Jane', 'Mme', '456 Second Street', '123-456-789', 'manager', 'banquier@test.com', '$2a$10$YeHcZPrLQQTNZbQa7k7qDeUW0eeZj3hOZ/2Ilt6jbGVHy80dn35QK', '2023-12-05 21:45:04');

-- --------------------------------------------------------

--
-- Structure de la table `customer`
--

CREATE TABLE `customer` (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `firstname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `account_number` bigint NOT NULL,
  `banker_id` int NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `date_` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `customer`
--

INSERT INTO `customer` (`id`, `name`, `firstname`, `gender`, `address`, `phone`, `type`, `account_number`, `banker_id`, `email`, `password`, `date_`) VALUES
(1, 'DOE', 'John', 'Mr', '123 second street', '123-456-789', 'individual', 245857, 1, 'client1@test.com', '$2a$10$uAGascHzNDRfDZ7VPj4cbOnfAQOvcnoGpfLKJnTMW9vwoB/mC0I/q', '2023-12-05 21:49:23'),
(2, 'BARRY', 'Allen', 'Mr', '123 Main Street', '555-555-5555', 'individual', 245858, 1, 'client2@test.com', '$2a$10$L9ty8pUueN5XZx5ogmBI/OsvV/BhQ8Ce2Ob0izyrZzzMltIk2IaI6', '2023-12-06 01:23:26'),
(3, 'QUEEN', 'Oliver', 'Mr', '123 Main Street', '555-555-5555', 'individual', 245859, 1, 'client3@test.com', '$2a$10$dcRs1xN.knx2kPD2purxKedYIkmW8JdvKAgmB2vgXo3t8PCufMN2.', '2023-12-06 01:28:41'),
(4, 'MARTIN', 'Jean', 'Mr', '123 Main Street', '555-555-5555', 'individual', 245860, 1, 'client4@test.com', '$2a$10$D9Tu.QynkdVLw7YTklhYx.TbkHAd13Dm0.xyLdTl6IDMid20sc0Oa', '2023-12-06 01:30:40'),
(5, 'MARTIN', 'Jean', 'Mr', '123 Main Street', '555-555-5555', 'individual', 245861, 1, 'client5@test.com', '$2a$10$a60fV70xLAfg/sizjFfpo.HugsEs9NCj01sQtQIBERKflEKdgBV.W', '2023-12-07 21:08:52'),
(6, 'EMIR', 'Abdel', 'Mr', '94 boulevard Mansart', '123-456-789', 'individual', 245862, 1, 'client6@test.com', '$2a$10$nHZWg88Cv6STo63Kc6Z0i.yJ6BgTYIQ4Dhr.KSLgThVZzqXqXiAyK', '2023-12-13 22:25:12');

-- --------------------------------------------------------

--
-- Structure de la table `history`
--

CREATE TABLE `history` (
  `id` int NOT NULL,
  `amount` double NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `account_number` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `history`
--

INSERT INTO `history` (`id`, `amount`, `type`, `date`, `account_number`) VALUES
(1, 2.5, 'Depot', '2023-12-16 01:15:11', 245857),
(2, 5, 'Depot', '2023-12-16 01:15:11', 245857),
(3, 7.5, 'Depot', '2023-12-16 01:15:11', 245857),
(4, 10, 'Depot', '2023-12-16 01:15:11', 245857),
(5, 12.5, 'Depot', '2023-12-16 01:15:11', 245857),
(6, 15, 'Depot', '2023-12-16 01:15:11', 245857),
(7, 17.5, 'Depot', '2023-12-16 01:15:11', 245857),
(8, 20, 'Depot', '2023-12-16 01:15:11', 245857),
(9, 22.5, 'Depot', '2023-12-16 01:15:11', 245857),
(10, 25, 'Depot', '2023-12-16 01:15:11', 245857),
(11, 1250, 'Depot', '2023-12-18 00:12:01', 245857),
(12, 30000, 'Retrait', '2023-12-18 01:04:12', 245857),
(13, 1000, 'Depot', '2023-12-27 09:13:20', 245860),
(14, 70000, 'Retrait', '2024-01-13 09:31:30', 245857),
(15, 5000, 'Depot', '2024-01-13 09:36:33', 245860),
(16, 5000, 'Depot', '2024-01-13 09:36:53', 245860),
(17, 10000, 'Retrait', '2024-01-13 09:37:09', 245860),
(18, 900000, 'Retrait', '2024-01-13 10:27:04', 245857);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `account_fk_bank` (`banker_id`),
  ADD KEY `account_fk_customer` (`customer_id`),
  ADD KEY `number` (`number`);

--
-- Index pour la table `banker`
--
ALTER TABLE `banker`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `customer_fk_banker` (`banker_id`);

--
-- Index pour la table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `account`
--
ALTER TABLE `account`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `banker`
--
ALTER TABLE `banker`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `history`
--
ALTER TABLE `history`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_fk_bank` FOREIGN KEY (`banker_id`) REFERENCES `banker` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `account_fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_fk_banker` FOREIGN KEY (`banker_id`) REFERENCES `banker` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
