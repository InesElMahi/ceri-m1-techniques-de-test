package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Implémentation de l'interface IPokedex.
 * Ce Pokedex permet de stocker, de retrouver et de lister les informations
 * sur les Pokémon capturés, en respectant leur métadonnées par défaut.
 *
 * Il est construit autour d'un fournisseur de métadonnées Pokémon et
 * d'une fabrique de Pokémon, permettant la récupération des informations des Pokémon.
 * @author Inès El Mahi
 */
public class IPokedexImpl implements IPokedex {

    private List<Pokemon> pokemons = new ArrayList<>();
    private final IPokemonMetadataProvider metadataProvider;
    private final IPokemonFactory pokemonFactory;

    /**
     * Constructeur du Pokedex.
     *
     * @param metadataProvider Fournisseur des métadonnées des Pokémon.
     * @param pokemonFactory Fabrique pour la création des instances de Pokémon.
     */
    public IPokedexImpl(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    /**
     * Retourne le nombre de Pokémon contenus dans le Pokedex.
     *
     * @return Le nombre total de Pokémon dans le Pokedex.
     */
    @Override
    public int size() {
        return pokemons.size();
    }

    /**
     * Ajoute un Pokémon donné au Pokedex et retourne son index unique.
     * L'index unique est déterminé par la position du Pokémon dans la liste.
     *
     * @param pokemon Le Pokémon à ajouter.
     * @return L'index du Pokémon ajouté.
     */
    @Override
    public int addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        return pokemons.indexOf(pokemon);
    }

    /**
     * Localise un Pokémon identifié par son ID unique.
     *
     * @param id L'identifiant unique du Pokémon à retrouver.
     * @return Le Pokémon correspondant à l'identifiant donné.
     * @throws PokedexException Si l'index donné n'est pas valide.
     */
    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if (id < 0 || id >= pokemons.size()) {
            throw new PokedexException("L'ID spécifié est invalide : " + id);
        }
        return pokemons.get(id);
    }

    /**
     * Retourne une liste non modifiable de tous les Pokémon du Pokedex.
     *
     * @return Liste non modifiable de tous les Pokémon.
     */
    @Override
    public List<Pokemon> getPokemons() {
        return Collections.unmodifiableList(pokemons);
    }

    /**
     * Retourne une liste non modifiable de tous les Pokémon du Pokedex,
     * triée en utilisant le comparateur spécifié.
     *
     * @param order Le comparateur à utiliser pour le tri.
     * @return Liste triée et non modifiable de tous les Pokémon.
     */
    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> sortedPokemons = new ArrayList<>(pokemons);
        sortedPokemons.sort(order);
        return Collections.unmodifiableList(sortedPokemons);
    }

    /**
     * Crée une instance de Pokémon avec les paramètres spécifiés.
     *
     * Utilise la fabrique de Pokémon pour la création.
     *
     * @param index Index du Pokémon.
     * @param cp Puissance de Combat du Pokémon.
     * @param hp Points de Vie du Pokémon.
     * @param dust Coût en Poussière d'Étoile pour la montée de niveau.
     * @param candy Coût en Bonbons pour l'évolution.
     * @return Nouvelle instance de Pokémon.
     */
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }

    /**
     * Récupère les métadonnées d'un Pokémon spécifié par son index.
     *
     * @param index Index du Pokémon.
     * @return Métadonnées du Pokémon.
     * @throws PokedexException Si les métadonnées ne peuvent pas être récupérées.
     */
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        try {
            return metadataProvider.getPokemonMetadata(index);
        } catch (Exception e) {
            throw new PokedexException("Impossible de récupérer les métadonnées pour le Pokémon d'index " + index + ".");
        }
    }
}
