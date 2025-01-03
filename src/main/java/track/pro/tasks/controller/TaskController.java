
 
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
import track.pro.timesheet.services.TimesheetService;
import track.pro.createtask.services.CreateTaskService;
import track.pro.project.entites.Project;
import track.pro.tasks.entites.Task;
import track.pro.tasks.services.TaskService;
import track.pro.user.entites.User;
 
@Controller
@RequestMapping("/task")
public class TaskController {
	@Autowired
	CreateTaskService createtaskService;
 
	@Autowired
	TaskService taskService;

	@Autowired
	private TimesheetService timesheetService;
	
	
	@GetMapping("/opencreateTaskPage")
	public String opencreateTaskPage(Model model) {

		List<User> listOfUsers = createtaskService.getAllUser();
		model.addAttribute("listOfUsers", listOfUsers);

		List<User> listOfEmployees = createtaskService.getAllEmployee();
		model.addAttribute("listOfEmployees", listOfEmployees);

		List<Project> listofProjects = createtaskService.getAllProject();
		model.addAttribute("listofProjects", listofProjects);

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
	public String updateTask(@ModelAttribute Task task, Model model) {
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
	        long durationInMillis = java.time.Duration.between(
	            java.time.LocalDateTime.parse(task.getStartTime()),
	            java.time.LocalDateTime.parse(task.getCompTime())
	        ).toMillis();
	        BigDecimal durationInHours = BigDecimal.valueOf(durationInMillis / (1000.0 * 60 * 60));
	        existingTask.setDuration(durationInHours);
	        System.out.println("Updated Task: " + existingTask);

	        // Save the updated task
	        taskService.updateTask(existingTask);
	        return "redirect:/task/openTaskPage?success";
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "Error updating task: " + e.getMessage());
	        return "error";
	    }
	}
	
	@PostMapping("/createtask")
	public String task(@ModelAttribute Task task, Project project, Model model) {

		createtaskService.addtaskUser(task);
		List<User> listOfUsers = createtaskService.getAllUser();
		model.addAttribute("listOfUsers", listOfUsers);

		List<User> listOfEmployees = createtaskService.getAllEmployee();
		model.addAttribute("listOfEmployees", listOfEmployees);

		
		List<Project> listofProjects = createtaskService.getAllProject();

		model.addAttribute("listofProjects", listofProjects);
		return "task/createtask";
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