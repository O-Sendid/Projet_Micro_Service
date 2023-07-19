# shop-Api-SENDID

## But du projet
Ils peuvent créditer leur portefeuille en achetant des Poké-Dollar, au taux de change d'1 euro pour 20 000 . Chaque dresseur démarre avec la somme de 10 000  offerts. Un dresseur qui se crée un compte, peut aussi également choisir un Pokemon offert parmi les 3 starters (id 1, 4, 7)
## Lancer l'application
Pour lancer l'application, il suffit d'exécuter la commande suivante:
```
mvn clean install && mvn spring-boot:run
```
L'application sera accessible via l'url http://localhost:7001/  

La boutique doit :

- gérer le portefeuille de chaque dresseur, et lui permettre de l’approvisionner via une API REST
- exposer une API REST consommable par le game-ui pour afficher la liste des produits
- mettre à jour l’équipe du dresseur lors d’un achat (avec un PUT REST)


Pages disponibles:
- http://localhost:7001/shop : affiche la liste des produits ( GET )
- http://localhost:7001/shop/{name_trainer}/buy/{id_produit} : un trainer peut acheter un produit selon son wallet  ( POST )
- http://localhost:7001/wallet : la liste des walles des trainers
- http://localhost:7001/wallet/trainer/{name_trainer} : affichage du waller par trainer

## Exécuter les tests
Pour exécuter les tests, utiliser la commande suivante:
```
mvn test
```

## Déploiement
- L'application est deployé sur Clever cloud
- le lien : http://app-bd4a73bd-4f05-4e44-8ca5-06132c33ea7c.cleverapps.io/

## Auteur

SENDID Omar 
Etudiant en Master 2 MIAGE FA