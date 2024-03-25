package fr.univavignon.pokedex.api;

/**
 * Fournit une implémentation concrète de l'interface IPokedexFactory.
 * Cette classe est responsable de la création des instances de IPokedex, en utilisant
 * les services fournis par IPokemonMetadataProvider pour les métadonnées des Pokémon
 * et IPokemonFactory pour la création des instances de Pokémon.
 * @author Inès El Mahi
 */
public class IPokedexFactoryImpl implements IPokedexFactory {

    /**
     * Crée et retourne une nouvelle instance de IPokedex.
     *
     * @param metadataProvider Le fournisseur de métadonnées pour les Pokémon,
     *                         nécessaire pour accéder aux informations de base des Pokémon.
     * @param pokemonFactory   La fabrique utilisée pour créer de nouvelles instances de Pokémon.
     * @return Une instance de IPokedex, configurée avec les fournisseurs de métadonnées et de création de Pokémon spécifiés.
     */
    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        return new IPokedexImpl(metadataProvider, pokemonFactory);
    }
}


