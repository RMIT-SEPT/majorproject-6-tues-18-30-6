package onlineBookingSystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import OnlineBookingSystem.ModelClasses.Employee;



public class EmployeeTest {

	
	@Test
	public void getEmployeeByIdTest(){
		Employee mock = new Employee(99, "test", "test", "test", "test");
		Employee.saveEmployee(mock);
		Employee result = Employee.getEmployee(99);
		
		Assertions.assertNotNull(result);
		
		Employee.removeEmployee(99);
	}
	
	
	@Test
	public void testGetEmployeeByNameTest() {
		Employee mock = new Employee(99, "test", "test", "test", "test");
		Employee.saveEmployee(mock);
		Employee result = Employee.getEmployee("test");
		
		Assertions.assertNotNull(result);
		
		Employee.removeEmployee(99);

	}
	

	
	@Test
	public void getEmployeeIdByNametest() {
		
		Employee mock = new Employee(99, "test", "test", "test", "test");
		Employee.saveEmployee(mock);
		int result = Employee.getEmployeeIdByName("test");
		
		Assertions.assertTrue(result == 99);
		
		Employee.removeEmployee(99);
		
	}
	
	
	
	
	
}
