package main;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.controller.ScreenController;
import main.debugging.Debugging;
import main.model.Expense;
import main.model.Income;
import main.model.dao.ExpenseDao;
import main.model.dao.IncomeDao;

/**
 * Main
 */
public class Main extends Application {

	public static void main(String[] args) {
		//Sample Data
        for (Expense e : Debugging.getRandomExpenses(20)) {
            ExpenseDao.getInstance().expenses.add(e);
        }
		for (Income i : Debugging.getRandomIncomes(20)) {
            IncomeDao.getInstance().incomes.add(i);
        }

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Home Page
		ScreenController.getInstance().activate("start_page");

		// primary stage settings
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("resources/assets/icon.png"));
		primaryStage.setTitle("Monthly Expenses");
		primaryStage.setScene(ScreenController.getInstance().getScene());
		primaryStage.show();
	}

}