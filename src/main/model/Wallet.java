package main.model;
import java.util.ArrayList;

public class Wallet {
    String name;
    private int total=0;
    ArrayList<Balance> balance;
    ArrayList<Expenses> expenses;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
