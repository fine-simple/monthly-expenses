package main.debugging;

import java.time.LocalDate;
import java.util.ArrayList;

import main.model.Expense;
import main.model.Income;
import main.model.dao.ExpenseDao;
import main.model.dao.IncomeDao;
import main.model.dao.WalletDao;

public class debugging {
    public static void printAllExpenses() {
        var list = ExpenseDao.getInstance().getAll();
        for(var i : list) {
            System.out.println("Title:");
            System.out.println(i.getTitle());
            System.out.println("Price:");
            System.out.println(i.getPrice());
            System.out.println("Date:");
            System.out.println(i.getDate().toString());
            System.out.println("Wallet:");
            System.out.println(i.getWallet().getName() + "   " + i.getWallet().total);
        }
    }

    public static void addDebugData() { //! not done creates exception
        //* Debugging
        WalletDao.getInstance().add("debugWallet");
        var wallet = WalletDao.getInstance().get("debugWallet");
        IncomeDao.getInstance().add(new Income(10000, LocalDate.now(), wallet));
        //var income = IncomeDao.getInstance().getAll().get(0);
        ExpenseDao.getInstance().add(new Expense("debugExpense", 10, LocalDate.now(), wallet), "debugCategory");
        //* End Debugging
    }
}
