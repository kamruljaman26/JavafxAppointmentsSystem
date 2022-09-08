import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * CRUD operation testing for User Table
 */
public class UserTest {

    private UserDAO userDAO;

    @BeforeEach
    public void setup() {
        userDAO = new UserDAO();
    }

    @Test
    public void createUserTest(){
        User user1 = new User();
        user1.setMobileNum("12345678");
        user1.setName("Jhon Due");
        user1.setPassword("1234");
        user1.setRole(Role.EMPLOYEE);
        user1.setUsername("jhondue1");

        // create user
        User createdUser = userDAO.create(user1);
        User one = userDAO.findOne(createdUser.getId());
        assertEquals(createdUser, one);
    }

    @Test
    public void updateUserTest(){
        User user1 = new User();
        user1.setMobileNum("12345678");
        user1.setName("Jhon Due");
        user1.setPassword("1234");
        user1.setRole(Role.EMPLOYEE);
        user1.setUsername("jhondue12");
        userDAO.create(user1);

        // update and test user
        User user = userDAO.getUserByUsername("jhondue12");
        user.setName("Jhon Akkarm");
        userDAO.update(user);

        assertEquals(user, userDAO.getUserByUsername("jhondue12"));
    }

    @Test
    public void readUserTest(){
        User user1 = new User();
        user1.setMobileNum("12345678");
        user1.setName("John Smit");
        user1.setPassword("12345");
        user1.setRole(Role.EMPLOYEE);
        user1.setUsername("jhonsmit1");
        User user = userDAO.create(user1);

        assertEquals(user, userDAO.getUserByUsername("jhonsmit1"));
    }

    @Test
    public void deleteUserTest(){
        userDAO.delete(userDAO.getUserByUsername("jhonsmit1"));
        userDAO.delete(userDAO.getUserByUsername("jhondue12"));
        userDAO.delete(userDAO.getUserByUsername("jhondue1"));

        assertNull(userDAO.getUserByUsername("jhonsmit1"));
        assertNull(userDAO.getUserByUsername("jhondue12"));
        assertNull(userDAO.getUserByUsername("jhondue1"));
    }

}
