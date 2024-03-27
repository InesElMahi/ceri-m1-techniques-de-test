package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;

@ExtendWith(MockitoExtension.class)
public class IPokedexFactoryTest {

    @Mock
    private IPokemonMetadataProvider metadataProvider;

    @Mock
    private IPokemonFactory pokemonFactory;

    @Mock
    private PokedexFactory pokedexFactory;

    private IPokedex pokedex;
    private Pokemon pikachu;
    private Pokemon bulbasaur;

    @BeforeEach
    void setUp() {
        pokedexFactory = new PokedexFactory();

    	pokedex = mock(IPokedex.class, withSettings().lenient());
        pikachu = new Pokemon(0, "Pikachu", 55, 40, 90, 260, 35, 500, 50, 0.6);
        bulbasaur = new Pokemon(1, "Bulbasaur", 45, 49, 45, 230, 30, 500, 50, 0.5);

        try {
            when(pokedex.addPokemon(any(Pokemon.class))).thenAnswer(i -> {
                Pokemon p = i.getArgument(0);
                return p.getIndex();
            });
            when(pokedex.getPokemon(0)).thenReturn(pikachu);
            when(pokedex.getPokemons()).thenReturn(Arrays.asList(pikachu, bulbasaur));
            when(pokedex.getPokemons(any(Comparator.class))).thenReturn(Arrays.asList(bulbasaur, pikachu));
            doThrow(new PokedexException("Invalid index")).when(pokedex).getPokemon(-1);
        } catch (PokedexException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testCreatePokedex() {
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        assertNotNull(createdPokedex, "Le pokedex créé ne doit pas être null");

    }

    @Test
    void testGetPokemonInvalidIndexException() throws PokedexException {
        doThrow(new PokedexException("Invalid index")).when(pokedex).getPokemon(-1);
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        assertThrows(PokedexException.class, () -> createdPokedex.getPokemon(-1),
                "Accès à un index invalide doit lever une PokedexException.");
    }

    @Test
    void testPokedexSizeAfterAddingPokemons() throws PokedexException {
        when(pokedex.addPokemon(any(Pokemon.class))).thenReturn(0).thenReturn(1);
        when(pokedex.size()).thenReturn(2);
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        createdPokedex.addPokemon(pikachu);
        createdPokedex.addPokemon(bulbasaur);

        assertEquals(2, createdPokedex.size(), "Taille du Pokedex doit être 2 après ajout de deux Pokémons.");
    }

    @Test
    void testAddPokemonToPokedex() throws PokedexException {
        // Assuming metadata and factory are set up correctly.
        when(metadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Pikachu", 55, 40, 90));
        when(pokemonFactory.createPokemon(0, 42, 30, 50, 100)).thenReturn(pikachu); // Example arguments

        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        // This should trigger the createPokemon() call with the specified arguments.
        createdPokedex.addPokemon(pokemonFactory.createPokemon(0, 42, 30, 50, 100));

        // Verify that createPokemon was called with the correct arguments.
        verify(pokemonFactory, times(1)).createPokemon(0, 42, 30, 50, 100);
        assertEquals(1, createdPokedex.size(), "Le Pokedex devrait contenir 1 Pokémon après l'ajout.");
    }




}
