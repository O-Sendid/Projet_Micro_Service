package com.miage.alom.service;

import java.util.List;
import java.util.Optional;

import com.miage.alom.bo.ProductWallet;

public interface WalletService {
	
	List<ProductWallet> listWallets();
    Optional<ProductWallet> getWallet(int id);
    ProductWallet createWallet(String name);
    void deleteWallet(int id);
    Optional<ProductWallet> getWalletOfTrainer(String name);

}
