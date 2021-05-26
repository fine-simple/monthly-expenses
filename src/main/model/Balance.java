package main.model;

import java.util.Date;

public class Balance {
    private int value;
    private Date date;
    public static int totalBalance=0;

    public Balance(int value, Date date) {
        this.value = value;
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
