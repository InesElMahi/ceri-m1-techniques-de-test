package fr.univavignon.pokedex.api;

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
    public void getPokemonMetadata_invalidIndex() throws PokedexException {
        
        when(metadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Invalid index"));

        
        int invalidIndex = -1;

        
        assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(invalidIndex);
        }, "Une PokedexException devrait être lancée pour un index invalide");
    }
    
    @Test
    public void getPokemonMetadata_firstIndex() throws PokedexException {
        when(metadataProvider.getPokemonMetadata(0)).thenReturn(
            new PokemonMetadata(0, "Bulbasaur", 118, 111, 128)
        );
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(0);
        assertNotNull(metadata, "Les métadonnées pour Bulbasaur ne devraient pas être nulles");
        assertEquals(0, metadata.getIndex());
    }

    @Test
    public void getPokemonMetadata_lastIndex() throws PokedexException {

        int lastIndex = 151;
        when(metadataProvider.getPokemonMetadata(lastIndex)).thenReturn(
            new PokemonMetadata(lastIndex, "Mew", 100, 100, 100)
        );
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(lastIndex);
        assertNotNull(metadata, "Les métadonnées pour Mew ne devraient pas être nulles");
        assertEquals(lastIndex, metadata.getIndex());
    }
    @Test
    public void getPokemonMetadata_consistencyCheck() throws PokedexException {
        when(metadataProvider.getPokemonMetadata(25)).thenReturn(
            new PokemonMetadata(25, "Pikachu", 112, 96, 111)
        );
        PokemonMetadata pikachu = metadataProvider.getPokemonMetadata(25);
        assertEquals("Pikachu", pikachu.getName());
        assertNotEquals("Aquali", pikachu.getName());
    }
    @Test
    public void getPokemonMetadata_edgeIndexLower() throws PokedexException {

        when(metadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbasaur", 118, 118, 90));
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(0);
        assertEquals("Bulbasaur", metadata.getName(), "Le premier Pokémon devrait être Bulbasaur");
    }

    @Test
    public void getPokemonMetadata_edgeIndexUpper() throws PokedexException {

        when(metadataProvider.getPokemonMetadata(151)).thenReturn(new PokemonMetadata(151, "Mew", 100, 100, 100));
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(151);
        assertEquals("Mew", metadata.getName(), "Le dernier Pokémon devrait être Mew");
    }
    
    @Test
    public void getPokemonMetadata_ensureDataIntegrity() throws PokedexException {

        when(metadataProvider.getPokemonMetadata(25)).thenReturn(new PokemonMetadata(25, "Pikachu", 112, 101, 90));
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(25);
        assertAll("Les données doivent être cohérentes",
            () -> assertEquals("Pikachu", metadata.getName()),
            () -> assertEquals(112, metadata.getAttack()),
            () -> assertEquals(101, metadata.getDefense()),
            () -> assertEquals(90, metadata.getStamina())
        );
    }

   
    @Test
    public void getPokemonMetadata_validIndexCharizard() throws PokedexException {

        when(metadataProvider.getPokemonMetadata(6)).thenReturn(new PokemonMetadata(6, "Charizard", 223, 176, 156));
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(6);
        assertNotNull(metadata, "Les métadonnées pour Charizard ne devraient pas être nulles");
        assertEquals(6, metadata.getIndex(), "L'index devrait être 6");
        assertEquals("Charizard", metadata.getName(), "Le nom devrait être Charizard");
        assertEquals(223, metadata.getAttack(), "L'attaque devrait être 223");
        assertEquals(176, metadata.getDefense(), "La défense devrait être 176");
        assertEquals(156, metadata.getStamina(), "L'endurance devrait être 156");
    }

   

    @Test
    public void getPokemonMetadata_validIndexMewtwo() throws PokedexException {

        when(metadataProvider.getPokemonMetadata(150)).thenReturn(new PokemonMetadata(150, "Mewtwo", 300, 250, 200));
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(150);
        assertNotNull(metadata, "Les métadonnées pour Mewtwo ne devraient pas être nulles");
        assertEquals(150, metadata.getIndex(), "L'index devrait être 150");
        assertEquals("Mewtwo", metadata.getName(), "Le nom devrait être Mewtwo");
        assertEquals(300, metadata.getAttack(), "L'attaque devrait être 300");
        assertEquals(250, metadata.getDefense(), "La défense devrait être 250");
        assertEquals(200, metadata.getStamina(), "L'endurance devrait être 200");
    }

    @Test
    public void getPokemonMetadata_validIndexDitto() throws PokedexException {

        when(metadataProvider.getPokemonMetadata(132)).thenReturn(new PokemonMetadata(132, "Ditto", 100, 100, 100));
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(132);
        assertNotNull(metadata, "Les métadonnées pour Ditto ne devraient pas être nulles");
        assertEquals(132, metadata.getIndex(), "L'index devrait être 132");
        assertEquals("Ditto", metadata.getName(), "Le nom devrait être Ditto");
        assertEquals(100, metadata.getAttack(), "L'attaque devrait être 100");
        assertEquals(100, metadata.getDefense(), "La défense devrait être 100");
        assertEquals(100, metadata.getStamina(), "L'endurance devrait être 100");
    }

    @Test
    public void getPokemonMetadata_validIndexEmptyName() throws PokedexException {

        when(metadataProvider.getPokemonMetadata(160)).thenReturn(new PokemonMetadata(160, "", 50, 50, 50));
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(160);
        assertNotNull(metadata, "Les métadonnées pour un Pokémon avec un nom vide ne devraient pas être nulles");
        assertEquals(160, metadata.getIndex(), "L'index devrait être 160");
        assertEquals("", metadata.getName(), "Le nom devrait être vide");
        assertEquals(50, metadata.getAttack(), "L'attaque devrait être 50");
        assertEquals(50, metadata.getDefense(), "La défense devrait être 50");
        assertEquals(50, metadata.getStamina(), "L'endurance devrait être 50");
    }

    @Test
    public void getPokemonMetadata_consistencyCheckDifferentInstances() throws PokedexException {

        PokemonMetadata pikachu1 = new PokemonMetadata(25, "Pikachu", 112, 96, 111);
        when(metadataProvider.getPokemonMetadata(25)).thenReturn(pikachu1);
        PokemonMetadata pikachu2 = metadataProvider.getPokemonMetadata(25);
        assertSame(pikachu1, pikachu2, "Les instances devraient être les mêmes");
    }
    @Test
    public void getPokemonMetadata_missingMetadata() throws PokedexException {
        int missingIndex = 999; // Index où aucune métadonnée n'est disponible
        assertNull(metadataProvider.getPokemonMetadata(missingIndex), "Aucune métadonnée ne devrait être disponible pour cet index");
    }


}
