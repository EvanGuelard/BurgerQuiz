<?php // Ce fichier fait partie du projet BurgerQuiz

	include "head.php";

	echo '
		<section>
			<h1>Salon <strong>multijoueur</strong></h1>
			<div class="devider"><i class="fa fa-users fa-lg"></i></div>

	        <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script> 
	        <script src="http://localhost:8000/socket.io/socket.io.js"></script>
	        <script src="View/js/game.js"></script>
	        <div class="divContenu"><p id="waitUser"></p></div>
	        <script>
	            init();
	        </script> 
       	</section>
	';
	include "foot.php";