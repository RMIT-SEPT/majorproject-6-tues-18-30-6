package onlineBookingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import OnlineBookingSystem.ModelClasses.Customer;
import OnlineBookingSystem.ModelClasses.Database;

public class CustomerTest {
	@BeforeEach
	public void setup() {
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement("" +
                    "INSERT INTO customer (" +
                    "id, firstName, lastName, username, password,email)" +
                    "VALUES (" +
                    "?, ?, ?, ?, ?, ? )");
            statement.setInt(1, -99);
            statement.setString(2, "mock_firstname");
            statement.setString(3, "mock_lastname");
            statement.setString(4, "mock_username");
            statement.setString(5, "mock_password");
            statement.setString(6, "email");
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	@AfterEach
	public void cleanup() {
		try {
			  String sql = "delete from customer where id=-99";
			  Connection conn = Database.getConnection();
			  Statement stmt = conn.createStatement();
			  stmt.executeUpdate(sql);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testGetByUsername(){
		String username = "mock_username";
		//call getByUsername
		Customer res = Customer.getByUsername(username);
		//result id should be -99, the same as in setup()
		assert(res.getId()==-99);
	}
	
	
	@Test
	public void testGetById() {
		Customer res = Customer.getById(-99);
		//the username of the user with id==-99 should be "mock_username"
		assert(res.getUsername()=="mock_username");
	}
	

	
	@Test
	public void testGetAllCustomers() {
		//get number of all users before
		ArrayList<Customer> before = Customer.getAllCustomers();
		int numBefore = before.size();
		
        try {
        	//insert one more record
            PreparedStatement statement = Database.getConnection().prepareStatement("" +
                    "INSERT INTO customer (" +
                    "id, firstName, lastName, username, password,email) " +
                    "VALUES (" +
                    "?, ?, ?, ?,?,? )");
            statement.setInt(1, -100);
            statement.setString(2, "test");
            statement.setString(3, "test");
            statement.setString(4, "test");
            statement.setString(5, "test");
            statement.setString(6, "test");
            statement.executeUpdate();
            //get number of user after insertion
            ArrayList<Customer> after= Customer.getAllCustomers();
            int numAfter = after.size();
            //after should be one more than before
            assert(numBefore+1==numAfter);
            
            //delete that inserted record
			String sql = "delete from customer where id=-100";
			Connection conn = Database.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
         
	}
	
	
	@Test
	public void testSaveCustomer() {
		//construct a object, with no id, the id will be -1
		Customer duplicate = new Customer("should_not_save","should_not_save","should_not_save","should_not_save","should_not_save");
		ArrayList<Customer> before = Customer.getAllCustomers();
		int numBefore = before.size();
		
		//insert a use alfready exist
		Customer.saveCustomer(duplicate);
		
		
		ArrayList<Customer> after = Customer.getAllCustomers();
		int numAfter = after.size();
		
		//number should be equal before and after insertion
		assert(numBefore==numAfter);
		
		
	}
	
	
	
	
	
}
