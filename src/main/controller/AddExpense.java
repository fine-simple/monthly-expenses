package main.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.debugging.debugging;
import main.model.Expense;
import main.model.dao.ExpenseDao;
import main.model.dao.WalletDao;

public class AddExpense implements Initializable {
    @FXML
    TextField title;
    @FXML
    TextField amount;
    @FXML
    DatePicker date;
    @FXML
    ComboBox<String> categoryCombo;
    @FXML
    ComboBox<String> walletCombo;

    @FXML
    void addExpense() {
        float value;
        String tit;
        LocalDate localdate;
        try {
            value = Float.parseFloat(amount.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("NumberFormatException " + e.getMessage());
            alert.show();
            amount.setText("");
            return;
        }

        try {
            tit = title.getText();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong Date Format");
            alert.show();
            title.setText(" ");
            return;
        }
        try {
            localdate = date.getValue();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong Date Format");
            alert.show();
            date.setValue(LocalDate.now());
            return;
        }

        String wallname = walletCombo.getValue();
        String catname = categoryCombo.getValue();
        Float wallet = WalletDao.getInstance().wallets.get(wallname);

        if (value > wallet) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Expenses Value is more that Wallet Value");
            alert.show();
            return;
        } 

        WalletDao.getInstance().wallets.put(wallname, wallet - value);
        Expense e = new Expense(tit, value, localdate, wallname);
        ExpenseDao.getInstance().add(e, catname);

        amount.setText("");
        debugging.printAllExpenses();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> allWallets = new ArrayList<String>();
        for (String s : WalletDao.getInstance().wallets.keySet()) {
            allWallets.add(s);
        }

        List<String> allCategories = new ArrayList<String>();
        
        for (String string : ExpenseDao.getInstance().categories.keySet()) {
            allCategories.add(string);
        }

        ObservableList<String> List2 = FXCollections.observableArrayList(allCategories);
        categoryCombo.setItems(List2);

        ObservableList<String> List = FXCollections.observableArrayList(allWallets);
        walletCombo.setItems(List);
        date.setValue(LocalDate.now());
    }

}