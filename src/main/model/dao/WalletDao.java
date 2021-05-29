package main.model.dao;

import java.util.HashMap;

import main.model.Wallet;

public class WalletDao {
    public HashMap<String,Wallet> wallets = new HashMap<>();
    
    private static WalletDao instance;

    public static WalletDao getInstance() {
        if(instance == null)
            instance = new WalletDao();
        return instance;
    }
    
    private WalletDao() {

    }

    Wallet get(String name) {
        return wallets.get("name");
    }

    HashMap<String, Wallet> getAll() {
        return wallets;
    }

    void add(Wallet wallet) {
        wallets.put(wallet.getName(), wallet);
    }
}