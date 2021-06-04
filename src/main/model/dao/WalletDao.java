package main.model.dao;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

import main.model.Wallet;

public class WalletDao {
    public final Map<String, Wallet> wallets;

    private static WalletDao instance;
    public static WalletDao getInstance() {
        if (instance == null)
            instance = new WalletDao();
        return instance;
    }

    private WalletDao() {
        wallets = new HashMap<>();
        wallets.put("Cash", new Wallet());
        wallets.put("Credit", new Wallet());
        wallets.put("Debit", new Wallet());
    }

    public void addToTotal(String walletName, YearMonth yearMonth,Float value) {
        Wallet wallet = wallets.get(walletName);
		Float current = wallet.budget.getOrDefault(yearMonth, 0f);
        wallet.budget.put(yearMonth, current + value);
        
    }
    public float gettotal(String walletName)
    {
        Wallet wallet= WalletDao.getInstance().wallets.get(walletName);
        return wallet.total;       
    }
   
}