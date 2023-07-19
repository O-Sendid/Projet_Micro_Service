package com.miage.alom.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.alom.bo.Trainer;

@Service
public class TrainerServiceImpl implements TrainerService{
	
	
	RestTemplate restTemplate;
	
	
	@Autowired
	public TrainerServiceImpl() {
		this.restTemplate = new RestTemplate();
	}
	
	
	private String url;
	
	@Autowired
	
	
	@Qualifier("trainerApiRestTemplate") 
	void setRestTemplate(RestTemplate restTemplate) {
	    this.restTemplate = restTemplate;
	}
	
	
	@Override
	public Trainer getTrainer(String string) {
		return this.restTemplate.getForObject(this.url+"/trainers/"+string, Trainer.class);
	}
	
	
	public List<Trainer> getAllTrainers() {
		var tmp = this.restTemplate.getForObject(this.url+"/trainers/", Trainer[].class);
		return Arrays.stream(tmp).toList();
	}
	
	@Override
    public void updateTrainer(Trainer trainer) {
		this.restTemplate.put(this.url+"/trainers/", trainer , Trainer.class);
		
		
    }
	
	@Autowired
	@Value("${trainer.service.url}")
	void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
		this.url = pokemonServiceUrl;
	}
}