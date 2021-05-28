package main.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class ScreenController {
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
    }

    public void activate(String name){
        try {
            main.setRoot( (Pane)FXMLLoader.load(getClass().getResource("/resources/fxml/" + name + ".fxml") ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}