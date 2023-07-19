package com.miage.alom.game_ui.pokemonTypes.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(PokemonTypeServiceImpl.class)
@AutoConfigureWebClient(registerRestTemplate = true)
@TestPropertySource(properties = "pokemonType.service.url=http://localhost:8080")
class PokemonTypeServiceIntegrationTest {

    @Autowired
    PokemonTypeService pokemonTypeService;

    @Autowired
    MockRestServiceServer server;

    @Autowired
    PokemonTypeService service;

    @Autowired
    RestTemplate restTemplate;

    @Test
    void serviceAndTemplateShouldNotBeNull(){
        assertNotNull(service);
        assertNotNull(restTemplate);
    }

    @Test
    void listPokemonsTypes_shouldCallTheRemoteService() {
        // given
        var response = """
                       [
                           {
                               "id": 151,
                               "name": "mew",
                               "types": ["psychic"]
                           }
                       ]
                       """;
        server.expect(requestTo("http://localhost:8080/pokemon-types/"))
                .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));

        var pokemons = pokemonTypeService.listPokemonsTypes();
        assertThat(pokemons).hasSize(1);
    }

    @Test
    void pokemonServiceImpl_shouldBeAnnotatedWithService(){
        assertNotNull(PokemonTypeServiceImpl.class.getAnnotation(Service.class));
    }

    @Test
    void setRestTemplate_shouldBeAnnotatedWithAutowired() throws NoSuchMethodException {
        var setRestTemplateMethod = PokemonTypeServiceImpl.class.getDeclaredMethod("setRestTemplate", RestTemplate.class);
        assertNotNull(setRestTemplateMethod.getAnnotation(Autowired.class));
    }

}
