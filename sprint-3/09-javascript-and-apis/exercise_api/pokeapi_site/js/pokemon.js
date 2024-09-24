// code here is the logic to manage the home (or pokemon) page
let pokemonService;
let next;
let previous;
let container;

let nextPokemon = null;
let previousPokemon = null;

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
        loadPokemons(nextPokemon);
    })

    previous.addEventListener("click", () => {
        loadPokemons(previousPokemon);
    })

    loadPokemons();
})

function loadPokemons(url)
{

    let pokemonPromise;

    if (url)
    {
        pokemonPromise = pokemonService.getPokemons(url);
    }
    else 
    {
        pokemonPromise = pokemonService.getPokemons();
    }
    
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
    
    // if (previousPage)
    // {
    //     previous.disabled = false;

    //     previous.addEventListener("click", () => {
    //         loadPokemons(previousPage);
    //     }, {once: true})
    // }
    // else 
    // {
    //     previous.disabled = true;
    // }
}

function setNext(nextPage)
{
    if (nextPage)
    {
        next.disabled = false;
        nextPokemon = nextPage;
    }
    else next.disabled = true;
    
    // if (nextPage)
    // {
    //     next.disabled = false;

    //     next.addEventListener("click", () => {
    //     loadPokemons(nextPage);
    //     }, {once: true})
    // }
    // else
    // {
    //     next.disabled = true;
    // }
}