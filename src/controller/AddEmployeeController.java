package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.Employee;
import service.EmployeeService;

public class AddEmployeeController {
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
	
	private Stage parentStage;

	
	@FXML
	public void saveBtnClicked(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		Employee emp = new Employee(nameField.getText(), lastNameField.getText(), emailField.getText(), phoneNumberField.getText(), Integer.parseInt(salaryField.getText()), Integer.parseInt(managerIdField.getText()), Integer.parseInt(departmentIdField.getText()));
		EmployeeService.save(emp);
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("view/EmployeeView.fxml"));
		loader.load();
		EmployeeController employeeCon = loader.getController();
		
		ObservableList<Employee> empData = FXCollections.observableArrayList();
		empData = EmployeeService.searchEmployees();
		employeeCon.populateEmployee(empData);
		
		Parent root;
	    root = loader.getRoot();
	    parentStage.setScene(new Scene(root));
	    
	    nameField.getScene().getWindow().hide();
	}

	@FXML
	public void clearBtnClicked(ActionEvent event) {
		nameField.clear();
		lastNameField.clear();
		emailField.clear();
		phoneNumberField.clear();
		salaryField.clear();
		managerIdField.clear();
		departmentIdField.clear();
	}
	
	public void setParentStage(Stage stage) {
		parentStage = stage;
	}
}
