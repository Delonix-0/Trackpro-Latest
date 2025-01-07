package track.pro;
 
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
 
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import track.pro.leaves.controller.LeavesController;
import track.pro.leaves.entites.LeaveBalance;
import track.pro.leaves.entites.Leaves;
import track.pro.leaves.services.LeavesService;
import track.pro.user.services.UserService;
 
public class LeavesControllerTest {
 
	private MockMvc mockMvc;
 
	@Mock
	private LeavesService leavesService;
 
	@Mock
	private UserService userService;
 
	@InjectMocks
	private LeavesController leavesController;
 
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(leavesController).build();
	}
 
	@Test
	public void testOpenLeaveRequestPage() throws Exception {
		LeaveBalance leaveBalance = new LeaveBalance();
		leaveBalance.setRemaining_leaves(10);
 
		when(userService.getUserIdByUserName("rahul")).thenReturn(1);
		when(leavesService.getLeaveBalance(1)).thenReturn(leaveBalance);
 
		mockMvc.perform(get("/leaves_management").param("user_name", "rahul")).andExpect(status().isOk())
				.andExpect(view().name("leaves/leaveDashboard")).andExpect(model().attributeExists("leaveBalance"))
				.andExpect(model().attributeExists("user_name"));
	}
 
	@Test
	public void testSubmitLeaveRequestSuccess() throws Exception {
		Leaves leave = new Leaves();
		leave.setStart_date(Date.valueOf("2025-01-10"));
		leave.setEnd_date(Date.valueOf("2025-01-15"));
 
		when(userService.getUserIdByUserName("rahul")).thenReturn(1);
		when(leavesService.calculateLeaveDays(Date.valueOf("2025-01-10"), Date.valueOf("2025-01-15"))).thenReturn(6);
		LeaveBalance leaveBalance = new LeaveBalance();
		leaveBalance.setRemaining_leaves(10);
		when(leavesService.getLeaveBalance(1)).thenReturn(leaveBalance);
 
		mockMvc.perform(post("/submitleaverequest").param("user_name", "rahul").flashAttr("leave", leave))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/leaves_management?user_name=rahul"));
	}
 
	@Test
	public void testGetAllLeaveRequests() throws Exception {
		List<Leaves> leaveRequests = Arrays.asList(new Leaves(), new Leaves());
 
		when(leavesService.fetchAllLeaveRequests()).thenReturn(leaveRequests);
 
		mockMvc.perform(get("/leaves")).andExpect(status().isOk()).andExpect(view().name("super_admin/leaves_list"))
				.andExpect(model().attributeExists("leaveRequests"));
	}
 
	@Test
	public void testToggleAuthority() throws Exception {
		mockMvc.perform(get("/toggleAuthority/1")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/leaves"));
	}
}