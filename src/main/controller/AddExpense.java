package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.model.Wallet;

public class AddExpense {
    @FXML
    TextField title;
    @FXML
    TextField amount;
    @FXML
    DatePicker date;
    @FXML
    ComboBox<String> category;
    @FXML
    ComboBox<Wallet> wallet;

    @FXML
    void chooseWallet() {

    }

    @FXML
    void chooseCategory() {

    }

    @FXML
    void addExpense() {
        
    }
}