package com.miage.alom.service;

import java.util.List;

import com.miage.alom.bo.Trainer;

public interface  TrainerService {
	
	 Trainer getTrainer(String string);
	 List<Trainer>getAllTrainers();
	 void updateTrainer(Trainer trainer);

	 
}
