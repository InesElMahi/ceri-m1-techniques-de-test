package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Implémentation de IPokemonMetadataProvider.
 * Cette classe fournit les métadonnées des Pokémon en fonction de leur index.
 * Elle utilise une HashMap pour stocker les métadonnées de manière efficace.
 * Les métadonnées spécifiques à certains Pokémon sont pré-initialisées,
 * tandis que de nouvelles métadonnées peuvent être ajoutées dynamiquement pour de nouveaux Pokémon.
 * @author Inès El Mahi
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private final Map<Integer, PokemonMetadata> metadataMap;

    /**
     * Construit un nouveau PokemonMetadataProvider avec une carte de métadonnées vide.
     */
    public PokemonMetadataProvider() {
        this.metadataMap = new HashMap<>();
        initialiserMetadonnees();
    }

    /**
     * Initialise les métadonnées pour les Pokémon spécifiques et d'autres Pokémon avec des valeurs par défaut.
     */
    private void initialiserMetadonnees() {
        // Initialise les métadonnées pour certains Pokémon spécifiques
        metadataMap.put(1, new PokemonMetadata(1, "Bulbizarre", 45, 49, 49));
        metadataMap.put(2, new PokemonMetadata(2, "Herbizarre", 60, 62, 63));
        metadataMap.put(3, new PokemonMetadata(3, "Florizarre", 80, 82, 83));
        metadataMap.put(133, new PokemonMetadata(133, "Aquali", 186, 168, 260));

        for (int i = 4; i <= 150; i++) {
            metadataMap.put(i, new PokemonMetadata(i, "Non Capturé", 0, 0, 0));
        }
    }

    /**
     * Récupère et retourne les métadonnées du Pokémon désigné par l'index donné.
     *
     * @param index Index du Pokémon pour lequel récupérer les métadonnées.
     * @return Métadonnées du Pokémon.
     * @throws PokedexException Si l'index donné n'est pas valide.
     */
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        PokemonMetadata metadata = metadataMap.get(index);
        if (metadata == null) {
            throw new PokedexException("Index invalide : " + index);
        }
        return metadata;
    }
}
