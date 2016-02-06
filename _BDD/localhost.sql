-- phpMyAdmin SQL Dump
-- version 4.2.12deb2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Lun 01 Juin 2015 à 17:42
-- Version du serveur :  5.5.43-0+deb8u1
-- Version de PHP :  5.6.7-1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `BurgerQuiz`
--

-- --------------------------------------------------------

--
-- Structure de la table `CATEGORIE`
--

CREATE TABLE IF NOT EXISTS `CATEGORIE` (
  `Nom_Categorie` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `CATEGORIE`
--

INSERT INTO `CATEGORIE` (`Nom_Categorie`) VALUES
('ISEN'),
('Jeux Vidéo'),
('Séries TV');

-- --------------------------------------------------------

--
-- Structure de la table `PALMARES`
--

CREATE TABLE IF NOT EXISTS `PALMARES` (
`Id_palmares` int(11) NOT NULL,
  `Pseudo` varchar(100) DEFAULT NULL,
  `Score` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `PALMARES`
--

INSERT INTO `PALMARES` (`Id_palmares`, `Pseudo`, `Score`) VALUES
(1, 'Vavan', 28),
(15, 'Evan', 32),
(19, 'david', 32),
(20, 'Dragavnir', 27),
(26, 'yoh', 30),
(35, 'Test', 3);

-- --------------------------------------------------------

--
-- Structure de la table `QUESTION`
--

CREATE TABLE IF NOT EXISTS `QUESTION` (
  `Intitule_Question` varchar(200) DEFAULT NULL,
  `Reponse` int(11) DEFAULT NULL,
  `Explication` varchar(500) DEFAULT NULL,
`Id_Question` int(11) NOT NULL,
  `Id_Theme` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `QUESTION`
--

INSERT INTO `QUESTION` (`Intitule_Question`, `Reponse`, `Explication`, `Id_Question`, `Id_Theme`) VALUES
('A la fonction de directeur ?', 1, NULL, 1, 4),
('A un ongle ?', 0, NULL, 2, 4),
('Est l''abréviation de restaurant universitaire ?', 2, NULL, 3, 5),
('Est un bâtard ?', 1, NULL, 4, 3),
('Est un jeu vidéo ?', 1, NULL, 5, 2),
('Fait parti de la garde de nuit ?', 1, NULL, 6, 3),
('Ont des colles ?', 2, NULL, 7, 1),
('Ont peut y acheter à manger ?', 0, NULL, 8, 5),
('Ont une alternance potentielle en 3ème année ?', 1, NULL, 9, 1),
('Peut avoir un bleu ?', 0, NULL, 10, 4),
('Peut brosser ?', 2, NULL, 11, 2),
('Peut s''acheter dans dans une grande surface ?', 0, NULL, 12, 2),
('Se trouve à moins de 500 mètres de l''ISEN Brest ?', 1, NULL, 13, 5),
('Sont moins nombreux ?', 1, '', 14, 1),
('Vient de Game of Thrones', 1, NULL, 15, 3),
('Est un jeu vidéo ?', 0, NULL, 16, 6),
('Le héro principal s’appelle Steve ?', 1, NULL, 17, 6),
('Ont peut y trouver des zombies ?', 0, 'Mode Zombie dans COD et les montres dans Minecraft', 18, 6),
('Est un singe ?', 1, '', 19, 7),
('Peut manger ?', 1, NULL, 20, 7),
('Est fait de métal ?', 2, NULL, 21, 7),
('Tire des flèches ?', 0, NULL, 22, 8),
('Sa tenue est rouge ?', 2, NULL, 23, 8),
('Est joué par Stephen Amell dans la série portant son nom ?', 1, NULL, 24, 8),
('Le personnage principal à comme surnom Liv ?', 2, 'Olivia « Liv » Moore', 25, 9),
('Est une série TV ?', 0, NULL, 26, 9),
('Se passe à Chester''s Mill ?', 1, 'Chester''s Mill est une petite ville du Maine où les événements exceptionnels sont rares. Mais un jour, un dôme invisible apparaît et englobe toute la ville.', 28, 9),
('Est disponible sur Brest ?', 0, NULL, 29, 1),
('Est un personnage Dysney ?', 2, NULL, 30, 3),
('Vaut mieux ne pas le cogner ?', 0, NULL, 31, 4),
('Est un "Open-World" ?', 1, NULL, 32, 6),
('S''étale sur 9 saisons ?', 2, NULL, 33, 10),
('Débute dans l''espace ?', 1, NULL, 34, 10),
('Est basé sur un roman ?', 1, 'Roman de Kass Morgan', 35, 10),
(NULL, NULL, NULL, 36, 10),
('Se nomme Roy dans la série ?', 2, NULL, 37, 8),
(NULL, NULL, NULL, 38, 8);

-- --------------------------------------------------------

--
-- Structure de la table `THEME`
--

CREATE TABLE IF NOT EXISTS `THEME` (
`Id_Theme` int(11) NOT NULL,
  `Theme1` varchar(100) DEFAULT NULL,
  `Theme2` varchar(100) DEFAULT NULL,
  `Nom_Categorie` varchar(100) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `THEME`
--

INSERT INTO `THEME` (`Id_Theme`, `Theme1`, `Theme2`, `Nom_Categorie`) VALUES
(1, 'CIR', 'CSI', 'ISEN'),
(2, 'Super Mario Bros', 'Une brosse', 'Jeux Vidéo'),
(3, 'Jon Snow', 'Blanche Neige', 'Séries TV'),
(4, 'Mr Faudeil', 'Un orteil', 'ISEN'),
(5, 'Super U', 'RU', 'ISEN'),
(6, 'Minecraft', 'Call of Duty', 'Jeux Vidéo'),
(7, 'Donkey Kong', 'Un gong', 'Jeux Vidéo'),
(8, 'Arrow', 'Arsenal', 'Séries TV'),
(9, 'Under the Dome', 'iZombie', 'Séries TV'),
(10, 'The 100', 'How I met your mother ?', 'Séries TV');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `CATEGORIE`
--
ALTER TABLE `CATEGORIE`
 ADD PRIMARY KEY (`Nom_Categorie`);

--
-- Index pour la table `PALMARES`
--
ALTER TABLE `PALMARES`
 ADD PRIMARY KEY (`Id_palmares`);

--
-- Index pour la table `QUESTION`
--
ALTER TABLE `QUESTION`
 ADD PRIMARY KEY (`Id_Question`), ADD KEY `FK_QUESTION_Id_Theme` (`Id_Theme`);

--
-- Index pour la table `THEME`
--
ALTER TABLE `THEME`
 ADD PRIMARY KEY (`Id_Theme`), ADD KEY `FK_THEME_Nom_Categorie` (`Nom_Categorie`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `PALMARES`
--
ALTER TABLE `PALMARES`
MODIFY `Id_palmares` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT pour la table `QUESTION`
--
ALTER TABLE `QUESTION`
MODIFY `Id_Question` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT pour la table `THEME`
--
ALTER TABLE `THEME`
MODIFY `Id_Theme` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `QUESTION`
--
ALTER TABLE `QUESTION`
ADD CONSTRAINT `FK_QUESTION_Id_Theme` FOREIGN KEY (`Id_Theme`) REFERENCES `THEME` (`Id_Theme`);

--
-- Contraintes pour la table `THEME`
--
ALTER TABLE `THEME`
ADD CONSTRAINT `FK_THEME_Nom_Categorie` FOREIGN KEY (`Nom_Categorie`) REFERENCES `CATEGORIE` (`Nom_Categorie`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
