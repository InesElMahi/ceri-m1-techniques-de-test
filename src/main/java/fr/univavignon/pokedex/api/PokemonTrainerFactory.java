package fr.univavignon.pokedex.api;

/**
 * Implémentation de l'interface IPokemonTrainerFactory.
 * Cette implémentation crée des instances de PokemonTrainer.
 *
 * @author VotreNom
 */
public class PokemonTrainerFactory implements IPokemonTrainerFactory {

    private final IPokemonMetadataProvider metadataProvider;
    private final IPokemonFactory pokemonFactory;

    public PokemonTrainerFactory(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    /**
     * Crée et retourne une instance de PokemonTrainer.
     *
     * @param name Nom du dresseur créé.
     * @param team Équipe du dresseur créé.
     * @param pokedexFactory Factory à utiliser pour créer l'instance de Pokedex associée.
     * @return Instance du dresseur créé.
     */
    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        return new PokemonTrainer(name, team, pokedexFactory.createPokedex(metadataProvider, pokemonFactory));
    }
}
