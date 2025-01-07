package track.pro.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import track.pro.profile.entites.Profile;
import track.pro.profile.service.ProfileService;
import track.pro.user.services.UserService;

@Controller
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@GetMapping("/profile_management")
	public String viewProfile(@RequestParam("user_name") String user_name, Model model) {
		if (user_name == null || user_name.isEmpty()) {
			model.addAttribute("error", "Invalid username provided!");
			return "error";
		}

		Profile profile = profileService.getUserByUsername(user_name);
		if (profile == null) {
			model.addAttribute("error", "User not found!");
			return "error";
		}

		model.addAttribute("full_name", profile.getFull_name());
		model.addAttribute("mobile", profile.getMobile());
		model.addAttribute("email", profile.getEmail());
		model.addAttribute("role_id", profile.getRole_id());

		if (profile.getProfileimg() != null) {
			String profileImageBase64 = profileService.convertBlobToBase64(profile.getProfileimg());
			model.addAttribute("profile_image", profileImageBase64);
		}

		if (profile.getProfileresume() != null) {
			String profileResumeBase64 = profileService.convertBlobToBase64(profile.getProfileresume());
			model.addAttribute("profile", profileResumeBase64);
		}

		return "profile/view";
	}

	@GetMapping("/update_profile_form")
	public String showUpdateProfileForm(@RequestParam("user_name") String user_name, Model model) {
		if (user_name == null || user_name.isEmpty()) {
			model.addAttribute("error", "Invalid username provided!");
			return "error";
		}

		Profile profile = profileService.getUserByUsername(user_name);
		if (profile == null) {
			model.addAttribute("error", "User not found!");
			return "error";
		}

		model.addAttribute("user_name", user_name);
		model.addAttribute("full_name", profile.getFull_name());
		model.addAttribute("mobile", profile.getMobile());
		model.addAttribute("email", profile.getEmail());
		model.addAttribute("role_id", profile.getRole_id());

		return "profile/update";
	}

	@PostMapping("/update_profile")
	public String updateProfile(@RequestParam("user_name") String username, @RequestParam("mobile") String mobile,
	                            @RequestParam("email") String email, @RequestParam("profile_image") MultipartFile profileImage,
	                            @RequestParam("profile_resume") MultipartFile profileResume, Model model) {
	    Profile profile = profileService.getUserByUsername(username);
	    if (profile == null) {
	        model.addAttribute("error", "User not found!");
	        return "error";
	    }

	    profile.setMobile(mobile);
	    profile.setEmail(email);

	    try {
	        if (!profileImage.isEmpty()) {
	            profile.setProfileimg(profileImage.getBytes());
	        }
	        if (!profileResume.isEmpty()) {
	            profile.setProfileresume(profileResume.getBytes());
	        }
	    } catch (Exception e) {
	        model.addAttribute("error", "Error updating profile!");
	        return "error";
	    }

	    profileService.updateProfile(profile);
	    return "redirect:/update_profile_form?user_name=" + username + "&success=true";
	}
}