package com.miage.alom.trainers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.miage.alom.trainers.bo.Pokemon;
import com.miage.alom.trainers.bo.Trainer;
import com.miage.alom.trainers.repository.TrainerRepository;

@SpringBootApplication 
public class TrainerApi {

    public static void main(String... args){ 
        SpringApplication.run(TrainerApi.class, args);
    }
    
    
    @Bean
    @Autowired
    public CommandLineRunner demo(TrainerRepository repository) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); 

        return (args) -> {
            var ash = new Trainer("Ash");
            var pikachu = new Pokemon(25, 18);
            ash.setTeam(List.of(pikachu));
            ash.setPassword(bCryptPasswordEncoder.encode("ash_password")); 

            var misty = new Trainer("Misty");
            var staryu = new Pokemon(120, 18);
            var starmie = new Pokemon(121, 21);
            misty.setTeam(List.of(staryu, starmie));
            misty.setPassword(bCryptPasswordEncoder.encode("misty_password")); 
            
            
            
            var omar = new Trainer("Omar");
            var omar_staryu = new Pokemon(47, 18);
            var omar_starmie = new Pokemon(98, 21);
            omar.setTeam(List.of(omar_staryu, omar_starmie));
            omar.setPassword(bCryptPasswordEncoder.encode("omar"));
            repository.save(omar);
            // save a couple of trainers
            repository.save(ash);
            repository.save(misty);
        };
    }
    

}