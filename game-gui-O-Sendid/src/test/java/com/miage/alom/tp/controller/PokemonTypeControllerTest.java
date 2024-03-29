package com.miage.alom.tp.controller;

import com.miage.alom.game_ui.controller.PokemonTypeController;
import com.miage.alom.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.alom.game_ui.pokemonTypes.bo.Sprites;
import com.miage.alom.game_ui.pokemonTypes.bo.Stats;
import com.miage.alom.game_ui.pokemonTypes.service.PokemonTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokemonTypeControllerTest {
    @Test
    void controllerShouldBeAnnotated(){
        assertNotNull(PokemonTypeController.class.getAnnotation(Controller.class));
    }

    @Test
    void pokemons_shouldReturnAModelAndView() {
        var pokemonTypeService = mock(PokemonTypeService.class);

        var pikachu = new PokemonType(21, 21, 21, "z", null, null, 21,null );

        when(pokemonTypeService.listPokemonsTypes()).thenReturn(List.of(pikachu));

        var pokemonTypeController = new PokemonTypeController();
        pokemonTypeController.setPokemonTypeService(pokemonTypeService);
        var modelAndView = pokemonTypeController.pokedex();

        assertEquals("pokedex", modelAndView.getViewName());
        var pokemons = (List<PokemonType>)modelAndView.getModel().get("pokemonTypes");
        assertEquals(1, pokemons.size());
        verify(pokemonTypeService).listPokemonsTypes();
    }

    @Test
    void pokemons_shouldBeAnnotated() throws NoSuchMethodException {
        var pokemonsMethod = PokemonTypeController.class.getDeclaredMethod("pokedex");
        var getMapping = pokemonsMethod.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/pokedex"}, getMapping.value());
    }


}