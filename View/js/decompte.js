// Ce fichier fait partie du projet BurgerQuiz

var temps = 6;
function decompte() { 
	temps = temps - 1;
	document.getElementById('temps').innerHTML = temps;
}
setInterval(decompte, 1000); 