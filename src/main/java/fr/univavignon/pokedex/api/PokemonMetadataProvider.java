package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Fournit des métadonnées pour les Pokémon.
 * Cette classe permet d'accéder aux métadonnées des Pokémon par leur index.
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private final Map<Integer, PokemonMetadata> metadataMap;


    /**
     * Constructeur de PokemonMetadataProvider.
     * Initialise un nouveau conteneur pour les métadonnées des Pokémon.
     */
    public PokemonMetadataProvider() {
        this.metadataMap = new HashMap<>();
    }

    /**
     * Retourne les métadonnées d'un Pokémon spécifié par son index.
     *
     * @param index L'index du Pokémon dans le Pokedex.
     * @return Les métadonnées du Pokémon correspondant à l'index donné.
     * @throws PokedexException Si l'index est invalide ou si les métadonnées ne sont pas trouvées.
     */
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
