// code here is the logic to manage the home (or pokemon) page
let pokemonService;
let next;
let previous;
let container;

document.addEventListener('DOMContentLoaded', () => {
    pokemonService = new PokemonService();

    // add logic to display a list of Pokemon from the PokeAPI
    // note that it will defautl to displaying 20 pokemon
    // at a time.

    // look at the API and discover how you can access
    // page 2 or page 3

    // can you add previous and next buttons to the page
    // to navigate between pages?

    next = document.getElementById("next-button");
    previous = document.getElementById("previous-button");
    container = document.getElementById("pokemon-list-container");

    loadPokemon();
})

function loadPokemon(url)
{
    let pokemonPromise;

    if (url)
    {
        pokemonPromise = pokemonService.getPokemon(url);
    }
    else 
    {
        pokemonPromise = pokemonService.getPokemon()
    }
    
    pokemonPromise.then(pokemons => {
        
        const pokemonsList = pokemons.results;

        pokemonsList.forEach(pokemon => {
            const div = document.createElement("div");
            div.textContent = pokemon.name;
            div.style.fontSize = '18px';

            container.appendChild(div);
        })
    })
}