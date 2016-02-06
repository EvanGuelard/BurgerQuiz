<?php // Ce fichier fait partie du projet BurgerQuiz

	include "head.php";

	// postion du jeu dans les tableaux
	$position = $_SESSION["position"];
	// x ieme question
	$valeurQuestion = ($position + 1);
	// pourecentage en question pour la barre de progression du jeu
	$pourcentageQuestion = ($position + 1)*100/12;

	// Ajout de la voix et lecture de la question
	// script pour aller à la réponse après un certain temp
	// &script pour le décompte (chiffré) du temps
	// affichage de la zone de jeu ...
	echo '
		<script type="text/javascript" src="View/js/mespeak/mespeak.js"></script>
		<script type="text/javascript">
			meSpeak.speak("'.$_SESSION["question"][$position].'");
			meSpeak.loadConfig("View/js/mespeak/mespeak_config.json");
			meSpeak.loadVoice("View/js/mespeak/voices/fr.json");
		</script>
		<script type="text/javascript" src="View/js/goReponse.js"></script>
		<script type="text/javascript" src="View/js/decompte.js"></script>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script>$(document).ready(function() { $(\'.zoneTemps\').animate({\'width\': \'100%\'},6000); });</script>
		<script>$(document).ready(function() { $(\'#avancement\').css(\'width\', \''.$pourcentageQuestion.'%\');})</script>
		<div id="audio"></div>
		<section class="jeu">
			<h1>'.$_SESSION["categorie"][$position].'</h1>
			<div class="devider"><i class="fa fa-gamepad fa-lg"></i></div>
			<div class="zoneJeu">
				<div class="zoneTemps" id="zoneTemps">
					<span id="temps"></span>
				</div>
				<h3>'.$_SESSION["theme1"][$position].', '.$_SESSION["theme2"][$position].' ou les deux ?</h3>
				<h2 id="question">'.$_SESSION["question"][$position].'</h2>
				<div class="divClic">
					<a href="index.php?page=jeu&choix=1" class="proposition">'.$_SESSION["theme1"][$position].'</a>
					<a href="index.php?page=jeu&choix=2" class="proposition">'.$_SESSION["theme2"][$position].'</a>
					<a href="index.php?page=jeu&choix=0" class="proposition">Les deux</a>
				</div>
				<div id = "avancement">
					<p>Q '.$valeurQuestion.'/12</p>
				</div>
			</div>
		</section>
	';
	include "foot.php";