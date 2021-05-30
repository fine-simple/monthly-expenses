package main.model;

import java.time.LocalDate;

public class Expense {
    private String title;
    private float price;
    private LocalDate date;
    private String wallet;

    public Expense(String title, float price, LocalDate date, String wallet) {
        this.title = title;
        this.price = price;
        this.date = date;
        this.wallet = wallet;
    }

    public String getWallet() {
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