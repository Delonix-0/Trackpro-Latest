package track.pro.user.controller;
 
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
 
import track.pro.user.entites.User;
 
@Controller
@RequestMapping
public class UserController {
	@GetMapping("/openIndexPage")
	public String openIndexPage() {
		return "index";
	}
	@GetMapping("/openloginPage")
	public String openloginPage() {
		return "user/login";
	}
	@GetMapping("/openRegistrationPage")
	public String openRegistrationPage() {
		return "user/registration";
	}
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password) {
 
		System.out.println("\n username : " + username);
		System.out.println("\n password : " + password);		
		return "user/login";	
}
	@PostMapping("/registration")
	public Model registration(@ModelAttribute User user,Model mView) {
 
		System.out.println(user);
		return mView;
		
	
}
}