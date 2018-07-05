package controller;

import java.io.IOException;
import java.sql.SQLException;

import dao.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Employee;
import service.EmployeeService;

public class EditEmployeeController {
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
	private int id;

	@FXML
	public void saveBtnClicked(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		EmployeeService.update(String.valueOf(id), nameField.getText(), lastNameField.getText(), emailField.getText(), phoneNumberField.getText(), Integer.parseInt(salaryField.getText()), Integer.parseInt(managerIdField.getText()), Integer.parseInt(departmentIdField.getText()));

		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("view/EmployeeView.fxml"));
		loader.load();
		EmployeeController employeeCon = loader.getController();
		
		ObservableList<Employee> empData = FXCollections.observableArrayList();
		empData = EmployeeDAO.searchEmployees();
		employeeCon.populateEmployee(empData);
		
		Parent root;
	    root = loader.getRoot();
	    parentStage.setScene(new Scene(root));
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

	public void setText(int id, String name, String lastName, String email, String phoneNumber, String salary, String managerId, String departmentId) {
		nameField.setText(name);
		lastNameField.setText(lastName);
		emailField.setText(email);
		phoneNumberField.setText(phoneNumber);
		salaryField.setText(salary);
		managerIdField.setText(managerId);
		departmentIdField.setText(departmentId);
		this.id = id;
	}

	public void setParentStage(Stage stage) {
		parentStage = stage;
	}
}
