package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private List<PokemonMetadata> metadataList;

    public PokemonMetadataProvider() {
        this.metadataList = new ArrayList<>();

        addPokemonMetadata(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        addPokemonMetadata(new PokemonMetadata(133, "Aquali", 186, 168, 260));
    }

    private void addPokemonMetadata(PokemonMetadata metadata) {

        while (metadataList.size() <= metadata.getIndex()) {
            metadataList.add(null);
        }
        metadataList.set(metadata.getIndex(), metadata);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (index < 0 || index >= 151) {
            throw new PokedexException("Index invalide : " + index);
        }
        if (index >= metadataList.size() || metadataList.get(index) == null) {
            throw new PokedexException("Métadonnées non trouvées pour l'index : " + index);
        }
        return metadataList.get(index);
    }
}
