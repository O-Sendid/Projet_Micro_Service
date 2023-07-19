package com.miage.alom.tp.battle_api.entity;

import java.util.List;

public class Trainer {
	
    private String name;
    private List<Pokemon> team;
    private List<PokemonType> teamtype;
    private boolean nextTurn;


	
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }


    public void setNextTurn(boolean next) {
        this.nextTurn  = next;
    }

    public Boolean getNextTurn(){
        return this.nextTurn;
    }


    public List<PokemonType> getTeamtype() {
        return teamtype;
    }

    public void setTeamtype(List<PokemonType> teamtype) {
        this.teamtype = teamtype;
    }

}