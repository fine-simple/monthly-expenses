package main.model;

import java.time.LocalDate;
import main.model.dao.WalletDao;

public class Income {
    private float value;
    private LocalDate date;
    private String wallet;
    public Income(float value, LocalDate date, String wallet) {
        this.value = value;
        this.date = date;
        this.wallet = wallet;
      
        
    }

    public float getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getWallet() {
        return wallet;
    }
}
