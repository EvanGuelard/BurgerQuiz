<?php // Ce fichier fait partie du projet BurgerQuiz
	
	/**
	 * La classe Connector permet de se connecter à la base de données
	 *
	 * @author Evan <evan.guelard[@]gmail.com>
	 */
	class Connector {
		
		private $bdd;
		
		/**
		 * Initialise la connection à la base de donnée, passe les résultats en UTF8 et la stoque dans $bdd
		 */
		function __construct () {
			//Variables nécéssaires pour le connecter
			$host = "localhost";
			$user = "BurgerQuiz";
			$password = "Vavan28*";
			$table = "BurgerQuiz";

			$this->bdd = mysqli_connect ($host, $user, $password, $table) or die (mysqli_error($this->bdd));
			mysqli_query($this->bdd, "SET NAMES UTF8");
		}
		/**
		 * Permet de récupérer l'accès à la base de données
		 *
		 * @return l'accès à la base de données
		 */
		function getBdd () { return $this->bdd; }

		/**
		 * Ferme la connection à la base de données
		 */
		function __destruct () {
			mysqli_close($this->bdd);
		}
	}