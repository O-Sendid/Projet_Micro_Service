package com.miage.alom.tp.pokemon_type_api.bo;

import java.util.List;

public record PokemonType(
        int id,
        String name,
        Sprites sprites,
        List<String> types,
        Stats stats
) {

	public PokemonType(int id){
		this(id,null,null,null,null);
	}

}