-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 31 déc. 2024 à 09:40
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `apdata`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `admin_cours`
--

CREATE TABLE `admin_cours` (
  `admin_id` bigint(20) NOT NULL,
  `cours_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE `cours` (
  `id` bigint(20) NOT NULL,
  `nom_cours` varchar(255) NOT NULL,
  `forme_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`id`, `nom_cours`, `forme_id`) VALUES
(1, 'Spring boot basics', NULL),
(2, 'Advanced JPA', NULL),
(3, 'RESTful APIs', NULL),
(4, 'Microservices Architecture', NULL),
(5, 'Hibernate Deep Dive', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `cours_formes`
--

CREATE TABLE `cours_formes` (
  `cours_id` bigint(20) NOT NULL,
  `formes_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `cours_formes`
--

INSERT INTO `cours_formes` (`cours_id`, `formes_id`) VALUES
(2, 1),
(2, 2),
(2, 3),
(5, 4),
(5, 5),
(5, 6),
(4, 7),
(4, 8),
(4, 9),
(3, 10),
(3, 11),
(3, 12),
(1, 13),
(1, 14),
(1, 15);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `etat` bit(1) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `num_etudiant` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`etat`, `id`, `num_etudiant`) VALUES
(b'1', 1, NULL),
(b'1', 2, NULL),
(b'1', 3, NULL),
(b'1', 4, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant_cours`
--

CREATE TABLE `etudiant_cours` (
  `cours_id` bigint(20) NOT NULL,
  `etudiant_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `etudiant_cours`
--

INSERT INTO `etudiant_cours` (`cours_id`, `etudiant_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(1, 3),
(2, 3),
(3, 3),
(4, 3),
(5, 3),
(1, 4),
(2, 4),
(3, 4),
(4, 4),
(5, 4);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant_cours_form`
--

CREATE TABLE `etudiant_cours_form` (
  `cours_id` bigint(20) NOT NULL,
  `etudiant_id` bigint(20) NOT NULL,
  `forme_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `fiche_data`
--

CREATE TABLE `fiche_data` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `fiche` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `forme`
--

CREATE TABLE `forme` (
  `anonyme` bit(1) NOT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  `reponse_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `forme`
--

INSERT INTO `forme` (`anonyme`, `etudiant_id`, `id`, `question_id`, `reponse_id`) VALUES
(b'0', 2, 1, NULL, NULL),
(b'0', 1, 2, NULL, NULL),
(b'0', NULL, 3, NULL, NULL),
(b'0', NULL, 4, NULL, NULL),
(b'0', NULL, 5, NULL, NULL),
(b'0', NULL, 6, NULL, NULL),
(b'0', NULL, 7, NULL, NULL),
(b'0', NULL, 8, NULL, NULL),
(b'0', NULL, 9, NULL, NULL),
(b'0', NULL, 10, NULL, NULL),
(b'0', NULL, 11, NULL, NULL),
(b'0', NULL, 12, NULL, NULL),
(b'0', NULL, 13, NULL, NULL),
(b'0', NULL, 14, NULL, NULL),
(b'0', NULL, 15, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `forme_question_reponses`
--

CREATE TABLE `forme_question_reponses` (
  `forme_id` bigint(20) NOT NULL,
  `question_reponses_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `forme_question_reponses`
--

INSERT INTO `forme_question_reponses` (`forme_id`, `question_reponses_id`) VALUES
(3, 2),
(4, 2),
(5, 2),
(6, 5),
(7, 4),
(8, 3),
(9, 1),
(10, 1),
(11, 1),
(12, 2),
(13, 1),
(14, 5),
(15, 4),
(1, 2),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(2, 1),
(2, 10),
(2, 11),
(2, 12),
(2, 13);

-- --------------------------------------------------------

--
-- Structure de la table `intervenant`
--

CREATE TABLE `intervenant` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `specialiste` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `intervenant`
--

INSERT INTO `intervenant` (`id`, `description`, `specialiste`) VALUES
(9, NULL, NULL),
(10, NULL, NULL),
(11, NULL, NULL),
(12, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `intervenant_cours`
--

CREATE TABLE `intervenant_cours` (
  `cours_id` bigint(20) NOT NULL,
  `intervenant_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE `question` (
  `id` bigint(20) NOT NULL,
  `question` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `question_reponse`
--

CREATE TABLE `question_reponse` (
  `id` bigint(20) NOT NULL,
  `question` varchar(255) NOT NULL,
  `reponse` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `question_reponse`
--

INSERT INTO `question_reponse` (`id`, `question`, `reponse`) VALUES
(1, 'What is Spring Boot?', NULL),
(2, 'Explain JPA relationships.', NULL),
(3, 'How to secure a REST API?', NULL),
(4, 'What is a microservice?', NULL),
(5, 'What are HTTP status codes?', NULL),
(6, 'What are HTTP status codes?', 'sara je suis un spring 1'),
(7, 'What are HTTP status codes?', 'sara je suis un spring 2'),
(8, 'What are HTTP status codes?', 'sara je suis un spring 3'),
(9, 'What are HTTP status codes?', 'sara je suis un spring 4'),
(10, 'What are HTTP status codes?', 'othmane je suis un spring 1'),
(11, 'What are HTTP status codes?', 'othmane je suis un spring 2'),
(12, 'What are HTTP status codes?', 'othmane je suis un spring 3'),
(13, 'What are HTTP status codes?', 'othmane je suis un spring 5');

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `id` bigint(20) NOT NULL,
  `reponse` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `role`) VALUES
(1, 'etudiant'),
(2, 'professor'),
(3, 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `date_naissance` date DEFAULT NULL,
  `get_statut` bit(1) NOT NULL,
  `statut` bit(1) NOT NULL,
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `image_profile` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `num_tel` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`date_naissance`, `get_statut`, `statut`, `id`, `email`, `image_profile`, `nom`, `num_tel`, `password`, `prenom`) VALUES
(NULL, b'0', b'1', 1, 'user9@gmail.com', NULL, NULL, NULL, '$2a$10$LDdhJ4qU9AUAgRMb5Mdbm.CqS7/UcucH6Z93KWQ.v/y7YiR3/1xDG', NULL),
(NULL, b'0', b'1', 2, 'user2@gmail.com', NULL, NULL, NULL, '$2a$10$/ev1OycOpEZTtJh1rT3ZOuRrFeGPEmrgpAa/QQ5Rs7Zs2aq4pgJl.', NULL),
(NULL, b'0', b'1', 3, 'user3@gmail.com', NULL, NULL, NULL, '$2a$10$qxfn0n.MJbBz4c9gB3rMbO8y6g1CGcgVM8v6HvTV/hHiL7sKvPIEK', NULL),
(NULL, b'0', b'1', 4, 'admin@gmail.com', NULL, NULL, NULL, '$2a$10$zXKwmxXbUICiCDJb3st4c.kYNSnqJmj1U0sqjUPzmJwvjQApzWlmu', NULL),
(NULL, b'0', b'0', 9, 'Clément@gmail.com', NULL, NULL, NULL, NULL, NULL),
(NULL, b'0', b'0', 10, 'Mathieu@gmail.com', NULL, NULL, NULL, NULL, NULL),
(NULL, b'0', b'0', 11, 'Arabi@gmail.com', NULL, NULL, NULL, NULL, NULL),
(NULL, b'0', b'0', 12, 'Saouane@gmail.com', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_roles`
--

CREATE TABLE `utilisateur_roles` (
  `roles_id` bigint(20) NOT NULL,
  `utilisateur_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur_roles`
--

INSERT INTO `utilisateur_roles` (`roles_id`, `utilisateur_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(3, 4),
(2, 9),
(2, 10),
(2, 11),
(2, 12);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `admin_cours`
--
ALTER TABLE `admin_cours`
  ADD KEY `FK9xgubd9255efbilfpm6xjex9x` (`cours_id`),
  ADD KEY `FK8tggqc6yk0ocm41esgks5sbxg` (`admin_id`);

--
-- Index pour la table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKjsec2afm84l3imn54m0v0nekg` (`nom_cours`),
  ADD KEY `FK5svk1m8d0tarimmupw2lrii62` (`forme_id`);

--
-- Index pour la table `cours_formes`
--
ALTER TABLE `cours_formes`
  ADD KEY `FKqwkvy4dx7uy2mw0pghw33uf3t` (`formes_id`),
  ADD KEY `FK52x8xwd1p85wl8aa79k407nbf` (`cours_id`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etudiant_cours`
--
ALTER TABLE `etudiant_cours`
  ADD KEY `FK8xoxpcrl5quoan1pdgv8mvrdi` (`cours_id`),
  ADD KEY `FKqtsuuvn21mdkme0gwu7cp0dyd` (`etudiant_id`);

--
-- Index pour la table `etudiant_cours_form`
--
ALTER TABLE `etudiant_cours_form`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjtmp78dctvw71n19qqj9c75j7` (`cours_id`),
  ADD KEY `FKd30t2risinfg48l691f0j6qmy` (`etudiant_id`),
  ADD KEY `FKt9pb74a9irahwnmhx0vcosdl` (`forme_id`);

--
-- Index pour la table `fiche_data`
--
ALTER TABLE `fiche_data`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `forme`
--
ALTER TABLE `forme`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKai4owdues1wkhy0bciby1od6c` (`etudiant_id`),
  ADD KEY `FKk8peekclqu4wdeyelc09fcujr` (`question_id`),
  ADD KEY `FKis41klnu6g4awluh3hhvj29ps` (`reponse_id`);

--
-- Index pour la table `forme_question_reponses`
--
ALTER TABLE `forme_question_reponses`
  ADD KEY `FK35q9593n4e9x38bqaqmqc7b50` (`question_reponses_id`),
  ADD KEY `FK21cdy6ksykls6i3ntbd2bg1mh` (`forme_id`);

--
-- Index pour la table `intervenant`
--
ALTER TABLE `intervenant`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `intervenant_cours`
--
ALTER TABLE `intervenant_cours`
  ADD KEY `FKn3xo99er6ckccrhcv57i0qt44` (`cours_id`),
  ADD KEY `FKtrg1925b7hs2h9wb3djrm1e7p` (`intervenant_id`);

--
-- Index pour la table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `question_reponse`
--
ALTER TABLE `question_reponse`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKrma38wvnqfaf66vvmi57c71lo` (`email`);

--
-- Index pour la table `utilisateur_roles`
--
ALTER TABLE `utilisateur_roles`
  ADD KEY `FKgvjufa2i7moss3i9eh2i9yoaq` (`roles_id`),
  ADD KEY `FK9lop304xtodorgho9w56lpjhn` (`utilisateur_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `cours`
--
ALTER TABLE `cours`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `etudiant_cours_form`
--
ALTER TABLE `etudiant_cours_form`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `fiche_data`
--
ALTER TABLE `fiche_data`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `forme`
--
ALTER TABLE `forme`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `question`
--
ALTER TABLE `question`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `question_reponse`
--
ALTER TABLE `question_reponse`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FKgodqjbbtwk30kf3s0xuxklkr3` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `admin_cours`
--
ALTER TABLE `admin_cours`
  ADD CONSTRAINT `FK8tggqc6yk0ocm41esgks5sbxg` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`),
  ADD CONSTRAINT `FK9xgubd9255efbilfpm6xjex9x` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`id`);

--
-- Contraintes pour la table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `FK5svk1m8d0tarimmupw2lrii62` FOREIGN KEY (`forme_id`) REFERENCES `forme` (`id`);

--
-- Contraintes pour la table `cours_formes`
--
ALTER TABLE `cours_formes`
  ADD CONSTRAINT `FK52x8xwd1p85wl8aa79k407nbf` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`id`),
  ADD CONSTRAINT `FKqwkvy4dx7uy2mw0pghw33uf3t` FOREIGN KEY (`formes_id`) REFERENCES `forme` (`id`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `FK8r3ygi23xihnm6jbtxoyrmlrj` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `etudiant_cours`
--
ALTER TABLE `etudiant_cours`
  ADD CONSTRAINT `FK8xoxpcrl5quoan1pdgv8mvrdi` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`id`),
  ADD CONSTRAINT `FKqtsuuvn21mdkme0gwu7cp0dyd` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`);

--
-- Contraintes pour la table `etudiant_cours_form`
--
ALTER TABLE `etudiant_cours_form`
  ADD CONSTRAINT `FKd30t2risinfg48l691f0j6qmy` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `FKjtmp78dctvw71n19qqj9c75j7` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`id`),
  ADD CONSTRAINT `FKt9pb74a9irahwnmhx0vcosdl` FOREIGN KEY (`forme_id`) REFERENCES `forme` (`id`);

--
-- Contraintes pour la table `forme`
--
ALTER TABLE `forme`
  ADD CONSTRAINT `FKai4owdues1wkhy0bciby1od6c` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `FKis41klnu6g4awluh3hhvj29ps` FOREIGN KEY (`reponse_id`) REFERENCES `reponse` (`id`),
  ADD CONSTRAINT `FKk8peekclqu4wdeyelc09fcujr` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`);

--
-- Contraintes pour la table `forme_question_reponses`
--
ALTER TABLE `forme_question_reponses`
  ADD CONSTRAINT `FK21cdy6ksykls6i3ntbd2bg1mh` FOREIGN KEY (`forme_id`) REFERENCES `forme` (`id`),
  ADD CONSTRAINT `FK35q9593n4e9x38bqaqmqc7b50` FOREIGN KEY (`question_reponses_id`) REFERENCES `question_reponse` (`id`);

--
-- Contraintes pour la table `intervenant`
--
ALTER TABLE `intervenant`
  ADD CONSTRAINT `FK55l2u4ce5aree5nsxfk39g99i` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `intervenant_cours`
--
ALTER TABLE `intervenant_cours`
  ADD CONSTRAINT `FKn3xo99er6ckccrhcv57i0qt44` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`id`),
  ADD CONSTRAINT `FKtrg1925b7hs2h9wb3djrm1e7p` FOREIGN KEY (`intervenant_id`) REFERENCES `intervenant` (`id`);

--
-- Contraintes pour la table `utilisateur_roles`
--
ALTER TABLE `utilisateur_roles`
  ADD CONSTRAINT `FK9lop304xtodorgho9w56lpjhn` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FKgvjufa2i7moss3i9eh2i9yoaq` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
