package main.model.dao;

import java.time.YearMonth;
import java.util.ArrayList;

import main.model.Expense;
import main.model.Wallet;

public class ExpenseDao {
    public final ArrayList<Expense> expenses;
    private static ExpenseDao instance;

    private ExpenseDao() {
        expenses = new ArrayList<>() {
            public boolean add(Expense expense) {
                super.add(expense);
                WalletDao.getInstance().addToTotal(expense.getWallet(), YearMonth.from(expense.getDate()),
                        -expense.getPrice());
                Wallet wall= WalletDao.getInstance().wallets.get(expense.getWallet());
                 wall.total-=expense.getPrice();
                return true;
            };
        };
    }

    public static ExpenseDao getInstance() {
        if (instance == null)
            instance = new ExpenseDao();
        return instance;
    }
}