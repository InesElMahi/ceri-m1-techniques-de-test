package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private final Map<Integer, PokemonMetadata> metadataMap;

    public PokemonMetadataProvider() {
        this.metadataMap = new HashMap<>();
        initialiserMetadonnees();
    }


    private void initialiserMetadonnees() {

        metadataMap.put(1, new PokemonMetadata(1, "Bulbizarre", 45, 49, 49));
        metadataMap.put(2, new PokemonMetadata(2, "Herbizarre", 60, 62, 63));
        metadataMap.put(3, new PokemonMetadata(3, "Florizarre", 80, 82, 83));
        metadataMap.put(133, new PokemonMetadata(133, "Aquali", 186, 168, 260));

        for (int i = 4; i <= 150; i++) {
            metadataMap.put(i, new PokemonMetadata(i, "Non Capturé", 0, 0, 0));
        }
    }


    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        PokemonMetadata metadata = metadataMap.get(index);
        if (metadata == null) {
            throw new PokedexException("Index invalide : " + index);
        }
        return metadata;
    }
}