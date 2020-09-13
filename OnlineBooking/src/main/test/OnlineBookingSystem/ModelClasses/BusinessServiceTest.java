package OnlineBookingSystem.ModelClasses;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusinessServiceTest {

    @Test
    void saveService() {
        //Create a service
        BusinessService testService = new BusinessService(0, "testService", 10, 30);
        //Get initial database size
        int sizeBefore = getNumServices();

        //Save the service
        BusinessService.saveService(testService);

        //Make sure that the service has been added to the database
        assert (serviceInDatabase(testService));
        assert (getNumServices() == sizeBefore + 1);
    }

    @Test
    void deleteService_ServiceExistsInDatabase() {
        int initialSize = getNumServices();

        //Insert a service into the database
        BusinessService testService = new BusinessService(20, "testService", 5, 15);
        insertService(testService);
        assert (initialSize == getNumServices() - 1);
        assert (serviceInDatabase(testService));
        //Delete the service
        BusinessService.deleteService(testService.getId());

        //Check that the service is no longer in the database
        assert (!serviceInDatabase(testService));
        assert (initialSize == getNumServices());
    }

    @Test
    void deleteService_ServiceNotInDatabase() {
        //Create some services
        BusinessService mock1 = new BusinessService(5, "testService", 100, 60);
        BusinessService mock2 = new BusinessService(6, "testService", 20, 15);
        BusinessService mock3 = new BusinessService(7, "testService", 1, 5);
        BusinessService mock4 = new BusinessService(8, "testService", 75, 45);
        int expectedSize = getNumServices();

        //Insert them into the database
        insertService(mock1);
        insertService(mock2);
        insertService(mock3);
        insertService(mock4);

        //Delete a service that does not exist
        int id = 0;
        while (!idInDatabase(id)) {
            id++;
        }
        BusinessService.deleteService(id);

        //Ensure that none of the mock services have been deleted
        assertEquals(expectedSize, getNumServices());
        assert (serviceInDatabase(mock1));
        assert (serviceInDatabase(mock2));
        assert (serviceInDatabase(mock3));
        assert (serviceInDatabase(mock4));
    }


    @Test
    void getServiceById_ValidID() {
        //Insert a service into the database
        BusinessService expectedService = new BusinessService(0, "testService", 10, 30);
        insertService(expectedService);

        //Attempt to get the service
        BusinessService receivedService = BusinessService.getServiceById(expectedService.getId());

        //Make sure the services are the same
        assert (expectedService.getId() == receivedService.getId());
        assert (expectedService.getBusiness() == receivedService.getBusiness());
        assert (expectedService.getServiceCost() == receivedService.getServiceCost());
        assert (expectedService.getDuration() == receivedService.getDuration());
        assert (expectedService.getServiceName().equals(receivedService.getServiceName()));
    }

    private int getNumServices() {
        int size = 0;
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM service;");
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                size++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    private void insertService(BusinessService service) {
        try {

            PreparedStatement statement = Database.getConnection().prepareStatement("" +
                    "INSERT INTO service (" +
                    "business, serviceName, serviceCost, duration) " +
                    "VALUES (" +
                    "?, ?, ?, ? )");
            statement.setInt(1, service.getBusiness());
            statement.setString(2, service.getServiceName());
            statement.setInt(3, service.getServiceCost());
            statement.setInt(4, service.getDuration());
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean idInDatabase(int id) {
        boolean found = false;
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM service WHERE id = ?;");
            stmt.setInt(1, id);
            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                found = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return found;
    }

    private boolean serviceInDatabase(BusinessService service) {
        boolean identical = false;
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM service WHERE id = ?;");
            stmt.setInt(1, service.getId());
            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                identical = true;
                identical &= (service.getBusiness() == results.getInt("business"));
                identical &= (service.getServiceName() == results.getString("serviceName"));
                identical &= (service.getServiceCost() == results.getInt("serviceCost"));
                identical &= (service.getDuration() == results.getInt("duration"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return identical;
    }
}