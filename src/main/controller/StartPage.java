package main.controller;

import javafx.fxml.FXML;

public class StartPage {
    
    @FXML
    void start() {
        ScreenController.getInstance().activate("navigation");
    }
    
    @FXML
    void members() {
        
    }
}