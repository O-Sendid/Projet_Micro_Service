package com.miage.alom.tp.battle_api.service;


import org.springframework.data.util.Pair;

import com.miage.alom.tp.battle_api.bo.Battle;
import com.miage.alom.tp.battle_api.entity.PokemonType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;



public interface BattleService {

	
    HashMap<UUID, Battle> getBattles();    
    Battle getBattle(UUID uuid);
    Battle attackBattle(UUID uuid, String trainerName) throws ExceptionNotFound;
    PokemonType getPokemon(List<PokemonType> pokemons);
    UUID getUuid(String trainer,String opponent) throws ExceptionNotFound;
    
    
}
