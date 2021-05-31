package main.controller;

import javafx.fxml.FXML;
import main.debugging.debugging;

public class StartPage {
    
    @FXML
    void start() {
        ScreenController.getInstance().activate("navigation");
        debugging.addDebugData(150);
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