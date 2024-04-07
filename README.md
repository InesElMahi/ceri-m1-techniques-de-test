# UCE Génie Logiciel Avancé : Techniques de tests


Inès El Mahi
M1 ia classique  <br><br>

**CircleCI** automatise les tests et le déploiement, fournissant des retours rapides sur les modifications apportées au code.

# CircleCI
[![CircleCI](https://dl.circleci.com/status-badge/img/gh/InesElMahi/ceri-m1-techniques-de-test/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/InesElMahi/ceri-m1-techniques-de-test/tree/master)  <br><br>


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
