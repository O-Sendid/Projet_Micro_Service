package com.miage.alom.tp.battle_api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.miage.alom.tp.battle_api.bo.Battle;
import com.miage.alom.tp.battle_api.entity.PokemonType;
import com.miage.alom.tp.battle_api.entity.Trainer;
import java.util.List;
import java.util.UUID;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;




@Service
public class BattleServiceImpl implements BattleService {
	
	private HashMap<UUID, Battle>  battles=new HashMap<UUID,Battle>();
 
	@Autowired
    TrainerService trainerService;
	@Autowired
	PokemonTypeService pokemonTypeService;


	
	
    public HashMap<UUID, Battle> getBattles() {
    	
        return battles;
        
    }
    
    public Battle getBattle(UUID uuid) {
    	
        return this.battles.get(uuid);
        
    }



    public UUID getUuid(String trainer,String opponent) throws ExceptionNotFound {
    	
        Battle newBattle= new Battle();
        UUID uuid= UUID.randomUUID();
        newBattle.setUuid(uuid);
        Trainer trainers_t = trainerService.getTrainer(trainer);
        trainers_t.setTeamtype(pokemonTypeService.listPokemon(trainers_t));
        newBattle.setTrainer(trainers_t);
        Trainer trainers_oppent=trainerService.getTrainer(opponent);
        trainers_oppent.setTeamtype(pokemonTypeService.listPokemon(trainers_oppent));
        newBattle.setOponent(trainers_oppent);
        newBattle.getTrainer().setNextTurn(true);
        newBattle.getOponent().setNextTurn(false);
        this.battles.put(uuid,newBattle);
        return uuid;

    }


    public Battle attackBattle(UUID uuid, String trainerName) throws ExceptionNotFound {

       Battle current_battle=this.battles.get(uuid);
       Trainer attack = current_battle.getTrainer().getName().equals(trainerName) ? current_battle.getTrainer() : current_battle.getOponent();
       Trainer defense = current_battle.getTrainer().getName().equals(trainerName) ? current_battle.getOponent() : current_battle.getTrainer();
       List<PokemonType> attackTeam=attack.getTeamtype();
       List<PokemonType> defenseTeam=defense.getTeamtype();
       PokemonType attackPokememon = this.getPokemon(attackTeam);
       PokemonType defensePokemon = this.getPokemon(defenseTeam);
       
       if( attackPokememon == null || defensePokemon == null ){
    	   throw new ExceptionNotFound();
    	   }

        if(!attack.getNextTurn()){ 
        	throw new ExceptionNotFound();
        }

       int score = 2+((2*attackPokememon.getLevel()/ 5+ (2*(attackPokememon.getStats().getAttack()/defensePokemon.getStats().getDefense()))));
       int defenseHp=defensePokemon.getStats().getHp();
       int total = defenseHp-score;
	   defensePokemon.getStats().setHp(total <= 0 ? 0 : total);
	   defensePokemon.setAlive(total <= 0 ? false : true);
	   attack.setNextTurn(false);
	   defense.setNextTurn(true);
	   
	    return current_battle;

    }


	public PokemonType getPokemon(List<PokemonType> pokemons){
	        for(PokemonType pok:pokemons) {
	            if (pok.getAlive())
	                return pok;
	        }
	        return null;
	}





}
