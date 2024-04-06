package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RocketPokemonFactoryTest {

    private RocketPokemonFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new RocketPokemonFactory();
    }

    @Test
    public void testCreatePokedex() {
        IPokedexFactory pokedexFactory = new PokedexFactory();
        IPokedex createdPokedex = pokedexFactory.createPokedex(null, null);

        assertNotNull(createdPokedex, "Le pokedex créé ne doit pas être null");
    }

    @Test
    public void testGetPokemonInvalidIndexException() throws PokedexException {
        IPokedexFactory pokedexFactory = new PokedexFactory();
        IPokedex createdPokedex = pokedexFactory.createPokedex(null, null);

        assertThrows(PokedexException.class, () -> createdPokedex.getPokemon(-1),
                "Accès à un index invalide doit lever une PokedexException.");
    }

    @Test
    public void testPokedexSizeAfterAddingPokemons() {
        IPokedexFactory pokedexFactory = new PokedexFactory();
        IPokedex createdPokedex = pokedexFactory.createPokedex(null, null);

        Pokemon pikachu = factory.createPokemon(0, 55, 40, 90, 260);
        Pokemon bulbasaur = factory.createPokemon(1, 45, 49, 45, 230);

        createdPokedex.addPokemon(pikachu);
        createdPokedex.addPokemon(bulbasaur);

        assertEquals(2, createdPokedex.size(), "Taille du Pokedex doit être 2 après ajout de deux Pokémons.");
    }
}
