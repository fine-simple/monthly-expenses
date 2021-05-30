package main.model;

import java.time.LocalDate;

public class Expense {
    private String title;
    private float price;
    private LocalDate date;
    private Wallet wallet;

    public Expense(String title, float price, LocalDate date, Wallet wallet) {
        this.title = title;
        this.price = price;
        this.date = date;
        this.wallet = wallet;
    }

    public Wallet getWallet() {
        return wallet;
    }
    
    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }
}