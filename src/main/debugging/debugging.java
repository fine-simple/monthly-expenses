package main.debugging;

import java.time.LocalDate;
import java.util.ArrayList;

import main.model.Expense;
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

	public static int getRandomNum(int min, int max) {
		return (int) Math.floor(Math.random()*(max-min+1)+min);
	}

	static ArrayList<String> EXPENSES_NAMES;
	static ArrayList<String> FULL_EXPENSES_NAMES;

	public static String getEXPENSES_NAME() {
		var y = getRandomNum(0,EXPENSES_NAMES.size()-1);
		var x = EXPENSES_NAMES.get(y);
		EXPENSES_NAMES.remove(y);
		return x;
	}

	static LocalDate getRandomDate() {
		return LocalDate.of(2020, getRandomNum(1, 12), getRandomNum(1, 28));
	}

	static String getRandomWalletName() {
		ArrayList<String> temp = new ArrayList<>();
		for(String i : WalletDao.getInstance().wallets.keySet()) {
			temp.add(i);
		}
		return temp.get(getRandomNum(0, temp.size()-1));
	}
    public static void addDebugData(int numOfFakeData) {
		// sample expenses
		EXPENSES_NAMES = new ArrayList<>();
		EXPENSES_NAMES.add("Mouse");
		EXPENSES_NAMES.add("Computer");
		EXPENSES_NAMES.add("Monitor");
		EXPENSES_NAMES.add("Mobile");
		EXPENSES_NAMES.add("SuperMarket");
		EXPENSES_NAMES.add("Vegies");
		EXPENSES_NAMES.add("TV");
		EXPENSES_NAMES.add("Coffee");
		EXPENSES_NAMES.add("Cake");
		EXPENSES_NAMES.add("Desk");
		EXPENSES_NAMES.add("HeadPhones");
		// EXPENSES_NAMES.add("");
		FULL_EXPENSES_NAMES = new ArrayList<>(EXPENSES_NAMES);
		

        // WalletDao.getInstance().wallets.put("debugWallet", new Wallet());
        // IncomeDao.getInstance().incomes.add(new Income(10000, LocalDate.now(), "debugWallet"));
        // var income = IncomeDao.getInstance().getAll().get(0);
		for (int i = 0; i < numOfFakeData; i++) {
			if(EXPENSES_NAMES.size()-1 <= 1) {
				EXPENSES_NAMES = new ArrayList<>(FULL_EXPENSES_NAMES);
			}
			String randomCategory = CategoryDao.getInstance().categories.get(getRandomNum(0, CategoryDao.getInstance().categories.size()-1));
			ExpenseDao.getInstance().expenses.add(new Expense(getEXPENSES_NAME(), getRandomNum(10, 2500), getRandomDate(), getRandomWalletName(), randomCategory));
		}
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
