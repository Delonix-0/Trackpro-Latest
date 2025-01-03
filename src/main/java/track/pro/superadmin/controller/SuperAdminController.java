package track.pro.superadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import track.pro.superadmin.service.SuperAdminService;
import track.pro.user.entites.User;
import track.pro.user.services.UserService;


@Controller
@RequestMapping("/superadmin")
public class SuperAdminController {
	@Autowired
	SuperAdminService superadminService;
	@Autowired
	UserService userService;

	@GetMapping("/viewAllManagers")
	public ModelAndView viewAllManagers(ModelAndView mView) {
		List<User> listOfManagers = superadminService.getAllManager();
		mView.addObject("listOfManagers", listOfManagers);
		mView.setViewName("super_admin/manager_list");
		return mView;
	}
	@GetMapping("/toggleAuthority/{user_id}")
	public String toggleAuthority(@PathVariable String user_id) {
		int id= Integer.parseInt(user_id);
		userService.updateAuthority(id);

		
		
		return  "redirect:/superadmin/viewAllManagers";
	}
	
}
