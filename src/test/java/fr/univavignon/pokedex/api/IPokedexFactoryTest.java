package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
public class IPokedexFactoryTest {

    @Mock
    private IPokemonMetadataProvider metadataProvider;

    @Mock
    private IPokemonFactory pokemonFactory;

    @Mock
    private IPokedexFactory pokedexFactory;

    private IPokedex pokedex;

    @BeforeEach
    void setUp() {
    	
        // Préparation des mocks
        pokedex = mock(IPokedex.class);
       
        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);
    }

    @Test
    void testCreatePokedex() {
        
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        
        assertNotNull(createdPokedex, "Le pokedex créé ne doit pas être null");
        assertSame(pokedex, createdPokedex, "Le pokedex créé doit être le même que le mock retourné par createPokedex");

       
        verify(pokedexFactory).createPokedex(metadataProvider, pokemonFactory);
    }
}
