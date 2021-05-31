package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.model.Wallet;
import main.model.dao.WalletDao;

public class AddWallet {
	@FXML
	TextField wallet;

	@FXML
	void addWallet() {
		String wallname = wallet.getText().trim();
		wallet.setText("");
		if (wallname.length() < 1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Wallet Can't Be Empty");
			alert.show();
			return;
		}

		if (WalletDao.getInstance().wallets.containsKey(wallname)) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("This wallet already exists");
			alert.show();
			return;
		}
		WalletDao.getInstance().wallets.put(wallname, new Wallet());
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Wallet Added");
		alert.setTitle("Success!");
		alert.setContentText("You added a new wallet: " + wallname);
		alert.show();
	}
}
