package main.controller;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import static main.controller.AddExpense.convertToDate;
import main.model.Expenses;
import main.model.Income;
import static main.model.Income.totalBalance;
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
        try{
             String ee =amountText.getText();
            float value =Float.parseFloat(ee);
              LocalDate localdate= incomeDate.getValue();
         Date d = convertToDate(localdate);
        Income e = new Income(value,d);
        String wallname = walletCombo.getValue();
       
        
        Wallet w = Wallet.Wall.get(wallname);        
        w.incomes.add(e); 
        w.setTotal(value);
        totalBalance += e.getValue(); 
        Wallet.Wall.replace(wallname, w);
        
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("NumberFormatException "+e.getMessage());
            alert.show();
        }
        amountText.setText(" ");
        
                     
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
       List<String> list = new ArrayList<String>();
         for(String s: Wallet.Wall.keySet())
         {
            list.add(s);
            
         }
        ObservableList<String> List2 = FXCollections.observableArrayList(list);
       walletCombo.setItems(List2);
            
    }
}
