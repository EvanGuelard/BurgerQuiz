var io = require('socket.io'); // Chargement du module pour mettre en place les websockets

// Variables
var server; // Le socket
var nbPlayer = 0;
var nomPlayer;

// Gestion des evenements
// Attend l'évènement "connection"
// Le client génère cet évènement lorsque la connexion est établie avec le serveur (voir l'établissement de connexion côté client)
// En cas de connexion appel à la fonctione onSocketConnection
// Un paramètre est envoyé en paramètre de l'évènement "connection" : ce paramètre représente le client
var setEventHandlers = function() {
    server.sockets.on("connection", onSocketConnection);
};

// Fonction prenant en paramètre le client (voir ci-dessus)
// Réception ou envoi d'évènement à partir de cet objet : client
function onSocketConnection(client) {

    // Attente de l'évènement "new"
    // Dans cet exemple l'évènement "new" est envoyé avec un paramètre "pseudo"
    client.on('nouveau', function(pseudo) {
        // Log pour debug
        console.log('Nouveau Joueur : '+ pseudo +'!');

        // Envoi d'un message aux autres clients connectés
        client.broadcast.emit('newPlayer', pseudo);

        if(nbPlayer) { 
            client.emit('alreadyPlayer', nomPlayer);
            nbPlayer = 0; 
        }
        else {
            nbPlayer++;
            nomPlayer = pseudo;
        }
    });

};

// Initialisation
function init() {
    // Le server temps réel écoute sur le port 8000
    server = io.listen(8000);

    // Gestion des évènements
    setEventHandlers();

};

// Lance l'initialisation
init();
