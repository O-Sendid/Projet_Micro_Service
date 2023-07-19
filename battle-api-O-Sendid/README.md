# battle-api-SENDID

## But du projet
API permettant de représenter un combat entre deux dresseurs de pokémon.

## Lancer l'application
Pour lancer l'application, il suffit d'exécuter la commande suivante:
```
mvn clean install && mvn spring-boot:run
```
L'application sera accessible via l'url http://localhost:7000/  
Cette API utilise les API pokemon-type et trainer .

Routes disponibles:

Dans un premier temps, notre API de combat devra exposer les routes suivantes :

- POST /battles : Prend 2 paramètres (noms des 2 dresseurs en paramètres). Crée une instance de combat et retourne un UUID permettant de l’identifier.
http://localhost:7000/battles?trainer=Ash&opponent=Misty
- GET /battles : liste les combats en cours http://localhost:7000/battles
- GET /battles/{uuid} : Récupère l’état d’un combat en cours
http://localhost:7000/battles/c65c3674-b59b-4568-aaa5-99fea991fb81/Misty/attack
- POST /battles/{uuid}/{trainerName}/attack : Permet à un dresseur de donner un ordre d’attaque pendant le combat. Retourne l’état du combat.
http://localhost:7000/battles/c65c3674-b59b-4568-aaa5-99fea991fb81

## Exécuter les tests
Pour exécuter les tests, utiliser la commande suivante:
```
mvn test
```

## Déploiement
L'application est deployé sur clever-cloud



## Auteur

SENDID OMAR
Etudiant en Master 2 MIAGE FA