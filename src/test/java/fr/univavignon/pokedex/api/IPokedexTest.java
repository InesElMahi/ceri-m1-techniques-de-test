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
    @Test
    public void testGetPokemonsSortedByName() {
        List<Pokemon> sortedPokemons = pokedex.getPokemons(PokemonComparators.NAME);
        assertEquals("Bulbasaur", sortedPokemons.get(0).getName(), "Le premier Pokémon devrait être Bulbasaur lors du tri par nom.");
        assertEquals("Pikachu", sortedPokemons.get(1).getName(), "Le deuxième Pokémon devrait être Pikachu lors du tri par nom.");

        int comparisonResultName = PokemonComparators.NAME.compare(sortedPokemons.get(0), sortedPokemons.get(1));
        assertTrue(comparisonResultName < 0, "Bulbasaur devrait venir avant Pikachu lors du tri par nom.");
    }

    @Test
    public void testGetPokemonsSortedByIndex() {
        when(pokedex.getPokemons(PokemonComparators.INDEX)).thenReturn(Arrays.asList(pikachu, bulbasaur));

        List<Pokemon> sortedPokemons = pokedex.getPokemons(PokemonComparators.INDEX);
        assertEquals(pikachu.getIndex(), sortedPokemons.get(0).getIndex(), "Le premier Pokémon devrait être Pikachu lors du tri par index.");
        assertEquals(bulbasaur.getIndex(), sortedPokemons.get(1).getIndex(), "Le deuxième Pokémon devrait être Bulbasaur lors du tri par index.");
    }

    @Test
    public void testCreatePokemon() {
        Pokemon pikachu = pokedex.createPokemon(25, 320, 120, 3000, 50);
        assertNotNull(pikachu, "La méthode createPokemon ne devrait pas renvoyer null.");
        assertEquals(25, pikachu.getIndex(), "L'index du Pokémon créé devrait être 25.");
        assertEquals(320, pikachu.getCp(), "La puissance de combat du Pokémon créé devrait être 320.");
    }

    @Test
    public void testGetPokemonMetadata() {
        try {
            PokemonMetadata pikachuMetadata = pokedex.getPokemonMetadata(25);
            assertNotNull(pikachuMetadata, "Les métadonnées du Pokémon ne devraient pas être null.");
            assertEquals("Pikachu", pikachuMetadata.getName(), "Le nom du Pokémon devrait être Pikachu.");
        } catch (PokedexException e) {
            fail("La méthode getPokemonMetadata ne devrait pas lever d'exception.");
        }
    }

    @Test
    public void testSortingPokemons() {
        Comparator<Pokemon> testComparator = Comparator.comparing(Pokemon::getIndex);

        List<Pokemon> unsortedPokemons = Arrays.asList(
                new Pokemon(25, "Pikachu", 320, 120, 3000, 50, 0, 0, 0, 0),
                new Pokemon(4, "Charmander", 300, 110, 2800, 45, 0, 0, 0, 0),
                new Pokemon(7, "Squirtle", 310, 115, 2900, 48, 0, 0, 0, 0)
        );

        for (Pokemon pokemon : unsortedPokemons) {
            pokedex.addPokemon(pokemon);
        }
        List<Pokemon> sortedPokemons = pokedex.getPokemons(testComparator);
        for (int i = 0; i < sortedPokemons.size() - 1; i++) {
            assertTrue(testComparator.compare(sortedPokemons.get(i), sortedPokemons.get(i + 1)) <= 0, "Les pokemons devraient être triés par index.");
        }
    }

}
