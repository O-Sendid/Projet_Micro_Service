package com.miage.alom.trainers.service;

import com.miage.alom.trainers.bo.Pokemon;
import com.miage.alom.trainers.bo.Trainer;

public interface TrainerService {

	
	Iterable<Trainer> getAllTrainers();
    Trainer getTrainer(String name);
    Trainer createTrainer(Trainer trainer);
    void deleteTrainer(String trainer);
    void updateTrainer(Trainer trainer);
    
	
}
