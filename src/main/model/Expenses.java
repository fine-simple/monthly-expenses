package main.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Expenses {
    private String title;
    private float price;
    private Date date;
     public ArrayList<Expenses> exp= new ArrayList<>();

    public Expenses(String title, float price, Date date) {
        this.title = title;
        this.price = price;
        this.date = date;
    }


    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
