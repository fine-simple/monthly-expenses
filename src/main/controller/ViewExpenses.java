package main.controller;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.YearMonth;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

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
import main.model.Wallet;
import main.model.dao.CategoryDao;
import main.model.dao.ExpenseDao;
import main.model.dao.WalletDao;

public class ViewExpenses implements Initializable {

	ObservableList<Expense> filteredExpenses;

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
		filteredExpenses.clear();
		for (Expense expense : ExpenseDao.getInstance().expenses) {
			if ((month.getValue().equals("Any") || month.getValue().toUpperCase().equals(expense.getDate().getMonth().name()))
					&& (year.getValue().equals("Any") || year.getValue().equals(String.valueOf(expense.getDate().getYear())))
					&& (category.getValue().equals("Any") || category.getValue().equals(expense.getCategory()))
					&& (wallet.getValue().equals("Any") || wallet.getValue().equals(expense.getWallet()))
					&& (moneyFrom.getText().length() < 1 || Float.valueOf(moneyFrom.getText()) <= expense.getPrice())
					&& (moneyTo.getText().length() < 1 || Float.valueOf(moneyTo.getText()) >= expense.getPrice())) {
				filteredExpenses.add(expense);
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadCategories();
		loadWallets();
		loadYears();
		loadMonths();
		loadAndDisplayTable();
	}

	private void loadMonths() {
		month.getItems().add("Any");
		month.getItems().addAll(DateFormatSymbols.getInstance().getMonths());
		month.setValue("Any");
	}

	private void loadYears() {
		Set<Integer> yearsList = new TreeSet<>();
		year.getItems().add("Any");

		for (Wallet wallet : WalletDao.getInstance().wallets.values()) {
			for (YearMonth yearMonth : wallet.budget.keySet()){
				yearsList.add(yearMonth.getYear());
			}
		}

		for (Integer value : yearsList) {
			year.getItems().add(String.valueOf(value));
		}
		
		year.setValue("Any");
	}

	private void loadWallets() {
		wallet.getItems().add("Any");
		wallet.getItems().addAll(WalletDao.getInstance().wallets.keySet());
		wallet.setValue("Any");
	}

	private void loadCategories() {
		category.getItems().add("Any");
		category.getItems().addAll(CategoryDao.getInstance().categories);
		category.setValue("Any");
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

		// get expenses
		filteredExpenses = FXCollections.observableArrayList(ExpenseDao.getInstance().expenses);

		// add expenses to table
		table.setItems(filteredExpenses);

		// add columns to table
		table.getColumns().addAll(titleColumn, priceColumn, dateColumn, categoryColumn, walletColumn);
	}
}