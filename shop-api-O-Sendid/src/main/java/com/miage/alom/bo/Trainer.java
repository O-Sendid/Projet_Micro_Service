package com.miage.alom.bo;


import javax.persistence.*;
import java.util.List;




@Embeddable
public class Trainer {

   
    private String name;
    private List<Pokemon> team; 

    
    private String password;

    public Trainer() {
    }

    public Trainer(String name, List<Pokemon> team) {
        this.name = name;
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


}