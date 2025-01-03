package track.pro.user.controller;

import java.util.List;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import track.pro.user.entites.Role;
import track.pro.user.entites.User;
import track.pro.user.services.UserService;

@Controller
@RequestMapping
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/openIndexPage")
	public String openIndexPage() {
		return "index";
	}

	@GetMapping("/openloginPage")
	public String openloginPage() {
		return "user/login";
	}

	@GetMapping("/managerDashboard")
	public String managerDashboard() {
		return "manager/manager";
	}

	@GetMapping("/superAdminDashboard")
	public String superAdminDashboard() {
		return "super_admin/admindashboard";
	}
	
	@GetMapping("/assosiateDashboard")
	public String assosiateDashboard() {
		return "assosiate/assosiate";
	}

	@GetMapping("/openRegistrationPage")
	public String openRegistrationPage(Model model) {

		List<Role> listOfRoles = userService.getAllRoles();

		model.addAttribute("listOfRoles", listOfRoles);

		return "user/registration";
	}

	@GetMapping("/openChangePasswordPage")
	public String openChangePasswordPage() {
		return "user/changePassword";
	}

	@PostMapping("/changePassword")
	public String changePassword(@RequestParam String user_name, @RequestParam String oldPassword,
			@RequestParam String newPassword, Model model) {
		Optional<User> optUser = userService.fetchUserBy(user_name);

		if (optUser.isEmpty()) {
			model.addAttribute("message", "User not found");
			return "user/changePassword";
		}

		User user = optUser.get();

		if (!userService.matchPassword(oldPassword, user)) {
			model.addAttribute("message", "Old password is incorrect");
			return "user/changePassword";
		}

		userService.updatePassword(user, newPassword);
		model.addAttribute("message", "Password changed successfully");
		return "user/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String user_name, @RequestParam String password, Model model,
			HttpServletResponse response) {

		String message = "";
		String destination = "";

		Optional<User> optUser = userService.fetchUserBy(user_name);

		if (optUser.isEmpty()) {
			model.addAttribute("message", "wrong username");
			return "user/login";
		}

		User registeredUser = optUser.get();

		if (!userService.matchPassword(password, registeredUser)) {
			model.addAttribute("message", "wrong password.");
			return "user/login";
		}

		// Set username in a cookie after successful login
		Cookie usernameCookie = new Cookie("username", user_name);
		usernameCookie.setMaxAge(24 * 60 * 60); // Cookie valid for 1 day
		usernameCookie.setPath("/"); // Available across the whole app
		response.addCookie(usernameCookie);

		model.addAttribute("registeredUser", registeredUser);
		int roleId = registeredUser.getRole_id();
		boolean isAuthorized = registeredUser.isIs_authorized();
		if (roleId == 1)
			return "super_admin/admindashboard";
		else if (isAuthorized && roleId == 2)
			return "manager/manager";
		else if (isAuthorized && roleId == 3)
			return "assosiate/assosiate";

		else {
			model.addAttribute("message", " Your Application is Pending");
			return "user/login";
		}

	}

	@PostMapping("/registration")
	public ModelAndView registration(@ModelAttribute User user, ModelAndView mView) {
		Optional<User> existingUserByUsername = userService.fetchUserBy(user.getUser_name());
		if (existingUserByUsername.isPresent()) {
			mView.setViewName("user/registration");
			mView.addObject("errorMessage", "User already exists.");
			List<Role> listOfRoles = userService.getAllRoles();

			mView.addObject("listOfRoles", listOfRoles);
			return mView;
		}

		// Check for existing email
		Optional<User> existingUserByEmail = userService.fetchUserByEmail(user.getEmail());
		if (existingUserByEmail.isPresent()) {
			mView.setViewName("user/registration");
			mView.addObject("errorMessage", "Email already exists.");
			List<Role> listOfRoles = userService.getAllRoles();
			mView.addObject("listOfRoles", listOfRoles);
			return mView;
		}

		// Check for existing mobile
		Optional<User> existingUserByMobile = userService.fetchUserByMobile(user.getMobile());
		if (existingUserByMobile.isPresent()) {
			mView.setViewName("user/registration");
			mView.addObject("errorMessage", "Mobile number already exists.");
			return mView;
		}

		int result = userService.registerUser(user);

		if (result > 0) {
			mView.setViewName("user/login");
			mView.addObject("message", "\n Registration success login to continue");
		} else {
			mView.setViewName("user/registration");
			mView.addObject("message", "\n Registration failed, Please try again!");

			List<Role> listOfRoles = userService.getAllRoles();

			mView.addObject("listOfRoles", listOfRoles);
		}
		return mView;
	}

}
