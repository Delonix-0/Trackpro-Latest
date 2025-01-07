package track.pro;
 
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
 
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import track.pro.user.controller.UserController;
import track.pro.user.entites.User;
import track.pro.user.services.UserService;
 
public class UserControllerTest {
 
	private MockMvc mockMvc;
 
	@Mock
	private UserService userService;
 
	@InjectMocks
	private UserController userController;
 
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
 
	@Test
	public void testOpenIndexPage() throws Exception {
		mockMvc.perform(get("/openIndexPage")).andExpect(status().isOk()).andExpect(view().name("index"));
	}
 
	@Test
	public void testOpenLoginPage() throws Exception {
		mockMvc.perform(get("/openloginPage")).andExpect(status().isOk()).andExpect(view().name("user/login"));
	}
 
	@Test
	public void testManagerDashboard() throws Exception {
		mockMvc.perform(get("/managerDashboard")).andExpect(status().isOk()).andExpect(view().name("manager/manager"));
	}
 
	@Test
	public void testSuperAdminDashboard() throws Exception {
		mockMvc.perform(get("/superAdminDashboard")).andExpect(status().isOk())
				.andExpect(view().name("super_admin/admindashboard"));
	}
 
	@Test
	public void testAssosiateDashboard() throws Exception {
		mockMvc.perform(get("/assosiateDashboard")).andExpect(status().isOk())
				.andExpect(view().name("assosiate/assosiate"));
	}
 
	@Test
	public void testOpenRegistrationPage() throws Exception {
		mockMvc.perform(get("/openRegistrationPage")).andExpect(status().isOk())
				.andExpect(view().name("user/registration")).andExpect(model().attributeExists("listOfRoles"));
	}
 
	@Test
	public void testLoginSuccess() throws Exception {
		User user = new User();
		user.setUser_name("testUser");
		user.setPassword("testPassword");
		user.setRole_id(1);
		user.setIs_authorized(true);
 
		when(userService.fetchUserBy("testUser")).thenReturn(Optional.of(user));
		when(userService.matchPassword("testPassword", user)).thenReturn(true);
 
		mockMvc.perform(post("/login").param("user_name", "testUser").param("password", "testPassword"))
				.andExpect(status().isOk()).andExpect(view().name("super_admin/admindashboard"));
	}
 
	@Test
	public void testLoginFailure() throws Exception {
		when(userService.fetchUserBy("wrongUser")).thenReturn(Optional.empty());
 
		mockMvc.perform(post("/login").param("user_name", "wrongUser").param("password", "wrongPassword"))
				.andExpect(status().isOk()).andExpect(view().name("user/login"))
				.andExpect(model().attributeExists("message"));
	}
 
	@Test
	public void testRegistrationSuccess() throws Exception {
		User user = new User();
		user.setUser_name("newUser");
		user.setEmail("newUser@example.com");
		user.setMobile("1234567890");
 
		when(userService.fetchUserBy("newUser")).thenReturn(Optional.empty());
		when(userService.fetchUserByEmail("newUser@example.com")).thenReturn(Optional.empty());
		when(userService.fetchUserByMobile("1234567890")).thenReturn(Optional.empty());
		when(userService.registerUser(user)).thenReturn(1);
 
		mockMvc.perform(post("/registration").flashAttr("user", user)).andExpect(status().isOk())
				.andExpect(view().name("user/login")).andExpect(model().attributeExists("message"));
	}
 
	@Test
	public void testRegistrationFailure() throws Exception {
		User user = new User();
		user.setUser_name("existingUser");
		user.setEmail("existingUser@example.com");
		user.setMobile("1234567890");
 
		when(userService.fetchUserBy("existingUser")).thenReturn(Optional.of(user));
 
		mockMvc.perform(post("/registration").flashAttr("user", user)).andExpect(status().isOk())
				.andExpect(view().name("user/registration")).andExpect(model().attributeExists("errorMessage"));
	}
}