package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections4.map.UnmodifiableMap;

/**
 * Factory for creating Pokemon instances with potentially randomized stats.
 */
public class RocketPokemonFactory implements IPokemonFactory {

    /**
     * Default value for Pokemon stats.
     */
    private static final int DEFAULT_STAT_VALUE = 1000;

    /**
     * Map associating Pokemon index to their names.
     */
    private static final Map<Integer, String> INDEX_TO_NAME;

    static {
        Map<Integer, String> aMap = new HashMap<>();
        aMap.put(-1, "Ash's Pikachu");
        aMap.put(0, "MISSINGNO");
        aMap.put(1, "Bulbasaur");
        //TODO : Gotta map them all !
        INDEX_TO_NAME = UnmodifiableMap.unmodifiableMap(aMap);
    }

    /**
     * Generates a random stat for a Pokémon.
     * @return the generated random stat.
     */
    private static int generateRandomStat() {
        int total = 0;
        for (int i = 0; i < DEFAULT_STAT_VALUE; i++) {
            Random rn = new Random();
            int r = rn.nextInt(2);
            total = total + r;
        }
        return total / DEFAULT_STAT_VALUE;
    }

    /**
     * Creates a Pokemon instance.
     * @param index The index of the Pokemon.
     * @param cp The combat power of the Pokemon.
     * @param hp The hit points of the Pokemon.
     * @param dust The dust cost of the Pokemon.
     * @param candy The candy cost of the Pokemon.
     * @return The created Pokemon.
     */
    @Override
    public Pokemon createPokemon(final int index, final int cp,
                                       final int hp,
                                       final int dust, final int candy) {
        String name;
        if (!INDEX_TO_NAME.containsKey(index)) {
            name = INDEX_TO_NAME.get(0);
        } else {
            name = INDEX_TO_NAME.get(index);
        }
        int attack;
        int defense;
        int stamina;
        double iv;
        if (index < 0) {
            attack = DEFAULT_STAT_VALUE;
            defense = DEFAULT_STAT_VALUE;
            stamina = DEFAULT_STAT_VALUE;
            iv = 0;
        } else {
            attack = generateRandomStat();
            defense = generateRandomStat();
            stamina = generateRandomStat();
            iv = 1;
        }
        return new Pokemon(index, name, attack, defense, stamina,
                cp, hp, dust, candy, iv);
    }
}
