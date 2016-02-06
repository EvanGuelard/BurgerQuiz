<?php // Ce fichier fait partie du projet BurgerQuiz

	// inclusion de toutes les classes
	include 'Model/classes.php';

	// Connection à la base de données
	$bddConnect = new Connector ();

	//s'il ne demande pas de pages spéciales, on le dirige vers l'accueil
	if (!isset($_GET["page"])) {
		include 'View/accueil.php';
	}

	else {

		$page = $_GET["page"];

		//démarage d'une session
		session_start();

		switch ($page) {
			case 'acceuil':
				include 'View/accueil.php';
				break;
			
			case 'palmares':
				// création d'un objet Palmares et récupération des valeurs
				$Palmares = new Palmares ($bddConnect->getBdd());
				$tabPseudo = $Palmares->getPseudo();
				$tabScore = $Palmares->getScore();
				include 'View/palmares.php';
				break;

			case 'jouer':
				include 'View/jouer.php';
				break;

			case 'theme':
				include 'View/theme.php';
				break;

			case 'jeu':
				// si la page jeu est demandée via le choix du nombre de joueurs, début du jeu et création d'une partie
				if(isset($_GET["start"])) {

					if (!isset($_GET["multi"])) {
						$Partie = new Game ();

						// met les informations de la partie dans des SESSIONS
						$Partie->initSession();
					}

					// on prend le temps actuel pour calculer ensuite le score
					include 'View/themesPartie.php';
				}

				//envoi vers la page de la réponse à la question avec retenue du temps et calcul du score
				elseif (isset($_GET["choix"])){
					echo $_SESSION["position"].' - '.sizeof($_SESSION["question"]);
					if ($_GET["choix"] == $_SESSION["reponse"][$_SESSION["position"]]) {
						$temps = microtime(true) - $_SESSION["temps"];
						$_SESSION["score"] += (int)(7 - $temps);
					}
					include 'View/jeuReponse.php';
					$_SESSION["position"]++;

				}

				// on lance le jeu les questions
				else {
					echo $_SESSION["position"].' - '.sizeof($_SESSION["question"]);
					// on prend le temps actuel pour calculer ensuite le score
					$_SESSION["temps"] = microtime(true);
					include 'View/jeu.php';
				}
				break;

			case 'score':
				// si un score est obtenu
				if (isset($_SESSION["score"])) {
					$scoreEu = $_SESSION["score"];
					// on prend le palmarès
					$requetePalmares = mysqli_query($bddConnect->getBdd(),"select score from PALMARES order by score desc, id_palmares limit 10") or die(mysqli_error($bddConnect->getBdd()));
					//Pour acceder à la ernière valeur du palmarès
					for ($i = 0 ; $i < 10 ; $i++) {
						$tabRequetePalmares = mysqli_fetch_array($requetePalmares);
					}
					// et on regarde si le score est suppérieur à la dernière valeur
					if ($scoreEu > $tabRequetePalmares["score"]) {
						$goPalmares = 1;
					}
					include 'View/score.php';
					mysqli_free_result($requetePalmares);
				}
				// sinon envoie vers l'accueil
				else {
					include 'View/accueil.php';
				}
				break;

			case 'multi':
				
				if (isset($_GET["player"])) {

					$fichierJeu = fopen('View/game.txt', 'r+');

					if (!strcmp($_GET["player"], 1)) {

						$PartieMulti = new Game ();

						ftruncate($fichierJeu, 0);

						fputs($fichierJeu, serialize($PartieMulti));
					}

					elseif (!strcmp($_GET["player"], 2)) {

						$PartieMulti = unserialize(fgets($fichierJeu));
					}

					$_SESSION["position"] = 0;

					$PartieMulti->initSession();

					fclose($fichierJeu);

					header("Location: index.php?page=jeu&start=1&multi=1");
				}
				else {
					include 'View/multi.php';
				}
				break;

			case 'about':
				include 'View/about.php';
				break;

			default:
				include 'View/accueil.php';
				break;
		}
	}
