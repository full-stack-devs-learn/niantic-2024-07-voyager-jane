import axios from "axios";
import { PokemonListResponse } from "../models/PokemonListResponse";

class PokemonService
{
    pageNum = 0;
    baseUrl = 'https://pokeapi.co/api/v2/pokemon'

    async getPokemons(page: string, num: number): Promise<PokemonListResponse>
    {
        let response;
        this.pageNum = num * 20;

        if (page)
        {
            response = await axios.get<PokemonListResponse>(`${this.baseUrl}?offset=${this.pageNum}&limit=20`);
        }
        else 
        {
            response = await axios.get<PokemonListResponse>(`${this.baseUrl}?offset=0&limit=20`);
        }

        return response.data;
    }

    async getPokemonByName(name: string)
    {
        const response = await axios.get(`${this.baseUrl}/${name}`);

        return response.data;
    }
}

const pokemonService = new PokemonService();
export default pokemonService;