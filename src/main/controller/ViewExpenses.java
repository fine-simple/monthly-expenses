package main.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import main.model.dao.WalletDao;

public class ViewExpenses implements Initializable {
	@FXML
	TableView<Expense> table;
	@FXML
	TextField moneyFrom;
	@FXML
	TextField moneyTo;
	@FXML
	ComboBox<String> month;
	@FXML
	ComboBox<String> year;
	@FXML
	ComboBox<String> category;
	@FXML
	ComboBox<String> wallet;

	@FXML
	String chooseMonth() {
		return wallet.getValue();
	}

	@FXML
	String chooseYear() {
		return category.getValue();
	}

	@FXML
	String chooseCategory() {
		return category.getValue();
	}

	@FXML
	String chooseWallet() {
		return wallet.getValue();
	}

	ObservableList<Expense> filteredExpenses = FXCollections.observableArrayList(ExpenseDao.getInstance().expenses);
	ObservableList<Expense> expenses = FXCollections.observableArrayList(ExpenseDao.getInstance().expenses);

	@FXML
	void init() {
		// Initialize Months
		List<String> months = new ArrayList<String>(Arrays.asList("Any", "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November", "December"));
		ObservableList<String> monthsObservable = FXCollections.observableArrayList(months);
		month.setItems(monthsObservable);

		// Initialize Year
		List<String> years = new ArrayList<String>(Arrays.asList("Any", "2000", "2001", "2002", "2003", "2004", "2005",
				"2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
				"2019", "2020", "2021", "2022"));
		ObservableList<String> yearsObservable = FXCollections.observableArrayList(years);
		year.setItems(yearsObservable);

		// Initialize Category
		List<String> categories = new ArrayList<String>();
		categories.add("Any");
		for (var f : CategoryDao.getInstance().categories) {
			categories.add(f);
		}

		ObservableList<String> categoriesObservable = FXCollections.observableArrayList(categories);
		category.setItems(categoriesObservable);

		// Initialize Wallet
		List<String> wallets = new ArrayList<String>();
		wallets.add("Any");
		for (String s : WalletDao.getInstance().wallets.keySet()) {
			wallets.add(s);
		}

		ObservableList<String> walletsObservable = FXCollections.observableArrayList(wallets);
		wallet.setItems(walletsObservable);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		init();
		loadAndDisplayTable();
	}

	@FXML
	void filter() {
		filteredExpenses.clear();
		// Booleans to check which search criteria used
		boolean isMonth = false, isYear = false, isCategory = false, isWallet = false, isMoney = false;
		if (month.getValue() != null && !month.getValue().equals("Any")) {
			isMonth = true;
		}
		if (year.getValue() != null && !year.getValue().equals("Any")) {
			isYear = true;
		}
		if (category.getValue() != null && !category.getValue().equals("Any")) {
			isCategory = true;
		}
		if (wallet.getValue() != null && !wallet.getValue().equals("Any")) {
			isWallet = true;
		}
		if (!moneyFrom.getText().equals("") && !moneyTo.getText().equals("")) {
			isMoney = true;
		}

		// Copy expenses into filteredExpenses
		for (int i = 0; i < expenses.size(); i++) {
			filteredExpenses.add(expenses.get(i));
		}

		// Filtration Process (combinations)
		// Month only
		if (isMonth) {
			for (int i = 0; i < filteredExpenses.size(); i++) {
				if (!(filteredExpenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase()))) {
					filteredExpenses.remove(i);
					i--;
				}
			}
		}
		// Year only
		if (isYear) {
			for (int i = 0; i < filteredExpenses.size(); i++) {
				if (!(filteredExpenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()))) {
					filteredExpenses.remove(i);
					i--;
				}
			}
		}
		// Category only
		if (isCategory) {
			for (int i = 0; i < filteredExpenses.size(); i++) {
				if (!(filteredExpenses.get(i).getCategory().equals(category.getValue()))) {
					filteredExpenses.remove(i);
					i--;
				}
			}
		}
		// Wallets only
		if (isWallet) {
			for (int i = 0; i < filteredExpenses.size(); i++) {
				if (!(filteredExpenses.get(i).getWallet().equals(wallet.getValue()))) {
					filteredExpenses.remove(i);
					i--;
				}
			}
		}
		// Money only
		if (isMoney) {
			for (int i = 0; i < filteredExpenses.size(); i++) {
				if (!(Integer.parseInt(moneyFrom.getText()) <= filteredExpenses.get(i).getPrice()
						&& Integer.parseInt(moneyTo.getText()) >= filteredExpenses.get(i).getPrice())) {
					filteredExpenses.remove(i);
					i--;
				}
			}
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


		System.out.println(filteredExpenses.size());
		// add expenses to table
		table.setItems(filteredExpenses);

		// add columns to table
		table.getColumns().addAll(titleColumn, priceColumn, dateColumn, categoryColumn, walletColumn);
	}
}