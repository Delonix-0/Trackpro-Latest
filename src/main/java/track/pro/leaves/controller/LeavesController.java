/*
 * package track.pro.leaves.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * import track.pro.leaves.entites.Leaves; import
 * track.pro.leaves.entites.LeaveBalance; import
 * track.pro.leaves.services.LeavesService; import
 * track.pro.user.services.UserService;
 * 
 * import java.sql.Timestamp; import java.util.List;
 * 
 * @Controller public class LeavesController {
 * 
 * @Autowired private LeavesService leavesService;
 * 
 * @Autowired private UserService userService;
 * 
 * // Fetch leave balance based on user_name
 * 
 * @GetMapping("/leaves_management") public String
 * openLeaveRequestPage(@RequestParam("user_name") String user_name, Model
 * model) {
 * 
 * int user_id = userService.getUserIdByUserName(user_name);
 * 
 * LeaveBalance lb = leavesService.getLeaveBalance(user_id);
 * 
 * model.addAttribute("leaveBalance", lb); model.addAttribute("user_name",
 * user_name);
 * 
 * return "leaves/leaveDashboard"; }
 * 
 * // Submit leave request based on user_name
 * 
 * @PostMapping("/submitleaverequest") public String
 * submitLeaveRequest(@RequestParam("user_name") String
 * user_name,@ModelAttribute Leaves leave, Model model) {
 * System.out.println(user_name); int user_id =
 * userService.getUserIdByUserName(user_name);
 * 
 * System.out.println("Fetched user_id: " + user_id); leave.setUser_id(user_id);
 * leave.setStatus(false); leave.setRequested_at(new
 * Timestamp(System.currentTimeMillis()));
 * 
 * int daysRequested = leavesService.calculateLeaveDays(leave.getStart_date(),
 * leave.getEnd_date()); LeaveBalance leaveBalance =
 * leavesService.getLeaveBalance(user_id);
 * 
 * if (leaveBalance.getRemaining_leaves() < daysRequested) {
 * model.addAttribute("errorMessage", "Insufficient leave balance.");
 * model.addAttribute("userName", user_name); return "leaves/leaveDashboard"; }
 * 
 * leavesService.submitLeaveRequest(leave); return
 * "redirect:/leaves_management?user_name=" + user_name; }
 * 
 * @GetMapping("/leaves") public String getAllLeaveRequests(Model model) {
 * model.addAttribute("leaveRequests", leavesService.fetchAllLeaveRequests());
 * return "super_admin/leaves_list"; }
 * 
 * @GetMapping("/toggleAuthority/{leaveId}") public String
 * toggleAuthority(@PathVariable String leaveId) { int id =
 * Integer.parseInt(leaveId); leavesService.updateStatus(id); return
 * "redirect:/leaves"; }
 * 
 * 
 * 
 * }
 */

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
    public String submitLeaveRequest(@RequestParam("user_name") String user_name, @ModelAttribute Leaves leave, Model model) {
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
        return "redirect:/leaves_management?user_name=" + user_name;
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
}