package com.miage.alom.trainers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.miage.alom.trainers.bo.Trainer;
import com.miage.alom.trainers.repository.TrainerRepository;



@Service
public class TrainerServiceImpl implements TrainerService { 
	
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); 

    private TrainerRepository trainerRepository;
    
    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Iterable<Trainer> getAllTrainers() {
    	return trainerRepository.findAll();
    }

    @Override
    public Trainer getTrainer(String name) {
        return trainerRepository.findById(name).orElse(null);
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
    	trainer.setPassword(bCryptPasswordEncoder.encode(trainer.getPassword()));
        return trainerRepository.save(trainer);
    }
    
    @Override
    public void updateTrainer(Trainer trainer) {
    	//trainer.setPassword(trainer.getPassword());
         trainerRepository.save(trainer);
    }
    
    
    @Override
    public void deleteTrainer(String trainer) {
    	 trainerRepository.deleteById(trainer);
	
    }
}
