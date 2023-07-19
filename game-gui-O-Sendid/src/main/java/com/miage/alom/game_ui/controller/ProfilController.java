package com.miage.alom.game_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.miage.alom.game_ui.pokemonTypes.bo.Trainer;
import com.miage.alom.game_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.alom.game_ui.pokemonTypes.service.TrainerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;



@Controller
public class ProfilController {

    @Autowired
    TrainerService trainerService; 
    @Autowired
    private PokemonTypeService pokemonTypeService;


    @GetMapping("/profile")
    public ModelAndView getProfile(Principal principal){
        Trainer trainer = trainerService.getTrainer(principal.getName());
        trainer.setPokemonTypes(pokemonTypeService.getPokemonType(trainer.getTeam()));
        var modelAndView = new ModelAndView("trainer");
        modelAndView.addObject("trainer", trainer);
        return modelAndView;
    }
    

    @PostMapping(value ="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
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
