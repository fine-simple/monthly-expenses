package main.model.dao;

import java.util.HashMap;

public class WalletDao {
    public final HashMap<String, Float> wallets;

    private static WalletDao instance;

    public static WalletDao getInstance() {
        if (instance == null)
            instance = new WalletDao();
        return instance;
    }

    private WalletDao() {
        wallets = new HashMap<>();
        wallets.put("Cash", 0f);
        wallets.put("Credit", 0f);
        wallets.put("Debit", 0f);
    }
}