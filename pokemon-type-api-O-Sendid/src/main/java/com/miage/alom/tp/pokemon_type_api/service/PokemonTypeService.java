package com.miage.alom.tp.pokemon_type_api.service;

import java.util.List;

import org.springframework.http.HttpHeaders;

import com.miage.alom.tp.pokemon_type_api.bo.PokemonType;

public interface PokemonTypeService {
	PokemonType getPokemonType(int id, HttpHeaders headers);
    List<PokemonType> getAllPokemonTypes(HttpHeaders headers);
	PokemonType getPokemonTypeByName(String name);
	List<PokemonType> getPokemonTypeByType(String[] types);
}