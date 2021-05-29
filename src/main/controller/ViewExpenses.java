package main.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import main.model.Expense;
import main.model.Wallet;

public class ViewExpenses implements Initializable {
	@FXML
	ListView list;

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
		for (String f : Wallet.Wall.keySet()) {
			Wallet w = Wallet.Wall.get(f);
			HashMap<String, ArrayList<Expense>> hashmap = w.getCategories();
			for (String e : hashmap.keySet()) {
				if (!(categories.contains(e)))
					categories.add(e);
			}

		}

		ObservableList<String> categoriesObservable = FXCollections.observableArrayList(categories);
		category.setItems(categoriesObservable);

		// Initialize Wallet
		List<String> wallets = new ArrayList<String>();
		wallets.add("Any");
		for (String s : Wallet.Wall.keySet()) {
			wallets.add(s);
		}

		ObservableList<String> walletsObservable = FXCollections.observableArrayList(wallets);
		wallet.setItems(walletsObservable);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		init();
	}
}
