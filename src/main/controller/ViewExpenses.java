package main.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.Expense;

public class ViewExpenses implements Initializable{
    @FXML
    TableView<Expense> list;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        TableColumn<Expense, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(175);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));


        TableColumn<Expense, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(75);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


        TableColumn<Expense, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(150);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        list.setItems(getExpenses());
        list.getColumns().addAll(titleColumn, priceColumn, dateColumn);
    }

    ObservableList<Expense> getExpenses() {
        //TODO: add real data

        ObservableList<Expense> list = FXCollections.observableArrayList();
        LocalDate ld = LocalDate.now();
        list.add(new Expense("Shoes", 20, ld));
        list.add(new Expense("Laptop", 20000, ld));
        list.add(new Expense("Desk", 250, ld));
        list.add(new Expense("Bed", 1500, ld));
        list.add(new Expense("Watch", 2000, ld));
        list.add(new Expense("Mobile", 3000, ld));
        list.add(new Expense("T-Shirt", 100, ld));
        list.add(new Expense("shit", 20, ld));
        return list;
    }
    
}
