package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IPokemonMetadataProviderTest {

    @Mock
    private IPokemonMetadataProvider metadataProvider;

    @Test
    public void getPokemonMetadata_validIndexAquali() throws PokedexException {
        // Test pour Aquali (ID 133)
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
        // Test pour un index invalide supérieur à 200
        when(metadataProvider.getPokemonMetadata(200)).thenThrow(new PokedexException("Invalid index"));
        int invalidIndex = 200;
        assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(invalidIndex);
        }, "si l'indice invalide est lancé par PokedexException");
    }

    @Test
    public void getPokemon_invalidIndexInf0() throws PokedexException {
        // Test pour un index invalide inférieur à 0
        when(metadataProvider.getPokemonMetadata(-29)).thenThrow(new PokedexException("Invalid index"));
        int invalidIndex = -29;
        assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(invalidIndex);
        }, "si l'indice invalide est lancé par PokedexException");
    }

    // Nouveau test pour un Pokémon connu avec un index valide
    @Test
    public void getPokemonMetadata_validIndexBulbizarre() throws PokedexException {
        // Test pour Bulbizarre (ID 1)
        when(metadataProvider.getPokemonMetadata(1)).thenReturn(
                new PokemonMetadata(1, "Bulbizarre", 45, 49, 49)
        );

        int index = 1;

        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);

        assertNotNull(metadata, "Les métadonnées pour Bulbizarre ne devraient pas être nulles");
        assertEquals(1, metadata.getIndex(), "L'index devrait être 1");
        assertEquals("Bulbizarre", metadata.getName(), "Le nom devrait être Bulbizarre");
        assertEquals(45, metadata.getAttack(), "L'attaque devrait être 45");
        assertEquals(49, metadata.getDefense(), "La défense devrait être 49");
        assertEquals(49, metadata.getStamina(), "L'endurance devrait être 49");
    }

}
