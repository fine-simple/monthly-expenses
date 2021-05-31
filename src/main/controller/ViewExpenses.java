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
import main.model.dao.ExpenseDao;

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
		loadAndDisplayTable();
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