package track.pro;
 
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import track.pro.profile.controller.ProfileController;
import track.pro.profile.entites.Profile;
import track.pro.profile.service.ProfileService;
 
public class ProfileControllerTest {
 
	private MockMvc mockMvc;
 
	@Mock
	private ProfileService profileService;
 
	@InjectMocks
	private ProfileController profileController;
 
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
	}
 
	@Test
	public void testViewProfileSuccess() throws Exception {
		Profile profile = new Profile();
		profile.setFull_name("Amit Sharma");
		profile.setMobile("9876543210");
		profile.setEmail("amit.sharma@example.com");
		profile.setRole_id(1);
 
		when(profileService.getUserByUsername("amit_sharma")).thenReturn(profile);
 
		mockMvc.perform(get("/profile_management").param("user_name", "amit_sharma")).andExpect(status().isOk())
				.andExpect(view().name("profile/view")).andExpect(model().attributeExists("full_name"))
				.andExpect(model().attributeExists("mobile")).andExpect(model().attributeExists("email"))
				.andExpect(model().attributeExists("role_id"));
	}
 
	@Test
	public void testViewProfileUserNotFound() throws Exception {
		when(profileService.getUserByUsername("unknown_user")).thenReturn(null);
 
		mockMvc.perform(get("/profile_management").param("user_name", "unknown_user")).andExpect(status().isOk())
				.andExpect(view().name("error")).andExpect(model().attributeExists("error"));
	}
 
	@Test
	public void testShowUpdateProfileFormSuccess() throws Exception {
		Profile profile = new Profile();
		profile.setMobile("9876543210");
		profile.setEmail("amit.sharma@example.com");
		profile.setRole_id(1);
 
		when(profileService.getUserByUsername("amit_sharma")).thenReturn(profile);
 
		mockMvc.perform(get("/update_profile_form").param("user_name", "amit_sharma")).andExpect(status().isOk())
				.andExpect(view().name("profile/updateProfile")).andExpect(model().attributeExists("user_name"))
				.andExpect(model().attributeExists("mobile")).andExpect(model().attributeExists("email"))
				.andExpect(model().attributeExists("role_id"));
	}
 
	@Test
	public void testShowUpdateProfileFormUserNotFound() throws Exception {
		when(profileService.getUserByUsername("unknown_user")).thenReturn(null);
 
		mockMvc.perform(get("/update_profile_form").param("user_name", "unknown_user")).andExpect(status().isOk())
				.andExpect(view().name("error")).andExpect(model().attributeExists("error"));
	}
 
	@Test
	public void testUpdateProfileSuccess() throws Exception {
		Profile profile = new Profile();
		profile.setUser_name("amit_sharma");
 
		when(profileService.getUserByUsername("amit_sharma")).thenReturn(profile);
 
		MockMultipartFile profileImage = new MockMultipartFile("profile_image", "profile.jpg", "image/jpeg",
				new byte[0]);
		MockMultipartFile profileResume = new MockMultipartFile("profile_resume", "resume.pdf", "application/pdf",
				new byte[0]);
 
		mockMvc.perform(
				multipart("/update_profile").file(profileImage).file(profileResume).param("user_name", "amit_sharma")
						.param("mobile", "9876543210").param("email", "amit.sharma@example.com"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/profile_management?user_name=amit_sharma"));
	}
 
	@Test
	public void testUpdateProfileUserNotFound() throws Exception {
		when(profileService.getUserByUsername("unknown_user")).thenReturn(null);
 
		MockMultipartFile profileImage = new MockMultipartFile("profile_image", "profile.jpg", "image/jpeg",
				new byte[0]);
		MockMultipartFile profileResume = new MockMultipartFile("profile_resume", "resume.pdf", "application/pdf",
				new byte[0]);
 
		mockMvc.perform(multipart("/update_profile").file(profileImage).file(profileResume)
				.param("user_name", "unknown_user").param("mobile", "9876543210").param("email", "unknown@example.com"))
				.andExpect(status().isOk()).andExpect(view().name("error")).andExpect(model().attributeExists("error"));
	}
 
	@Test
	public void testUpdateProfileError() throws Exception {
		Profile profile = new Profile();
		profile.setUser_name("amit_sharma");
 
		when(profileService.getUserByUsername("amit_sharma")).thenReturn(profile);
 
		MockMultipartFile profileImage = new MockMultipartFile("profile_image", "profile.jpg", "image/jpeg",
				new byte[0]);
		MockMultipartFile profileResume = new MockMultipartFile("profile_resume", "resume.pdf", "application/pdf",
				new byte[0]);
 
		mockMvc.perform(
				multipart("/update_profile").file(profileImage).file(profileResume).param("user_name", "amit_sharma")
						.param("mobile", "9876543210").param("email", "amit.sharma@example.com"))
				.andExpect(status().isOk()).andExpect(view().name("error")).andExpect(model().attributeExists("error"));
	}
}