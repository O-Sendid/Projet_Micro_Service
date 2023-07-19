package com.miage.alom.bo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class ProductWallet {
	
	@Column
    private int poke_dollar;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
    private String trainer_id;

    public ProductWallet(int poke_dollar, int id, String trainer_id) {
        this.poke_dollar = poke_dollar;
        this.id = id;
        this.trainer_id = trainer_id;
    }

    public ProductWallet() {
    }

	public int getPoke_dollar() {
		return poke_dollar;
	}

	public void setPoke_dollar(int poke_dollar) {
		this.poke_dollar = poke_dollar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTrainer_id() {
		return trainer_id;
	}

	public void setTrainer_id(String trainer_id) {
		this.trainer_id = trainer_id;
	}
   

}
