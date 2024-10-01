import { useEffect, useState } from "react";
import { PokemonNavigate } from '../../models/PokemonNavigate'
import pokemonService from "../../services/pokemon-service";

export default function PokemonList()
{
    const [pokemon, setPokemon] = useState<PokemonNavigate[]>([]);
    const [action, setAction] = useState<string>("");
    const [previous, setPrevious] = useState<string>("");
    const [next, setNext] = useState<string>("");
    const [pageNum, setPageNum] = useState<number>(0);

    useEffect(() => {
        getPokemon()
    }, [pageNum]);

    async function getPokemon()
    {
        let response;
        if (action === "previous") {response = await pokemonService.getPokemons(previous, pageNum)}
        else if (action === "next") {response = await pokemonService.getPokemons(next, pageNum)}
        else {response = await pokemonService.getPokemons("", 0)}
        
        setPokemon(response.results)
        setPrevious(response.previous)
        setNext(response.next)
    }

    function goPrevious()
    {
        setPageNum(pageNum - 1);
        setAction("previous");
    }

    function goNext()
    {
        setPageNum(pageNum + 1);
        setAction("next");
    }

    return (
        <>
        <button disabled={previous === ""} onClick={() => goPrevious()}>Previous</button>
        <button disabled={next === ""} onClick={() => goNext()}>Next</button>
        <table>
        {
            pokemon.map((pokemon) => (
                <tr>
                    <td>{pokemon.name}</td>
                    <td>{pokemon.url}</td>
                </tr>
            ))
        }
        </table>
        </>
    )
}