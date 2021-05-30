package main.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
import main.model.Expense;
import main.model.Wallet;

public class AddExpense implements Initializable {
    @FXML
    TextField title;
    @FXML
    TextField amount;
    @FXML
    DatePicker date;
    @FXML
    ComboBox<String> category;
    @FXML
    ComboBox<String> wallet;

    @FXML
    String chooseWallet() {
        return wallet.getValue();
    }

    @FXML
    String chooseCategory() {
        return category.getValue();
    }

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
             amount.setText(" ");
            return;
        }
            
             try{
                 tit= title.getText();
             }
           catch(Exception e){
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
            
            String wallname = chooseWallet();
            String catname = chooseCategory();
            Wallet w = Wallet.Wall.get(wallname);
            
            if (value > w.getTotal()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Expenses Value is more that Wallet Value");
                alert.show();
            } else {
                w.setTotal(w.getTotal() - value);
                Expense e = new Expense(tit, value, localdate);

                ArrayList<Expense> temp = w.categories.get(catname);
                temp.add(e);
            }
            for (String s : Wallet.Wall.keySet()) {
                Wallet f = Wallet.Wall.get(s);
                f.printData(f);

            }
            amount.setText(" ");
        }

        
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> list = new ArrayList<String>();
        for (String s : Wallet.Wall.keySet()) {
            list.add(s);
        }

        List<String> list2 = new ArrayList<String>();
        for (String f : Wallet.Wall.keySet()) {
            Wallet w = Wallet.Wall.get(f);
            HashMap<String, ArrayList<Expense>> hashmap = w.getCategories();
            for (String e : hashmap.keySet()) {
                if (!(list2.contains(e)))
                    list2.add(e);
            }

        }

        ObservableList<String> List2 = FXCollections.observableArrayList(list2);
        category.setItems(List2);

        ObservableList<String> List = FXCollections.observableArrayList(list);
        wallet.setItems(List);
        date.setValue(LocalDate.now());

    }

}
