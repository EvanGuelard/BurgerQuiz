/**************************************************
** GAME VARIABLES
**************************************************/
var socket;         // Socket


/**************************************************
** GAME INITIALISATION
**************************************************/
function init() {

    // Connexion à socket.io
    socket = io.connect('http://localhost:8000');

    $("#waitUser").html("En attente d'un adversaire, veuillez patienter !");
    // Gestion des evenements
    setEventHandlers();

    // On demande le pseudo a l'utilisateur, on l'envoie au serveur et on l'affiche dans le titre
    pseudo = prompt('Quel est votre pseudo ?');
    socket.emit('nouveau', pseudo);
    document.title = pseudo + ' - ' + document.title;
};

/**************************************************
** GAME EVENT HANDLERS
**************************************************/
var setEventHandlers = function() {
    socket.on("newPlayer", onNewPlayer);
    socket.on("alreadyPlayer", onAlreadyPlayer);
};

function onNewPlayer(pseudoSecond) {
    alert("Voici un nouveau joueur : " + pseudoSecond);
    $("#waitUser").html("Adversaire trouvé ! Le jeu va commencer.");
    setTimeout(document.location.href="index.php?page=multi&player=1", 8000);
};


function onAlreadyPlayer(pseudoFirst) {
    alert("Un joueur vous attendait : " + pseudoFirst);
    $("#waitUser").html("Adversaire trouvé ! Le jeu va commencer.");
    setTimeout(document.location.href="index.php?page=multi&player=2", 8000);
};
