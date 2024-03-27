package fr.univavignon.pokedex.api;

import java.util.Random;

public class PokemonFactory implements IPokemonFactory {

    private final IPokemonMetadataProvider metadataProvider;

    public PokemonFactory(IPokemonMetadataProvider metadataProvider) {
        this.metadataProvider = metadataProvider;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
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
            System.err.println("Erreur lors de la récupération des métadonnées du Pokémon : " + e.getMessage());
            return null;
        }
    }

    private double calculerIV(int ivAttaque, int ivDefense, int ivEndurance) {
        return ((double) (ivAttaque + ivDefense + ivEndurance) / 45) * 100;
    }
}

