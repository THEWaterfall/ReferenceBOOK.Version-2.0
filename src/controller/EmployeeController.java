package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;

import java.io.IOException;
import java.sql.SQLException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Employee;
import service.EmployeeService;

public class EmployeeController {
	@FXML
	private TableView<Employee> employeeTable;
	@FXML
	private Button addBtn;
	@FXML
	private Button deleteBtn;
	@FXML
	private Button editBtn;
	@FXML
	private Button viewBtn;
	@FXML
	private Button showTableBtn;
	@FXML
	private Button searchBtn;
	@FXML
	private TextField searchField;
	@FXML
	private TableColumn<Employee, Integer> empId;
	@FXML
	private TableColumn<Employee, String> empName;
	@FXML
	private TableColumn<Employee, String> empLastName;

	public void initialize() {
		 //Context menu
		 ContextMenu contextMenu = new ContextMenu();
	     MenuItem show = new MenuItem("Show");
	     show.setGraphic(new ImageView(new Image("/Images/icons8_More_Info_24px.png")));
	     show.setAccelerator(KeyCombination.keyCombination("s"));
	     show.setOnAction(event -> {
			try {
				viewBtnClicked(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
	     });
	    
	     MenuItem edit = new MenuItem("Edit");
	     edit.setAccelerator(KeyCombination.keyCombination("e"));
	     edit.setOnAction(event -> {
	    	 try {
				editBtnClicked(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
	     });
	     
	     Menu delete = new Menu("Delete");
	     
	     CheckMenuItem confirm = new CheckMenuItem("Confirm");
	     confirm.setOnAction(event -> {
	    	 deleteBtnClicked(event);
 	     });
	     
	     delete.getItems().addAll(confirm);
	     
	     
	     contextMenu.getItems().addAll(show, edit, new SeparatorMenuItem(), delete);
	
		 empId.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
	     empName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
	     empLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	     
	     
	     employeeTable.setRowFactory(tv -> {
	    	 TableRow<Employee> row = new TableRow<>();
	    	 row.setOnMouseClicked(event -> {
	    		//Show info when double clicked
	    		 if(!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
	    			 Employee emp = row.getItem();
	    			 
	    			 FXMLLoader loader = new FXMLLoader();
	    			 loader.setLocation(getClass().getClassLoader().getResource("view/ViewEmployeeView.fxml"));
	    			 try {
						loader.load();
					 } catch (IOException e) {
						e.printStackTrace();
					 }
	    			 ViewEmployeeController viewEmployee = loader.getController();
	    			 viewEmployee.setText(emp.getFirstName(), emp.getLastName(), emp.getEmail(), emp.getPhoneNumber(), String.valueOf(emp.getSalary()), String.valueOf(emp.getManagerId()), String.valueOf(emp.getDepartmentId()));
	    			
	    			 
	    			 Parent root = loader.getRoot();
	    			 Stage stage = new Stage();
	    			 stage.setTitle("View employee");
	    			 stage.initModality(Modality.WINDOW_MODAL);
	    			 stage.initOwner(addBtn.getScene().getWindow());
	    			 stage.setScene(new Scene(root));
	    			 stage.show();
	    			 stage.setResizable(false);
    			 //Show context menu when right button is clicked
	    		 } else if (event.getButton() == MouseButton.SECONDARY) {
	    			 row.setContextMenu(contextMenu);
	    		 }
	    	 });
	    	 return row;
	     });
	     
	    
	   
	}

	@FXML
	public void addBtnClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("view/AddEmployeeView.fxml"));
		loader.load();
		
		AddEmployeeController addEmployee = loader.getController();
		addEmployee.setParentStage((Stage) addBtn.getScene().getWindow());
		
		Parent root = loader.getRoot();		
	    Stage stage = new Stage();
	    stage.setTitle("Add employee");
	    stage.setScene(new Scene(root));
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(addBtn.getScene().getWindow());
	    stage.show();
	    stage.setResizable(false);
	}
	
	@FXML
	public void deleteBtnClicked(ActionEvent event) {
		Employee emp = employeeTable.getSelectionModel().getSelectedItem();
		
		EmployeeService.delete(EmployeeService.findById(emp.getEmployeeId()));
		
		employeeTable.getItems().remove(emp);
		employeeTable.refresh();
	}
	
	@FXML
	public void editBtnClicked(ActionEvent event) throws IOException {
		Employee emp = employeeTable.getSelectionModel().getSelectedItem();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("view/EditEmployeeView.fxml"));
		loader.load();
		
		EditEmployeeController editEmployee = loader.getController();
		editEmployee.setText(emp.getEmployeeId(), emp.getFirstName(), emp.getLastName(), emp.getEmail(), emp.getPhoneNumber(), String.valueOf(emp.getSalary()), String.valueOf(emp.getManagerId()), String.valueOf(emp.getDepartmentId()));
		editEmployee.setParentStage((Stage) addBtn.getScene().getWindow());
		
		Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setTitle("Edit employee");
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(addBtn.getScene().getWindow());
		stage.show();
		stage.setResizable(false);
		
	
	}
	
	@FXML
	public void viewBtnClicked(ActionEvent event) throws IOException {
		Employee emp = employeeTable.getSelectionModel().getSelectedItem();
		
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getClassLoader().getResource("view/ViewEmployeeView.fxml"));
	    loader.load();
	
	    ViewEmployeeController viewEmployee = loader.getController();
	    viewEmployee.setText(emp.getFirstName(), emp.getLastName(), emp.getEmail(), emp.getPhoneNumber(), String.valueOf(emp.getSalary()), String.valueOf(emp.getManagerId()), String.valueOf(emp.getDepartmentId()));
	    
		Parent root;
	    root = loader.getRoot();
	    Stage stage = new Stage();
	    stage.setTitle("View employee");
	    stage.setScene(new Scene(root));
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(addBtn.getScene().getWindow());
	    stage.show();
	    stage.setResizable(false);
	}
	
	@FXML
	public void showTableBtnClicked(ActionEvent event) throws ClassNotFoundException, SQLException  {
		ObservableList<Employee> empData = FXCollections.observableArrayList();
		empData = EmployeeService.searchEmployees();
		populateEmployee(empData);
	}

	@FXML
	public void searchBtnClicked(ActionEvent event) throws ClassNotFoundException, SQLException  {
		Employee emp = EmployeeService.findById(Integer.parseInt(searchField.getText()));
		populateEmployee(emp);
	}
	
	
	private void populateEmployee(Employee emp) {
		//Declare and ObservableList for table view
		ObservableList<Employee> empData = FXCollections.observableArrayList();
		
		//Add employee to ObservableList
		empData.add(emp);
		
		//Set items to the employeeTable
		employeeTable.setItems(empData);
	}
	
	
	public void populateEmployee(ObservableList<Employee> empData) {
		//Set items to the employeeTable
		employeeTable.setItems(empData);
	}

}
