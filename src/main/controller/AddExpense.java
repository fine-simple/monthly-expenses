package main.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
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
import main.model.Expense;
import main.model.dao.CategoryDao;
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
		try {
			value = Float.parseFloat(amount.getText());
		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("NumberFormatException " + e.getMessage());
			alert.show();
			amount.setText("");
			return;
		}

		String tit;
		try {
			tit = title.getText();
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Wrong Date Format");
			alert.show();
			title.setText(" ");
			return;
		}

		LocalDate localdate;
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
		if (!WalletDao.getInstance().wallets.containsKey(wallname)) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Please Choose Wallet");
			alert.show();
			return;
		}

		String catname = categoryCombo.getValue();
		if (!CategoryDao.getInstance().categories.contains(catname)) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Please Choose Category");
			alert.show();
			return;
		}

		// Add to Expenses
		Expense e = new Expense(tit, value, localdate, wallname, catname);
		ExpenseDao.getInstance().expenses.add(e);
		//Add to budget
		WalletDao.getInstance().addToTotal(wallname, YearMonth.from(localdate), -value);
		amount.setText("");
		title.setText("");
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Expense recorded");
		alert.setTitle("Success!");
		alert.setContentText("You paid " + value + "$ from " + wallname + " for " + tit);
		alert.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<String> allWallets = new ArrayList<String>();
		for (String s : WalletDao.getInstance().wallets.keySet()) {
			allWallets.add(s);
		}

		ObservableList<String> List2 = FXCollections.observableArrayList(CategoryDao.getInstance().categories);
		categoryCombo.setItems(List2);

		ObservableList<String> List = FXCollections.observableArrayList(allWallets);
		walletCombo.setItems(List);
		date.setValue(LocalDate.now());
	}

}