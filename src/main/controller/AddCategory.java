package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.model.dao.ExpenseDao;

public class AddCategory {
    @FXML
    TextField category;

    @FXML
    void addCategory() {
        String name = category.getText();
        if (ExpenseDao.getInstance().hasCategory(name)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Category Already Exists");
            alert.show();
            return;
        } 
        ExpenseDao.getInstance().addCategory(name);
        category.setText("");
    }
}