package track.pro.leaves.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import track.pro.leaves.entites.Leaves;
import track.pro.leaves.entites.LeaveBalance;
import track.pro.leaves.services.LeavesService;
import track.pro.user.services.UserService;

import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

@Controller
public class LeavesController {

	@Autowired
	private LeavesService leavesService;

	@Autowired
	private UserService userService;

	// Fetch leave balance based on user_name
	@GetMapping("/leaves_management")
	public String openLeaveRequestPage(@RequestParam("user_name") String user_name, Model model) {
		int user_id = userService.getUserIdByUserName(user_name);
		LeaveBalance lb = leavesService.getLeaveBalance(user_id);
		model.addAttribute("leaveBalance", lb);
		model.addAttribute("user_name", user_name);
		return "leaves/leaveDashboard";
	}

	// Submit leave request based on user_name
	@PostMapping("/submitleaverequest")
	public String submitLeaveRequest(@RequestParam("user_name") String user_name, @ModelAttribute Leaves leave,
			Model model) {
		int user_id = userService.getUserIdByUserName(user_name);
		leave.setUser_id(user_id);
		leave.setStatus(false);
		leave.setRequested_at(new Timestamp(System.currentTimeMillis()));

		int daysRequested = leavesService.calculateLeaveDays(leave.getStart_date(), leave.getEnd_date());
		LeaveBalance leaveBalance = leavesService.getLeaveBalance(user_id);

		if (leaveBalance.getRemaining_leaves() < daysRequested) {
			model.addAttribute("errorMessage", "Insufficient leave balance.");
			model.addAttribute("userName", user_name);
			return "leaves/leaveDashboard";
		}

		leavesService.submitLeaveRequest(leave);
		return "redirect:/leaves_management?user_name=" + user_name + "&success=true";
	}

	// Fetch all leave requests
	@GetMapping("/leaves")
	public String getAllLeaveRequests(Model model) {
		model.addAttribute("leaveRequests", leavesService.fetchAllLeaveRequests());
		return "super_admin/leaves_list";
	}

	// Toggle leave status and update remaining leaves
	@GetMapping("/toggleAuthority/{leaveId}")
	public String toggleAuthority(@PathVariable String leaveId) {
		int id = Integer.parseInt(leaveId);
		leavesService.updateStatus(id);
		return "redirect:/leaves";
	}

	@GetMapping("/leaveInformation")
	public String getLeaveInformation(@RequestParam("user_name") String user_name, Model model) {
		System.out.println("=== Leave Information Request ===");
		System.out.println("Received user_name: " + user_name);

		try {
			// Get user_id from user_name
			System.out.println("Attempting to get user_id for username: " + user_name);
			int user_id = userService.getUserIdByUserName(user_name);
			System.out.println("Found user_id: " + user_id);

			// Fetch leave information for the user
			System.out.println("Fetching leave information for user_id: " + user_id);
			List<Leaves> leaveInformation = leavesService.fetchAllLeaveRequestsByUserId(user_id);
			System.out.println("Retrieved leave information size: " +
					(leaveInformation != null ? leaveInformation.size() : "null"));
			System.out.println("Leave information : " + leaveInformation.get(0).getLeave_id());
			System.out.println("Leave information : " + leaveInformation.get(0).getUser_id());
			System.out.println("Leave information : " + leaveInformation.get(0).getLeave_type());
			System.out.println("Leave information : " + leaveInformation.get(0).getStart_date());
			System.out.println("Leave information : " + leaveInformation.get(0).getEnd_date());
			System.out.println("Leave information : " + leaveInformation.get(0).isStatus());
			System.out.println("Leave information : " + leaveInformation.get(0).getRequested_at());
			// Add to model
			model.addAttribute("leaveInformation", leaveInformation);
			model.addAttribute("user_name", user_name);

			System.out.println("Successfully prepared model, returning view");
			return "leaves/leaveInformation";
		} catch (Exception e) {
			System.out.println("=== Error in Leave Information ===");
			System.out.println("Error type: " + e.getClass().getName());
			System.out.println("Error message: " + e.getMessage());
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error fetching leave information: " + e.getMessage());
			return "leaves/leaveInformation";
		}
	}
}