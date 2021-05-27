package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.model.Wallet;

public class AddIncome {
    
    @FXML
    TextField amountText;
    @FXML
    ComboBox<Wallet> walletCombo;
    @FXML
    DatePicker incomDate;

    @FXML
    void addIncome() {
        
    }
}
