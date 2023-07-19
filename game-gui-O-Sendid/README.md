# game-ui-SENDID

## But du projet
L'application est une interface WEB permettant de consomer les API mis en place dans SHOP,TRAINER,POKEMON,BATTLE

## Lancer l'application
Pour lancer l'application, il suffit d'exécuter la commande suivante:
```
mvn clean install && mvn spring-boot:run
```
L'application sera accessible via l'url http://localhost:9000/  



Identifiant et Password : 
- User : Ash | Password : ash_password
- User : Misty | Password : misty_password
- User : Omar | Password :omar

Routes disponibles:

- http://localhost:9000/pokedex : affiche les pokémons d'User
- http://localhost:9000/trainers : affiche les trainers des adversaires
- http://localhost:9000/fight/{name} : commencer un combat avec un trainer
- http://localhost:9000/profile : affiche votre profil avec votre équipe
- http://localhost:9000/shop : liste des produit à acheter selon les régles métiers mis en place
- http://localhost:9000/shop/Ash/buy/Pokeball : Trainer peut acheter un produit selon son porte monnaie 

## Exécuter les tests
Pour exécuter les tests, utiliser la commande suivante:
```
mvn test
```

## Déploiement
Gestion des profiles : 
- Local : http://localhost:9000/ 
- PROD : L'application est deployé sur clever cloud

## Auteur

SENDID Omar
Etudiant en Master 2 MIAGE FA