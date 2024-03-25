package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IPokemonMetadataProviderTest {

    private PokemonMetadataProvider metadataProvider;

    @BeforeEach
    public void setUp() {
        // Initialisation avec l'implémentation réelle
        metadataProvider = new PokemonMetadataProvider();
        // Ajout de métadonnées pour Aquali avec un index valide pour les tests
        metadataProvider.addOrUpdateMetadata(133, "Aquali", 186, 168, 260);
    }

    @Test
    public void getPokemonMetadata_validIndexAquali() throws PokedexException {
        // Test de récupération des métadonnées pour un index valide
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(133);

        assertNotNull(metadata, "Les métadonnées pour Aquali ne devraient pas être nulles");
        assertEquals(133, metadata.getIndex(), "L'index devrait être 133");
        assertEquals("Aquali", metadata.getName(), "Le nom devrait être Aquali");
        assertEquals(186, metadata.getAttack(), "L'attaque devrait être 186");
        assertEquals(168, metadata.getDefense(), "La défense devrait être 168");
        assertEquals(260, metadata.getStamina(), "L'endurance devrait être 260");
    }

    @Test
    public void getPokemon_invalidIndexMore200() {
        // Test de récupération des métadonnées pour un index invalide (>150)
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(200),
                "Accès à un index invalide (200) devrait lancer une PokedexException");
    }

    @Test
    public void getPokemon_invalidIndexInf0() {
        // Test de récupération des métadonnées pour un index invalide (<0)
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(-29),
                "Accès à un index invalide (-29) devrait lancer une PokedexException");
    }

    @Test
    public void testAddMetadata_validIndex() throws PokedexException {
        // Test d'ajout de métadonnées pour un nouvel index valide
        metadataProvider.addOrUpdateMetadata(25, "Pikachu", 55, 40, 35);
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(25);

        assertNotNull(metadata, "Les métadonnées pour Pikachu ne devraient pas être nulles");
        assertEquals("Pikachu", metadata.getName(), "Le nom devrait être Pikachu");
    }

    @Test
    public void testAddMetadata_invalidIndex() {
        // Test d'ajout de métadonnées pour un index invalide
        assertThrows(IllegalArgumentException.class, () -> metadataProvider.addOrUpdateMetadata(151, "Test", 10, 10, 10),
                "L'ajout de métadonnées avec un index invalide (151) devrait lancer une IllegalArgumentException");
    }
}
