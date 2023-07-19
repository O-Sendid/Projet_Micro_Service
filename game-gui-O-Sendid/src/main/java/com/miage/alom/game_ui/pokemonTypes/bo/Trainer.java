package com.miage.alom.game_ui.pokemonTypes.bo;


import java.util.List;








public class Trainer {
	
	
	
    private List<Pokemon> team;	
    private List<PokemonType> pokemonTypes;
    private String name;
    private String password;



    public Trainer() {
    }

    
    public Trainer(String name,String password,List<Pokemon> team) {
        this.name = name;
        this.password = password;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public List<PokemonType> getPokemonTypes() {
        return pokemonTypes;
    }

    public void setPokemonTypes(List<PokemonType> pokemonTypes) {
        this.pokemonTypes = pokemonTypes;
    }
}
