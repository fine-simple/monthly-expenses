package main.model.dao;

import java.time.YearMonth;
import java.util.ArrayList;

import main.model.Income;
import main.model.Wallet;

public class IncomeDao {
    public final ArrayList<Income> incomes;

    private static IncomeDao instance;

    private IncomeDao() {
        incomes = new ArrayList<>() {
            public boolean add(Income income) {
                super.add(income);
                WalletDao.getInstance().addToTotal(income.getWallet(), YearMonth.from(income.getDate()), income.getValue());
                 Wallet wall= WalletDao.getInstance().wallets.get(income.getWallet());
                  wall.total+=income.getValue();
                return true;
            };
        };
    }

    public static IncomeDao getInstance() {
        if(instance == null)
            instance = new IncomeDao();
        return instance;
    }

}