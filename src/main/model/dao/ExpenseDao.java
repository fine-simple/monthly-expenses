package main.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import main.model.Expense;

public class ExpenseDao {
    public HashMap<String, ArrayList<Expense>> categories;
    
    private static ExpenseDao instance;

    private ExpenseDao() {
        categories = new HashMap<>();
    }

    public static ExpenseDao getInstance() {
        if(instance == null)
            instance = new ExpenseDao();
        return instance;
    }

    public ArrayList<Expense> getAll() {
        ArrayList<Expense> expenses = new ArrayList<>();
        for (var entry : categories.values()) {
            expenses.addAll(entry);
        }
        return expenses;
    }

    public void add(Expense expense, String category) {
        categories.get(category).add(expense);
    }

    public ArrayList<Expense> getByCategory(String category) {
        return categories.get(category);
    }

    public void addCategory(String category) {
        categories.put(category, new ArrayList<>());
    }
    
    public boolean hasCategory(String category) {
        return categories.containsKey(category);
    }
}