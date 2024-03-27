package fr.univavignon.pokedex.api;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IPokemonMetadataProviderTest {

    private PokemonMetadataProvider metadataProvider;
    @BeforeEach
    public void setUp() {
        metadataProvider = new PokemonMetadataProvider();
    }

    @Test
    public void getPokemonMetadata_validIndexAquali() throws PokedexException {
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(133);

        assertNotNull(metadata, "Les métadonnées pour Aquali ne devraient pas être nulles");
        assertEquals(133, metadata.getIndex(), "L'index devrait être 133");
        assertEquals("Aquali", metadata.getName(), "Le nom devrait être Aquali");
        assertEquals(186, metadata.getAttack(), "L'attaque devrait être 186");
        assertEquals(168, metadata.getDefense(), "La défense devrait être 168");
        assertEquals(260, metadata.getStamina(), "L'endurance devrait être 260");
    }

    @Test
    public void getPokemonMetadata_notFound() {
        int nonExistentIndex = 999;
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(nonExistentIndex),
                "Une PokedexException doit être lancée si aucune métadonnée n'est trouvée pour l'index donné");
    }

    @Test
    public void getPokemon_invalidIndexMore200() {
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(200),
                "Accès à un index invalide (200) devrait lancer une PokedexException");
    }

    @Test
    public void getPokemon_invalidIndexInf0() {
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(-29),
                "Accès à un index invalide (-29) devrait lancer une PokedexException");
    }



    @Test
    public void getPokemonMetadata_MetadataNotFound() {
        int nonExistentIndex = 2;
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(nonExistentIndex),
                "Une PokedexException doit être lancée si aucune métadonnée n'est trouvée pour l'index donné.");
    }
    @Test
    public void getPokemonMetadata_IndexTooLow() {
        assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(-1);
        });
    }


    @Test
    public void getPokemonMetadata_IndexTooHigh() {
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(151),
                "Accès à un index 151 devrait lancer une PokedexException.");
    }




}
