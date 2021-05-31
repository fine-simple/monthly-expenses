package main.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.Expense;
import main.model.dao.CategoryDao;
import main.model.dao.ExpenseDao;
import main.model.dao.IncomeDao;
import main.model.dao.WalletDao;

public class ViewExpenses implements Initializable {
	@FXML
	TableView<Expense> table;

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

		// Initialize Table
		loadAndDisplayTable();
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
	
	@SuppressWarnings("unchecked")
	private void loadAndDisplayTable() {
		// Initialize Table
        TableColumn<Expense, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(150);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));


        TableColumn<Expense, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(51);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


        TableColumn<Expense, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		TableColumn<Expense, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setMinWidth(100);
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

		TableColumn<Expense, String> walletColumn = new TableColumn<>("Wallet");
        walletColumn.setMinWidth(75);
        walletColumn.setCellValueFactory(new PropertyValueFactory<>("wallet"));

		//get expenses
		ObservableList<Expense> expenses = FXCollections.observableArrayList(ExpenseDao.getInstance().expenses);

		//add expenses to table
        table.setItems(expenses);
		
		//add columns to table
        table.getColumns().addAll(titleColumn, priceColumn, dateColumn, categoryColumn, walletColumn);
	}
}