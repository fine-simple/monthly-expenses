package main.model;

import java.time.LocalDate;

public class Expense implements Comparable<Expense> {
    private String title;
    private float price;
    private LocalDate date;
    private Wallet wallet;

    public Expense(String title, Wallet wallet, float price, LocalDate date) {
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

    @Override
    public int compareTo(Expense o) {
        return date.compareTo(o.getDate());
    }
}