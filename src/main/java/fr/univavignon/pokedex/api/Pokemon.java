package fr.univavignon.pokedex.api;

/**
 * Pokemon POJO.
 *
 * @author fv
 */
public final class Pokemon extends PokemonMetadata {

    /** Combat Point of the pokemon. **/
    private final int cp;

    /** HP of the pokemon. **/
    private final int hp;

    /** Required dust for upgrading this pokemon. **/
    private final int dust;

    /** Required candy for upgrading this pokemon. **/
    private final int candy;

    /** IV perfection percentage. **/
    private final double iv;

    /**
     * Default constructor.
     *
     * @param index      Pokemon index.
     * @param name       Pokemon name.
     * @param attack     Attack level.
     * @param defense    Defense level.
     * @param stamina    Stamina level.
     * @param cpValue    Pokemon cp.
     * @param hpValue    Pokemon hp.
     * @param dustValue  Required dust for upgrading this pokemon.
     * @param candyValue Required candy for upgrading this pokemon.
     * @param ivValue    IV perfection percentage.
     */
    public Pokemon(
            final int index,
            final String name,
            final int attack,
            final int defense,
            final int stamina,
            final int cpValue,
            final int hpValue,
            final int dustValue,
            final int candyValue,
            final double ivValue) {
        super(index, name, attack, defense, stamina);
        this.cp = cpValue;
        this.hp = hpValue;
        this.dust = dustValue;
        this.candy = candyValue;
        this.iv = ivValue;
    }

    /**
     * Combat Point getter.
     *
     * @return The Combat Point of the pokemon.
     */
    public int getCp() {
        return cp;
    }

    /**
     * HP getter.
     *
     * @return The HP of the pokemon.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Dust getter.
     *
     * @return The required dust for upgrading this pokemon.
     */
    public int getDust() {
        return dust;
    }

    /**
     * Candy getter.
     *
     * @return The required candy for upgrading this pokemon.
     */
    public int getCandy() {
        return candy;
    }

    /**
     * IV getter.
     *
     * @return The IV perfection percentage.
     */
    public double getIv() {
        return iv;
    }

}
