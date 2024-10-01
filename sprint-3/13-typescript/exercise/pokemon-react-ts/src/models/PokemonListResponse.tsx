import { PokemonNavigate } from './PokemonNavigate'

export class PokemonListResponse {
    count!: number
    next: string = ""
    previous: string = ""
    results!: PokemonNavigate[]
}