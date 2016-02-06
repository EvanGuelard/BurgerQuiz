<?php // Ce fichier fait partie du projet BurgerQuiz

	/**
	 * La classe Categorie permet de creer un objet qui stoquera toutes les catégories utilisées pour la partie
	 *
	 * @author Evan <evan.guelard[@]gmail.com>
	 */
	class Categorie {
		
		private $nomCategorie;

		/**
		 * Initialise le tableau de catégories puis en stoque 2 aléatoires de la base
		 *
		 * @param $bdd la connection à la base de données
		 */
		function __construct ($bdd) {

			// on dit qu'il n'y a pas assez de themes
			$themes = 0;
			// et que la catégorie n'ayant pas assez de theme se nomme "" (aucune pour le moment)
			$categorieThemes = "";

			//s'il n'y a pas de thèmes
			while (!$themes) {

				// on dit qu'il y a des thèmes
				$themes = 1;
				$this->nomCategorie = array();

				// Sélectionne 2 catégories aléatoires
				$requeteCategorie = mysqli_query($bdd,"select * from CATEGORIE where Nom_Categorie != '".$categorieThemes."' order by rand() limit 2") or die(mysqli_error($bdd));
				
				while ($tabRequeteCategorie = mysqli_fetch_array($requeteCategorie)) {

					// récupération du nombre de themes de la catégories
					$verifThemes = mysqli_query($bdd,"select count(*) from THEME where Nom_Categorie = '".$tabRequeteCategorie["Nom_Categorie"]."'") or die(mysqli_error($bdd));

					$count = mysqli_fetch_array($verifThemes);

					// s'il y en a moins de 2, on refera l'aléatoire
					if ($count[0] < 2) {
						//il n'y a pas assez de themes
						$themes = 0;
						// la catégorie où il n'y a pas assez de themes
						$categorieThemes = $tabRequeteCategorie["Nom_Categorie"];
					}

					else {
						array_push($this->nomCategorie, $tabRequeteCategorie["Nom_Categorie"]);
					}

					mysqli_free_result($verifThemes);
				}

				if (sizeof($this->nomCategorie) < 2) {
					echo "<p>Il n'y a pas assez de catégories pour jouer.</p>";
					// s'il n'y a pas 2 catégories, de toutes facon il n'y auras pas 2x2 thèmes
					$themes = 1;
				}

				mysqli_free_result($requeteCategorie);
			}
		}

		/**
		 * Permet de récupérer le tableau de nom de catégories
		 *
		 * @return tableau de nom de catégories
		 */
		function getNomCategorie () { return $this->nomCategorie; }
	}