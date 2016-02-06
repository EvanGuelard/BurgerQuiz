<?php // Ce fichier fait partie du projet BurgerQuiz

	include "head.php";

	echo '
		<section>
			<h1>Palmarès</h1>
			<div class="devider"><i class="fa fa-sort-amount-desc fa-lg"></i></div>
			<table>';
			// affichage de tout le palmarès récupéré
				for ($i = 0 ; $i < sizeof($tabPseudo) ; $i++) {
					// place dans le palmarès (petite case)
					// pseudo
					// score (petite case)
					echo '
						<tr>
							<td class="petitTD">'.($i+1).'</td>
							<td>'.$tabPseudo[$i].'</td>
							<td class="petitTD">'.$tabScore[$i].'</td>
						</tr>';
				}
				echo '
			</table>
		</section>';

	include "foot.php";