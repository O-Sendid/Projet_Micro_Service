package com.miage.alom.game_ui.pokemonTypes.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.miage.alom.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.alom.game_ui.pokemonTypes.bo.Trainer;






@Service
public class TrainerServiceImpl implements TrainerService{
	
	
	
	RestTemplate restTemplate;
	private String url;
	

	
	
	@Autowired
	public TrainerServiceImpl() {
		this.restTemplate = new RestTemplate();
	}
	
	
	
	@Autowired
	@Qualifier("trainerApiRestTemplate") 
	void setRestTemplate(RestTemplate restTemplate) {
	    this.restTemplate = restTemplate;
	}
	
	
	@Autowired
	@Value("${trainer.service.url}")
	void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
		this.url = pokemonServiceUrl;
	}
	
	
	
	@Override
	public Trainer getTrainer(String string) {
		Trainer trainer = this.restTemplate.getForObject(this.url+"/trainers/"+string, Trainer.class);
	    return trainer;
	}
	
	
	public List<Trainer> getAllTrainers() {
		Trainer[] listTrainer = this.restTemplate.getForObject(this.url+"/trainers/", Trainer[].class);
        return Arrays.asList(listTrainer);
	}
	

    @Override
    public List<Trainer> getOtherTrainers(String name) {
        List<Trainer> trainerList_tmp = Arrays.asList(restTemplate.getForObject(this.url + "/trainers/", Trainer[].class));
        List<Trainer> trainers = new ArrayList<>();
        for(Trainer t : trainerList_tmp){
            if(!t.getName().equals(name)){
                trainers.add(t);
            }
        }
        return trainers;
    }
	

    public void createTrainer(Trainer trainer) {
        this.restTemplate.postForEntity(this.url+"/trainers/create", trainer , Trainer.class);
    }
	

	
	
	
	
}
