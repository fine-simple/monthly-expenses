package main.controller;

import javafx.fxml.FXML;

public class StartPage {
    
    @FXML
    void start() {
        ScreenController.getInstance().activate("navigation");
    }
    
    @FXML
    void members() {
        ScreenController.getInstance().activate("team_members");
    }

    @FXML
    void goBack() {
        ScreenController.getInstance().activate("start_page");
    }
}