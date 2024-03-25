package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;

@ExtendWith(MockitoExtension.class)
public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProviderMock;
    private PokemonMetadataProvider metadataProvider;

    @BeforeEach
    public void setUp() {

        metadataProviderMock = mock(IPokemonMetadataProvider.class);

        metadataProvider = new PokemonMetadataProvider();
    }

    @Test
    public void getPokemonMetadata_validIndexAquali() throws PokedexException {


        when(metadataProvider.getPokemonMetadata(133)).thenReturn(
            new PokemonMetadata(133, "Aquali", 186, 168, 260)
        );

        int index = 133;

        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);



        assertNotNull(metadata, "Les métadonnées pour Aquali ne devraient pas être nulles");
        assertEquals(133, metadata.getIndex(), "L'index devrait être 133");
        assertEquals("Aquali", metadata.getName(), "Le nom devrait être Aquali");
        assertEquals(186, metadata.getAttack(), "L'attaque devrait être 186");
        assertEquals(168, metadata.getDefense(), "La défense devrait être 168");
        assertEquals(260, metadata.getStamina(), "L'endurance devrait être 260");
    }

    @Test
    public void getPokemon_invalidIndexMore200() throws PokedexException {
        when(metadataProvider.getPokemonMetadata(200)).thenThrow(new PokedexException("Invalid index"));
        int invalidIndex = 200;
        assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(invalidIndex);
        }, "si l'indice invalide est lancer par PodexException");
    }
    // inf à 0
    @Test
    public void getPokemon_invalidIndexInf0() throws PokedexException {
        when(metadataProvider.getPokemonMetadata(-29)).thenThrow(new PokedexException("Invalid index"));
        int invalidIndex = -29;
        assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(invalidIndex);
        }, "si l'indice invalide est lancer par PodexException");
    }
    @Test
    public void testAddMetadata_validIndex() {
        assertDoesNotThrow(() -> metadataProvider.addOrUpdateMetadata(25, "Pikachu", 55, 40, 35));
        try {
            PokemonMetadata metadata = metadataProvider.getPokemonMetadata(25);
            assertNotNull(metadata, "Les métadonnées pour Pikachu ne devraient pas être nulles");
            assertEquals("Pikachu", metadata.getName(), "Le nom devrait être Pikachu");
        } catch (PokedexException e) {
            fail("PokedexException should not be thrown for a valid index.");
        }
    }

    @Test
    public void testAddMetadata_invalidIndex() {
        assertThrows(IllegalArgumentException.class, () -> metadataProvider.addOrUpdateMetadata(151, "Test", 10, 10, 10),
                "L'ajout de métadonnées avec un index invalide devrait lancer une exception.");
    }

}
