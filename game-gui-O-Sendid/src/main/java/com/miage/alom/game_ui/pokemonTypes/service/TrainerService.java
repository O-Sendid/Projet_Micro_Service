package com.miage.alom.game_ui.pokemonTypes.service;

import java.util.List;
import com.miage.alom.game_ui.pokemonTypes.bo.Trainer;

public interface TrainerService {

	 Trainer getTrainer(String string); 
	 List<Trainer>getAllTrainers(); 
	 List<Trainer> getOtherTrainers(String name);
	 void createTrainer(Trainer trainer);	 
	 

}
