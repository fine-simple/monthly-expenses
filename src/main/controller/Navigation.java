package main.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class Navigation {

	@FXML
	HBox mainHBox;

	@FXML
	void addIncome() {
		try {
			mainHBox.getChildren().set(1,
					(Node) FXMLLoader.load(getClass().getResource("/resources/fxml/add_income.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void addExpense() {
		try {
			mainHBox.getChildren().set(1,
					(Node) FXMLLoader.load(getClass().getResource("/resources/fxml/add_expense.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void addWallet() {
		try {
			mainHBox.getChildren().set(1,
					(Node) FXMLLoader.load(getClass().getResource("/resources/fxml/add_wallet.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void addCategory() {
		try {
			mainHBox.getChildren().set(1,
					(Node) FXMLLoader.load(getClass().getResource("/resources/fxml/add_category.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void viewExpenses() {
		try {
			mainHBox.getChildren().set(1,
					(Node) FXMLLoader.load(getClass().getResource("/resources/fxml/view_expenses.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void viewRemaining() {
		try {
			mainHBox.getChildren().set(1,
					(Node) FXMLLoader.load(getClass().getResource("/resources/fxml/remaining_money.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void exportCSV() {
		try {
			mainHBox.getChildren().set(1,
					(Node) FXMLLoader.load(getClass().getResource("/resources/fxml/export_csv.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}