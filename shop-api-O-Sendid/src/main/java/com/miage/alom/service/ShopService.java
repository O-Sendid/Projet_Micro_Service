package com.miage.alom.service;


import java.util.Optional;

import com.miage.alom.bo.ProductShop;

public interface ShopService {
    Iterable<ProductShop> listProducts();
    Optional<ProductShop> getProduct(String id);
    ProductShop createProduct(ProductShop product);
    void deleteProduct(String id);
    void TrainerBuyProduct(String id,String trainer) throws ExceptionNotFound;
}
