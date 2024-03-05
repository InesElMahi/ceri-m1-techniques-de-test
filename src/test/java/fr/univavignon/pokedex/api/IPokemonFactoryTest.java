package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IPokemonFactoryTest {

    @Mock
    private IPokemonFactory pokemonFactory;

    @BeforeEach
    void setUp() {
    	
       
        when(pokemonFactory.createPokemon(eq(0), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(
            new Pokemon(0, "Bulbasaur", 126, 126, 90, 600, 100, 5000, 50, 0.56)
        );

        
        when(pokemonFactory.createPokemon(eq(133), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(
            new Pokemon(133, "Eevee", 186, 168, 260, 2729, 202, 5000, 4, 1.0)
        );
    }

    @Test
    void testCreationPokemon() {
    	
        // Test de la création de Bulbasaur 
        Pokemon bulbasaur = pokemonFactory.createPokemon(0, 600, 100, 5000, 50);
        assertNotNull(bulbasaur, "Bulbizarre ne devrait pas être null");
        assertEquals(0, bulbasaur.getIndex(),  "L'index de Bulbizarre devrait correspondre");
        assertEquals("Bulbasaur", bulbasaur.getName(), "Le nom de Bulbizarre devrait correspondre");
        assertEquals(600, bulbasaur.getCp(), "Les CP de Bulbizarre devraient correspondre");
        assertEquals(100, bulbasaur.getHp(), "Les HP de Bulbizarre devraient correspondre");
        assertEquals(0.56, bulbasaur.getIv(), "L'IV de Bulbizarre devrait correspondre");

        // Test de la création d'Eevee 
        Pokemon eevee = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);
        assertNotNull(eevee, "Eevee ne devrait pas être null");
        assertEquals(133, eevee.getIndex(), "L'index d'Eevee devrait correspondre");
        assertEquals("Eevee", eevee.getName(), "Le nom d'Eevee devrait correspondre");
        assertEquals(2729, eevee.getCp(), "Les CP d'Eevee devraient correspondre");
        assertEquals(202, eevee.getHp(), "Les HP d'Eevee devraient correspondre");
        assertEquals(1.0, eevee.getIv(), "L'IV d'Eevee devrait être parfait");

       
    }

}
