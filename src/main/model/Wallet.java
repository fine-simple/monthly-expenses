package main.model;

import java.util.ArrayList;
import java.util.HashMap;


public class Wallet {
    private String name;
    private float total = 0;
    public  ArrayList<Income> incomes;
    public HashMap<String, ArrayList<Expense>> categories;
    public static HashMap<String,Wallet> Wall = new HashMap<>();

    public Wallet(String name) {
        this.name = name;
        incomes = new ArrayList<Income>();
        categories = new HashMap<>();
    }

    public ArrayList<Income> getIncomes() {
        return incomes;
    }

    public HashMap<String, ArrayList<Expense>> getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }
   public void setTotal(float total) {
        this.total = total;
    }
    public  float getTotal() {
        return total;
    }
     public void printData(Wallet w){
        System.out.println("Wallet Name: " + w.getName());
        System.out.println("Wallet Total: " + w.getTotal());
        ArrayList<Income> inn = w.getIncomes();
        for (Income income: inn){
            System.out.println("Income Value: " + income.getValue());
            //System.out.println("Income Date: " + income.getDate());
        }
        HashMap<String, ArrayList<Expense>> cat = w.getCategories();
        for(String s: cat.keySet()) {
            ArrayList<Expense> e = cat.get(s);
            System.out.println("Category: " + s);
            for (Expense ee:e){
                System.out.println("Expenses Title: " + ee.getTitle());
                //System.out.println("Expenses Date: " + ee.getDate());
                System.out.println("Expenses Price: " + ee.getPrice());
            } 
        }
        
    }

    

     
   
}
