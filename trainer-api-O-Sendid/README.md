# trainer-api-SENDID

## But du projet
API permettant de représenter un dresseur de pokémon;

## Lancer l'application
Pour lancer l'application, il suffit d'exécuter la commande suivante:
```
mvn clean install && mvn spring-boot:run
```
- L'application sera accessible via l'url http://localhost:8081/  
- BDD = PostgreSql

Routes disponibles:
- GET http://localhost:8081/trainers/ : retourne  tous les trainers
- GET http://localhost:8081/trainers/Ash: retourne le trainer avec le nom Ash
- POST http://localhost:8081/trainers/create : créer un nouveau trainer trainer
- PUT http://localhost:8081/trainers/: modifier un trainer
- DELETE http://localhost:8081/trainers/delete/{name}: supprimer un trainer

## Exécuter les tests
Pour exécuter les tests, utiliser la commande suivante:
```
mvn test
```

## Déploiement
L'application est deployé en cloud


## Auteur

SENDID Omar 
Etudiant en Master 2 MIAGE FA