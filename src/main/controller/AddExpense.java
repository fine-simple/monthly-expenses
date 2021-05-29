package main.controller;

import java.net.URL;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.model.Expenses;
import main.model.Wallet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

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
     return  wallet.getValue();
    }

    @FXML
    String chooseCategory() {
        return category.getValue();
    }
 public static Date convertToDate(LocalDate date) {
        return java.util.Date.from(date.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
    @FXML
            
    void addExpense() {
        try{        
           float value =Float.parseFloat(amount.getText());
           String tit =title.getText();
           LocalDate localdate= date.getValue();
           Date d = convertToDate(localdate);
           String wallname=chooseWallet();
           String catname=chooseCategory();       
           Wallet w = Wallet.Wall.get(wallname);      
           if(value > w.getTotal()) {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setContentText("Expenses Value is more that Wallet Value");
              alert.show();         
        }
       else
       {
            w.setTotal(w.getTotal()-value);
            Expenses e = new Expenses(tit, value, d);

            ArrayList<Expenses> temp = w.categories.get(catname);
            temp.add(e);
            w.categories.replace(catname, temp);  
            Wallet.Wall.replace(wallname, w);
        }
        for(String s: Wallet.Wall.keySet())
        {
            Wallet f= Wallet.Wall.get(s);
            f.printData(f);
               
        }     
    }
    catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("NumberFormatException "+e.getMessage());
            alert.show();
        }
        
        amount.setText(" ");
   }
      
    @Override
      public void initialize(URL location, ResourceBundle resources)
    {
        List<String> list = new ArrayList<String>();
         for(String s: Wallet.Wall.keySet())
         {
            list.add(s);
         }
         
            List<String> list2 = new ArrayList<String>();
         for(String f: Wallet.Wall.keySet())
         {
            Wallet w = Wallet.Wall.get(f);
            HashMap<String, ArrayList<Expenses>> hashmap = w.getCategories();
            for(String e: hashmap.keySet())
            {
             if(!(list2.contains(e)))
                 list2.add(e);
            }
            
         }
    
        ObservableList<String> List2 = FXCollections.observableArrayList(list2);
        category.setItems(List2);
       
       
        ObservableList<String> List = FXCollections.observableArrayList(list);
        wallet.setItems(List);
            
    }
    
       
        
  
      
}
      
