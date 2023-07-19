package com.miage.alom.tp.battle_api.bo;



import java.util.UUID;
import com.miage.alom.tp.battle_api.entity.Trainer;



public class Battle {

    private UUID uuid;
    private Trainer trainer;
    private Trainer oponent;
	
	
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Trainer getOponent() {
        return oponent;
    }

    public void setOponent(Trainer oponent) {
        this.oponent = oponent;
    }


}