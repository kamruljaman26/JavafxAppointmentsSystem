
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * CRUD operation testing for User Table
 */
public class EmployeeTest {

    private EmployeeDAO employeeDAO;
    private UserDAO userDAO;

    @BeforeEach
    public void setup() {
        employeeDAO = new EmployeeDAO();
        userDAO = new UserDAO();
    }

    @Test
    public void createEmployeeTest() {
        User user1 = new User();
        user1.setMobileNum("12345678");
        user1.setName("John Smit");
        user1.setPassword("12345");
        user1.setRole(Role.EMPLOYEE);
        user1.setUsername("jhonsmit12345");
        User user = userDAO.create(user1);

        Employee employee = new Employee();
        employee.setSalary(1234);
        employee.setDesignation("CEO");
        employee.setDepartment("IT");
        employee.setUser(user);

        Employee employee1 = employeeDAO.create(employee);

        assertEquals(employee1, employeeDAO.getByUsername("jhonsmit12345"));

    }


    @Test
    public void deleteEmployeeTest() {
        Employee jhonsmit12345 = employeeDAO.getByUsername("jhonsmit12345");
        User user = jhonsmit12345.getUser();

        employeeDAO.delete(jhonsmit12345);
        userDAO.delete(user);

        assertNull(employeeDAO.getByUsername("jhonsmit12345"));
    }

}
