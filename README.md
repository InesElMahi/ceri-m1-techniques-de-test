# UCE Génie Logiciel Avancé : Techniques de tests


Inès El Mahi
M1 ia classique

# CircleCI
[![CircleCI](https://dl.circleci.com/status-badge/img/gh/InesElMahi/ceri-m1-techniques-de-test/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/InesElMahi/ceri-m1-techniques-de-test/tree/master)

# CodeCov
[![codecov](https://codecov.io/gh/InesElMahi/ceri-m1-techniques-de-test/graph/badge.svg?token=EPOYW3G6KB)](https://codecov.io/gh/InesElMahi/ceri-m1-techniques-de-test)

# CheckStyle
![CheckStyle](badges/checkstyle-result.svg)

## Outils et Technologies Utilisés

- **Langage de Programmation** : Java
- **Gestion de Projet** : Maven, pour la gestion des dépendances et la standardisation du build.
- **CI/CD** : CircleCI, pour automatiser les tests et le déploiement.
- **Tests** : JUnit et Mockito pour les tests unitaires et les mocks.
- **Couverture de Code** : JaCoCo pour générer des rapports de couverture de tests, intégrés à Codecov pour le suivi et l'analyse.

## Architecture

- **IPokemonMetadataProvider** : Fournit des métadonnées pour les espèces de Pokémon.
- **IPokemonFactory** : Crée des instances de Pokémon.
- **IPokedex** : Agit comme un conteneur pour stocker et gérer les Pokémon.
- **IPokedexFactory** : Permet la création de l'instance IPokedex.
- **IPokemonTrainerFactory** : Permet de créer des instances de PokémonTrainer.

## Intégration Continue avec CircleCI

Le fichier `circle.yml` a été configuré pour répondre aux exigences suivantes :

- Build sur la branche master.
- Utilisation de Java pour l'environnement d'exécution.
- Exécution des tests unitaires via Maven.

## Choix Techniques d’Implémentation

- **Pokedex** : Fonctionne comme un espace de stockage en mémoire pour les Pokémon, nous l'avons conçu pour ajouter, retrouver, et organiser facilement les Pokémon. 

- **PokedexFactory** : Utilise le modèle Factory pour créer des Pokedex,ce qui nous aide à mieux organiser le code et à faciliter les tests.

- **PokemonFactory** : Crée des Pokémon avec des caractéristiques uniques en mélangeant des informations fixes et des calculs aléatoires ce qui rend chaque Pokémon unique.

- **PokemonMetadataProvider** : Centralise les informations sur les Pokémon, rendant les mises à jour et la gestion des données plus simples et moins susceptible aux erreurs.

- **PokemonTrainerFactory** : Permet aux joueurs de créer leurs propres entraîneurs Pokémon avec un Pokedex unique. 

