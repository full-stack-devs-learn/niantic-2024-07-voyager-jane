let pokemonService;
let container;
let pokemonName;
let pokemonId;
let typeDiv;
let abilityDiv;

document.addEventListener('DOMContentLoaded', () => {
    pokemonService = new PokemonService();
    container = document.getElementById("pokemon-container");

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    pokemonName = urlParams.get('pokeName'); 

    loadDetails();
})

function loadDetails()
{
    container.innerHTML = '';
    typeDiv = document.createElement("div");
    abilityDiv = document.createElement("div");

    const detailPromise = pokemonService.getPokemonByName(pokemonName);

    detailPromise.then(details => {
        const name = document.createElement("h1");
        const typesList = details.types;
        const abilityList = details.abilities;

        name.textContent = details.name.charAt(0).toUpperCase() + details.name.slice(1);

        displayType(typesList);
        displayAbility(abilityList);

        container.appendChild(name);
        container.appendChild(typeDiv);
        container.appendChild(abilityDiv);
    })
}

function displayType(list)
{
    let title = document.createElement("h4");
    title.textContent = 'Types';
    typeDiv.appendChild(title);

    list.forEach(typeElement => {
        let li = document.createElement("li");
        li.textContent = typeElement.type.name;

        typeDiv.appendChild(li);
    });
}

function displayAbility(list)
{
    let title = document.createElement("h4");
    title.textContent = 'Abilities';
    abilityDiv.appendChild(title);

    list.forEach(abilityElement => {
        let li = document.createElement("li");
        li.textContent = abilityElement.ability.name;

        abilityDiv.appendChild(li);
    })
}