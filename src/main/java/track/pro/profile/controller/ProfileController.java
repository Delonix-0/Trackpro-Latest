//package track.pro.profile.controller;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import track.pro.user.entites.User;
//import track.pro.user.services.UserService;
//
//
//@Controller
//public class ProfileController {
//
// @Autowired
// private UserService userService;
//
// @GetMapping("/profile_management")
// public String viewProfile(@RequestParam("user_name") String user_name, Model model) {
// // Validate the username
// if (user_name == null || user_name.isEmpty()) {
// model.addAttribute("error", "Invalid username provided!");
// return "error";
// }
//
// // Fetch user details from service
// User user = userService.getUserByUsername(user_name);
// if (user == null) {
// model.addAttribute("error", "User not found!");
// return "error";
// }
//model.addAttribute("full_name",user.getFull_name());
//model.addAttribute("mobile",user.getMobile());
//model.addAttribute("email",user.getEmail());
//
//if(user.getProfileimg() != null) {
//	String profileImageBase64 = userService.convertBlobToBase64(user.getProfileimg());
//	model.addAttribute("profile_image",profileImageBase64);
//}
//if(user.getProfileresume()!=null) {
//	String profileImageBase64 = userService.convertBlobToBase64(user.getProfileresume());
//	model.addAttribute("profile",profileImageBase64);
//}
//return "profile/view";
//
//}
//}



package track.pro.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
