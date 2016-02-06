<?php // Ce fichier fait partie du projet BurgerQuiz

	/**
	 * La classe Game permet de creer une partie de jeu avec tout les éléments dont on a besoin
	 *
	 * @author Evan <evan.guelard[@]gmail.com>
	 */
	class Game {
		
		private $tabCategorie;
		private $tabTheme1;
		private $tabTheme2;
		private $tabQuestion;
		private $tabReponse;
		private $tabExplication;
		private $position;

		/**
		 * Initialise les variables en tableaux, et crée l'ensemble des catégories question de la partie
		 */
		function __construct () {
			$bddConnect = new Connector ();

			$this->tabCategorie = array();
			$this->tabTheme1 = array();
			$this->tabTheme2 = array();
			$this->tabQuestion = array();
			$this->tabReponse = array();
			$this->tabExplication = array();

			// étape actuelle de la partie (1 étape = 1 question)
			$this->position = 0;

			// création et récupération des catégories de la partie
			$Categorie = new Categorie ($bddConnect->getBdd());
			$nomCategorie = $Categorie->getNomCategorie();
			for ($i = 0 ; $i < sizeof($nomCategorie) ; $i++) {
				// création et récupération des thèmes de la partie associés aux catégories choisies
				$Theme = new Theme ($bddConnect->getBdd(), $nomCategorie[$i]);
				$idTheme = $Theme->getIdTheme();
				$theme1 = $Theme->getTheme1();
				$theme2 = $Theme->getTheme2();
				for ($j = 0 ; $j < sizeof($theme1) ; $j++) {
					// cration et récupération des questions de la partie associées aux thémes
					$Question = new Question ($bddConnect->getBdd(), $idTheme[$j]);
					$intituleQuestion = $Question->getIntituleQuestion();
					$reponse = $Question->getReponse();
					$explication = $Question->getExplication();
					for ($h = 0 ; $h < sizeof($intituleQuestion) ; $h++) {
						// 1 indice de chauqe tableau équivaut au catégorie / theme question / ... de la question actuelle
						array_push($this->tabCategorie, $nomCategorie[$i]);
						array_push($this->tabTheme1, $theme1[$j]);
						array_push($this->tabTheme2, $theme2[$j]);
						array_push($this->tabQuestion, $intituleQuestion[$h]);
						array_push($this->tabReponse, $reponse[$h]);
						// s'il existe ou non une explication de la réponse
						if (isset($explication[$h])) {
							array_push($this->tabExplication, $explication[$h]);
						}
						else {
							array_push($this->tabExplication, null);
						}
					}
				}
			}
		}

		/**
		 * Ajoute aux sessions les variables de la partie
		 */
		function initSession () {
			$_SESSION["categorie"] = $this->tabCategorie;
			$_SESSION["theme1"] = $this->tabTheme1;
			$_SESSION["theme2"] = $this->tabTheme2;
			$_SESSION["question"] = $this->tabQuestion;
			$_SESSION["reponse"] = $this->tabReponse;
			$_SESSION["explication"] = $this->tabExplication;
			$_SESSION["position"] = $this->position;

			$_SESSION["score"] = 0;
		}
		
	}