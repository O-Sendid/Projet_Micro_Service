package com.miage.alom.trainers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.miage.alom.trainers.bo.Trainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrainerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TrainerController controller;
    
    @Value("user") 
    private String username;
    
    @Value("root")
    private String password;

    @Test
    void trainerController_shouldBeInstanciated(){
        assertNotNull(controller);
    }



    @Test
    void getAllTrainers_shouldReturnAshAndMisty() {
        var trainers = this.restTemplate
        		.withBasicAuth(username, password) 
        		.getForObject("http://localhost:" + port + "/trainers/", Trainer[].class);
        System.out.println("khdm azbi");
        assertNotNull(trainers);
        assertEquals(3, trainers.length);
        assertEquals("Omar", trainers[0].getName());
        assertEquals("Ash", trainers[1].getName());
    }
    
    
    
    
    @Test 
    void getTrainers_shouldThrowAnUnauthorized(){
        var responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(responseEntity);
        assertEquals(401, responseEntity.getStatusCodeValue());
    }

    @Test 
    void getTrainer_withNameAsh_shouldReturnAsh() {
        var ash = this.restTemplate
                .withBasicAuth(username, password) 
                .getForObject("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(ash);
        assertEquals("Ash", ash.getName());
        assertEquals(1, ash.getTeam().size());
        assertEquals(25, ash.getTeam().get(0).getPokemonTypeId());
        assertEquals(18, ash.getTeam().get(0).getLevel());
    }
    
}