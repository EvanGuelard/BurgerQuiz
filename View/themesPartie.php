<?php // Ce fichier fait partie du projet BurgerQuiz

	include "head.php";

	// cette page s'affiche un certain temps pui commence le jeu 
	// on affiche les thèmes de la partie chosis aléatoirement
	echo '
		<div class=zoneTemps></div>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script>$(document).ready(function() { $(\'.zoneTemps\').animate({\'width\': \'100%\'},8000); });</script>
		<script type="text/javascript" src="View/js/goPlay.js"></script>
		<section class="jeu">
			<h1><strong>Thèmes</strong> de la partie</h1>
			<div class="devider"><i class="fa fa-gamepad fa-lg"></i></div>
			<div class="divContenu">';

			for ($i = 0 ; $i < sizeof($_SESSION["theme1"]) ; $i += 3) {
				echo '<p>'.$_SESSION["theme1"][$i].', '.$_SESSION["theme2"][$i].' ou les deux ?</p>';
			}

			// ou bien on commence le jeu en cliquant
			echo '
			</div>
			<a href="index.php?page=jeu" id="rejouer">Commencer le jeu</a>
		</section>';

	include "foot.php";