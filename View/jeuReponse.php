<?php // Ce fichier fait partie du projet BurgerQuiz

	include "head.php";

	$position = $_SESSION["position"];
	// barre d'avancement des questions en pourcentage
	$valeurQuestion = ($position + 1)*100/sizeof($_SESSION["question"]);

	//nom de la classe pour mettre en valeur la réponse
	$classWin = ' class="win" ';

	// réponse à la question actuelle
	$reponseQuestion = $_SESSION["reponse"][$position];

	// si on est à la derniere reponse, on passe au score
	if ($_SESSION["position"] == sizeof($_SESSION["question"])-1) {
		echo '<script type="text/javascript" src="View/js/goScore.js"></script>';
	}
	// sinon on passe à la question suivante
	else {
		echo '<script type="text/javascript" src="View/js/goJeu.js"></script>';
	}
	// barre de défilment du temps en jQuery
	// + affichage de la catégories, du thème ...
	echo '
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script>$(document).ready(function() { $(\'#avancement\').css(\'width\', \''.$valeurQuestion.'%\');})</script>
		<section class="jeu">
			<h1>'.$_SESSION["categorie"][$position].'</h1>
			<div class="devider"><i class="fa fa-gamepad fa-lg"></i></div>
			<div class="zoneJeu">
				<div class="zoneTemps full">
					<span class="temps"></span>
				</div>
				<h3>'.$_SESSION["theme1"][$position].', '.$_SESSION["theme2"][$position].' ou les deux ?</h3>
				<h2>'.$_SESSION["question"][$position].'</h2>
				<div class="divClicRep">';

				// on met la classe win sur la balise a de la bonne réponse
				echo '<a ';
					if ($reponseQuestion == 1) { 
						echo $classWin;
					}
				echo '>'.$_SESSION["theme1"][$position].'</a>
					<a ';
					if ($reponseQuestion == 2) {
						echo $classWin;
					}
				echo '>'.$_SESSION["theme2"][$position].'</a>
					<a ';
					if ($reponseQuestion == 0) {
						echo $classWin;
					}
				echo '>Les deux</a>';

			// s'il y a une explication de la réponse on l'affiche
			if ($_SESSION["explication"][$position] != null) {
				echo '<p>'.$_SESSION["explication"][$position].'</p><br>';
			}

			// affichage de l'avancement et fin de la div
			echo '</div>
				<div id = "avancement">
					<p>Score '.$_SESSION["score"].'</p>
				</div>
			</div>
		</section>
	';
	include "foot.php";