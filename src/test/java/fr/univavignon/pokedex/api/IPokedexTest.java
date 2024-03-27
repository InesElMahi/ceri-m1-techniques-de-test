package fr.univavignon.pokedex.api;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Comparator;
import java.util.Arrays;

public class IPokedexTest {

    private IPokedex pokedex;
    private Pokemon pikachu;
    private Pokemon bulbasaur;

    @BeforeEach
    public void setup() {
        pokedex = mock(IPokedex.class);
        pikachu = new Pokemon(0, "Pikachu", 55, 40, 90, 260, 35, 500, 50, 0.6);
        bulbasaur = new Pokemon(1, "Bulbasaur", 45, 49, 45, 230, 30, 500, 50, 0.5);

        try {
            when(pokedex.size()).thenReturn(2);
            when(pokedex.addPokemon(any(Pokemon.class))).thenAnswer(i -> {
                Pokemon p = i.getArgument(0);
                return p.getIndex();
            });
            when(pokedex.getPokemon(0)).thenReturn(pikachu);
            when(pokedex.getPokemon(1)).thenReturn(bulbasaur);
            when(pokedex.getPokemons()).thenReturn(Arrays.asList(pikachu, bulbasaur));
            when(pokedex.getPokemons(any(Comparator.class))).thenReturn(Arrays.asList(bulbasaur, pikachu));

            doThrow(new PokedexException("Invalid index")).when(pokedex).getPokemon(-1);
        } catch (PokedexException e) {

            e.printStackTrace();
        }
    }

    @Test
    public void testAddPokemon() {

        int indexPikachu = pokedex.addPokemon(pikachu);
        int indexBulbasaur = pokedex.addPokemon(bulbasaur);
        assertNotEquals(indexPikachu, indexBulbasaur, "chaque Pokemon doit avoir un unique index.");
    }

    @Test
    public void testGetSize() {
        // On test la taille du Pokedex
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testGetPokemonValidIndex() {

        try {
            Pokemon resultPikachu = pokedex.getPokemon(0);
            Pokemon resultBulbasaur = pokedex.getPokemon(1);
            assertEquals(pikachu, resultPikachu, "Should return Pikachu.");
            assertEquals(bulbasaur, resultBulbasaur, "Should return Bulbasaur.");
        } catch (PokedexException e) {
            fail("PokedexException should not be thrown for valid indexes.");
        }
    }


    @Test
    public void testGetPokemons() {
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertNotNull(pokemons);
        assertEquals(2, pokemons.size());
    }

    @Test
    public void testGetPokemonThrowsException() {
        // Test pour vérifier le comportement lorsqu'un index invalide est utilisé
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(-1));
    }



}