
CREATE DATABASE IF NOT EXISTS APDATA;
USE APDATA;

INSERT INTO cours (nom_cours) VALUES 
('Mathématiques'),
('Advanced Programming'),
('Software Cloud Engineering'),
('Physique'),
('Anglais'),
('Espagnol'),
('DevOps');

INSERT INTO role (role) VALUES 
('intervenant'),
('etudiant'),
('admin');

INSERT INTO forme (id, question_id, reponse_id) VALUES
(1, NULL, NULL); -- Assuming this ID is for the BasicTemplate form

INSERT INTO question (question) VALUES
('Comment évaluez-vous le cours Mathématiques? (1-5)'),
('Comment évaluez-vous le cours Advanced Programming? (1-5)'),
('Comment évaluez-vous le cours Software Cloud Engineering? (1-5)'),
('Comment évaluez-vous le cours Physique? (1-5)'),
('Comment évaluez-vous le cours Anglais? (1-5)'),
('Comment évaluez-vous le cours Espagnol? (1-5)'),
('Comment évaluez-vous le cours DevOps? (1-5)'),
('Comment évaluez-vous le matériel fourni par le professeur? (1-5)'),
('Quel aspect du cours Mathématiques avez-vous trouvé le plus utile?'),
('Quel aspect du cours DevOps mérite d être amélioré?');
-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 03 déc. 2024 à 11:24
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
-- Base de données : `db_apro`
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
  `nom_cours` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `cours_formes`
--

CREATE TABLE `cours_formes` (
  `cours_id` bigint(20) NOT NULL,
  `formes_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `etat` bit(1) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `num_etudiant` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `etudiant_cours`
--

CREATE TABLE `etudiant_cours` (
  `cours_id` bigint(20) NOT NULL,
  `etudiant_id` bigint(20) NOT NULL
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
  `id` bigint(20) NOT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  `reponse_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `intervenant`
--

CREATE TABLE `intervenant` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `specialiste` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `id` bigint(20) NOT NULL,
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

INSERT INTO `utilisateur` (`date_naissance`, `statut`, `id`, `email`, `image_profile`, `nom`, `num_tel`, `password`, `prenom`) VALUES
(NULL, b'1', 1, 'user4@gmail.com', NULL, NULL, NULL, '$2a$10$Huc.eXUE7rZUPfKFFY5ji.q06kLkL83mEoCq1AUG/xVf8vMAR2omi', NULL),
(NULL, b'1', 2, 'user2@gmail.com', NULL, NULL, NULL, '$2a$10$grBSY9hACcabaIwUdLLywO38Nj0WvtKRLn3mZqvJA8fA/nuuFSiCm', NULL),
(NULL, b'1', 3, 'user3@gmail.com', NULL, NULL, NULL, '$2a$10$BoK19qblNYTpvBPRCLkyJeIc3cgQ3jX05C4sfrFF17Zx9UI6EPhU.', NULL),
(NULL, b'1', 4, 'admin@gmail.com', NULL, NULL, NULL, '$2a$10$qRBc0eAosSCz3w.dTh00OOJS9G0jB3dpiXYS7SZj./ZehELfGB4E2', NULL);

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
(3, 4);

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
  ADD UNIQUE KEY `UKjsec2afm84l3imn54m0v0nekg` (`nom_cours`);

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
-- Index pour la table `fiche_data`
--
ALTER TABLE `fiche_data`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `forme`
--
ALTER TABLE `forme`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKk8peekclqu4wdeyelc09fcujr` (`question_id`),
  ADD KEY `FKis41klnu6g4awluh3hhvj29ps` (`reponse_id`);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `question`
--
ALTER TABLE `question`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
-- Contraintes pour la table `forme`
--
ALTER TABLE `forme`
  ADD CONSTRAINT `FKis41klnu6g4awluh3hhvj29ps` FOREIGN KEY (`reponse_id`) REFERENCES `reponse` (`id`),
  ADD CONSTRAINT `FKk8peekclqu4wdeyelc09fcujr` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`);

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
