package com.miage.alom.tp.pokemon_type_api.service;

import com.miage.alom.tp.pokemon_type_api.bo.PokemonType;
import com.miage.alom.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.alom.tp.pokemon_type_api.repository.TranslationRepository;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Locale;

class PokemonTypeServiceImplTest {

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindById(){
        var pokemonTypeRepository = mock(PokemonTypeRepository.class); 
        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository); 

        pokemonTypeService.getPokemonType(25,null);

        verify(pokemonTypeRepository).findPokemonTypeById(25);
    }

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindAll(){
        var pokemonTypeRepository = mock(PokemonTypeRepository.class); 
        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository); 

        pokemonTypeService.getAllPokemonTypes(null);

        verify(pokemonTypeRepository).findAllPokemonTypes();
    }
    
    @Test
    void applicationContext_shouldLoadPokemonTypeService(){
        var context = new AnnotationConfigApplicationContext("com.miage.alom.tp.pokemon_type_api");
        var serviceByName = context.getBean("pokemonTypeServiceImpl");
        var serviceByClass = context.getBean(PokemonTypeService.class);

        assertEquals(serviceByName, serviceByClass);
        assertNotNull(serviceByName);
        assertNotNull(serviceByClass);
    }

    @Test
    void pokemonTypeRepository_shouldBeAutowired_withSpring(){
        var context = new AnnotationConfigApplicationContext("com.miage.alom.tp.pokemon_type_api");
        var service = context.getBean(PokemonTypeServiceImpl.class);
        assertNotNull(service.pokemonTypeRepository);
    }
    @Test
    void pokemonNames_shouldBeTranslated_usingLocaleResolver(){
        var pokemonTypeService = new PokemonTypeServiceImpl();

        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        pokemonTypeService.setPokemonTypeRepository(pokemonTypeRepository);
        when(pokemonTypeRepository.findPokemonTypeById(25)).thenReturn(new PokemonType(25));

        var translationRepository = mock(TranslationRepository.class);
        pokemonTypeService.setTranslationRepository(translationRepository);
        when(translationRepository.getPokemonName(25, Locale.FRENCH)).thenReturn("Pikachu-FRENCH");

        LocaleContextHolder.setLocale(Locale.FRENCH);

        var pikachu = pokemonTypeService.getPokemonType(25,null);

        assertEquals("Pikachu-FRENCH", translationRepository.getPokemonName(pikachu.id(), Locale.FRENCH));
        verify(translationRepository).getPokemonName(25, Locale.FRENCH);
    }

    @Test
    void allPokemonNames_shouldBeTranslated_usingLocaleResolver(){
        var pokemonTypeService = new PokemonTypeServiceImpl();

        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        pokemonTypeService.setPokemonTypeRepository(pokemonTypeRepository);

        var pikachu = new PokemonType(25);
        var raichu = new PokemonType(26);
        when(pokemonTypeRepository.findAllPokemonTypes()).thenReturn(List.of(pikachu, raichu));

        var translationRepository = mock(TranslationRepository.class);
        pokemonTypeService.setTranslationRepository(translationRepository);
        when(translationRepository.getPokemonName(25, Locale.FRENCH)).thenReturn("Pikachu-FRENCH");
        when(translationRepository.getPokemonName(26, Locale.FRENCH)).thenReturn("Raichu-FRENCH");

        LocaleContextHolder.setLocale(Locale.FRENCH);

        var pokemonTypes = pokemonTypeService.getAllPokemonTypes(null);

        assertEquals("Pikachu-FRENCH", translationRepository.getPokemonName(pokemonTypes.get(0).id(), Locale.FRENCH));
        assertEquals("Raichu-FRENCH", translationRepository.getPokemonName(pokemonTypes.get(1).id(), Locale.FRENCH));;
        verify(translationRepository).getPokemonName(25, Locale.FRENCH);
        verify(translationRepository).getPokemonName(26, Locale.FRENCH);
    }

}