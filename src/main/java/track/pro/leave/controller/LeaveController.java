package track.pro.leave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import track.pro.leave.entities.Leave;
import track.pro.leave.services.LeaveService;

@Controller
@RequestMapping("/leave")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;

	@GetMapping("/openLeavePage")
	public String openLeavePage() {
		return "leave/leave";
	}

	@PostMapping("/applyLeave")
	public String applyLeave(@ModelAttribute Leave leave, Model model) {
		try {
			System.out.println("Received leave data: " + leave);
			leaveService.applyLeave(leave);
			model.addAttribute("message", "Leave applied successfully!");
			return "redirect:/leave/openLeavePage?success";
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error applying leave: " + e.getMessage());
			return "error";
		}
	}
}