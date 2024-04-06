package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the RocketPokemonFactory class.
 */
public class RocketPokemonFactoryTest {

    private RocketPokemonFactory factory;

    @BeforeEach
    public void setUp() throws Exception {
        factory = new RocketPokemonFactory();
    }

    @Test
    public void testCreatePokemonWithNegativeIndex() {

        int index = -1;
        Pokemon pokemon = factory.createPokemon(index, 100, 100, 1000, 10);

        assertEquals("Ash's Pikachu", pokemon.getName());
        assertEquals(1000, pokemon.getAttack());
        assertEquals(1000, pokemon.getDefense());
        assertEquals(1000, pokemon.getStamina());
        assertEquals(0, pokemon.getIv(), 0.0);
    }

    @Test
    public void testCreatePokemonWithUnknownIndex() {

        int index = 999;
        Pokemon pokemon = factory.createPokemon(index, 200, 150, 1500, 20);

        assertEquals("MISSINGNO", pokemon.getName());
        assertNotNull(pokemon.getAttack());
        assertNotNull(pokemon.getDefense());
        assertNotNull(pokemon.getStamina());
        assertEquals(1, pokemon.getIv(), 0.0);
    }

    @Test
    public void testCreatePokemonWithKnownIndex() {

        int index = 1;
        Pokemon pokemon = factory.createPokemon(index, 300, 200, 2000, 25);

        assertEquals("Bulbasaur", pokemon.getName());
        assertTrue(pokemon.getAttack() >= 0);
        assertTrue(pokemon.getDefense() >= 0);
        assertTrue(pokemon.getStamina() >= 0);
        assertEquals(1, pokemon.getIv(), 0.0);
    }
}
