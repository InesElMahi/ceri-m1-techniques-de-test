package fr.univavignon.pokedex.api;

/**
 * Trainer POJO.
 *
 * This class represents a Pokemon trainer.
 * It contains information about the trainer's name, team, and Pokedex.
 *
 * Author: fv
 */
public class PokemonTrainer {

    /** Trainer name. */
    private final String trainerName;

    /** Trainer team. */
    private final Team trainerTeam;

    /** Trainer Pokedex. */
    private final IPokedex trainerPokedex;

    /**
     * Default constructor.
     *
     * @param name Trainer name.
     * @param team Trainer team.
     * @param pokedex Trainer Pokedex.
     */
    public PokemonTrainer(
            final String name,
            final Team team,
            final IPokedex pokedex
    ) {
        this.trainerName = name;
        this.trainerTeam = team;
        this.trainerPokedex = pokedex;
    }

    /**
     * Name getter.
     * @return the name of the trainer.
     */
    public String getName() {
        return trainerName;
    }

    /**
     * Team getter.
     * @return the team of the trainer.
     */
    public Team getTeam() {
        return trainerTeam;
    }

    /**
     * Pokedex getter.
     * @return the Pokedex of the trainer.
     */
    public IPokedex getPokedex() {
        return trainerPokedex;
    }

}
