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
    private final String name;

    /** Trainer team. */
    private final Team team;

    /** Trainer Pokedex. */
    private final IPokedex pokedex;

    /**
     * Default constructor.
     *
     * @param name Trainer name.
     * @param team Trainer team.
     * @param pokedex Trainer Pokedex.
     */
    public PokemonTrainer(final String name, final Team team, final IPokedex pokedex) {
        this.name = name;
        this.team = team;
        this.pokedex = pokedex;
    }

    /** Name getter. */
    public String getName() {
        return name;
    }

    /** Team getter. */
    public Team getTeam() {
        return team;
    }

    /** Pokedex getter. */
    public IPokedex getPokedex() {
        return pokedex;
    }

}
