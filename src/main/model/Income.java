package main.model;

import java.util.Date;

public class Income {
    private float value;
    private Date date;
    public static int totalBalance=0;

    public Income(float value, Date date) {
        this.value = value;
        this.date = date;
    }

    public float getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }
}
