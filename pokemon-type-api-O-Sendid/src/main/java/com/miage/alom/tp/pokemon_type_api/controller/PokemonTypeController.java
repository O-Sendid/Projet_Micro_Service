package com.miage.alom.tp.pokemon_type_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.miage.alom.tp.pokemon_type_api.bo.PokemonType;
import com.miage.alom.tp.pokemon_type_api.service.PokemonTypeService;

@RestController
@RequestMapping("/pokemon-types")
class PokemonTypeController {
	
	@Autowired
	private PokemonTypeService pokemonTypeService;
	
	public PokemonTypeController(PokemonTypeService pokemonTypeService) { 
    	this.pokemonTypeService = pokemonTypeService;
    }
	
	public PokemonTypeController() { }
	
	
	@GetMapping("/{id}")
    PokemonType getPokemonTypeFromId(@PathVariable int id ,@RequestHeader HttpHeaders headers){
        return pokemonTypeService.getPokemonType(id,headers);
    }
	
	
	
	
    @GetMapping("/")
    public List<PokemonType> getAllPokemonTypes(@RequestHeader HttpHeaders headers) {
        return pokemonTypeService.getAllPokemonTypes(headers);
    }
    
	@RequestMapping(value = "/", params = "name", method = RequestMethod.GET)
	@ResponseBody
	public PokemonType getPokemonTypeByName(
			@RequestParam("name") String name) {
		return pokemonTypeService.getPokemonTypeByName(name);
	}
    
    
	@RequestMapping(value = "/", params = "types", method = RequestMethod.GET)
	@ResponseBody
	public List<PokemonType> getPokemonTypeByTypes(
			@RequestParam("types") String[] types) {
		return pokemonTypeService.getPokemonTypeByType(types);
	}
	
	
}
