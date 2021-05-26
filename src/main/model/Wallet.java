package main.model;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Wallet {
    private String name;
    private int total=0;
    ArrayList<Balance> balance = new ArrayList<Balance>();
    Map<String,ArrayList<Expenses>> categories = new TreeMap<>();
    public Wallet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }
}
