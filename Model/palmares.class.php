<?php // Ce fichier fait partie du projet BurgerQuiz
	
	/**
	 * La classe Palmarès permet de creer un objet qui stoquera toutes les valeurs du palmarès
	 *
	 * @author Evan <evan.guelard[@]gmail.com>
	 */
	class Palmares {
		
		private $idPalmares;
		private $pseudo;
		private $score;

		/**
		 * Initialise le tableau de valeurs du palmarès puis stoque dedans les valeurs de la base
		 *
		 * @param $bdd la connection à la base de données
		 */
		function __construct ($bdd) {
			$this->idPalmares = array();
			$this->pseudo = array();
			$this->score = array();

			// récupère les 10 premirères valeurs de palmarès dans l'odre décroissant, calssé par score, 
			// puis id pour avoir le plus ancien en 1er position en cas d'égalité (car id = auto incrément)
			$requetePalmares = mysqli_query($bdd,"select * from PALMARES order by score desc, id_palmares limit 10") or die(mysqli_error($bdd));

			while ($tabRequetePalmares = mysqli_fetch_array($requetePalmares)) {
				array_push($this->idPalmares, $tabRequetePalmares["Id_palmares"]);
				array_push($this->pseudo, $tabRequetePalmares["Pseudo"]);
				array_push($this->score, $tabRequetePalmares["Score"]);
			}
			mysqli_free_result($requetePalmares);
		}

		/**
		 * Permet de récupérer le tableau de pseudos
		 *
		 * @return tableau de pseudos
		 */
		function getPseudo () { return $this->pseudo; }

		/**
		 * Permet de récupérer le tableau de scores
		 *
		 * @return tableau de scores
		 */
		function getScore () { return $this->score; }
	}