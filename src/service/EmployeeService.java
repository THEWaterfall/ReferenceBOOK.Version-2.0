package service;

import dao.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;

public class EmployeeService {
	
	public static void save(Employee emp) {
		EmployeeDAO.save(emp);
	}
	
	public static void delete(Employee emp) {
		EmployeeDAO.delete(emp);
	}
	
	public static void update(String id, String firstName, String lastName, String email, String phoneNumber, int salary, int managerId, int departmentId) {
		Employee emp = findById(Integer.parseInt(id));
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setEmail(email);
		emp.setPhoneNumber(phoneNumber);
		emp.setSalary(salary);
		emp.setManagerId(managerId);
		emp.setDepartmentId(departmentId);
		
		EmployeeDAO.update(emp);

	}
	
	public static Employee findById(Integer empId) {
		Employee emp = EmployeeDAO.findById(empId);
		return emp;
	}
	
	public static ObservableList<Employee> searchEmployees () {
		ObservableList<Employee> empList = FXCollections.observableArrayList();
		empList = EmployeeDAO.searchEmployees();
		return empList;
	}
	
}
