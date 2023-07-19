package com.miage.alom.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.miage.alom.bo.ProductShop;
import com.miage.alom.bo.Trainer;
import com.miage.alom.service.ExceptionNotFound;
import com.miage.alom.service.ShopService;
import com.miage.alom.service.TrainerService;


@RestController
@RequestMapping(value = "/shop")
public class ShopController {
	
	
	@Autowired
	ShopService shopService;
	
	@Autowired
    TrainerService trainerService;



    @GetMapping
    public Iterable<ProductShop> getAllProducts(){
        Iterable<ProductShop> products=shopService.listProducts();
        return products;
    }

    @PostMapping(value = "/{trainer}/buy/{id}") 
    public void BuyProduct(@PathVariable String id,@PathVariable String trainer) throws ExceptionNotFound {
    	 shopService.TrainerBuyProduct(id, trainer); 
   
    }
    



}
