package main.model;

import java.util.Date;

public class Balance {
    private int value;
    Date date;
    static int totalBalance=0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



    public static int getTotalBalance() {
        return totalBalance;
    }

    public static void setTotalBalance(int totalBalance) {
        Balance.totalBalance = totalBalance;
    }
}
