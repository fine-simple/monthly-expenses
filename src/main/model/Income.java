package main.model;

import java.time.LocalDate;

public class Income {
    private float value;
    private LocalDate date;
    private static int totalBalance=0;

    public Income(float value, LocalDate date) {
        this.value = value;
        this.date = date;
        totalBalance += value;
    }

    public float getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public static int getTotalBalance() {
        return totalBalance;
    }
}
