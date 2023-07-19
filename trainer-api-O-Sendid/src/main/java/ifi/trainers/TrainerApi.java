package ifi.trainers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.miage.alom.trainers.bo.Pokemon;
import com.miage.alom.trainers.bo.Trainer;
import com.miage.alom.trainers.repository.TrainerRepository;

public class TrainerApi {
	@Bean 
	@Autowired 
	public CommandLineRunner demo(TrainerRepository repository) { 
	    return (args) -> { 
	        var ash = new Trainer("Ash");
	        var pikachu = new Pokemon(25, 18);
	        ash.setTeam(List.of(pikachu));

	        var misty = new Trainer("Misty");
	        var staryu = new Pokemon(120, 18);
	        var starmie = new Pokemon(121, 21);
	        misty.setTeam(List.of(staryu, starmie));

	        // save a couple of trainers
	        repository.save(ash); 
	        repository.save(misty);
	    };
	}
}
