package onlineBookingSystem;


import OnlineBookingSystem.Controllers.RegisterController;
import OnlineBookingSystem.ModelClasses.Customer;
import OnlineBookingSystem.ModelClasses.Interface;
import OnlineBookingSystem.ModelClasses.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RegisterControllerTest
{
    @Autowired
    private RegisterController registerController;
    private RedirectAttributes redirectAttrs;

    @Test
    public void performValidRegisterTest()
    {
    	
    	Interface onlineBookingSystem = Model.getModel();
    	Customer before = onlineBookingSystem.getCustomerByUsername("test");
    	Assertions.assertNull(before);
    	
        registerController.register("test", "test", "test", "test@email", "test", "test", redirectAttrs);
        
        Customer after = onlineBookingSystem.getCustomerByUsername("test");
        Assertions.assertNotNull(after);
        
       
    }
    
    
    
    
}
