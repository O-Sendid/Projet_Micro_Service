package com.miage.alom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.miage.alom.bo.ProductShop;


@Repository
public interface ShopRepository extends CrudRepository<ProductShop, String> {

}


