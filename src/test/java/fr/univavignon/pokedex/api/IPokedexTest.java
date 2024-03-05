package fr.univavignon.pokedex.api;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Comparator;

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
            when(pokedex.getPokemons()).thenReturn(List.of(pikachu, bulbasaur));
            when(pokedex.getPokemons(any(Comparator.class))).thenReturn(List.of(bulbasaur, pikachu));
            //lance une exception PokedexException
            doThrow(new PokedexException("Invalid index")).when(pokedex).getPokemon(-1);
        } catch (PokedexException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddPokemon() {
    	
        // On test l'ajout du Pokémon 
        int indexPikachu = pokedex.addPokemon(pikachu);
        assertEquals(0, indexPikachu);
    }

    @Test
    public void testGetSize() {
        // On test la taille du Pokedex
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        // Test pour récupérer un Pokémon par son index
        assertEquals(pikachu, pokedex.getPokemon(0));
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
    
 

}