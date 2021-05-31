package main.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import main.model.dao.ExpenseDao;
import main.model.dao.IncomeDao;

public class ExportCSV {

	private String path;

	@FXML
	Button choosePath;

	@FXML
	Button export;

	@FXML
	Label label;

	@FXML
	void choosePathAction() {

		DirectoryChooser directoryChooser = new DirectoryChooser();

		File selectedDirectory = directoryChooser.showDialog(ScreenController.getInstance().getScene().getWindow());
		if (selectedDirectory == null) {
			// No Directory selected
			export.setDisable(true);
			label.setText("No path provided");
		} else {
			System.out.println(selectedDirectory.getAbsolutePath());
			export.setDisable(false);
			path = selectedDirectory.getAbsolutePath();
			label.setText(path);
		}

	}

	@FXML
	void exportAction() {
		try {
			// 1. Writing Expenses
			FileWriter expensesWriter = new FileWriter(path + "\\expenses.csv");
			expensesWriter.write("Date,Title,Price,Category,Wallet\n");
			for (var expense : ExpenseDao.getInstance().expenses) {
				expensesWriter.write("\"" + expense.getDate().toString() + "\",\"" + expense.getTitle() + "\",\""
						+ expense.getPrice() + "\",\"" + expense.getCategory() + "\",\"" + expense.getWallet()
						+ "\"\n");
			}
			expensesWriter.close();

			// 2. Writing Income
			// IncomeDao.getInstance().incomes
			FileWriter incomeWriter = new FileWriter(path + "\\incomes.csv");
			incomeWriter.write("Date,Wallet,Value\n");
			for (var income : IncomeDao.getInstance().incomes) {
				incomeWriter.write("\"" + income.getDate().toString() + "\",\"" + income.getWallet() + "\",\""
						+ income.getValue() + "\"\n");
			}
			incomeWriter.close();

			// End Logic
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Data Exported");
			alert.setTitle("Success!");
			alert.setContentText("your data was exported to \"expenses.csv\" & \"incomes.csv\" files");
			alert.show();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Error Occured");
			alert.setTitle("Error!");
			alert.setContentText("Something wrong happend, please restart the program");
			alert.show();
		}
	}
}
