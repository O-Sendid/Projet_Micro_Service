package com.miage.alom.game_ui.pokemonTypes.service;


import java.util.Optional;

import com.miage.alom.game_ui.pokemonTypes.bo.ProductShop;


public interface ShopService {
    Iterable<ProductShop> listProducts();
    void TrainerBuyProduct(String id,String trainer);


}
