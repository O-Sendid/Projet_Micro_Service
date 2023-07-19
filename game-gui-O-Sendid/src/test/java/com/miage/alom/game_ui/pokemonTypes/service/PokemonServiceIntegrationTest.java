package com.miage.alom.game_ui.pokemonTypes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

public class PokemonServiceIntegrationTest {

	
	
	@Test
	void setPokemonServiceUrl_shouldBeAnnotatedWithValue() throws NoSuchMethodException {
	    var setter = PokemonTypeServiceImpl.class.getDeclaredMethod("setPokemonTypeServiceUrl", String.class);
	    var valueAnnotation = setter.getAnnotation(Value.class); 
	    assertNotNull(valueAnnotation);
	    assertEquals("${pokemonType.service.url}", valueAnnotation.value()); 
	}
	
}
