package com.miage.alom.game_ui.pokemonTypes.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.alom.game_ui.pokemonTypes.bo.ProductWallet;
import com.miage.alom.game_ui.pokemonTypes.bo.Trainer;




@Service
public class WalletServiceImpl implements WalletService {
	
	
	
	
	@Autowired
    RestTemplate restTemplate;
	
	String shopServiceUrl;
	
	
	
	@Override
	public ProductWallet getWalletOfTrainer(String string) {
		ProductWallet wallet = this.restTemplate.getForObject(this.shopServiceUrl+"/wallet/trainer/"+string, ProductWallet.class);
	    return wallet;
	}
	
    @Autowired  
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Autowired
    @Value("${shop.service.url}")
    void setPokemonTypeServiceUrl(String shopServiceUrl) {
         this.shopServiceUrl= shopServiceUrl;
    }
}
