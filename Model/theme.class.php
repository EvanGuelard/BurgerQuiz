<?php // Ce fichier fait partie du projet BurgerQuiz

	/**
	 * La classe Theme permet de creer un objet qui recupère 2 thème aléatoires dans la base de données
	 *
	 * @author Evan <evan.guelard[@]gmail.com>
	 */
	class Theme {
		
		private $idTheme;
		private $theme1;
		private $theme2;

		/**
		 * Initialise le tableau de thèmes et autres informations des thèmes (id ...)
		 *
		 * @param $bdd la connection à la base de données
		 * @param $categorie le nom de la catégorie correspondante
		 */
		function __construct ($bdd, $categorie) {

			// on dit qu'il n'y a pas assez de questions
			$questions = 0;
			// et que le thème n'ayant pas assez de questions se nomme "" (aucun pour le moment)
			$themeQuestions = "";

			//s'il n'y a pas de thèmes
			while (!$questions) {

				// on dit qu'il y a des questions
				$questions = 1;

				$this->idTheme = array();
				$this->theme1 = array();
				$this->theme2 = array();

				// recupération de 2 thèmes aléatoires correspondant à la catégorie donnée
				$requeteTheme = mysqli_query($bdd,"select * from THEME where Nom_Categorie = '$categorie' order by rand() limit 2") or die(mysqli_error($bdd));

				while ($tabRequeteTheme = mysqli_fetch_array($requeteTheme)) {

					// récupération du nombre de themes de la catégories
					$verifQuestions = mysqli_query($bdd,"select count(*) from QUESTION where Id_Theme = '".$tabRequeteTheme["Id_Theme"]."'") or die(mysqli_error($bdd));

					$count = mysqli_fetch_array($verifQuestions);

					// s'il y en a moins de 2, on refera l'aléatoire
					if ($count[0] < 3) {
						//il n'y a pas assez de themes
						$questions = 0;
						// la catégorie où il n'y a pas assez de themes
						$themeQuestions = $tabRequeteTheme["Id_Theme"];
					}

					else {
						array_push($this->idTheme, $tabRequeteTheme["Id_Theme"]);
						array_push($this->theme1, $tabRequeteTheme["Theme1"]);
						array_push($this->theme2, $tabRequeteTheme["Theme2"]);
					}

					mysqli_free_result($verifQuestions);
				}

				mysqli_free_result($requeteTheme);
			}
		}

		/**
		 * Permet de récupérer le tableau d'id de thèmes
		 *
		 * @return tableau d'id de thèmes
		 */
		function getIdTheme () { return $this->idTheme; }

		/**
		 * Permet de récupérer le tableau de nom de thèmes 1
		 *
		 * @return tableau de nom de thèmes 1
		 */
		function getTheme1 () { return $this->theme1; }

		/**
		 * Permet de récupérer le tableau de nom de thèmes 2
		 *
		 * @return tableau de nom de thèmes 2
		 */
		function getTheme2 () { return $this->theme2; }
	}