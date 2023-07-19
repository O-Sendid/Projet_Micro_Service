package com.miage.alom.tp.battle_api.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.alom.tp.battle_api.entity.Trainer;

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
	
	@Autowired
	@Value("${trainer.service.url}")
	void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
		this.url = pokemonServiceUrl;
	}
}