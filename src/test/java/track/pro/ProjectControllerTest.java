package track.pro;
 
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
 
import java.util.Arrays;
import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import track.pro.project.controller.ProjectController;
import track.pro.project.entites.Project;
import track.pro.project.services.ProjectService;
import track.pro.tasks.entites.Task;
import track.pro.user.entites.User;
 
public class ProjectControllerTest {
 
	private MockMvc mockMvc;
 
	@Mock
	private ProjectService projectService;
 
	@InjectMocks
	private ProjectController projectController;
 
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
	}
 
	@Test
	public void testOpenProjectPage() throws Exception {
		List<User> users = Arrays.asList(new User(), new User());
 
		when(projectService.getAllUsers()).thenReturn(users);
 
		mockMvc.perform(get("/project/openProjectPage")).andExpect(status().isOk())
				.andExpect(view().name("/project/project")).andExpect(model().attributeExists("listOfUsers"));
	}
 
	@Test
	public void testProjectSuccess() throws Exception {
		Project project = new Project();
 
		mockMvc.perform(post("/project/project").flashAttr("project", project)).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/project/openProjectPage?success"));
	}
 
	@Test
	public void testProjectFailure() throws Exception {
		Project project = new Project();
 
		when(projectService.insertProject(project)).thenThrow(new RuntimeException("Error"));
 
		mockMvc.perform(post("/project/project").flashAttr("project", project)).andExpect(status().isOk())
				.andExpect(view().name("error")).andExpect(model().attributeExists("errorMessage"));
	}
 
	@Test
	public void testViewAllProjects() throws Exception {
		List<Project> projects = Arrays.asList(new Project(), new Project());
 
		when(projectService.getAllProject()).thenReturn(projects);
 
		mockMvc.perform(get("/project/viewAllProject")).andExpect(status().isOk())
				.andExpect(view().name("super_admin/project_list"))
				.andExpect(model().attributeExists("listofProjects"));
	}
 
	@Test
	public void testViewAllProjectstask() throws Exception {
		List<Project> projects = Arrays.asList(new Project(), new Project());
 
		when(projectService.getAllProject()).thenReturn(projects);
 
		mockMvc.perform(get("/project/viewAllProjecttask")).andExpect(status().isOk())
				.andExpect(view().name("manager/filtertask")).andExpect(model().attributeExists("listofProjects"));
	}
 
	@Test
	public void testViewTasksByProject() throws Exception {
		List<Task> tasks = Arrays.asList(new Task(), new Task());
 
		when(projectService.getTasksByProjectId(1)).thenReturn(tasks);
 
		mockMvc.perform(post("/project/viewTasksByProject").param("projectId", "1")).andExpect(status().isOk())
				.andExpect(view().name("manager/task_list")).andExpect(model().attributeExists("listOfTasks"));
	}
 
	@Test
	public void testToggleAuthority() throws Exception {
		mockMvc.perform(get("/project/toggleAuthority/1")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/project/viewAllProject"));
	}
}