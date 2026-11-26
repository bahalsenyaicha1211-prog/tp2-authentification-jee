# TP2 – Authentification (JEE)

Application web : inscription et connexion avec Jakarta Servlet, JSP, JPA (Hibernate), MySQL.
Mot de passe haché en SHA-256, utilisateur gardé en session.

## Prérequis
- Java, Maven, Tomcat 10.1, MySQL

## Installation
1. Créer la base : `CREATE DATABASE tp2db;`
2. Dans `src/main/resources/META-INF/persistence.xml`, saisir son mot de passe MySQL.
3. `mvn package`, copier le WAR dans `webapps` de Tomcat.
4. Ouvrir http://localhost:8081/servlet-demo/inscription.jsp
   (la table `utilisateur` est créée automatiquement au premier lancement)

## Auteur
Alseny Bah