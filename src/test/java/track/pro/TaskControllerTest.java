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
 
import track.pro.createtask.services.CreateTaskService;
import track.pro.project.entites.Project;
import track.pro.tasks.controller.TaskController;
import track.pro.tasks.entites.Task;
import track.pro.tasks.services.TaskService;
import track.pro.user.entites.User;
 
public class TaskControllerTest {
 
	private MockMvc mockMvc;
 
	@Mock
	private CreateTaskService createtaskService;
 
	@Mock
	private TaskService taskService;
 
	@InjectMocks
	private TaskController taskController;
 
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
	}
 
	@Test
	public void testOpenCreateTaskPage() throws Exception {
		List<User> users = Arrays.asList(new User(), new User());
		List<User> employees = Arrays.asList(new User(), new User());
		List<Project> projects = Arrays.asList(new Project(), new Project());
 
		when(createtaskService.getAllUser()).thenReturn(users);
		when(createtaskService.getAllEmployee()).thenReturn(employees);
		when(createtaskService.getAllProject()).thenReturn(projects);
 
		mockMvc.perform(get("/task/opencreateTaskPage")).andExpect(status().isOk())
				.andExpect(view().name("task/createtask")).andExpect(model().attributeExists("listOfUsers"))
				.andExpect(model().attributeExists("listOfEmployees"))
				.andExpect(model().attributeExists("listofProjects"));
	}
 
	@Test
	public void testOpenTaskPage() throws Exception {
		List<Task> tasks = Arrays.asList(new Task(), new Task());
 
		when(taskService.getAllTasks()).thenReturn(tasks);
 
		mockMvc.perform(get("/task/openTaskPage")).andExpect(status().isOk()).andExpect(view().name("task/task"))
				.andExpect(model().attributeExists("listOfTasks"));
	}
 
	@Test
	public void testUpdateTaskSuccess() throws Exception {
		Task task = new Task();
		task.setTaskId(1);
		task.setStartTime("2023-01-01T10:00:00");
		task.setCompTime("2023-01-01T12:00:00");
 
		Task existingTask = new Task();
		existingTask.setTaskId(1);
 
		when(taskService.getTaskById(1)).thenReturn(existingTask);
 
		mockMvc.perform(post("/task/updateTask").flashAttr("task", task)).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/task/openTaskPage?success"));
	}
 
	@Test
	public void testUpdateTaskFailure() throws Exception {
		Task task = new Task();
		task.setTaskId(1);
		task.setStartTime(null);
		task.setCompTime(null);
 
		mockMvc.perform(post("/task/updateTask").flashAttr("task", task)).andExpect(status().isOk())
				.andExpect(view().name("error")).andExpect(model().attributeExists("errorMessage"));
	}
 
	@Test
	public void testCreateTask() throws Exception {
		Task task = new Task();
		Project project = new Project();
 
		mockMvc.perform(post("/task/createtask").flashAttr("task", task).flashAttr("project", project))
				.andExpect(status().isOk()).andExpect(view().name("task/createtask"))
				.andExpect(model().attributeExists("listOfUsers")).andExpect(model().attributeExists("listOfEmployees"))
				.andExpect(model().attributeExists("listofProjects"));
	}
 
	@Test
	public void testDeleteTask() throws Exception {
		mockMvc.perform(post("/task/delete").param("taskId", "1")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/task/viewAllTask"));
	}
 
	@Test
	public void testTaskManagement() throws Exception {
		mockMvc.perform(get("/task/taskManagement")).andExpect(status().isOk())
				.andExpect(view().name("task/task_management"));
	}
}