package main.model.dao;

import java.util.HashMap;

import main.model.Wallet;
public class WalletDao {
    public HashMap<String, Wallet> wallets;

    private static WalletDao instance;

    public static WalletDao getInstance() {
        if (instance == null)
            instance = new WalletDao();
        return instance;
    }

    private WalletDao() {
        wallets = new HashMap<>();
    }

    public Wallet get(String name) {
        return wallets.get("name");
    }

    public HashMap<String, Wallet> getAll() {
        return wallets;
    }

    public void add(String name) {
        wallets.put(name, new Wallet(name));
    }
}