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
    
    @Test
    void testCreateMultipleTrainers() {
        String trainerName1 = "Ash";
        Team team1 = Team.MYSTIC;

        String trainerName2 = "Misty";
        Team team2 = Team.VALOR;

        PokemonTrainer trainer1 = new PokemonTrainer(trainerName1, team1, pokedex);
        PokemonTrainer trainer2 = new PokemonTrainer(trainerName2, team2, pokedex);

        when(trainerFactory.createTrainer(trainerName1, team1, pokedexFactory)).thenReturn(trainer1);
        when(trainerFactory.createTrainer(trainerName2, team2, pokedexFactory)).thenReturn(trainer2);

        assertNotSame(trainerFactory.createTrainer(trainerName1, team1, pokedexFactory), 
                      trainerFactory.createTrainer(trainerName2, team2, pokedexFactory),
                      "Deux entraîneurs avec des noms ou des équipes différents ne devraient pas être identiques.");
    }
    @Test
    void testTrainerTeamConsistency() {
        String trainerName = "Brock";
        Team team = Team.VALOR;

        when(trainerFactory.createTrainer(trainerName, team, pokedexFactory)).thenReturn(new PokemonTrainer(trainerName, team, pokedex));

        PokemonTrainer trainer = trainerFactory.createTrainer(trainerName, team, pokedexFactory);

        assertEquals(team, trainer.getTeam(), "L'équipe de l'entraîneur créé doit correspondre à l'équipe spécifiée.");
    }
    @Test
    void testPokedexAssignment() {
        String trainerName = "Cynthia";
        Team team = Team.INSTINCT;

        when(trainerFactory.createTrainer(trainerName, team, pokedexFactory)).thenReturn(new PokemonTrainer(trainerName, team, pokedex));

        PokemonTrainer trainer = trainerFactory.createTrainer(trainerName, team, pokedexFactory);

        assertNotNull(trainer.getPokedex(), "Le Pokedex du trainer ne doit pas être null.");
        assertSame(pokedex, trainer.getPokedex(), "Le Pokedex assigné doit être celui qui est attendu.");
    }

    @Test
    void testTrainerNameConsistency() {
        String trainerName = "Lance";
        Team team = Team.VALOR;

        when(trainerFactory.createTrainer(trainerName, team, pokedexFactory)).thenReturn(new PokemonTrainer(trainerName, team, pokedex));

        PokemonTrainer trainer = trainerFactory.createTrainer(trainerName, team, pokedexFactory);

        assertEquals(trainerName, trainer.getName(), "Le nom de l'entraîneur doit correspondre au nom fourni.");
    }
    @Test
    void testTrainerCreationWithNullParameters() {
        when(trainerFactory.createTrainer(null, null, null)).thenThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class, () -> {
            trainerFactory.createTrainer(null, null, null);
        }, "La création d'un entraîneur avec des paramètres null devrait lancer une IllegalArgumentException.");
    }
    @Test
    void testUniqueTrainerCreation() {
        String trainerName = "Steven";
        Team team = Team.MYSTIC;
        PokemonTrainer trainer1 = new PokemonTrainer(trainerName, team, pokedex);
        PokemonTrainer trainer2 = new PokemonTrainer(trainerName, team, pokedex);

        when(trainerFactory.createTrainer(trainerName, team, pokedexFactory)).thenReturn(trainer1, trainer2);

        PokemonTrainer createdTrainer1 = trainerFactory.createTrainer(trainerName, team, pokedexFactory);
        PokemonTrainer createdTrainer2 = trainerFactory.createTrainer(trainerName, team, pokedexFactory);

        assertNotSame(createdTrainer1, createdTrainer2, "Chaque appel à la création de trainer devrait retourner une instance unique.");
    }

    @Test
    void testHandlingOfDuplicateTrainerCreation() {
        String trainerName = "Sabrina";
        Team team = Team.VALOR;
        
        when(trainerFactory.createTrainer(eq(trainerName), eq(team), eq(pokedexFactory)))
            .thenReturn(expectedTrainer).thenThrow(new IllegalStateException("Trainer déjà existant."));

        PokemonTrainer firstCreation = trainerFactory.createTrainer(trainerName, team, pokedexFactory);
        assertSame(expectedTrainer, firstCreation, "Le premier trainer créé doit être retourné.");

        assertThrows(IllegalStateException.class, () -> {
            trainerFactory.createTrainer(trainerName, team, pokedexFactory);
        }, "La tentative de recréation d'un trainer existant devrait échouer.");
    }
    @Test
    void testTrainerInformationConsistency() {
        String trainerName = "Lorelei";
        Team team = Team.MYSTIC;

        PokemonTrainer trainer = new PokemonTrainer(trainerName, team, pokedex);
        when(trainerFactory.createTrainer(trainerName, team, pokedexFactory)).thenReturn(trainer);

        PokemonTrainer createdTrainer = trainerFactory.createTrainer(trainerName, team, pokedexFactory);

        assertAll("Trainer information should be consistent",
            () -> assertEquals(trainerName, createdTrainer.getName()),
            () -> assertEquals(team, createdTrainer.getTeam()),
            () -> assertSame(pokedex, createdTrainer.getPokedex())
        );
    }
    @Test
    void testTrainerCreationWithEmptyName() {
        when(trainerFactory.createTrainer("", Team.VALOR, pokedexFactory)).thenThrow(new IllegalArgumentException("Le nom du trainer ne peut pas être vide."));

        assertThrows(IllegalArgumentException.class, () -> {
            trainerFactory.createTrainer("", Team.VALOR, pokedexFactory);
        }, "La création d'un entraîneur avec un nom vide devrait lancer une IllegalArgumentException.");
    }
    @Test
    void testTrainerCreationWithoutTeam() {
        when(trainerFactory.createTrainer("Gary", null, pokedexFactory)).thenThrow(new IllegalArgumentException("L'équipe du trainer ne peut pas être null."));

        assertThrows(IllegalArgumentException.class, () -> {
            trainerFactory.createTrainer("Gary", null, pokedexFactory);
        }, "La création d'un entraîneur sans équipe devrait lancer une IllegalArgumentException.");
    }
    @Test
    void testCreateTrainerWithDuplicateNameThrowsException() {
        String trainerName = "Misty";
        Team team = Team.MYSTIC;
        when(trainerFactory.createTrainer(trainerName, team, pokedexFactory)).thenReturn(expectedTrainer).thenThrow(new IllegalArgumentException("Trainer name must be unique."));

        PokemonTrainer firstTrainer = trainerFactory.createTrainer(trainerName, team, pokedexFactory);
        assertThrows(IllegalArgumentException.class, () -> {
            trainerFactory.createTrainer(trainerName, team, pokedexFactory);
        }, "Creating a trainer with a duplicate name should throw an exception.");
    }
    @Test
    void testCreateTrainerWithInvalidTeam() {
        String trainerName = "Lance";
        Team invalidTeam = null;

        when(trainerFactory.createTrainer(trainerName, invalidTeam, pokedexFactory)).thenThrow(new IllegalArgumentException("Invalid team specified."));

        assertThrows(IllegalArgumentException.class, () -> {
            trainerFactory.createTrainer(trainerName, invalidTeam, pokedexFactory);
        }, "Creating a trainer with an invalid team should throw an IllegalArgumentException.");
    }

}
