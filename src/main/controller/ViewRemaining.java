package main.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class ViewRemaining implements Initializable {
    @FXML
    BarChart chart;
    @FXML
    CategoryAxis months;
    @FXML
    NumberAxis money;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Draw Graph at startup    
    }


}