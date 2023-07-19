package com.miage.alom.trainers.bo;

import javax.persistence.Embeddable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {

    @Test
    void pokemon_shouldBeAnEmbeddable(){
        assertNotNull(Pokemon.class.getAnnotation(Embeddable.class)); 
    }

}