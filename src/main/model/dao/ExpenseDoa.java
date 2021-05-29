package main.model.dao;

import main.model.Expense;

public class ExpenseDoa {
    
    void addExpense(String walletName, String category, Expense expense) {
        WalletDao.getInstance().getAll().get(walletName).categories.get(category).add(expense);
    }

}