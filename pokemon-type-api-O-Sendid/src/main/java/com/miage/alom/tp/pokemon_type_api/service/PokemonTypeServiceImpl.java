package com.miage.alom.tp.pokemon_type_api.service;

import com.miage.alom.tp.pokemon_type_api.bo.PokemonType;
import com.miage.alom.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.alom.tp.pokemon_type_api.repository.TranslationRepository;
import com.miage.alom.tp.pokemon_type_api.repository.TranslationRepositoryImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService{
	
	@Autowired
	PokemonTypeRepository pokemonTypeRepository;
	@Autowired
	TranslationRepository translationRepository = new TranslationRepositoryImpl();
    public PokemonTypeServiceImpl(PokemonTypeRepository pokemonTypeRepository){ 
    	this.pokemonTypeRepository = pokemonTypeRepository;
    }
    public PokemonTypeServiceImpl() {}

    @Override
    public PokemonType getPokemonType(int id,HttpHeaders headers) {
    	if(headers == null) {
    		return pokemonTypeRepository.findPokemonTypeById(id);
		}else {
	        var pokemon = pokemonTypeRepository.findPokemonTypeById(id);
	        String translationn = ""; // par defaut vide
	        try {
	        	translationn = translationRepository.getPokemonName(pokemon.id(),headers.getAcceptLanguageAsLocales().get(0));
			} catch (Exception e) {
				translationn = translationRepository.getPokemonName(pokemon.id(),null);
			}
	        var pok = new PokemonType(id,translationn,pokemon.sprites(),pokemon.types(),pokemon.stats());
			return pok;
		}
    }

    @Override
    public List<PokemonType> getAllPokemonTypes(HttpHeaders headers){
    	if(headers == null) {
    		return pokemonTypeRepository.findAllPokemonTypes();
		}
    	else {
    		List<PokemonType> pokemons = pokemonTypeRepository.findAllPokemonTypes().stream().map(t -> this.getPokemonType(t.id(), headers)).toList();
    		return pokemons;
    	}
    }
    
	@Override
	public PokemonType getPokemonTypeByName(String name) {
		return pokemonTypeRepository.findPokemonTypeByName(name);
	}

	@Override
	public List<PokemonType> getPokemonTypeByType(String[] types) {
		return pokemonTypeRepository.findAllPokemonTypesByType(types);
	}
	
	public void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository) {
		this.pokemonTypeRepository = pokemonTypeRepository;	
	}
	public void setTranslationRepository(TranslationRepository translationRepository) {
		this.translationRepository = translationRepository;
	}


}