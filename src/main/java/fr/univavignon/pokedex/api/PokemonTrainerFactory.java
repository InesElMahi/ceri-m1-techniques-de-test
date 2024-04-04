package fr.univavignon.pokedex.api;

/**
 * Factory class for creating Pokemon trainers.
 *
 * This class provides methods for creating Pokemon trainers.
 *
 * Author: Inès El Mahi
 */
public class PokemonTrainerFactory implements IPokemonTrainerFactory {

    private final IPokemonMetadataProvider metadataProvider;

    private final IPokemonFactory pokemonFactory;

    /**
     * Constructs a PokemonTrainerFactory with the given metadata provider and Pokemon factory.
     *
     * @param metadataProvider The metadata provider to use.
     * @param pokemonFactory   The Pokemon factory to use.
     */
    public PokemonTrainerFactory(final IPokemonMetadataProvider metadataProvider, final IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    /**
     * Creates a Pokemon trainer with the specified name, team, and Pokedex factory.
     *
     * @param name           The name of the trainer.
     * @param team           The team of the trainer.
     * @param pokedexFactory The Pokedex factory to use for creating the trainer's Pokedex.
     * @return The created Pokemon trainer.
     */
    @Override
    public PokemonTrainer createTrainer(final String name, final Team team, final IPokedexFactory pokedexFactory) {
        return new PokemonTrainer(name, team, pokedexFactory.createPokedex(metadataProvider, pokemonFactory));
    }
}
