package fr.univavignon.pokedex.api;
import org.junit.jupiter.api.BeforeEach;

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
    private Pokemon bulbizarre;
    private Pokemon aquali;

    @BeforeEach
    void setUp() {
        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.0);

        lenient().when(pokemonFactory.createPokemon(eq(0), eq(613), eq(64), eq(4000), eq(4))).thenReturn(bulbizarre);
        lenient().when(pokemonFactory.createPokemon(eq(133), eq(2729), eq(202), eq(5000), eq(4))).thenReturn(aquali);
        lenient().when(pokemonFactory.createPokemon(eq(-1), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(null);
    }

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
        assertEquals(bulbizarre.getDust(), bulbasaur.getDust(), "La poussière du pokemon est incorrect");
        assertEquals(bulbizarre.getCandy(),bulbasaur.getCandy(), "Le candy du pokemon est incorrect");


        // Test de la création d'Eevee 
        Pokemon eevee = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);
        assertNotNull(eevee, "Eevee ne devrait pas être null");
        assertEquals(133, eevee.getIndex(), "L'index d'Eevee devrait correspondre");
        assertEquals("Eevee", eevee.getName(), "Le nom d'Eevee devrait correspondre");
        assertEquals(2729, eevee.getCp(), "Les CP d'Eevee devraient correspondre");
        assertEquals(202, eevee.getHp(), "Les HP d'Eevee devraient correspondre");
        assertEquals(1.0, eevee.getIv(), "L'IV d'Eevee devrait être parfait");
        assertEquals(aquali.getDust(), eevee.getDust(), "La poussière du pokemon est incorrect");
        assertEquals(aquali.getCandy(), eevee.getCandy(), "Le candy du pokemon est incorrect");

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
    void testCreationDePokemonRenvoieNull() {
        assertNull(pokemonFactory.createPokemon(-2, 10000, 10000, 10000, 10000), "La création d'un Pokémon avec des paramètres invalides devrait renvoyer null");
    }

    @Test
    void testLeHpDuPokemonCorrectementDefini() {
        assertEquals(bulbizarre.getHp(), pokemonFactory.createPokemon(1, 600, 60, 3000, 4).getHp(), "Le HP doit correspondre à celui de  Bulbizarre!!");
        assertEquals(aquali.getHp(), pokemonFactory.createPokemon(134, 2500, 200, 5000, 5).getHp(), "Le HP doit correspondre à celui de  Aquali!!");
    }
    @Test
    void testLeCpDuPokemonCorrectementDefini() {
        assertEquals(bulbizarre.getCp(), pokemonFactory.createPokemon(1, 600, 60, 3000, 4).getCp(), "Le CP doit correspondre à celui de  Bulbizarre!!");
        assertEquals(aquali.getCp(), pokemonFactory.createPokemon(134, 2500, 200, 5000, 5).getCp(), "Le CP doit correspondre à celui de  Aquali!!");
    }
}
