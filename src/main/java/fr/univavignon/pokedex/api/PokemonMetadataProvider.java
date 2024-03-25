package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Implémentation de IPokemonMetadataProvider.
 * Fournit les métadonnées des Pokémon en fonction de leur index.
 * Utilise une HashMap pour stocker efficacement les métadonnées.
 * Les métadonnées sont initialisées à la demande plutôt qu'à la création.
 * @author Inès El Mahi
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private final Map<Integer, PokemonMetadata> metadataMap;

    public PokemonMetadataProvider() {
        this.metadataMap = new HashMap<>();
        // Initialisation spécifique n'est plus nécessaire dans le constructeur
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (index <= 0 || index > 150) { // Assurer que l'index est valide
            throw new PokedexException("Index invalide : " + index);
        }

        PokemonMetadata metadata = metadataMap.get(index);
        if (metadata == null) {
            // Pour l'exemple, on retourne des valeurs par défaut ou on lance une exception
            // Dans une application réelle, vous pourriez chercher ces données depuis une base de données ou un fichier
            throw new PokedexException("Métadonnées non trouvées pour l'index : " + index);
        }
        return metadata;
    }

    /**
     * Méthode pour ajouter ou mettre à jour les métadonnées d'un Pokémon.
     * Cette méthode peut être utilisée pour peupler la map des métadonnées au fur et à mesure des besoins.
     */
    public void addOrUpdateMetadata(int index, String name, int attack, int defense, int stamina) {
        if (index <= 0 || index > 150) { // Vérifier que l'index est dans le bon intervalle
            throw new IllegalArgumentException("Index invalide : " + index);
        }
        metadataMap.put(index, new PokemonMetadata(index, name, attack, defense, stamina));
    }
}
