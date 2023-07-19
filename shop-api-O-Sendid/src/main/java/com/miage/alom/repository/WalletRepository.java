package com.miage.alom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.miage.alom.bo.ProductWallet;




@Repository
public interface WalletRepository extends CrudRepository<ProductWallet, Integer> {

}
