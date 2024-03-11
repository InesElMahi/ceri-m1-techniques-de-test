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
        // Mock de l'interface IPokedex
        pokedex = mock(IPokedex.class);

        // Création de Pokémon
        pikachu = new Pokemon(0, "Pikachu", 55, 40, 90, 260, 35, 500, 50, 0.6);
        bulbasaur = new Pokemon(1, "Bulbasaur", 45, 49, 45, 230, 30, 500, 50, 0.5);

        try {
            when(pokedex.size()).thenReturn(2);
            when(pokedex.addPokemon(any(Pokemon.class))).thenAnswer(i -> {
                Pokemon p = i.getArgument(0);
                return p.getIndex();
            });
            when(pokedex.getPokemon(0)).thenReturn(pikachu);
            when(pokedex.getPokemons()).thenReturn(Arrays.asList(pikachu, bulbasaur));
            when(pokedex.getPokemons(any(Comparator.class))).thenReturn(Arrays.asList(bulbasaur, pikachu));
            //lance une exception PokedexException
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
    public void testGetPokemonValidIndex() throws PokedexException {
        assertEquals(pikachu, pokedex.getPokemon(0), "Pikachu.");
        assertEquals(bulbasaur, pokedex.getPokemon(1), "Bulbasaur.");
    }


    @Test
    public void testGetPokemons() {
        // Test pour récupérer la liste de tous les Pokémon
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertNotNull(pokemons);
        assertEquals(2, pokemons.size());
    }

    @Test
    public void testGetPokemonThrowsException() {
        // Test pour vérifier le comportement lorsqu'un index invalide est utilisé
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(-1));
    }

    @Test
    public void testGetPokemonsWithOrder() {
        // Test pour récupérer la liste de tous les Pokémon
        List<Pokemon> pokemons = pokedex.getPokemons(Comparator.comparing(Pokemon::getName));
        assertNotNull(pokemons);
        assertEquals("Bulbasaur", pokemons.get(0).getName());
        assertEquals("Pikachu", pokemons.get(1).getName());
    }


    @Test
    public void testAddingPokemonFailsWhenPokedexIsAtCapacity() {

        when(pokedex.size()).thenReturn(151);

        when(pokedex.addPokemon(any(Pokemon.class))).thenReturn(-1);

        // Tentative d'ajouter un nouveau Pokémon à un Pokédex à capacité maximale
        Pokemon eevee = new Pokemon(133, "Eevee", 55, 50, 45, 325, 65, 6000, 3, 0.8);
        int indexEevee = pokedex.addPokemon(eevee);
        assertEquals(-1, indexEevee, "Aucun Pokémon ne devrait être ajouté si le Pokédex est à sa capacité maximale.");
    }


}
