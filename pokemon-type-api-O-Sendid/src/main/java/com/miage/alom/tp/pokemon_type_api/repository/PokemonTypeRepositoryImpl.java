package com.miage.alom.tp.pokemon_type_api.repository;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.alom.tp.pokemon_type_api.bo.PokemonType;



@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = new ClassPathResource("pokemons.json").getInputStream();

            var objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public PokemonType findPokemonTypeById(int id) {
        return pokemons.stream()
				.filter(t -> t.id() == id)
				.findAny()
				.get();
    }

    
    @Override
    public List<PokemonType> findAllPokemonTypes() {
        return pokemons;
    }
    
    @Override
    public PokemonType findPokemonTypeByName(String name) {
        return pokemons.stream()
				.filter(t -> t.name().equals(name))
				.findAny()
				.get();
    }
    
	@Override
	public List<PokemonType> findAllPokemonTypesByType(String[] types) {
		var parms =  Arrays.asList(types);
		
		return pokemons.stream()
				.filter(t -> t.types().containsAll(parms))
				.toList();
	}
    
    
}