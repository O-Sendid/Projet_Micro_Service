package com.miage.alom.trainers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miage.alom.trainers.bo.Trainer;
import com.miage.alom.trainers.service.TrainerService;





@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;
    @Autowired
    public TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }
    
    @GetMapping("/")
    public Iterable<Trainer> getAllTrainers(){
    	return trainerService.getAllTrainers();
    }

    
    @GetMapping("/{name}")
    public Trainer getTrainer(@PathVariable String name){
        return trainerService.getTrainer(name);
    }

    @PostMapping("/create")
    public Trainer getTrainer(@RequestBody Trainer name){
        return trainerService.createTrainer(name);
    }
    
    @PutMapping
    public void putTrainer(@RequestBody Trainer name){
         trainerService.updateTrainer(name);
    }  
    
    @DeleteMapping("/delete/{name}")
    public void deleteTrainerByname(@PathVariable String name) {
    	 trainerService.deleteTrainer(name);
    }
    
}

