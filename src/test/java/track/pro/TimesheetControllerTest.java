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
import track.pro.tasks.entites.Task;
import track.pro.timesheet.controller.TimesheetController;
import track.pro.timesheet.entities.Timesheet;
import track.pro.timesheet.services.TimesheetService;
import track.pro.user.entites.User;
 
public class TimesheetControllerTest {
 
	private MockMvc mockMvc;
 
	@Mock
	private TimesheetService timesheetService;
 
	@InjectMocks
	private TimesheetController timesheetController;
 
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(timesheetController).build();
	}
 
	@Test
	public void testOpenTimesheetPage() throws Exception {
		List<Task> tasks = Arrays.asList(new Task(), new Task());
		List<User> users = Arrays.asList(new User(), new User());
 
		when(timesheetService.getAllTasks()).thenReturn(tasks);
		when(timesheetService.getAllUsers()).thenReturn(users);
 
		mockMvc.perform(get("/timesheet/openTimesheetPage")).andExpect(status().isOk())
				.andExpect(view().name("timesheet/timesheet")).andExpect(model().attributeExists("listOfTasks"))
				.andExpect(model().attributeExists("listOfUsers"));
	}
 
	@Test
	public void testFillTimesheetSuccess() throws Exception {
		Timesheet timesheet = new Timesheet();
 
		mockMvc.perform(post("/timesheet/fillTimesheet").flashAttr("timesheet", timesheet))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/timesheet/openTimesheetPage?success"));
	}
 
	@Test
	public void testFillTimesheetFailure() throws Exception {
		Timesheet timesheet = new Timesheet();
 
		when(timesheetService.fillTimesheet(timesheet)).thenThrow(new RuntimeException("Error"));
 
		mockMvc.perform(post("/timesheet/fillTimesheet").flashAttr("timesheet", timesheet)).andExpect(status().isOk())
				.andExpect(view().name("error")).andExpect(model().attributeExists("errorMessage"));
	}
 
	@Test
	public void testViewAllTimesheet() throws Exception {
		List<Timesheet> timesheets = Arrays.asList(new Timesheet(), new Timesheet());
 
		when(timesheetService.getAllTimesheet()).thenReturn(timesheets);
 
		mockMvc.perform(get("/timesheet/viewAllTimesheet")).andExpect(status().isOk())
				.andExpect(view().name("manager/timesheet_list")).andExpect(model().attributeExists("listOfTimesheet"));
	}
 
	@Test
	public void testDeleteTimesheet() throws Exception {
		mockMvc.perform(post("/timesheet/delete").param("timesheetId", "1")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/timesheet/viewAllTimesheet"));
	}
 
	@Test
	public void testToggleAuthority() throws Exception {
		mockMvc.perform(get("/timesheet/toggleAuthority/1")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/timesheet/viewAllTimesheet"));
	}
}