package main.model.dao;

import java.util.ArrayList;

import main.model.Income;

public class IncomeDao {
    public final ArrayList<Income> incomes;

    private static IncomeDao instance;

    private IncomeDao() {
        incomes = new ArrayList<>();
    }

    public static IncomeDao getInstance() {
        if(instance == null)
            instance = new IncomeDao();
        return instance;
    }
}