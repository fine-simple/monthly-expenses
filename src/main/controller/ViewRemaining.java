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
				return dateToNumber(YearMonth.parse(label));
			}

			@Override
			public String toString(Number num) {
				String result =String.valueOf(num.intValue());
				if(num.intValue() % 100 == 0)
					return result.substring(0, 4);
				return result.substring(0, 4) + "-" + result.substring(4);
			}
		};
		months.setLowerBound(200001);
		months.setUpperBound(dateToNumber(YearMonth.now()).intValue());
		months.setTickLabelFormatter(converter);
		
		for (var entry : WalletDao.getInstance().wallets.entrySet()) {
			XYChart.Series<Number, Number> line = new XYChart.Series<>();
			line.setName(entry.getKey() + "(" + String.format("%,.2f", entry.getValue().total) + "$)");
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
}