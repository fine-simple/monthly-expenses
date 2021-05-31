package main.debugging;

import java.time.LocalDate;

import main.model.Expense;
import main.model.Income;
import main.model.Wallet;
import main.model.dao.CategoryDao;
import main.model.dao.ExpenseDao;
import main.model.dao.IncomeDao;
import main.model.dao.WalletDao;

public class debugging {
    public static void printAllExpenses() {
        var list = ExpenseDao.getInstance().expenses;
        for(var i : list) {
            System.out.println("Title:");
            System.out.println(i.getTitle());
            System.out.println("Price:");
            System.out.println(i.getPrice());
            System.out.println("Date:");
            System.out.println(i.getDate().toString());
            System.out.println("Wallet:");
            System.out.println(i.getWallet() + "   " + WalletDao.getInstance().wallets.get(i.getWallet()));
        }
    }

    public static void addDebugData() {
        //* Debugging
        WalletDao.getInstance().wallets.put("debugWallet", new Wallet());
        IncomeDao.getInstance().incomes.add(new Income(10000, LocalDate.now(), "debugWallet"));
        //var income = IncomeDao.getInstance().getAll().get(0);
        ExpenseDao.getInstance().expenses
                .add(new Expense("debugExpense", 10, LocalDate.now(), "debugWallet", "debugCategory"));
        //* End Debugging
    }

    //For testing
	public static void printAllDate() {
		// System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("Wallets:");
		for (var entry : WalletDao.getInstance().wallets.entrySet()) {
			for (var wallet : entry.getValue().budget.entrySet()) {
				System.out.println(entry.getKey() + " - " + wallet.getKey() + " - " + wallet.getValue());
			}
		}

		System.out.println("-------------------------------------------------------------------\n");
		System.out.println("Income:");
		for (var entry : IncomeDao.getInstance().incomes) {
			System.out.println(entry.getDate() + " - " + entry.getValue() + " - " + entry.getWallet());
		}

		System.out.println("-------------------------------------------------------------------\n");
		System.out.println("Expenses:");
		for (var expense : ExpenseDao.getInstance().expenses) {
				System.out.println(expense.getTitle() + " - " + expense.getDate() + " - " + expense.getPrice() + " - " + expense.getCategory() + " - " + expense.getWallet());
		}

		System.out.println("-------------------------------------------------------------------\n");
		System.out.println("Categories:");
		for (var entry : CategoryDao.getInstance().categories) {
			System.out.println(entry);
		}
	}
}
