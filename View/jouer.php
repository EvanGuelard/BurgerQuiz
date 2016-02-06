<?php // Ce fichier fait partie du projet BurgerQuiz

	include "head.php";

	// Choix solo ou multijoueur
	echo '
		<section id="choix_nbj">
			<h1>Nombre de <strong>joueurs</strong></h1>
			<div class="devider"><i class="fa fa-user fa-lg"></i></div>
			<a href = "index.php?page=jeu&start=1"><img src="View/img/burger1j.png" alt="1 joueur" id="unj"/></a>
			<a href = "index.php?page=multi"><img src="View/img/burger2j.png" alt="2 joueurs" id="deuxj"/></a>
			<br><br>
		</section>
	';
	include "foot.php";