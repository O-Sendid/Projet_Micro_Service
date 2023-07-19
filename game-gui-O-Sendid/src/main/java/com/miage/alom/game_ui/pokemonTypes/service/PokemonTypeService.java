package com.miage.alom.game_ui.pokemonTypes.service;

import java.util.List;

import com.miage.alom.game_ui.pokemonTypes.bo.Pokemon;
import com.miage.alom.game_ui.pokemonTypes.bo.PokemonType;

public interface PokemonTypeService {

    List<PokemonType> listPokemonsTypes();
    PokemonType getPokemonTypeByid(int id);
    List<PokemonType> getPokemonType(List<Pokemon> pokemon);


}