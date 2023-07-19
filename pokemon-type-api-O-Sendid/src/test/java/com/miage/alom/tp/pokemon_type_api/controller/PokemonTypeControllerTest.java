package com.miage.alom.tp.pokemon_type_api.controller;

import com.miage.alom.tp.pokemon_type_api.bo.PokemonType;
import com.miage.alom.tp.pokemon_type_api.service.PokemonTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

class PokemonTypeControllerTest {

    @Test
    void getPokemonType_shouldCallTheService(){
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        var pikachu = new PokemonType(25, "pikachu", null, List.of(),null);
        when(service.getPokemonType(25,null)).thenReturn(pikachu);

        var pokemon = controller.getPokemonTypeFromId(25,null);
        assertEquals("pikachu", pokemon.name());

        verify(service).getPokemonType(25,null);
    }

    @Test
    void getAllPokemonTypes_shouldCallTheService(){
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        controller.getAllPokemonTypes(null);

        verify(service).getAllPokemonTypes(null);
    }
    
    @Test
    void pokemonTypeController_shouldBeAnnotated(){
        var controllerAnnotation =
                PokemonTypeController.class.getAnnotation(RestController.class);
        assertNotNull(controllerAnnotation);

        var requestMappingAnnotation =
                PokemonTypeController.class.getAnnotation(RequestMapping.class);
        assertArrayEquals(new String[]{"/pokemon-types"}, requestMappingAnnotation.value());
    }
    
    
    @Test
    void getPokemonTypeFromId_shouldBeAnnotated() throws NoSuchMethodException {
        var getPokemonTypeFromId =
                PokemonTypeController.class.getDeclaredMethod("getPokemonTypeFromId", int.class , HttpHeaders.class);
        var getMapping = getPokemonTypeFromId.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/{id}"}, getMapping.value());
    }

    @Test
    void getAllPokemonTypes_shouldBeAnnotated() throws NoSuchMethodException {
        var getAllPokemonTypes =
                PokemonTypeController.class.getDeclaredMethod("getAllPokemonTypes",HttpHeaders.class);
        var getMapping = getAllPokemonTypes.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/"}, getMapping.value());
    }

}