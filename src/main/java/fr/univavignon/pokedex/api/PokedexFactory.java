package fr.univavignon.pokedex.api;


public class PokedexFactory implements IPokedexFactory {

    @Override
    public IPokedex createPokedex(final IPokemonMetadataProvider metadataProvider, final IPokemonFactory pokemonFactory) {
        return new Pokedex(metadataProvider, pokemonFactory);
    }
}
