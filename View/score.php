<?php // Ce fichier fait partie du projet BurgerQuiz

	include "head.php";

	echo '
		<section id="sectionScore">
			<h1>Score</h1>
			<div class="devider"><i class="fa fa-star fa-lg"></i></div>
			<div id="zoneScore"><p>'.$_SESSION["score"].'</p><p>points</p></div>';

		// si le joueur peut aller dans le palmarès (en fonction de ses points)
		if (isset($goPalmares)) {
			echo '<div id="goPalmares">';

				// si il a cliqué sur ajouter son pseudo au palmarès (le formulaire le permettant et au elseif)
				if (isset($_GET["ok"]) && isset($_POST["pseudo"])) {
					// s'il y a une appostrophe on y ajoute devant elle un anti slash
	                $pseudoAjout = str_replace("'", "\'", $_POST["pseudo"]);
	                $scoreAjout = $_SESSION["score"];

	                // insersion dans la base de son score / pseudo
                    mysqli_query($bddConnect->getBdd(), "INSERT INTO `PALMARES` (`Id_palmares`, `Pseudo`, `Score`) VALUES (NULL, '$pseudoAjout', '$scoreAjout');") or die(mysqli_error($bddConnect->getBdd()));

                    // message pour dire à l'utilisateur que son score est ajouté au palmarès
					echo '<p>Vous avez bien été <strong>ajouté</strong> au palmares.<br><br><a href = "index.php?page=palmares" id="navGauche"><span>Voir le palmarès.<span></a></p> ';

					// le unset permet d'empecher le "glitch" de précédent (dans le navigateur) et je rerentre mon pseudo pour ré-entrer dans la palmarès
					unset($_SESSION["score"]);
				}

				//s'il y a un score
				elseif ($_SESSION["score"]) {
					// formulaire pour entrer dans la palmarès (demande du pseudo)
					echo '
					<p>Entrez dans le palmarès en entrant votre <strong>pseudo</strong> ci-dessous.</p>
					<form method = "post" action = "index.php?page=score&ok=1">
						<input type = "text" placeholder = "Pseudo" name = "pseudo" required></input>
						<input type = "submit" value = "Valider"></input>
					</form>';
				}
				// sinon on ne lui propose pas d'y entrer
			echo '</div>';

		}
		else { echo '<br><br>'; }

	// proposition pour relancer le jeu
	echo '
		<a href="index.php?page=jeu&start=1" id="rejouer">Rejouer ?</a>
	</section>';

	include "foot.php";