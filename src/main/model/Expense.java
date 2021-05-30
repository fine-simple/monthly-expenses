package main.model;

import java.time.LocalDate;

public class Expense {
    private String title;
    private float price;
    private LocalDate date;

    public Expense(String title, float price, LocalDate date) {
        this.title = title;
        this.price = price;
        this.date = date;
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