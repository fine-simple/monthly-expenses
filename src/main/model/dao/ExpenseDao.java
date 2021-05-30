package main.model.dao;

import java.util.ArrayList;

import main.model.Expense;

public class ExpenseDao {
    ArrayList<Expense> expenses;
    ArrayList<String> categories;
    
    private static ExpenseDao instance;

    private ExpenseDao() {

    }

    public static ExpenseDao getInstance() {
        if(instance == null)
            instance = new ExpenseDao();
        return instance;
    }

    public ArrayList<Expense> getAllExpenses() {
        return expenses;
    }
}