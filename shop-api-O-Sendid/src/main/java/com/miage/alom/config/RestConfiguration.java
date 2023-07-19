package com.miage.alom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration 
public class RestConfiguration {
	   
    @Value("${trainer.service.username}")
    String username;
    
    @Value("${trainer.service.password}")
    String password;
    
    @Bean 
    RestTemplate restTemplate(){
        return new RestTemplate(); 
    }
       

    
    @Bean
    RestTemplate trainerApiRestTemplate(){ 
        RestTemplate restTemplate = restTemplate();
        RestTemplateBuilder restTemplateBuilder =  new RestTemplateBuilder();
        
        restTemplate =  restTemplateBuilder
                .basicAuthentication(this.username, this.password)
                .build();
        return restTemplate;
    }
    
    
    
    

}