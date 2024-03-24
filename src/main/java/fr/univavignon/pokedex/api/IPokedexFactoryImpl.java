package fr.univavignon.pokedex.api;

/**
 * Fabrique de Pokédex
 * @author Inès El Mahi
 */
public class IPokedexFactoryImpl implements IPokedexFactory {
    /**
     * Crée une nouvelle instance de Pokédex en utilisant les fournisseurs de métadonnées et de Pokémon
     *
     * @param metadataProvider Fournisseur de métadonnées que le Pokédex créé utilisera.
     * @param pokemonFactory   Fabrique de Pokémon que le Pokédex créé utilisera.
     * @return Instance de Pokédex créée.
     * @throws NullPointerException si metadataProvider ou pokemonFactory est null.
     */
    @Override
    public IPokedex createPokedex(
            final IPokemonMetadataProvider metadataProvider,
            final IPokemonFactory pokemonFactory) throws NullPointerException {

        if (metadataProvider == null || pokemonFactory == null) {
            throw new NullPointerException("metadataProvider et pokemonFactory ne doivent pas être null");
        }
        return new IPokedexImpl(metadataProvider, pokemonFactory);
    }

}
