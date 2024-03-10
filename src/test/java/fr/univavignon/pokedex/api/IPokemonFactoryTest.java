package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IPokemonFactoryTest {

    @Mock
    private IPokemonFactory pokemonFactory;

    @Test
    void testCreationPokemon() {
       
        when(pokemonFactory.createPokemon(eq(0), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(
            new Pokemon(0, "Bulbasaur", 126, 126, 90, 600, 100, 5000, 50, 0.56)
        );
        when(pokemonFactory.createPokemon(eq(133), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(
            new Pokemon(133, "Eevee", 186, 168, 260, 2729, 202, 5000, 4, 1.0)
        );

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

    @Test
    void testPokemonFactoryCreatesExpectedPokemon() {

        int testIndex = 25;
        int testCp = 800;
        int testHp = 120;
        int testDust = 3000;
        int testCandy = 40;
        double testIv = 0.85;

        when(pokemonFactory.createPokemon(eq(testIndex), eq(testCp), eq(testHp), eq(testDust), eq(testCandy))).thenReturn(
            new Pokemon(testIndex, "Pikachu", 55, 40, 90, testCp, testHp, testDust, testCandy, testIv)
        );

        Pokemon testPokemon = pokemonFactory.createPokemon(testIndex, testCp, testHp, testDust, testCandy);

        assertNotNull(testPokemon, "Le Pokémon créé ne devrait pas être null");
        assertEquals(testIndex, testPokemon.getIndex(), "L'index du Pokémon devrait correspondre");
        assertEquals("Pikachu", testPokemon.getName(), "Le nom du Pokémon devrait correspondre");
        assertEquals(testCp, testPokemon.getCp(), "Les CP du Pokémon devraient correspondre");
        assertEquals(testHp, testPokemon.getHp(), "Les HP du Pokémon devraient correspondre");
        assertEquals(testIv, testPokemon.getIv(), "L'IV du Pokémon devrait correspondre");
    }
    
    
    @Test
    void testConsistencyOfPokemonMetadata() {

        PokemonMetadata expectedMetadata = new PokemonMetadata(1, "ExpectedName", 50, 50, 50);
        when(pokemonFactory.createPokemon(eq(1), anyInt(), anyInt(), anyInt(), anyInt()))
                .thenReturn(new Pokemon(1, "ExpectedName", 50, 50, 50, 500, 50, 1000, 10, 0.5));

        Pokemon createdPokemon = pokemonFactory.createPokemon(1, 500, 50, 1000, 10);
        assertEquals(expectedMetadata.getIndex(), createdPokemon.getIndex(), "L'index des métadonnées devrait correspondre");
        assertEquals(expectedMetadata.getName(), createdPokemon.getName(), "Le nom devrait correspondre");
        assertEquals(expectedMetadata.getAttack(), createdPokemon.getAttack(), "L'attaque devrait correspondre");
        assertEquals(expectedMetadata.getDefense(), createdPokemon.getDefense(), "La défense devrait correspondre");
        assertEquals(expectedMetadata.getStamina(), createdPokemon.getStamina(), "L'endurance devrait correspondre");
    }
    
    @Test
    void testPokemonCreationWithSpecificInvalidIndices() {
        // Configuration du mock pour lancer une exception pour des indices invalides
        doThrow(new IllegalArgumentException("Index invalide")).when(pokemonFactory).createPokemon(eq(-1), anyInt(), anyInt(), anyInt(), anyInt());
        doThrow(new IllegalArgumentException("Index invalide")).when(pokemonFactory).createPokemon(eq(152), anyInt(), anyInt(), anyInt(), anyInt());

        // Test pour un indice négatif
        Exception exceptionForNegativeIndex = assertThrows(IllegalArgumentException.class, () -> {
            pokemonFactory.createPokemon(-1, 500, 50, 3000, 10);
        }, "Une exception devrait être lancée pour un index négatif");

        // Test pour un indice supérieur à la plage valide
        Exception exceptionForHighIndex = assertThrows(IllegalArgumentException.class, () -> {
            pokemonFactory.createPokemon(152, 500, 50, 3000, 10);
        }, "Une exception devrait être lancée pour un index trop élevé");
    }

    @Test
    void testPokemonCreationWithInvalidValues() {

        when(pokemonFactory.createPokemon(eq(1), anyInt(), anyInt(), anyInt(), anyInt()))
                .thenAnswer(invocation -> {
                    int cp = invocation.getArgument(1);
                    if (cp < 0) {
                        throw new IllegalArgumentException("Valeurs CP, HP, dust ou candy invalides");
                    }
                    return new Pokemon(1, "Pikachu", 55, 40, 90, cp, 50, 1000, 10, 0.5);
                });


        assertThrows(IllegalArgumentException.class, () -> pokemonFactory.createPokemon(1, -1, -1, -1, -1),
            "Une exception devrait être lancée pour des valeurs CP, HP, dust ou candy invalides");
    }

   
    @Test
    void testPokemonUniqueness() {
        when(pokemonFactory.createPokemon(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
            .thenAnswer(invocation -> new Pokemon(invocation.getArgument(0), "TestMon", 100, 100, 100, 1000, 100, 1000, 50, 0.5));

        Pokemon pokemon1 = pokemonFactory.createPokemon(1, 500, 50, 1000, 10);
        Pokemon pokemon2 = pokemonFactory.createPokemon(1, 500, 50, 1000, 10);

        assertNotSame(pokemon1, pokemon2, "Chaque appel à createPokemon devrait créer une nouvelle instance");
    }
    
    @Test
    void testPokemonCreationWithRandomData() {
        when(pokemonFactory.createPokemon(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
                .thenAnswer(invocation -> {
                    int index = invocation.getArgument(0);
                    int cp = invocation.getArgument(1);
                    int hp = invocation.getArgument(2);
                    return new Pokemon(index, "RandomDataMon", (int)(Math.random() * 200), (int)(Math.random() * 200), (int)(Math.random() * 200), cp, hp, 1000, 50, Math.random());
                });

        assertDoesNotThrow(() -> pokemonFactory.createPokemon((int)(Math.random() * 151), (int)(Math.random() * 3000), (int)(Math.random() * 500), 1000, 50), "La création d'un Pokémon avec des données aléatoires devrait réussir sans lancer d'exception");
    }

    @Test
    void testPokemonAttributesPositive() {

        when(pokemonFactory.createPokemon(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
                .thenReturn(new Pokemon(0, "PositiveMon", 100, 100, 100, 1000, 100, 1000, 10, 0.5));

        Pokemon pokemon = pokemonFactory.createPokemon(1, 500, 50, 1000, 10);
        assertTrue(pokemon.getAttack() > 0, "L'attaque du Pokémon devrait être positive");
        assertTrue(pokemon.getDefense() > 0, "La défense du Pokémon devrait être positive");
        assertTrue(pokemon.getStamina() > 0, "L'endurance du Pokémon devrait être positive");
    }

}
