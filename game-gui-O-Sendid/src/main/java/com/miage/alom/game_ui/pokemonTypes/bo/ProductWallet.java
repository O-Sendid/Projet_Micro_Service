package com.miage.alom.game_ui.pokemonTypes.bo;





public class ProductWallet {
	

    private int poke_dollar;
	

    private int id;
	
	
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
