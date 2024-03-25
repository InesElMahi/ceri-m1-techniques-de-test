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
    public void testHandlingOfDuplicateTrainerCreation() {
        String trainerName = "Misty";
        Team team = Team.VALOR;

        when(trainerFactory.createTrainer(eq(trainerName), eq(team), eq(pokedexFactory)))
                .thenReturn(expectedTrainer).thenThrow(new IllegalStateException("Trainer existe deja."));


        PokemonTrainer firstCreation = trainerFactory.createTrainer(trainerName, team, pokedexFactory);
        assertSame(expectedTrainer, firstCreation, "deverait retourner le premier trainer.");


        assertThrows(IllegalStateException.class, () -> {
            trainerFactory.createTrainer(trainerName, team, pokedexFactory);
        }, "ne pas recreer un trainer existant.");
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
    public void testTrainerInformationConsistency() {
        String trainerName = "Brock";
        Team team = Team.MYSTIC;

        PokemonTrainer trainer = new PokemonTrainer(trainerName, team, pokedex);
        when(trainerFactory.createTrainer(trainerName, team, pokedexFactory)).thenReturn(trainer);

        PokemonTrainer createdTrainer = trainerFactory.createTrainer(trainerName, team, pokedexFactory);
        assertAll("les infos de Trainer doivent etre consistant",
                () -> assertEquals(trainerName, createdTrainer.getName(), "Trainer's doivent matcher."),
                () -> assertEquals(team, createdTrainer.getTeam(), "Trainer's team doivent matcher."),
                () -> assertSame(pokedex, createdTrainer.getPokedex(), "Trainer's Pokedex doivet matcher.")
        );
    }
    @Test
    public void testCreateTrainerReturnsExpectedTrainer() {
        when(trainerFactory.createTrainer(anyString(), any(Team.class), any(IPokedexFactory.class))).thenReturn(expectedTrainer);
        PokemonTrainer trainer = trainerFactory.createTrainer("Ash", Team.MYSTIC, pokedexFactory);
        assertSame(expectedTrainer, trainer, "Created trainer should match the expected trainer.");
    }

    @Test
    public void testCreateTrainerWithExistingNameThrowsException() {
        when(trainerFactory.createTrainer("Misty", Team.VALOR, pokedexFactory)).thenThrow(new IllegalStateException("Trainer already exists."));
        assertThrows(IllegalStateException.class, () -> trainerFactory.createTrainer("Misty", Team.VALOR, pokedexFactory), "Should throw exception when creating a trainer with an existing name.");
    }

    @Test
    public void testCreateTrainer() {
        // Mock des dépendances
        IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);
        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
        IPokedexFactory pokedexFactory = mock(IPokedexFactory.class);

        // Création d'un PokemonTrainerFactory
        PokemonTrainerFactory trainerFactory = new PokemonTrainerFactory(metadataProvider, pokemonFactory);

        // Définition du nom, de l'équipe et du pokedexFactory
        String name = "Red";
        Team team = Team.MYSTIC;

        // Appel de la méthode createPokedex du pokedexFactory
        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(mock(IPokedex.class));

        // Appel de la méthode createTrainer de la PokemonTrainerFactory
        PokemonTrainer trainer = trainerFactory.createTrainer(name, team, pokedexFactory);

        // Vérification que le trainer est créé correctement avec le bon nom et l'équipe
        assertNotNull(trainer);
        assertEquals(name, trainer.getName());
        assertEquals(team, trainer.getTeam());
    }

}
