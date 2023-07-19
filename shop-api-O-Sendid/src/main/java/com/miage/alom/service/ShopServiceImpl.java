package com.miage.alom.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miage.alom.bo.Pokemon;
import com.miage.alom.bo.ProductShop;
import com.miage.alom.bo.ProductWallet;
import com.miage.alom.bo.Trainer;
import com.miage.alom.repository.ShopRepository;
import com.miage.alom.repository.WalletRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class ShopServiceImpl implements ShopService{
	
	@Autowired
    ShopRepository shopRepository;
	
	@Autowired
    TrainerService trainerService;
	
	@Autowired
    WalletService walletService;
	
	@Autowired
    WalletRepository walletRepository;
	
	ArrayList<Integer> pokemons_communs = new ArrayList<Integer>(
            Arrays.asList (10,13,16,19,41,133,48,43,129,96,52,21,69,46,98,116));
	ArrayList<Integer> pokemons_non_communs = new ArrayList<Integer>(
            Arrays.asList (35,32,29,23,104,118,60,90,39,81,92,102,79,54,124,120,72,132));    
	ArrayList<Integer> pokemons_rares = new ArrayList<Integer>(
            Arrays.asList (147,58,74,95,77,37,109,27,126,63,25,125,66,88,111,100,108,123,127,114,138,140)); 
	ArrayList<Integer> pokemons_legendaires = new ArrayList<Integer>(
            Arrays.asList (144,145,146,150,151)); 
	
     Random randomGenerator;

	
    @Override
    public void TrainerBuyProduct(String id,String trainer)  throws ExceptionNotFound {
    	int index,item;
    	Pokemon pok;
    	List<Pokemon> team;
    	randomGenerator = new Random();
    	Trainer trainers = trainerService.getTrainer(trainer);
    	String product = this.getProduct(id).get().getId();
    	System.out.println(trainers.getName());
    	Optional<ProductWallet> wallet = walletService.getWalletOfTrainer(trainers.getName());
    	if(wallet.isEmpty()) {
    		walletService.createWallet(trainers.getName());
    	}
    	ProductWallet wallet_trainer = walletService.getWalletOfTrainer(trainers.getName()).get();    	
    	if(this.getProduct(id).get().getPrice()  > wallet_trainer.getPoke_dollar()) {
    		 throw new ExceptionNotFound();
    	}else
    	{
    		var dollar = this.getProduct(id).get().getPrice();
    		wallet_trainer.setPoke_dollar(wallet_trainer.getPoke_dollar() - dollar);
    		System.out.println(wallet_trainer.getPoke_dollar());
    		walletRepository.save(wallet_trainer);
    		switch(product){
     	   
            case "SuperBonbon": 
            	System.out.println("SuperBonbon");
                index = randomGenerator.nextInt(trainers.getTeam().size());
                var teamOfTrainer = trainers.getTeam();
                Pokemon item1 = teamOfTrainer.get(index);                      
            	item1.setLevel(item1.getLevel()+1);
            	teamOfTrainer.set(index, item1);
            	trainers.setTeam(teamOfTrainer);
            	trainerService.updateTrainer(trainers);
                break;
        
            case "Pokeball":
            	System.out.println("Pokeball"); 
                 index = randomGenerator.nextInt(this.pokemons_communs.size());
                 item = pokemons_communs.get(index);             
                 pok = new Pokemon(item,5);
                 team =trainers.getTeam();
                 team.add(pok);
                 trainers.setTeam(team);
                 trainerService.updateTrainer(trainers);
                 
                break;
        
            case "SuperBall":
                System.out.println("SuperBall");
                index = randomGenerator.nextInt(this.pokemons_non_communs.size());
                item = pokemons_non_communs.get(index);
                pok = new Pokemon(item,10);
                team =trainers.getTeam();
                team.add(pok);
                trainers.setTeam(team);
                trainerService.updateTrainer(trainers);
                
                break;
            case "Hyperball":
                System.out.println("Hyperball");
                index = randomGenerator.nextInt(this.pokemons_rares.size());
                item = pokemons_rares.get(index);
                pok = new Pokemon(item,20);
                team =trainers.getTeam();
                team.add(pok);
                trainers.setTeam(team);
                trainerService.updateTrainer(trainers);
                break;
            case "MasterBall":
                System.out.println("MasterBall");
                index = randomGenerator.nextInt(this.pokemons_legendaires.size());
                item = pokemons_legendaires.get(index);
                pok = new Pokemon(item,40);
                team =trainers.getTeam();
                team.add(pok);
                trainers.setTeam(team);
                trainerService.updateTrainer(trainers);
                break;
            default:
                System.out.println("Choix incorrect");
                break;
        }
    		
    	}
    	
    	
    	
    	
    	
    
    	
    	
    	
    }

	
	
    @Override
    public Iterable<ProductShop> listProducts() {
        return shopRepository.findAll();
    }

    @Override
    public Optional<ProductShop> getProduct(String id) {
        return shopRepository.findById(id);
    }
    
 

    @Override
    public ProductShop createProduct(ProductShop product) {
        return shopRepository.save(product);
    }
    @Override
    public void deleteProduct(String id) {
         shopRepository.deleteById(id);
    }
    
}
