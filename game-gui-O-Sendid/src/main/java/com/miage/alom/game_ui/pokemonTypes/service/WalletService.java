package com.miage.alom.game_ui.pokemonTypes.service;

import java.util.Optional;

import com.miage.alom.game_ui.pokemonTypes.bo.ProductWallet;



public interface WalletService {
	
    ProductWallet getWalletOfTrainer(String name);


}
