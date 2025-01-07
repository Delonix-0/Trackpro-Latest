package track.pro.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import track.pro.createtask.services.CreateTaskService;
import track.pro.manager.service.ManagerService;
import track.pro.tasks.entites.Task;
import track.pro.tasks.services.TaskService;
import track.pro.timesheet.entities.Timesheet;

@Controller
@RequestMapping("/task")
public class ManagerController {
	@Autowired
	ManagerService managerService;
	@Autowired
	TaskService taskService;
	@Autowired
	CreateTaskService createtaskService;

	@GetMapping("/viewAllTask")
	public ModelAndView viewAllTask(ModelAndView mView) {
		List<Task> listOfTasks = managerService.getAllTask();
		mView.addObject("listOfTasks", listOfTasks);
		mView.setViewName("manager/task_list");
		return mView;
	}

	@GetMapping("/toggleAuthority/{taskId}")
	public String toggleAuthority(@PathVariable String taskId) {
		int id = Integer.parseInt(taskId);
		createtaskService.updateStatus(id);
		return "redirect:/task/viewAllTask";
	}

}
