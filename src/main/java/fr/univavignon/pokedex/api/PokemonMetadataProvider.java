package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;


public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private final Map<Integer, PokemonMetadata> metadataMap;

    public PokemonMetadataProvider() {
        this.metadataMap = new HashMap<>();
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (index <= 0 || index > 150) {
            throw new PokedexException("Index invalide : " + index);
        }

        PokemonMetadata metadata = metadataMap.get(index);
        if (metadata == null) {
            throw new PokedexException("Métadonnées non trouvées pour l'index : " + index);
        }
        return metadata;
    }

    public void addOrUpdateMetadata(int index, String name, int attack, int defense, int stamina) {
        if (index <= 0 || index > 150) {
            throw new IllegalArgumentException("Index invalide : " + index);
        }
        metadataMap.put(index, new PokemonMetadata(index, name, attack, defense, stamina));
    }
}
