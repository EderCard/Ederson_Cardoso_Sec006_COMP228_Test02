package application;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class OrderSystemController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField OrderIDTextField;

	@FXML
	private TextField CustomerNameTextField;

	@FXML
	private TextField DeliveryAddressTextField;

	@FXML
	private TextArea RegistrationDetailsTextArea;

	@FXML
	private ComboBox<String> ManufacturersComboBox = new ComboBox<String>();;

	@FXML
	private RadioButton StatusNewRadioButton;

	@FXML
	private ToggleGroup Status;

	@FXML
	private RadioButton StatusUsedRadioButton;

	@FXML
	private RadioButton SatusRefurbishedRadioButton;

	@FXML
	private CheckBox SmartPhoneCheckBox;

	@FXML
	private CheckBox SpeackersCheckBox;

	@FXML
	private CheckBox HeadSetCheckBox;

	@FXML
	private CheckBox WatchCheckBox;

	@FXML
	private Button PlaceOrderButton;

	@FXML
	private Label MessageLabel;

	@FXML
	void initialize() {
		ManufacturersComboBox.getItems().addAll("Apple", "Samsumg", "Blackberry");
		ManufacturersComboBox.setValue("---Select---");

		StatusNewRadioButton.setSelected(true);
	}

	@FXML
	void PlaceOrderClick(ActionEvent event) {
		try {

			// Validate Customer name
			if (CustomerNameTextField.getText().equals("")) {
				MessageLabel.setText("Customer Name cannot be empty.");
			}

			double subTotal = 0;
			String productsList = "";

			// Setting subtotal
			if (SmartPhoneCheckBox.isSelected()) {
				subTotal += 399;
				productsList += "Smart Phone, ";
			}
			if (HeadSetCheckBox.isSelected()) {
				subTotal += 199;
				productsList += "HeadSet, ";
			}
			if (WatchCheckBox.isSelected()) {
				subTotal += 299;
				productsList += "Watch, ";
			}
			if (SpeackersCheckBox.isSelected()) {
				subTotal += 99;
				productsList += "Speakers, ";
			}

			// Setting discount according to Status
			if (StatusNewRadioButton.isSelected()) {
				subTotal = subTotal * 1;
			}

			if (StatusUsedRadioButton.isSelected()) {
				subTotal = subTotal * 0.8;
			}

			if (SatusRefurbishedRadioButton.isSelected()) {
				subTotal = subTotal * 0.9;
			}

			// Showing results
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

			RegistrationDetailsTextArea.setText("Order ID: " + OrderIDTextField.getText() + "\nCustomer Name: "
					+ CustomerNameTextField.getText() + "\nSelected Products: " + productsList.toString()
					+ "\nTotal Cost: " + currencyFormat.format(subTotal).toString());

		} catch (Exception e) {
			RegistrationDetailsTextArea.setText("ERROR: " + e.getMessage());
		}
	}

}
