package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IPokemonTrainerFactoryTest {

    @Mock
    private IPokedexFactory pokedexFactory;

    @Mock
    private IPokedex pokedex;

    @Mock
    private IPokemonTrainerFactory trainerFactory;

    @Mock
    private PokemonTrainer expectedTrainer;

    @BeforeEach
    void setUp() {
        
        lenient().when(pokedexFactory.createPokedex(any(), any())).thenReturn(pokedex);
    }

    @Test
    void testCreateTrainer() {
        String trainerName = "Ash";
        Team team = Team.MYSTIC;

        when(trainerFactory.createTrainer(trainerName, team, pokedexFactory)).thenReturn(expectedTrainer);

        PokemonTrainer createdTrainer = trainerFactory.createTrainer(trainerName, team, pokedexFactory);
       
        verify(trainerFactory).createTrainer(trainerName, team, pokedexFactory);

        assertSame(expectedTrainer, createdTrainer, "Le trainer créé doit être le même que le mock attendu.");
    }
    
    
}
