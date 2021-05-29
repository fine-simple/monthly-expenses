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
import main.model.Income;
import main.model.Wallet;

public class AddIncome implements Initializable {

    @FXML
    TextField amountText;
    @FXML
    ComboBox<String> walletCombo;
    @FXML
    DatePicker incomeDate;

    @FXML
    void addIncome() {

        float value;
        try {
            value = Float.parseFloat(amountText.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong Number Format");
            alert.show();
            amountText.setText("");
            return;
        }

        LocalDate localdate;
        try {
            localdate = incomeDate.getValue();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong Date Format");
            alert.show();
            incomeDate.setValue(LocalDate.now());
            return;
        }

        Wallet wallet;
        try {
            wallet = Wallet.Wall.get(walletCombo.getValue());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Choose Wallet");
            alert.show();
            amountText.setText("");
            return;
        }

        Income income = new Income(value, localdate);
        wallet.incomes.add(income);
        wallet.setTotal(wallet.getTotal() + value);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> list = new ArrayList<String>();
        for (String s : Wallet.Wall.keySet()) {
            list.add(s);
        }
        ObservableList<String> List2 = FXCollections.observableArrayList(list);
        walletCombo.setItems(List2);
        incomeDate.setValue(LocalDate.now());
    }
}