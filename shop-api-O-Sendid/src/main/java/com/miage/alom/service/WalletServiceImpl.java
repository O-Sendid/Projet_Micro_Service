package com.miage.alom.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miage.alom.bo.Pokemon;
import com.miage.alom.bo.ProductShop;
import com.miage.alom.bo.ProductWallet;
import com.miage.alom.bo.Trainer;
import com.miage.alom.repository.ShopRepository;
import com.miage.alom.repository.WalletRepository;


@Service
public class WalletServiceImpl implements WalletService {
	@Autowired
    WalletRepository walletRepository;
	
	@Autowired
    TrainerService trainerService;
	
	
	@Override
	public Optional<ProductWallet> getWalletOfTrainer(String name) {
		return this.listWallets().stream().filter(t -> t.getTrainer_id().equals(name)).findFirst();
	}
	
    @Override
    public List<ProductWallet> listWallets() {
        return (List<ProductWallet>) walletRepository.findAll();
    }

    @Override
    public Optional<ProductWallet> getWallet(int id) {
        return walletRepository.findById(id);
    }
    

    @Override
    public ProductWallet createWallet(String name) {
    	ProductWallet wallet = new ProductWallet();
    	wallet.setPoke_dollar(10000);
    	wallet.setTrainer_id(name);
        return walletRepository.save(wallet);
    }
    
    @Override
    public void deleteWallet(int id) {
    	walletRepository.deleteById(id);
    }
    

}
