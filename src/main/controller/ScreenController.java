package main.controller;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class ScreenController {
    private HashMap<String, String> screenMap = new HashMap<>();
    private final Scene main;


    public Scene getScene() {
        return main;
    }

    private static ScreenController instance;
    
    public static ScreenController getInstance() {
        if(instance == null)
            instance = new ScreenController();
        return instance;
    }
    
    private ScreenController() {
        this.main = new Scene(new Pane());
        screenMap.put("add_income", "../../resources/fxml/add_income.fxml");
        screenMap.put("add_expense", "../../resources/fxml/add_expense.fxml");
        screenMap.put("remaining_money", "../../resources/fxml/remaining_money.fxml");
        screenMap.put("start_page", "../../resources/fxml/start_page.fxml");
        screenMap.put("view_expenses", "../../resources/fxml/view_expenses.fxml");
    }

    public void activate(String name){
        try {
            main.setRoot( (Pane)FXMLLoader.load(getClass().getResource(screenMap.get(name))) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}