package com.miage.alom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miage.alom.bo.ProductWallet;
import com.miage.alom.service.WalletService;

@RestController
@RequestMapping(value = "/wallet")
public class WalletController {
	
	@Autowired
    WalletService walletService;
	
	
    @GetMapping
    public Iterable<ProductWallet> getAllProducts(){
    	List<ProductWallet> products=walletService.listWallets();
        return products;
    }
    
    @GetMapping("/trainer/{name}")
    public Optional<ProductWallet> getWallerOfTrainer(@PathVariable String name){
    	System.out.print(name);
        return walletService.getWalletOfTrainer(name);
    }

}
