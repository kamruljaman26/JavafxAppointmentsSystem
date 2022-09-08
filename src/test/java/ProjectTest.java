import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * CRUD operation testing for User Table
 */
public class ProjectTest {

    private ProjectDAO projectDAO;

    @BeforeEach
    public void setup() {
        projectDAO = new ProjectDAO();
    }

    @Test
    public void createUserTest() {
        Project project = new Project();
        project.setName("Project 11");
        project.setBudget(120000);
        project.setDeadline(LocalDate.of(2023, 12, 12));

        Project project1 = projectDAO.create(project);
        assertEquals(project, project1);
    }

    @Test
    public void updateUserTest() {
        Project project = new Project();
        project.setName("Project 12");
        project.setBudget(1200);
        project.setDeadline(LocalDate.of(2024, 10, 12));

        Project project1 = projectDAO.create(project);

        project1.setName("Project 111");
        projectDAO.update(project1);

        assertEquals(projectDAO.findOne(project1.getId()).getName(), project1.getName());
    }

    @Test
    public void deleteUserTest() {
        Project project = new Project();
        project.setName("Project D");
        project.setBudget(120);
        project.setDeadline(LocalDate.of(2024, 10, 12));

        Project project1 = projectDAO.create(project);
        assertEquals(project, project1);

        projectDAO.delete(project1);
        assertNull(projectDAO.findOne(project1.getId()));
    }
}
