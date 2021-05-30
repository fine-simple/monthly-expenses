package main.debugging;

import java.time.LocalDate;

import main.model.Expense;
import main.model.Income;
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

    public static void addDebugData() { //! not done creates exception
        //* Debugging
        WalletDao.getInstance().wallets.put("debugWallet", 0f);
        IncomeDao.getInstance().incomes.add(new Income(10000, LocalDate.now(), "debugWallet"));
        //var income = IncomeDao.getInstance().getAll().get(0);
        ExpenseDao.getInstance().expenses
                .add(new Expense("debugExpense", 10, LocalDate.now(), "debugWallet", "debugCategory"));
        //* End Debugging
    }
}
