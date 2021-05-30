package main.model.dao;

import java.util.HashMap;

public class WalletDao {
    public HashMap<String, Float> wallets;

    private static WalletDao instance;

    public static WalletDao getInstance() {
        if (instance == null)
            instance = new WalletDao();
        return instance;
    }

    private WalletDao() {
        wallets = new HashMap<>();
        add("Cash");
        add("Credit");
        add("Debit");
    }

    public Float get(String name) {
        return wallets.get(name);
    }

    public HashMap<String, Float> getAll() {
        return wallets;
    }

    public void add(String name) {
        wallets.put(name, Float.valueOf(0));
    }
}