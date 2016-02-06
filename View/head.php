<?php // Ce fichier fait partie du projet BurgerQuiz

	// En-tête commune à toutes les pages
	// Avec lien vers css / icone / utf8 / titre de la page
	// Et avec barre de navigation
	echo '
		<!DOCTYPE html>
			<html>
				<head>
					<title> Burger Quiz </title>
			        <!-- Meta -->
					<meta charset = "utf-8"/>
			        <meta name=viewport content="width=device-width, initial-scale=1"/>
					<!-- Icone -->
					<link rel="SHORTCUT ICON" href="View/img/burger.png"/>
					<!-- Style -->
					<link rel="stylesheet" type="text/css" href="View/css/style.css">
					<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
				</head>
				<body>
					<nav>
				        <ul>
				            <li> <a href = "index.php?page=palmares" id="navGauche"><span>Palmarès<span></a></li>
				            <li> <a href = "index.php?page=accueil"> <img src= "View/img/burger.png" alt="Logo Burger Quiz" id="logo"/></a></li>
				            <li> <a href = "index.php?page=about" id="navDroite"><span>à propos</span></a></li>
				        </ul>
				    </nav>
	';