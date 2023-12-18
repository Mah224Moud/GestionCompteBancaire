# Gestion de compte bancaire avec Akka remote

Cette projet consite au développement d'un système de gestion de comptes bancaires à l’aide d’Akka. Un banquier
est en charge de plusieurs comptes, et travaille dans une banque. Lorsqu’un client se présente à la banque pour déposer ou retirer de l’argent, la banque doit transmettre la demande du client au banquier responsable de son compte.  
Un mécanisme de persistance à été implémenté en liaison avec un SGBD, afin de pouvoir retrouver l’état des comptes des clients même en cas d’arrêt du système.

## Pré-requis :

- [Java](https://www.java.com/fr/)
- [Maven](https://maven.apache.org/)
- [Akka](https://akka.io/)

## Compilation et exécution :

Pour compiler le code, utiliser Maven avec les commandes suivantes :

```
mvn clean compile install
```

#### Lancer d'abord la banque :

```
mvn -pl bank exec:java -Dexec.mainClass="sd.akka.App"
```

#### Puis lancer le banquier :

```
mvn -pl banquier exec:java -Dexec.mainClass="sd.akka.App"
```

##### Remarque :

Les infos de connexion du banquier vous seront demandées au moment du lancement.

```
Email: banquier@test.com
Mot de passe : banquier
```

#### Enfin, lancer le client :

```
mvn -pl client exec:java -Dexec.mainClass="sd.akka.App"
```

##### Remarque :

Les infos de connexion du client vous seront également demandées au moment du lancement.  
Ces informations varients suivants le moment où vous avez accès au compte. Peu importe, elles suivront toujours la même logique qui sera la suivante: `clientX@test.com` et `clientX` comme mot de passe. Où `X` est un chiffre qui commence à partir de 1 dans l'ordre croissant.

###### Exemple :

```
Email: client1@test.com
Mot de passe : client1
```

```
Email: client2@test.com
Mot de passe : client2
```

```
Email: client3@test.com
Mot de passe : client3
```

## Actions:

### Banque:

- Transmettre les demandes de depot ou de retrait d'argent du client au banquier.
- Renvoyer les messages de confirmation des depots et des retraits du banquier au client.
- Transmettre les demandes d'historique de transactions au banquier et renvoyées la réponse au client.

### Banquier:

- Consulter le solde d'un client à l'aide de son numéro de compte.
- Traiter les demandes du client venant de la banque à propos des depots ou des retraits d'argent et renvoyer les messages de confirmation à la banque.
- Créer un nouveau compte pour un client(compte client et compte bancaire).
- Traiter les deamandes d'historique de transaction recues de la banque et renvoyer l'historique.

### Client:

- Consulter son solde.
- Faire une demande de depot ou de retrait d'argent à la banque.
- Consulter son historique de transactions en faisant une demande à la banque.

## Author

[Mah224Moud](https://github.com/Mah224Moud)
