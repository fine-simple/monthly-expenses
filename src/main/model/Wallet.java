package main.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Wallet {
    private String name;
    private int total = 0;
    private ArrayList<Income> incomes;
    private HashMap<String, ArrayList<Expenses>> categories;

    public Wallet(String name) {
        this.name = name;
        incomes = new ArrayList<Income>();
        categories = new HashMap<>();
    }

    public ArrayList<Income> getIncomes() {
        return incomes;
    }

    public HashMap<String, ArrayList<Expenses>> getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }
}
