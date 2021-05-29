package main.controller;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.model.Expenses;
import main.model.Wallet;

public class AddCategory {
    @FXML
    TextField category;

    @FXML
    void addCategory() {
        String name = category.getText();
        
        if(Wallet.Wall.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You need to add a Wallet First");
            alert.show();  
        }
        else{
            for(String walletName: Wallet.Wall.keySet()) {
            Wallet temp = Wallet.Wall.get(walletName);
            ArrayList<Expenses> exp= new ArrayList<>();
            temp.categories.put(name, exp);
            Wallet.Wall.replace(walletName, temp);
        }
        }
        category.setText(" ");
    }
}
