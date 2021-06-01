package main.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.text.DateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.Expense;
import main.model.Wallet;
import main.model.dao.CategoryDao;
import main.model.dao.ExpenseDao;
import main.model.dao.WalletDao;

public class ViewExpenses implements Initializable {
	@FXML
	TableView<Expense> table;
	@FXML
	ListView list;
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
	void filter(){
		filteredExpenses.clear();
		// 	Booleans to check which search criteria used
		boolean isMonth = false, isYear=false, isCategory=false, isWallet=false, isMoney=false;
		if(month.getValue() != null && !month.getValue().equals("Any")){
			isMonth=true;
		}
		if(year.getValue() != null && !year.getValue().equals("Any")){
			isYear=true;
		}
		if(category.getValue() != null && !category.getValue().equals("Any")){
			isCategory=true;
		}
		if(wallet.getValue() != null && !wallet.getValue().equals("Any") ){
			isWallet=true;
		}
		if(!moneyFrom.getText().equals("") && !moneyTo.getText().equals("")){
			isMoney=true;
		}
		// Generate all expenses if "Any" is selected in all search criteria
		if(!isMonth && !isYear && !isCategory && !isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				filteredExpenses.add(expenses.get(i));
			}
		}
		//Filtration Process (combinations)
		// Month only
		if(isMonth && !isYear && !isCategory && !isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if (expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		// Year only
		if(!isMonth && isYear && !isCategory && !isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if (expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		// Category only
		if(!isMonth && !isYear && isCategory && !isWallet && !isMoney){
			System.out.println("HEY I AM IN CAT");
			for(int i=0;i<expenses.size();i++){
				if (expenses.get(i).getCategory().equals(category.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		// Wallets only
		if(!isMonth && !isYear && !isCategory && isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if (expenses.get(i).getWallet().equals(wallet.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		// Money only
		if(!isMonth && !isYear && !isCategory && !isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if (Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice()) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}

		//Month & Year
		if(isMonth && isYear && !isCategory && !isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if (( expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && (expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()))) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Month & Category
		if(isMonth && !isYear && isCategory && !isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if ((expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && (expenses.get(i).getCategory().equals(category.getValue()))) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Month & Wallet
		if(isMonth && !isYear && !isCategory && isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if ((expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && (expenses.get(i).getWallet().equals(wallet.getValue()))) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Month & Money
		if(isMonth && !isYear && !isCategory && !isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if (expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase()) && (Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}

		//Year && Category
		if(!isMonth && isYear && isCategory && !isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if (expenses.get(i).getCategory().equals(category.getValue()) && (expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()))) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Year && Wallets
		if(!isMonth && isYear && !isCategory && isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if (expenses.get(i).getWallet().equals(wallet.getValue()) && (expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()))) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Year && Money
		if(!isMonth && isYear && !isCategory && !isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if (Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice() && (expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()))) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}

		//Category & Wallets
		if(!isMonth && !isYear && isCategory && isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if (expenses.get(i).getWallet().equals(wallet.getValue()) && (expenses.get(i).getCategory().equals(category.getValue()))) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Category & Money
		if(!isMonth && !isYear && isCategory && !isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if (Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice() && (expenses.get(i).getCategory().equals(category.getValue()))) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}

		//Wallets & Money
		if(!isMonth && !isYear && !isCategory && isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if (expenses.get(i).getWallet().equals(wallet.getValue()) && Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice()) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}

		//Month & Year & Category
		if(isMonth && isYear && isCategory && !isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if ((expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()) && expenses.get(i).getCategory().equals(category.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Month & Year && Wallets
		if(isMonth && isYear && !isCategory && isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if ((expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()) && expenses.get(i).getWallet().equals(wallet.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Month & Year && Money
		if(isMonth && isYear && !isCategory && !isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if ((expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()) && Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice()) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Month && Category && Wallet
		if(isMonth && !isYear && isCategory && isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if ((expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && expenses.get(i).getWallet().equals(wallet.getValue()) && expenses.get(i).getCategory().equals(category.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Month && Category && Money
		if(isMonth && !isYear && isCategory && !isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if ((expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && (Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice()) && expenses.get(i).getCategory().equals(category.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Month && Wallet && Money
		if(isMonth && !isYear && !isCategory && isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if ((expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && (Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice()) && expenses.get(i).getWallet().equals(wallet.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}

		//Year & Category & Wallet
		if(!isMonth && isYear && isCategory && isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if (expenses.get(i).getWallet().equals(wallet.getValue()) && expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()) && expenses.get(i).getCategory().equals(category.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Year & Category & Money
		if(!isMonth && isYear && isCategory && !isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if (Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice() && expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()) && expenses.get(i).getCategory().equals(category.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Year & Wallet & Money
		if(!isMonth && isYear && !isCategory && isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if (Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice() && expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()) && expenses.get(i).getWallet().equals(wallet.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}

		//Category & Wallet & Money
		if(!isMonth && !isYear && isCategory && isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if (expenses.get(i).getWallet().equals(wallet.getValue()) && Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice() && expenses.get(i).getCategory().equals(category.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Month & Year & Category & Wallet
		if(isMonth && isYear && isCategory && isWallet && !isMoney){
			for(int i=0;i<expenses.size();i++){
				if (expenses.get(i).getWallet().equals(wallet.getValue()) && (expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()) && expenses.get(i).getCategory().equals(category.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Month & Year & Category & Money
		if(isMonth && isYear && isCategory && !isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if (Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice() && (expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()) && expenses.get(i).getCategory().equals(category.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Month & Year & Wallet & Money
		if(isMonth && isYear && !isCategory && isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if (Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice() && (expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()) && expenses.get(i).getWallet().equals(wallet.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Month & Category & Wallet & Money
		if(isMonth && !isYear && isCategory && isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if (Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice() && (expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && expenses.get(i).getCategory().equals(category.getValue()) && expenses.get(i).getWallet().equals(wallet.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Year & Category & Wallet & Money
		if(!isMonth && isYear && isCategory && isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if (Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice() && expenses.get(i).getWallet().equals(wallet.getValue()) && expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()) && expenses.get(i).getCategory().equals(category.getValue())) {
					filteredExpenses.add(expenses.get(i));
				}
			}
		}
		//Month & Year & Category & Wallet & Money
		if(isMonth && isYear && isCategory && isWallet && isMoney){
			for(int i=0;i<expenses.size();i++){
				if (Integer.parseInt(moneyFrom.getText())<=expenses.get(i).getPrice() && Integer.parseInt(moneyTo.getText())>=expenses.get(i).getPrice()  && expenses.get(i).getWallet().equals(wallet.getValue()) && (expenses.get(i).getDate().getMonth().toString().equals(month.getValue().toUpperCase())) && expenses.get(i).getDate().getYear() == Integer.parseInt(year.getValue()) && expenses.get(i).getCategory().equals(category.getValue())) {
					filteredExpenses.add(expenses.get(i));
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

		//get expenses

		//add expenses to table
		System.out.println(filteredExpenses.size());
		table.setItems(filteredExpenses);

		//add columns to table
		table.getColumns().addAll(titleColumn, priceColumn, dateColumn, categoryColumn, walletColumn);
	}
}