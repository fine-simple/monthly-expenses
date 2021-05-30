package main.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.model.Expense;
import main.model.dao.CategoryDao;
import main.model.dao.ExpenseDao;
import main.model.dao.IncomeDao;
import main.model.dao.WalletDao;

public class ViewExpenses implements Initializable {
	@FXML
	TableView<Expense> list;

	@FXML
	ComboBox<String> month;
	@FXML
	ComboBox<String> year;
	@FXML
	ComboBox<String> category;
	@FXML
	ComboBox<String> wallet;
	@FXML
	TextField moneyTo;
	@FXML 
	TextField moneyFrom;

	@FXML
	void filter() {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Load All Date in the table

		printAllDate();
	}

	//For testing
	void printAllDate() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("Wallets:");
		for (var entry : WalletDao.getInstance().wallets.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}

		System.out.println("\n");
		System.out.println("Income:");
		for (var entry : IncomeDao.getInstance().incomes) {
			System.out.println(entry.getDate() + " - " + entry.getValue() + " - " + entry.getWallet());
		}

		System.out.println("\n");
		System.out.println("Expenses:");
		for (var expense : ExpenseDao.getInstance().expenses) {
				System.out.println(expense.getTitle() + " - " + expense.getDate() + " - " + expense.getPrice() + " - " + expense.getCategory() + " - " + expense.getWallet());
		}

		System.out.println("\n");
		System.out.println("Categories:");
		for (var entry : CategoryDao.getInstance().categories) {
			System.out.println(entry);
		}
	}
}
