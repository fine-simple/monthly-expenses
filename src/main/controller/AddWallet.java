package main.controller;

import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.model.Wallet;

public class AddWallet {
    @FXML
    TextField wallet;

    @FXML
    void addWallet() {
        
       String wallname= wallet.getText();
       
       
       if(!(Wallet.Wall.containsKey(wallname))) {
           Wallet currWall = new Wallet(wallname);
            Wallet.Wall.put(wallname,currWall);
    }
       else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("This wallet already exists");
            alert.show();          
        }
       wallet.setText(" ");
    }
}
