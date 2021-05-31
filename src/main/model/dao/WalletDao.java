package main.model.dao;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;

import main.model.Wallet;

public class WalletDao {
    public final HashMap<String, Wallet> wallets;

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

    public void addToTotal(String walletName, LocalDate localDate,Float value) {
        Wallet wallet = wallets.get(walletName);
        YearMonth yearMonth = YearMonth.of(localDate.getYear(), localDate.getMonth());
		Float current = wallet.budget.getOrDefault(yearMonth, 0f);
        wallet.budget.put(yearMonth, current + value);
    }
}