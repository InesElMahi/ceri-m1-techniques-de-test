package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides metadata for Pokémon.
 * This class allows access to Pokémon metadata by their index.
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private final Map<Integer, PokemonMetadata> metadataMap;

    /**
     * Constructor for PokemonMetadataProvider.
     * Initializes a new container for Pokémon metadata.
     */
    public PokemonMetadataProvider() {
        this.metadataMap = new HashMap<>();
    }

    /**
     * Returns the metadata of a Pokémon specified by its index.
     *
     * @param index The index of the Pokémon in the Pokédex.
     * @return The metadata of the Pokémon corresponding to the given index.
     * @throws PokedexException If the index is invalid or if metadata is not found.
     */
    @Override
    public PokemonMetadata getPokemonMetadata(final int index) throws PokedexException {
        if (index <= 0 || index > 150) {
            throw new PokedexException("Invalid index: " + index);
        }

        PokemonMetadata metadata = metadataMap.get(index);
        if (metadata == null) {
            throw new PokedexException("Metadata not found for index: " + index);
        }
        return metadata;
    }

    /**
     * Adds or updates metadata for a Pokémon.
     *
     * @param index   The index of the Pokémon.
     * @param name    The name of the Pokémon.
     * @param attack  The attack value of the Pokémon.
     * @param defense The defense value of the Pokémon.
     * @param stamina The stamina value of the Pokémon.
     */
    public void addOrUpdateMetadata(final int index, final String name, final int attack,
                                    final int defense, final int stamina) {
        if (index <= 0 || index > 150) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        metadataMap.put(index, new PokemonMetadata(index, name, attack, defense, stamina));
    }
}
