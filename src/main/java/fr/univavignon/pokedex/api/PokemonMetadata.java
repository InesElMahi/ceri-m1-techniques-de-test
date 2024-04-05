package fr.univavignon.pokedex.api;

/**
 * Pokemon metadata POJO.
 *
 * @author fv
 */
public class PokemonMetadata {

    /** Pokemon index. */
    private final int index;

    /** Pokemon name. */
    private final String name;

    /** Pokemon attack level. */
    private final int attack;

    /** Pokemon defense level. */
    private final int defense;

    /** Pokemon stamina level. */
    private final int stamina;

    /**
     * Default constructor.
     *
     * @param aIndex Pokemon index.
     * @param aName Pokemon name.
     * @param aAttack Attack level.
     * @param aDefense Defense level.
     * @param aStamina Stamina level.
     */
    public PokemonMetadata(
            final int aIndex,
            final String aName,
            final int aAttack,
            final int aDefense,
            final int aStamina
    ) {
        this.index = aIndex;
        this.name = aName;
        this.attack = aAttack;
        this.defense = aDefense;
        this.stamina = aStamina;
    }



    /**
     * Index getter.
     * @return The index of the Pokemon.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Name getter.
     * @return The name of the Pokemon.
     */
    public String getName() {
        return name;
    }

    /**
     * Attack level getter.
     * @return The attack level of the Pokemon.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Defense level getter.
     * @return The defense level of the Pokemon.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Stamina level getter.
     * @return The stamina level of the Pokemon.
     */
    public int getStamina() {
        return stamina;
    }

}
