package main.model;

public class Wallet {
    public float total;
    private String name;

    public Wallet(String name) {
        this.name = name;
        total = 0;
    }

    public String getName() {
        return name;
    }
}