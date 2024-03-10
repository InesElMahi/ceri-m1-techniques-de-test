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
    void testPokemonCreationWithMinMaxValues() {
        // Configuration des mocks pour les cas extrêmes
        when(pokemonFactory.createPokemon(eq(0), eq(Integer.MIN_VALUE), eq(Integer.MIN_VALUE), eq(Integer.MIN_VALUE), eq(Integer.MIN_VALUE)))
                .thenReturn(new Pokemon(0, "MinValues", 10, 10, 10, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 0.0));
        when(pokemonFactory.createPokemon(eq(0), eq(Integer.MAX_VALUE), eq(Integer.MAX_VALUE), eq(Integer.MAX_VALUE), eq(Integer.MAX_VALUE)))
                .thenReturn(new Pokemon(0, "MaxValues", 10, 10, 10, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1.0));

        // Création et vérification pour les valeurs minimales
        Pokemon minValuesPokemon = pokemonFactory.createPokemon(0, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        assertNotNull(minValuesPokemon, "Le Pokémon avec des valeurs minimales ne devrait pas être null");

        // Création et vérification pour les valeurs maximales
        Pokemon maxValuesPokemon = pokemonFactory.createPokemon(0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertNotNull(maxValuesPokemon, "Le Pokémon avec des valeurs maximales ne devrait pas être null");
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
    void testPokemonCreationWithHighCPandHP() {

        when(pokemonFactory.createPokemon(anyInt(), eq(Integer.MAX_VALUE), eq(Integer.MAX_VALUE), anyInt(), anyInt())).thenReturn(new Pokemon(2, "HighCPHP", 200, 200, 200, Integer.MAX_VALUE, Integer.MAX_VALUE, 1000, 10, 0.9));
        
        Pokemon highCPHP = pokemonFactory.createPokemon(2, Integer.MAX_VALUE, Integer.MAX_VALUE, 1000, 10);
        assertNotNull(highCPHP, "Le Pokémon avec des valeurs de CP et HP élevées ne devrait pas être null");
        assertTrue(highCPHP.getCp() > 1000 && highCPHP.getHp() > 1000, "Les valeurs de CP et HP devraient être extrêmement élevées");
    }
    @Test
    void testPokemonCreationWithIncompleteData() {

        doThrow(new IllegalArgumentException("Données incomplètes")).when(pokemonFactory).createPokemon(anyInt(), eq(0), eq(0), eq(0), eq(0));
        
        assertThrows(IllegalArgumentException.class, () -> pokemonFactory.createPokemon(3, 0, 0, 0, 0), "Une exception devrait être lancée pour des données incomplètes");
    }
    
    @Test
    void testPokemonCreationWithExtremeIVs() {

        when(pokemonFactory.createPokemon(eq(0), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(
            new Pokemon(0, "PerfectIV", 100, 100, 100, 1000, 100, 1000, 10, 1.0)
        );

        when(pokemonFactory.createPokemon(eq(1), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(
            new Pokemon(1, "LowIV", 10, 10, 10, 10, 10, 1000, 10, 0.0)
        );

        Pokemon perfectIVPokemon = pokemonFactory.createPokemon(0, 1000, 100, 1000, 10);
        assertEquals(1.0, perfectIVPokemon.getIv(), "Le Pokémon devrait avoir un IV parfait de 100%");

        Pokemon lowIVPokemon = pokemonFactory.createPokemon(1, 10, 10, 1000, 10);
        assertEquals(0.0, lowIVPokemon.getIv(), "Le Pokémon devrait avoir le plus bas IV possible de 0%");
    }
    @Test
    void testPokemonCreationWithExtremeValues() {
        when(pokemonFactory.createPokemon(anyInt(), eq(Integer.MAX_VALUE), eq(Integer.MAX_VALUE), eq(Integer.MAX_VALUE), eq(Integer.MAX_VALUE)))
                .thenReturn(new Pokemon(0, "ExtremeMon", 255, 255, 255, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1.0));

        Pokemon extremePokemon = pokemonFactory.createPokemon(0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertNotNull(extremePokemon, "Le Pokémon avec des valeurs extrêmes ne devrait pas être null");
        assertEquals(Integer.MAX_VALUE, extremePokemon.getCp(), "Les CP du Pokémon devraient être au maximum");
    }
    
    @Test
    void testPokemonCreationWithBoundaryIndices() {

        assertDoesNotThrow(() -> pokemonFactory.createPokemon(0, 500, 50, 1000, 10), "La création d'un Pokémon avec l'indice minimal devrait réussir");
        assertDoesNotThrow(() -> pokemonFactory.createPokemon(150, 500, 50, 1000, 10), "La création d'un Pokémon avec l'indice maximal devrait réussir");
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
    void testPokemonIVRange() {
        when(pokemonFactory.createPokemon(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
                .thenAnswer(invocation -> new Pokemon(invocation.getArgument(0), "IVRangeMon", 100, 100, 100, 1000, 100, 1000, 50, Math.random()));

        Pokemon pokemon = pokemonFactory.createPokemon(1, 500, 50, 1000, 10);
        assertTrue(pokemon.getIv() >= 0.0 && pokemon.getIv() <= 1.0, "Les IVs du Pokémon devraient être compris entre 0.0 et 1.0");
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
