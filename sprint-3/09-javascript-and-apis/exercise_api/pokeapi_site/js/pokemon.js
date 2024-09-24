// code here is the logic to manage the home (or pokemon) page
let pokemonService;
let next;
let previous;
let container;
let action;

let nextPokemon = null;
let previousPokemon = null;
let pageCount = 0;

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

    next.addEventListener("click", () => {
        action = 'next';
        loadPokemons(nextPokemon);
    })

    previous.addEventListener("click", () => {
        action = 'previous';
        loadPokemons(previousPokemon);
    })

    loadPokemons();
})

function loadPokemons(url)
{
    if (action === 'next') pageCount++;
    if (action === 'previous') pageCount--;
    const pokemonPromise = pokemonService.getPokemons(url, pageCount);

        
    pokemonPromise.then(pokemons => {
        const pokemonsList = pokemons.results;
        container.innerHTML = '';

        setPrevious(pokemons.previous);
        setNext(pokemons.next);

        pokemonsList.forEach(pokemon => {
            const div = document.createElement("div");
            const a = document.createElement("a");
            a.textContent = pokemon.name;
            a.href = 'pokemon.html';
            a.style.fontSize = '18px';
            a.style.color = '#000000';

            div.appendChild(a);
            container.appendChild(div);
        })
    })
}

function setPrevious(previousPage)
{
    if (previousPage)
    {
        previous.disabled = false;
        previousPokemon = previousPage;
    }
    else previous.disabled = true;
}

function setNext(nextPage)
{
    if (nextPage)
    {
        next.disabled = false;
        nextPokemon = nextPage;
    }
    else next.disabled = true;
}