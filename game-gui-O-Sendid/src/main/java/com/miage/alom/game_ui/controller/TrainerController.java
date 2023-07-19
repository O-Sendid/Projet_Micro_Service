package com.miage.alom.game_ui.controller;



import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.miage.alom.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.alom.game_ui.pokemonTypes.bo.Trainer;
import com.miage.alom.game_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.alom.game_ui.pokemonTypes.service.TrainerService;




@Controller
public class TrainerController {

    @Autowired
    TrainerService trainerService;
    
    @Autowired
    private PokemonTypeService pokemonTypeService;
    
    

    @GetMapping("/trainers")
    public ModelAndView trainers(Principal principal){
        List<Trainer> trainerList = trainerService.getOtherTrainers(principal.getName());
        var modelAndView = new ModelAndView("trainers");
        modelAndView.addObject("trainers", trainerList);
        for(Trainer t : trainerList){
            t.setPokemonTypes(pokemonTypeService.getPokemonType(t.getTeam()));
        }
        return modelAndView;
    }
    
    
    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService){
        this.pokemonTypeService = pokemonTypeService;
    }
    
}

