package main.model.dao;

import java.util.ArrayList;

import main.model.Expense;

public class ExpenseDao {
    public final ArrayList<Expense> expenses;
    private static ExpenseDao instance;

    private ExpenseDao() {
        expenses = new ArrayList<>();
    }

    public static ExpenseDao getInstance() {
        if(instance == null)
            instance = new ExpenseDao();
        return instance;
    }
}