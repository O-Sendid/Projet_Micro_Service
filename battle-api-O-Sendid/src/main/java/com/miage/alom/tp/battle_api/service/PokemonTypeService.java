package com.miage.alom.tp.battle_api.service;

import java.util.List;

import com.miage.alom.tp.battle_api.entity.PokemonType;
import com.miage.alom.tp.battle_api.entity.Trainer;

public interface PokemonTypeService {
	
    List<PokemonType> listPokemonsTypes();
    PokemonType getPokemonTypeByid(int id);
    List<PokemonType> listPokemon(Trainer t);

}
