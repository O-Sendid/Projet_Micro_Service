package com.miage.alom.game_ui.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.miage.alom.game_ui.pokemonTypes.bo.Trainer;
import com.miage.alom.game_ui.pokemonTypes.service.TrainerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@Controller
public class IndexController {
	
    @Autowired
    TrainerService trainerService; 
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


	@GetMapping("/")
    public String index(){
        return "index"; 
    }
	
	
	 @PostMapping("/registerTrainer")
	 public ModelAndView registerNewTrainer(String trainerName){
		   var modelAndView = new ModelAndView("register");
		   modelAndView.addObject("name",trainerName);	   
		   return modelAndView;
	    }
	 
	 @GetMapping("/changepassword")
	    public ModelAndView chnagepassword(Principal principal){
	        ModelAndView modelAndView = new ModelAndView("changepassword");
	        modelAndView.addObject("user",principal);
	        return modelAndView;
	    }

	    @PostMapping("/changepasswordpost")
	    public ModelAndView chnagepasswordPost(String trainerPwd,Principal principal){
	        ModelAndView modelAndView = new ModelAndView("register");
	        modelAndView.addObject("name",principal.getName());
	        modelAndView.addObject("user",principal);
	        modelAndView.addObject("trainerPwd",trainerPwd);
	        for (Trainer t : trainerService.getAllTrainers()) {
	            if (t.getName().equals(principal.getName())) {
	                Trainer trainer = new Trainer(t.getName(),trainerPwd,t.getTeam());
	                trainerService.createTrainer(trainer);
	            }
	        }
	        return modelAndView;
	    }

}