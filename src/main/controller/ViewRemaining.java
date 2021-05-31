package main.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import main.model.dao.WalletDao;

public class ViewRemaining implements Initializable {
	@FXML
	BarChart<String, Integer> chart;
	@FXML
	CategoryAxis months;
	@FXML
	NumberAxis money;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		drawBarChart();
	}
	
 	void drawBarChart() {
		// Get wallets
		HashMap<String, Float> wallets = new HashMap<>();
		for (var entry : WalletDao.getInstance().wallets.entrySet()) {
			for (var wallet : entry.getValue().budget.entrySet()) {
				wallets.put(entry.getKey(), wallets.get(entry.getKey()) + wallet.getValue());
			}
		}
		
		ArrayList<String> walletsNames = new ArrayList<String>();
		for (var wallet : wallets.keySet()) {
			walletsNames.add(wallet);
		}
		System.out.println(walletsNames);
		ArrayList<Float> walletsValues = new ArrayList<Float>();
		for (var wallet : wallets.values()) {
			walletsValues.add(wallet);
		}
		System.out.println(walletsValues);

		XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		dataSeries.setName("Money");

		for (var walletIteratable : wallets.entrySet()) {
			dataSeries.getData()
					.add(new XYChart.Data<>(walletIteratable.getKey(), Math.round(walletIteratable.getValue())));
		}

		chart.setLegendSide(Side.BOTTOM);
		chart.getData().add(dataSeries);
	}
}