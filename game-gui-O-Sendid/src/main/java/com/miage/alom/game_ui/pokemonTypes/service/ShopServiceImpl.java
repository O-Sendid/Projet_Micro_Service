package com.miage.alom.game_ui.pokemonTypes.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.alom.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.alom.game_ui.pokemonTypes.bo.ProductShop;
import com.miage.alom.game_ui.pokemonTypes.bo.Trainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;


@Service
public class ShopServiceImpl implements ShopService{
	
	@Autowired
    RestTemplate restTemplate;
	
	String shopServiceUrl;
	
    public Iterable<ProductShop> listProducts() {
    	var tmp = restTemplate.getForObject(this.shopServiceUrl + "/shop",ProductShop[].class);
    	 return Arrays.stream(tmp).toList();    	
    }
    
    public void TrainerBuyProduct(String id,String trainer) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Map < String, String > params = new HashMap < String, String > ();
        params.put("id", id);
        params.put("trainer", trainer);

        this.restTemplate.exchange(this.shopServiceUrl+"/shop/{trainer}/buy/{id}",HttpMethod.POST,
        		entity,
        		String.class,
        		trainer,
        	    id);
    }
    

	

    
    @Autowired  
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Autowired
    @Value("${shop.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
         this.shopServiceUrl= pokemonServiceUrl;
    }
}
