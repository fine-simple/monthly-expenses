package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.model.dao.CategoryDao;

public class AddCategory {
	@FXML
	TextField category;

	@FXML
	void addCategory() {
		String name = category.getText().trim();

		if (name.length() < 1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Category Can't Be Empty");
			alert.show();
			return;
		}

		if (CategoryDao.getInstance().categories.contains(name)) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Category Already Exists");
			alert.show();
			return;
		}

		CategoryDao.getInstance().categories.add(name);
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Category Added");
		alert.setTitle("Success!");
		alert.setContentText("You added a new category: " + name);
		alert.show();

		category.setText("");
	}
}