<?php // Ce fichier fait partie du projet BurgerQuiz
	
	/**
	 * La classe Questions permet de creer un objet qui stoquera toutes les question et informations liées utilisées pour la partie
	 *
	 * @author Evan <evan.guelard[@]gmail.com>
	 */
	class Question {
		
		private $idQuestion;
		private $intituleQuestion;
		private $reponse;
		private $explication;


		/**
		 * Initialise le tableau de questions et autres informations des questions
		 *
		 * @param $bdd la connection à la base de données
		 * @param $idTheme le numéro d'un thème
		 */
		function __construct ($bdd, $idTheme) {
			$this->idQuestion = array();
			$this->intituleQuestion = array();
			$this->reponse = array();
			$this->explication = array();

			// 3 question dans un ordre aléatoire correspondant au thème donné
			$requeteQuestion = mysqli_query($bdd,"select * from QUESTION where Id_Theme = '$idTheme' order by rand() limit 3") or die(mysqli_error($bdd));

			while ($tabRequeteQuestion = mysqli_fetch_array($requeteQuestion)) {
				//remplissage des tableaux pour chaque valeurs
				array_push($this->idQuestion, $tabRequeteQuestion["Id_Question"]);
				array_push($this->intituleQuestion, $tabRequeteQuestion["Intitule_Question"]);
				array_push($this->reponse, $tabRequeteQuestion["Reponse"]);
				array_push($this->explication, $tabRequeteQuestion["Explication"]);
			}
			if (sizeof($this->idQuestion) < 3) {
				header("Location: index.php?page=jeu&start=1");
			}
			mysqli_free_result($requeteQuestion);
		}

		/**
		 * Permet de récupérer le tableau d'id de questions
		 *
		 * @return tableau d'id question
		 */
		function getIdQuestion () { return $this->idQuestion; }

		/**
		 * Permet de récupérer le tableau des intitulés de questions
		 *
		 * @return tableau d'intitule de questions
		 */
		function getIntituleQuestion () { return $this->intituleQuestion; }

		/**
		 * Permet de récupérer le tableau réponses aux questions
		 *
		 * @return tableau de réponses
		 */
		function getReponse () { return $this->reponse; }

		/**
		 * Permet de récupérer le tableau d'explications de réponses aux questions
		 *
		 * @return tableau de nom d'explication
		 */
		function getExplication () { return $this->explication; }
	}