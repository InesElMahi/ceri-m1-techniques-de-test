# UCE Génie Logiciel Avancé : Techniques de tests


Inès El Mahi
M1 ia classique

# CircleCI
[![CircleCI](https://dl.circleci.com/status-badge/img/gh/InesElMahi/ceri-m1-techniques-de-test/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/InesElMahi/ceri-m1-techniques-de-test/tree/master)

# CodeCov
[![codecov](https://codecov.io/gh/InesElMahi/ceri-m1-techniques-de-test/graph/badge.svg?token=EPOYW3G6KB)](https://codecov.io/gh/InesElMahi/ceri-m1-techniques-de-test)


## Choix Techniques d’Implémentation

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


## Introduction

Vous allez à travers ces projet mettre en application une partie des aspects évoqués en cours vis à vis des techniques de tests.  
Pour cela nous allons réaliser un projet logiciel de petite taille, en suivant la roadmap suivante : 
- Setup du projet
- Mise en place des outils d’intégration continue
- Écriture des tests unitaires
- Écriture des mocks, et validation des tests
- Développement dirigé par les tests
- Documentation et conventions de style
- Test d'une implémentation donnée

Durant cette série de TPs, le gestionnaire de version Git sera utilisé à foison, à travers la plateforme GitHub. Si vous n’êtes pas à l’aise avec cet outil[^1], [voici](http://rogerdudler.github.io/git-guide/) un petit guide à garder sous la main.

## Sujets

L'ensemble des sujets de TPs peut être trouvé dans le dossier `TPs`.

Le dossier `src` contient la définition de l'ensemble des interfaces qui seront l'objet de vos travaux.

## Rendus

Le rendu des TPs se fait au rythme suivant :

- TP1 : 2ème séance
- TP2 : 2ème séance
- TP3 : 3ème séance
- TP4 : 5ème séance
- TP5 : dernière séance
- TP6 : dernière séance

Pour chaque rendu vous devez créer un tag à partir du commit qui correspond à la complétion du TP.  
Si vous ne spécifiez pas de tag, le dernier commit à la date-heure de la fin de séance sera celui considéré.

[^1]: Si vous n’êtes vraiment pas à l’aise avec cet outil nous vous conseillons quand même vivement de vous y mettre.
