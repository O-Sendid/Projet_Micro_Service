package com.miage.alom.tp.battle_api.service;

import java.util.List;

import com.miage.alom.tp.battle_api.entity.Trainer;

public interface  TrainerService {
	
	 Trainer getTrainer(String string);
	 List<Trainer>getAllTrainers();
	 
}
