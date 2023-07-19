package com.miage.alom.game_ui.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.miage.alom.game_ui.pokemonTypes.bo.ProductShop;
import com.miage.alom.game_ui.pokemonTypes.bo.ProductWallet;
import com.miage.alom.game_ui.pokemonTypes.service.ShopService;
import com.miage.alom.game_ui.pokemonTypes.service.WalletService;

@RestController
@RequestMapping(value = "/shop")
public class ShopController {
	
	@Autowired
	ShopService shopService;
	
	@Autowired
	WalletService walletService;
	
	
	   @GetMapping
	    public ModelAndView getAllProducts(Principal principal){
		    var modelAndView = new ModelAndView("shop");
	        Iterable<ProductShop> products=shopService.listProducts();
	        ProductWallet wallet = walletService.getWalletOfTrainer(principal.getName());
	        int poke_dollar;
	        if(wallet == null) {poke_dollar=10000;} 
	        else
	        {
	        	poke_dollar = wallet.getPoke_dollar();
	        }
		    modelAndView.addObject("trainerName", principal.getName());
		    modelAndView.addObject("poke_dollar", poke_dollar);
		    modelAndView.addObject("product", products);
	        return modelAndView;
	    }
	   
	    @GetMapping(value = "/{trainer}/buy/{id}") 
	    public RedirectView BuyProduct(Principal principal,@PathVariable String id,@PathVariable String trainer) {
	        ProductWallet wallet = walletService.getWalletOfTrainer(principal.getName());
	        var modelAndView = new ModelAndView("shop");   
	        if(wallet != null ) {if(wallet.getPoke_dollar() == 0) return new RedirectView("/shop");}
    	    modelAndView.addObject("trainerName", principal.getName());
	    	shopService.TrainerBuyProduct(id, trainer); 
	    	return new RedirectView("/shop");
	        
	    }
	    


}
