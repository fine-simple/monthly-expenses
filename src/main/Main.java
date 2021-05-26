package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.controller.ScreenController;

/**
 * Main
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Home Page
        ScreenController.getInstance().activate("start_page");

        // primary stage settings
        primaryStage.setResizable(false);
        primaryStage.setTitle("Monthly Expenses");
        primaryStage.setScene(ScreenController.getInstance().getScene());
        primaryStage.show();
    }

}