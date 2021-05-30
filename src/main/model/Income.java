package main.model;

import java.time.LocalDate;

public class Income {
    private float value;
    private LocalDate date;
    private static long totalBalance=0;
    private Wallet wallet;

    public Income(float value, LocalDate date, Wallet wallet) {
        this.value = value;
        this.date = date;
        this.wallet = wallet;
        wallet.setTotal(wallet.getTotal() + value);
        totalBalance += value;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public float getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public static long getTotalBalance() {
        return totalBalance;
    }
}
