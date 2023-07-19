package com.miage.alom.tp.pokemon_type_api.repository;

import java.util.List;

import com.miage.alom.tp.pokemon_type_api.bo.PokemonType;

public interface PokemonTypeRepository {
    PokemonType findPokemonTypeById(int id);
    List<PokemonType> findAllPokemonTypes();
    PokemonType findPokemonTypeByName(String name);
	List<PokemonType> findAllPokemonTypesByType(String[] types);
}