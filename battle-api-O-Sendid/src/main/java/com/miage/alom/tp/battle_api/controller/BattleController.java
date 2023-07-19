package com.miage.alom.tp.battle_api.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miage.alom.tp.battle_api.bo.Battle;
import com.miage.alom.tp.battle_api.service.BattleService;
import com.miage.alom.tp.battle_api.service.BattleServiceImpl;
import com.miage.alom.tp.battle_api.service.ExceptionNotFound;
import org.springframework.data.util.Pair;
import java.util.UUID;




@CrossOrigin
@RestController
@RequestMapping("/battles")
public class BattleController {

    @Autowired
    BattleService battleService;
    
    
    @CrossOrigin
    @GetMapping
    public List<Battle> getBattles(){
        return new ArrayList<>(battleService.getBattles().values());

    }
    
    
    @CrossOrigin
    @GetMapping("/{uuid}")
    public Battle getBattleByUiid(@PathVariable UUID uuid){
        return battleService.getBattle(uuid);

    }

    @CrossOrigin
    @PostMapping
    public UUID setNewBattle(@RequestParam String trainer,@RequestParam  String opponent) throws ExceptionNotFound {
        return battleService.getUuid(trainer,opponent);
    }



    @CrossOrigin
    @PostMapping("/{uuid}/{trainerName}/attack")
    public Battle battleAttack(@PathVariable UUID uuid,@PathVariable String trainerName) throws ExceptionNotFound {
        return battleService.attackBattle(uuid,trainerName);
    }



}
