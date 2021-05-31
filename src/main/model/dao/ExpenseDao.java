package main.model.dao;

import java.time.YearMonth;
import java.util.ArrayList;

import main.model.Expense;

public class ExpenseDao {
    public final ArrayList<Expense> expenses;
    private static ExpenseDao instance;

    private ExpenseDao() {
        expenses = new ArrayList<>() {
            public boolean add(Expense expense) {
                super.add(expense);
                WalletDao.getInstance().addToTotal(expense.getWallet(), YearMonth.from(expense.getDate()), -expense.getPrice());
                return true;
            };
        };
        // TODO: Add Sample Data
        //Sample Data
        
    }

    public static ExpenseDao getInstance() {
        if(instance == null)
            instance = new ExpenseDao();
        return instance;
    }
}