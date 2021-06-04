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
import main.model.dao.IncomeDao;
import main.model.dao.WalletDao;

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

		if (!WalletDao.getInstance().wallets.containsKey(walletCombo.getValue())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Please Choose Wallet");
			alert.show();
			return;
		}
		
		// Add to Incomes
		Income income = new Income(value, localdate, walletCombo.getValue());
		IncomeDao.getInstance().incomes.add(income);
                
		
		amountText.setText("");
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Income recorded");
		alert.setTitle("Success!");
		alert.setContentText("You added " + value + "$ to the wallet: " + walletCombo.getValue());
		alert.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<String> list = new ArrayList<String>();
		for (String s : WalletDao.getInstance().wallets.keySet()) {
			list.add(s);
		}
		ObservableList<String> List2 = FXCollections.observableArrayList(list);
		walletCombo.setItems(List2);
		incomeDate.setValue(LocalDate.now());
	}
}