let pokemonService;
let container;

document.addEventListener('DOMContentLoaded', () => {
    pokemonService = new PokemonService();


})

function loadDetails()
{
    container.innerHTML = '';
    const detailPromise = pokemonService.getPokemonById(id);

    detailPromise.then(details => {
        const name = details.name;
        const type = details.types;
        const stats = details.stats;

        console.log(name);
        console.log(type);
    })
}