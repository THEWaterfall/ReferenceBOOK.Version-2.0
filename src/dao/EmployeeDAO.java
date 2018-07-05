package dao;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import util.HibernateUtil;


public class EmployeeDAO {

	//Insert Employee into DB
	public static void save(Employee emp) {
		HibernateUtil.openSessionWithTransaction();
		HibernateUtil.getCurrentSession().save(emp);
		HibernateUtil.closeSessionWithTransaction();
	}
	
	//Delete Employee from DB
	public static void delete (Employee emp) {
		HibernateUtil.openSessionWithTransaction();
		HibernateUtil.getCurrentSession().delete(emp);
		HibernateUtil.closeSessionWithTransaction();
    }
	
	//Update Employee
	public static void update(Employee emp) {
		HibernateUtil.openSessionWithTransaction();
		HibernateUtil.getCurrentSession().update(emp);
		HibernateUtil.closeSessionWithTransaction();
	}
	
	//Search Employee
	public static Employee findById (Integer empId) {
		HibernateUtil.openSessionWithTransaction();
	    Employee emp = HibernateUtil.getCurrentSession().get(Employee.class, empId);
	    HibernateUtil.closeSessionWithTransaction();
	    return emp;
	}

	//SELECT Employees
	public static ObservableList<Employee> searchEmployees () {
		ObservableList<Employee> empList = FXCollections.observableArrayList();
		HibernateUtil.openSessionWithTransaction();
		List<Employee> list = HibernateUtil.getCurrentSession().createQuery("from " + Employee.class.getSimpleName() +" ").list();
		HibernateUtil.closeSessionWithTransaction();
		empList.addAll(list);
		return empList;
	}

}
