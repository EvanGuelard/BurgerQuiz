<?php // Ce fichier fait partie du projet BurgerQuiz

	include "head.php";

	// Informations sur le jeu
	echo '
		<section>
			<h1>&Agrave; Propos</h1>
			<div class="devider"><i class="fa fa-info fa-lg"></i></div>
			<article>
				<h4>Début du jeu</h4>
				<p>Lors de l\'initialisation du jeu, deux catégories sont choisies aléatoirement. Dans ces catégories sont choisis aléatoirement deux thèmes pour chacune. Et enfin dans ces thèmes sont choisis pour chacun trois questions aléatoires et placées dans un ordre aléatoires.</p>

				<h4>Déroulement du jeu</h4>
				<p>Vous devez répondre à la question posée en moins de 6 secondes. Une fois le temps écoulé ou une réponse donnée, la bonne réponse est donnée pendant 3 secondes puis on passe à la question suivante.
				<br>Une fois toutes les questions passées, est affiché votre score et une possibilitée d\'entrer votre pseudo pour intégrer le palmarès si votre score est assez élevé.</p>

				<h4>Cadre de création du jeu</h4>
				<p>Ce jeu a été réalisé dans le cadre d\'un projet de fin d\'année en deuxième année de cycle informatique et réseaux à l\'ISEN Brest par Evan Guélard et Yohann Vinez.</p>
			</article>
		</section>
	';
	include "foot.php";