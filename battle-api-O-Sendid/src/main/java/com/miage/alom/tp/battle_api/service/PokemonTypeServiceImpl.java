package com.miage.alom.tp.battle_api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.alom.tp.battle_api.entity.Pokemon;
import com.miage.alom.tp.battle_api.entity.PokemonType;
import com.miage.alom.tp.battle_api.entity.Trainer;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {
    
	
	@Autowired
    RestTemplate restTemplate;
	String pokemonServiceUrl;

    
    public List<PokemonType> listPokemonsTypes() {
    	//this.pokemonServiceUrl = "http://localhost:8080";
    	var tmp = restTemplate.getForObject(this.pokemonServiceUrl + "/pokemon-types/",PokemonType[].class);
    	 return Arrays.asList(tmp);	
    }
    
    
    @Override
    public PokemonType getPokemonTypeByid(int id) {
        PokemonType pokemonType = this.restTemplate.getForObject(pokemonServiceUrl+"/pokemon-types/"+id, PokemonType.class);
        return pokemonType;
    }
    
    @Override
    public List<PokemonType> listPokemon(Trainer t) {
        List<PokemonType> p = this.listPokemonsTypes();
        List<PokemonType> pokemonnn=new ArrayList<PokemonType>();
        for(Pokemon pok:t.getTeam()){
            for(PokemonType type:p){
                if(pok.getPokemonTypeId()==type.getId()) {
                    type.setLevel(pok.getLevel());
                    type.getStats().init(pok.getLevel());
                    pokemonnn.add(type);
                }
            }
        }
        return pokemonnn;
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
