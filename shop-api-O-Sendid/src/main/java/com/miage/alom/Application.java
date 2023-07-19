package com.miage.alom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.miage.alom.bo.ProductShop;
import com.miage.alom.repository.ShopRepository;



@SpringBootApplication 
public class Application {

	
    public static void main(String... args){
        SpringApplication.run(Application.class, args); 
    }
    
    
    
    @Bean
    @Autowired
    public CommandLineRunner demo(ShopRepository shopRepository) {
        return (args) -> {

            ProductShop superBonbon=new ProductShop(5000,"SuperBonbon","Augmente le niveau d’un Pokemon !","https://www.pokewiki.de/images/a/a6/Itemsprite_Sonderbonbon_KAPU.png");
            shopRepository.save(superBonbon);
            ProductShop pokeball=new ProductShop(10000,"Pokeball","Contient un Pokemon aléatoire commun de niveau 5","https://www.pokepedia.fr/images/e/e2/Pok%C3%A9_Ball-RS.png");        
            shopRepository.save(pokeball);
            ProductShop superBall=new ProductShop(25000,"SuperBall","Contient un Pokemon aléatoire non-commun de niveau 10","https://www.pokewiki.de/images/2/2f/Sugimori_Superball.png");        
            shopRepository.save(superBall);
            ProductShop hyperball=new ProductShop(50000,"Hyperball","Contient un Pokemon aléatoire rare de niveau 20","https://www.pokepedia.fr/images/b/bd/Hyper_Ball-RS.png");        
            shopRepository.save(hyperball);
            ProductShop MasterBall=new ProductShop(100000,"MasterBall","Contient un Pokemon aléatoire légendaire (un seul achat max par dresseur) de niveau 40","https://i.pinimg.com/originals/c2/6d/2b/c26d2b5b239f966d2eab1d6015b115a9.png");        
            shopRepository.save(MasterBall);
            
            
        };
    }
}
