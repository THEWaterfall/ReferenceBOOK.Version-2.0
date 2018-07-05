package controller;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class ViewEmployeeController {
	@FXML
	private TextField nameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField phoneNumberField;
	@FXML
	private TextField salaryField;
	@FXML
	private TextField managerIdField;
	@FXML
	private TextField departmentIdField;
	
	public void setText(String name, String lastName, String email, String phoneNumber, String salary, String managerId, String departmentId) {
		nameField.setText(name);
		lastNameField.setText(lastName);
		emailField.setText(email);
		phoneNumberField.setText(phoneNumber);
		salaryField.setText(salary);
		managerIdField.setText(managerId);
		departmentIdField.setText(departmentId);
	}

}
