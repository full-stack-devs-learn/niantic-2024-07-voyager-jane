// define the SPokeAPI Pokemon service logic here

class PokemonService
{
    baseUrl = `${config.baseUrl}/pokemon`

    // write a function that allows you to request a page
    // of 20 pokemon - how can you request page 1,2, 3 etc?
    // consider using async/await with your axios request

    async getPokemons(page)
    {
        let response;

        if (page)
        {
            response = await axios.get(page);
        }
        else 
        {
            response = await axios.get(this.baseUrl);
        }

        return response.data;
    }

    async getPokemonById(id)
    {
        const response = await axios.get(`${this.baseUrl}/${id}`);

        return response.data;
    }
}