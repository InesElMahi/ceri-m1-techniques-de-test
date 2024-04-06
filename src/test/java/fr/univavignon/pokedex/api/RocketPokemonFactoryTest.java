package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RocketPokemonFactoryTest{

    private RocketPokemonFactory pokemonFactory;
    private Pokemon bulbizarre;
    private Pokemon aquali;

    @BeforeEach
    void setUp() {
        pokemonFactory = new RocketPokemonFactory();

        bulbizarre = new Pokemon(1, "Bulbasaur", 126, 100, 90, 600, 100, 4000, 4, 1.0);
        aquali = new Pokemon(133, "Vaporeon", 186, 168, 260, 2729, 202, 5000, 4, 1.0);
    }

    @Test
    void testCreationPokemon() {
        Pokemon bulbasaur = pokemonFactory.createPokemon(1, 600, 100, 5000, 50);
        assertNotNull(bulbasaur, "Bulbasaur ne devrait pas être null");
        assertEquals(1, bulbasaur.getIndex(), "L'index de Bulbasaur devrait correspondre");
        assertEquals("Bulbasaur", bulbasaur.getName(), "Le nom de Bulbasaur devrait correspondre");
        assertEquals(600, bulbasaur.getCp(), "Les CP de Bulbasaur devraient correspondre");
        assertEquals(100, bulbasaur.getHp(), "Les HP de Bulbasaur devraient correspondre");
        assertEquals(1.0, bulbasaur.getIv(), "L'IV de Bulbasaur devrait correspondre");
        assertEquals(4000, bulbasaur.getDust(), "La poussière du Pokémon devrait être de 4000");

        Pokemon vaporeon = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);
        assertNotNull(vaporeon, "Vaporeon ne devrait pas être null");
        assertEquals(133, vaporeon.getIndex(), "L'index de Vaporeon devrait correspondre");
        assertEquals("Vaporeon", vaporeon.getName(), "Le nom de Vaporeon devrait correspondre");
        assertEquals(2729, vaporeon.getCp(), "Les CP de Vaporeon devraient correspondre");
        assertEquals(202, vaporeon.getHp(), "Les HP de Vaporeon devraient correspondre");
        assertEquals(1.0, vaporeon.getIv(), "L'IV de Vaporeon devrait être parfait");
        assertEquals(5000, vaporeon.getDust(), "La poussière du Pokémon devrait être de 5000");
    }

    @Test
    void testConsistencyOfPokemonMetadata() {
        PokemonMetadata expectedMetadata = new PokemonMetadata(1, "Bulbasaur", 50, 50, 50);
        Pokemon createdPokemon = pokemonFactory.createPokemon(1, 500, 50, 1000, 10);
        assertEquals(expectedMetadata.getIndex(), createdPokemon.getIndex(), "L'index des métadonnées devrait correspondre");
        assertEquals(expectedMetadata.getName(), createdPokemon.getName(), "Le nom devrait correspondre");
        assertEquals(expectedMetadata.getAttack(), createdPokemon.getAttack(), "L'attaque devrait correspondre");
        assertEquals(expectedMetadata.getDefense(), createdPokemon.getDefense(), "La défense devrait correspondre");
        assertEquals(expectedMetadata.getStamina(), createdPokemon.getStamina(), "L'endurance devrait correspondre");
    }

    @Test
    void testPokemonCreationWithInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> pokemonFactory.createPokemon(1, -1, -1, -1, -1),
                "Devrait lancer IllegalArgumentException pour des valeurs invalides");
    }

    @Test
    void testCreationDePokemonRenvoieNull() {
        assertNull(pokemonFactory.createPokemon(-2, 10000, 10000, 10000, 10000), "La création d'un Pokemon avec des paramètres invalides devrait renvoyer null");
    }

    @Test
    void testPokemonCandyGetter() {
        assertEquals(4, bulbizarre.getCandy(), "Le nombre de candies de Bulbasaur devrait être correct");
        assertEquals(4, aquali.getCandy(), "Le nombre de candies de Vaporeon devrait être correct");
    }
}