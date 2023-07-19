package com.miage.alom.game_ui.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.miage.alom.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.alom.game_ui.pokemonTypes.service.PokemonTypeService;







@Controller
public class PokemonTypeController {
	
	
	@Autowired
	private PokemonTypeService pokemonTypeService;
	

	@GetMapping("/pokedex")
    public ModelAndView pokedex(){
		List<PokemonType> pokemons = this.pokemonTypeService.listPokemonsTypes();
        var modelAndView = new ModelAndView("pokedex");
        modelAndView.addObject("pokemonTypes", pokemons);
        return modelAndView;		
    }
	
	
	 @Autowired
	 public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
		 this.pokemonTypeService = pokemonTypeService; 
	 }
	

}
