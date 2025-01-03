package track.pro.timesheet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import track.pro.tasks.entites.Task;
import track.pro.timesheet.entities.Timesheet;
import track.pro.timesheet.services.TimesheetService;
import track.pro.user.entites.User;

@Controller

@RequestMapping("/timesheet")
public class TimesheetController {

	@Autowired
	private TimesheetService timesheetService;

	/*
	 * @GetMapping("/openTimesheetPage") public String openTimesheetPage(Model
	 * model) {
	 * 
	 * List<Task> listOfTasks = timesheetService.getAllTasks();
	 * 
	 * model.addAttribute("listOfTasks", listOfTasks);
	 * 
	 * List<User> listOfUsers = timesheetService.getAllUsers();
	 * 
	 * model.addAttribute("listOfUsers", listOfUsers);
	 * 
	 * return "timesheet/timesheet"; }
	 * 
	 * @PostMapping("/fillTimesheet") public String fillTimeSheet(@ModelAttribute
	 * Timesheet timesheet, Model model) { try {
	 * System.out.println("Received timesheet data: " + timesheet);
	 * timesheetService.fillTimesheet(timesheet); model.addAttribute("message",
	 * "Timesheet filled successfully!"); return
	 * "redirect:/timesheet/openTimesheetPage?success"; } catch (Exception e) {
	 * model.addAttribute("errorMessage", "Error filling timesheet: " +
	 * e.getMessage()); return "error"; } }
	 */
	
	
	
	@GetMapping("/openTimesheetPage")
    public String openTimesheetPage(@RequestParam("user_name") String username, Model model) {
        int userId = timesheetService.getUserIdByUsername(username);
        List<Task> assignedTasks = timesheetService.getTasksByUserId(userId);
        User user = timesheetService.getUserById(userId);
        model.addAttribute("listOfTasks", assignedTasks);
        model.addAttribute("user", user);
        model.addAttribute("user_name", username);
        return "timesheet/timesheet";
    }

	@PostMapping("/fillTimesheet")
	public String fillTimesheet(@RequestParam("user_name") String username,
	                            @RequestParam("task_id") int taskId,
	                            @RequestParam("date") String date,
	                            @RequestParam("hours_logged") Double hoursLogged,
	                            Model model) {
	    int userId = timesheetService.getUserIdByUsername(username);
	    Timesheet timesheet = new Timesheet();
	    timesheet.setTask_id(taskId);
	    timesheet.setUser_id(userId);
	    timesheet.setDate(date);
	    timesheet.setHours_logged(hoursLogged);
	    timesheet.setStatus(false); // Assuming status is false when filled
	    timesheetService.fillTimesheet(timesheet);
	    return "redirect:/timesheet/openTimesheetPage?user_name=" + username;
	}
    
    
    
	@GetMapping("/viewAllTimesheet")
	public ModelAndView viewAllTimesheet(ModelAndView mView) {
		List<Timesheet> listOfTimesheet = timesheetService.getAllTimesheet();
		mView.addObject("listOfTimesheet", listOfTimesheet);
		mView.setViewName("manager/timesheet_list");
		return mView;
	}

	@PostMapping("/delete")
	public String deleteTimesheet(@RequestParam int timesheetId, RedirectAttributes redirectAttributes) {
		timesheetService.deleteTimesheet(timesheetId);
		redirectAttributes.addFlashAttribute("success", "Timesheet deleted successfully!");
		return "redirect:/timesheet/viewAllTimesheet";
	}

	@GetMapping("/toggleAuthority/{task_id}")
	public String toggleAuthority(@PathVariable String task_id) {
		int id = Integer.parseInt(task_id);
		timesheetService.updateStatus(id);
		return "redirect:/timesheet/viewAllTimesheet";
	}
}
