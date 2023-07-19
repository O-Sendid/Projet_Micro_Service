# pokemon-type-api-SENDID

## But du projet
API permettant de représenter un pokémon sous un fichier.json en entrée 

## Lancer l'application
Pour lancer l'application, il suffit d'exécuter la commande suivante:
```
mvn clean install && mvn spring-boot:run
```
L'application sera accessible via l'url http://localhost:8082/  

Routes disponibles:
- http://localhost:8082/pokemon-types/ : retourne  tous les pokémons
- http://localhost:8082/pokemon-types/25 : retourne le pokémon l'id 25
- http://localhost:8082/pokemon-types/?name=pikachu : retourne par le nom 
- http://localhost:8082/pokemon-types/?types=bug,poison : retourne les pokémons possédant le type passé en parametre 

## Exécuter les tests
Pour exécuter les tests, utiliser la commande suivante:
```
mvn test
```

## Déploiement
L'application est deployé sur clever-cloud



## Auteur

SENDID Omar  
Etudiant en Master 2 MIAGE FA