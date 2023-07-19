package com.miage.alom.game_ui.pokemonTypes.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.alom.game_ui.pokemonTypes.bo.Pokemon;
import com.miage.alom.game_ui.pokemonTypes.bo.PokemonType;


@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {
    
	
	@Autowired
    RestTemplate restTemplate;
	
	String pokemonServiceUrl;

    
    public List<PokemonType> listPokemonsTypes() {
    	//this.pokemonServiceUrl = "http://localhost:8080";
    	var tmp = restTemplate.getForObject(this.pokemonServiceUrl + "/pokemon-types/",PokemonType[].class);
    	 return Arrays.stream(tmp).toList();    	
    }
    
    @Override
    public PokemonType getPokemonTypeByid(int id) {
        PokemonType pokemonType = this.restTemplate.getForObject(pokemonServiceUrl+"/pokemon-types/"+id, PokemonType.class);
        return pokemonType;
    }
    
    
    public List<PokemonType> getPokemonType(List<Pokemon> pokemons) {
        List<PokemonType> pokemonTypeList_tmp = new ArrayList<>();
        for(Pokemon p : pokemons){
        	pokemonTypeList_tmp.add(getPokemonTypeByid(p.getPokemonTypeId()));
        }
        return pokemonTypeList_tmp;
    }
    
    
    @Autowired  
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Autowired
    @Value("${pokemonType.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
         this.pokemonServiceUrl= pokemonServiceUrl;
    }
}