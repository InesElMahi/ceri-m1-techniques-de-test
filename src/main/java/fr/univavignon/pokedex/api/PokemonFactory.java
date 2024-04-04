package fr.univavignon.pokedex.api;

import java.util.Random;

/**
 * Implementation of the IPokemonFactory interface.
 *
 * This class represents a factory for creating Pokemon instances.
 * It provides a method to create a Pokemon with computed IVs.
 *
 * Author: Inès El Mahi
 */
public class PokemonFactory implements IPokemonFactory {

    private final IPokemonMetadataProvider metadataProvider;

    /**
     * Constructs a PokemonFactory with the given metadata provider.
     *
     * @param metadataProvider The metadata provider to use.
     */
    public PokemonFactory(final IPokemonMetadataProvider metadataProvider) {
        this.metadataProvider = metadataProvider;
    }

    /**
     * Creates a Pokemon instance with computed IVs based on the provided attributes.
     *
     * @param index The index of the Pokemon.
     * @param cp The combat power of the Pokemon.
     * @param hp The hit points of the Pokemon.
     * @param dust The required dust for upgrading the Pokemon.
     * @param candy The required candy for upgrading the Pokemon.
     * @return The created Pokemon instance.
     */
    @Override
    public Pokemon createPokemon(final int index, final int cp, final int hp, final int dust, final int candy) {
        try {
            PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);
            Random rand = new Random();

            int ivAttaque = rand.nextInt(16);
            int ivDefense = rand.nextInt(16);
            int ivEndurance = rand.nextInt(16);

            double iv = calculerIV(ivAttaque, ivDefense, ivEndurance);

            return new Pokemon(index, metadata.getName(), metadata.getAttack() + ivAttaque,
                    metadata.getDefense() + ivDefense, metadata.getStamina() + ivEndurance,
                    cp, hp, dust, candy, iv);

        } catch (PokedexException e) {
            System.err.println("Error while retrieving Pokemon metadata: " + e.getMessage());
            return null;
        }
    }

    /**
     * Calculates the IV (Individual Values) of a Pokemon based on its attack, defense, and endurance IVs.
     *
     * @param ivAttaque The attack IV.
     * @param ivDefense The defense IV.
     * @param ivEndurance The endurance IV.
     * @return The calculated IV value.
     */
    private double calculerIV(final int ivAttaque, final int ivDefense, final int ivEndurance) {
        return ((double) (ivAttaque + ivDefense + ivEndurance) / 45) * 100;
    }
}
