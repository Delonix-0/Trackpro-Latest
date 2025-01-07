package track.pro.assosiate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import track.pro.assosiate.service.AssosiateService;
import track.pro.user.entites.User;
import track.pro.user.services.UserService;

@Controller
@RequestMapping("/assosiate")
public class AssosiateController {
	@Autowired
	AssosiateService assosiateService;

	@Autowired
	UserService userService;

	@GetMapping("/viewAllAssosiate")
	public ModelAndView viewAllTask(ModelAndView mView) {
		List<User> listOfUser = assosiateService.getAllUser();
		mView.addObject("listOfUser", listOfUser);
		mView.setViewName("assosiate/assosiate_list");
		return mView;
	}

	@GetMapping("/toggleAuthority/{user_id}")
	public String toggleAuthority(@PathVariable String user_id) {
		int id = Integer.parseInt(user_id);
		userService.updateAuthority(id);

		return "redirect:/assosiate/viewAllAssosiate";
	}

}
