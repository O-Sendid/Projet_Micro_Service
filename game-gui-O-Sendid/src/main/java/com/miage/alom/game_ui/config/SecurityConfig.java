package com.miage.alom.game_ui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.authentication.BadCredentialsException;

import com.miage.alom.game_ui.pokemonTypes.service.TrainerService;

import java.util.Optional;



@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    TrainerService trainerService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> Optional.ofNullable(trainerService.getTrainer(username))
                .map(trainer ->User.withUsername(trainer.getName()).password(trainer.getPassword()).roles("USER").build())
                .orElseThrow(() -> new BadCredentialsException("No such user"));
    }


    
}