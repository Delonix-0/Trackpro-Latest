package track.pro.tasks.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import track.pro.createtask.services.CreateTaskService;
import track.pro.project.entites.Project;
import track.pro.tasks.entites.Task;
import track.pro.tasks.services.TaskService;
import track.pro.timesheet.repository.TimesheetRepository;
import track.pro.timesheet.services.TimesheetService;
import track.pro.user.entites.User;
import track.pro.user.services.UserService;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	CreateTaskService createtaskService;
	@Autowired
	TimesheetService timesheetService;

	@Autowired
	UserService userService;

	@Autowired
	TaskService taskService;

	@GetMapping("/opencreateTaskPage")
	public String openCreateTaskPage(@RequestParam("user_name") String username, Model model) {
		int user_id = userService.getUserIdByUserName(username);
		System.out.println(user_id);
		User currentUser = timesheetService.getUserById(user_id);
		model.addAttribute("currentUser", currentUser);

		List<Project> userProjects = createtaskService.getProjectsByUserId(user_id);
		model.addAttribute("userProjects", userProjects);

		List<User> listOfEmployees = createtaskService.getAllEmployee();
		model.addAttribute("listOfEmployees", listOfEmployees);
		model.addAttribute("user_name", username);

		return "task/createtask";
	}

	@GetMapping("/openTaskPage")
	public String openTaskPage(@RequestParam("user_name") String username, Model model) {

		// fetching tasks from database
		int userId = timesheetService.getUserIdByUsername(username);
		List<Task> assignedTasks = timesheetService.getTasksByUserId(userId);
		model.addAttribute("listOfTasks", assignedTasks);
		return "task/task";
	}

	@PostMapping("/updateTask")
	public String updateTask(@ModelAttribute Task task, @RequestParam("user_name") String username, Model model) {
	    try {
	        // Fetch the existing task from the database
	        Task existingTask = taskService.getTaskById(task.getTaskId());
	        System.out.println("Existing Task: " + existingTask);

	        if (task.getStartTime() == null || task.getCompTime() == null) {
	            model.addAttribute("errorMessage", "Start time or completion time is null");
	            return "error";
	        }

	        // Update the specified fields
	        existingTask.setStartTime(task.getStartTime());
	        existingTask.setCompTime(task.getCompTime());

	        // Calculate the duration
	        long durationInMillis = java.time.Duration.between(java.time.LocalDateTime.parse(task.getStartTime()),
	                java.time.LocalDateTime.parse(task.getCompTime())).toMillis();
	        BigDecimal durationInHours = BigDecimal.valueOf(durationInMillis / (1000.0 * 60 * 60));
	        existingTask.setDuration(durationInHours);
	        System.out.println("Updated Task: " + existingTask);

	        // Save the updated task
	        taskService.updateTask(existingTask);
	        return "redirect:/task/openTaskPage?user_name=" + username + "&success=true";
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "Error updating task: " + e.getMessage());
	        return "error";
	    }
	}
	

	@PostMapping("/createtask")
	public String createTask(@RequestParam("user_name") String username, @ModelAttribute Task task, Model model) {
	    System.out.println("Received user_name: " + username); // Log the received user_name

	    int user_id = userService.getUserIdByUserName(username);
	    System.out.println("User ID: " + user_id); // Log the user ID

	    task.setCreatedBy(user_id);
	    createtaskService.addtaskUser(task);

	    return "redirect:/task/opencreateTaskPage?user_name=" + username + "&success=true";
	}

	@PostMapping("/delete")
	public String deleteTask(@RequestParam int taskId, RedirectAttributes redirectAttributes) {
		createtaskService.deleteTask(taskId);
		redirectAttributes.addFlashAttribute("success", "Task deleted successfully!");
		return "redirect:/task/viewAllTask";
	}

	@GetMapping("/taskManagement")
	public String taskManagement() {
		return "task/task_management";
	}

}