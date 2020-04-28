# DiceGame

Enunciat: El joc de daus s’hi juga amb dos daus. En cas que el resultat dels dos daus sigui 7, la partida és guanyada, sinó és perduda.

Per poder jugar al joc, t’has de registrar com a jugador amb un nom. Un jugador pot veure un llistat de totes les tirades que ha fet i el percentatge d’èxit.

Per poder realitzar una tirada, un usuari s’ha de registrar amb un nom no re

petit. Al crear-se, se l’hi assigna un identificador numèric únic i una data de registre. Si l’usuari així ho desitja, pot no afegir cap nom i es dirà “ANÒNIM”. Pot haver-hi més d’un jugador “ANÒNIM”. Cada jugador pot veure un llistat de totes les tirades que ha fet, amb el valor de cada dau i si s’ha guanyat o no la partida. A més, pot saber el seu percentatge d’èxit per totes les tirades que ha realitzat.

No es pot eliminar una partida en concret, però si que es pot eliminar tot el llistat de tirades per un jugador.

El software ha de permetre llistar tots els jugadors que hi ha al sistema, el percentatge d’èxit de cada jugador i el percentatge d’èxit mig de tots els jugadors en el sistema.

El software ha de respectar els principals patrons de disseny.

Exercici Joc de Daus:

Model de diagrama de classes: de l'exercici Joc de Daus.

Crea l’estructura del projecte perquè funcioni amb bases de dades (mysql o MongoDB)

1) Implementar les següents funcionalitats:

POST: /players : crea un jugador

PUT /players : modifica el nom del jugador

POST /players/{id}/games/ : un jugador específic realitza una tirada dels daus.

DELETE /players/{id}/games: elimina les tirades del jugador.

GET /players/: retorna el llistat de tots els jugadors del sistema amb el seu percentatge mig d’èxits

GET /players/{id}/games: retorna el llistat de jugades per un jugador.

GET /players/ranking: retorna el ranking mig de tots els jugadors del sistema. És a dir, el percentatge mig d’èxits.

GET /players/ranking/loser: retorna el jugador amb pitjor percentatge d’èxit.

GET /players/ranking/winner: retorna el jugador amb pitjor percentatge d’èxit.

4) Modificar exercici Joc de Daus:

Per poder jugar al joc de daus ara es necessiten sis daus.

En cas que el resultat de tots els daus sigui sis o cinc, 
