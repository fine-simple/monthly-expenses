package main.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;
import main.model.dao.WalletDao;

public class ViewRemaining implements Initializable {
	@FXML
	LineChart<Number, Number> chart;
	@FXML
	NumberAxis months;
	@FXML
	NumberAxis money;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		drawLineChart();
	}
	
	void drawLineChart() {
		// Change Back to date from number
		StringConverter<Number> converter = new StringConverter<Number>(){
			@Override
			public Number fromString(String label) {
				return null;
			}

			@Override
			public String toString(Number num) {
				// TODO : Fix the String representation of Date
				String result =String.valueOf(num.intValue());
				System.out.println(result);
				return null;
				// return result.substring(0, 4) + "-" + result.substring(4);
			}
		};
		months.setTickLabelFormatter(converter);
		
		for (var entry : WalletDao.getInstance().wallets.entrySet()) {
			XYChart.Series<Number, Number> line = new XYChart.Series<>();
			line.setName(entry.getKey());
			for (var wallet : entry.getValue().budget.entrySet()) {
				line.getData().add(new XYChart.Data<>(dateToNumber(wallet.getKey()), wallet.getValue()));
			}
			chart.getData().add(line);
		}

		months.setLowerBound(2000);
		months.setUpperBound(LocalDate.now().getYear() + 1);
	}
	
	Number dateToNumber(YearMonth yearMonth) {
		Number result = yearMonth.getYear() * 100 + yearMonth.getMonthValue();
		return result;
	}

 	void drawBarChart() {
		// Get wallets
		// HashMap<String, Float> wallets = new HashMap<>();
		// for (var entry : WalletDao.getInstance().wallets.entrySet()) {
		// 	for (var wallet : entry.getValue().budget.entrySet()) {
		// 		wallets.put(entry.getKey(), wallets.get(entry.getKey()) + wallet.getValue());
		// 	}
		// }
		
		// ArrayList<String> walletsNames = new ArrayList<String>();
		// for (var wallet : wallets.keySet()) {
		// 	walletsNames.add(wallet);
		// }
		// System.out.println(walletsNames);
		// ArrayList<Float> walletsValues = new ArrayList<Float>();
		// for (var wallet : wallets.values()) {
		// 	walletsValues.add(wallet);
		// }
		// System.out.println(walletsValues);

		// XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		// dataSeries.setName("Money");

		// for (var walletIteratable : wallets.entrySet()) {
		// 	dataSeries.getData()
		// 			.add(new XYChart.Data<>(walletIteratable.getKey(), Math.round(walletIteratable.getValue())));
		// }

		// chart.setLegendSide(Side.BOTTOM);
		// chart.getData().add(dataSeries);
	}
}