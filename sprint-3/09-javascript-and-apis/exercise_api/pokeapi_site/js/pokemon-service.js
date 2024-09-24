// define the SPokeAPI Pokemon service logic here

class PokemonService
{
    pageNum = 0;
    baseUrl = `${config.baseUrl}/pokemon`

    // write a function that allows you to request a page
    // of 20 pokemon - how can you request page 1,2, 3 etc?
    // consider using async/await with your axios request

    async getPokemons(page, num)
    {
        let response;
        this.pageNum = num * 20;

        if (page)
        {
            response = await axios.get(`${this.baseUrl}?offset=${this.pageNum}&limit=20`);
        }
        else 
        {
            response = await axios.get(`${this.baseUrl}?offset=0&limit=20`);
        }

        return response.data;
    }

    async getPokemonById(id)
    {
        const response = await axios.get(`${this.baseUrl}/${id}`);

        return response.data;
    }
}