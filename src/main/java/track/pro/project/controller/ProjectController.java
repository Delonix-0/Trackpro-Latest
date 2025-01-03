package track.pro.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import track.pro.project.entites.Project;
import track.pro.project.services.ProjectService;
import track.pro.tasks.entites.Task;
import track.pro.user.entites.User;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@GetMapping("/openProjectPage")
	public String openProjectPage(Model model) {
		List<User> listOfUsers = projectService.getAllUsers();

		model.addAttribute("listOfUsers", listOfUsers);
		return "/project/project";
	}

	@PostMapping("/project")
	public String project(@ModelAttribute Project project, Model model) {
		try {
			projectService.insertProject(project);
			return "redirect:/project/openProjectPage?succes";
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error adding project: " + e.getMessage());
			return "error";
		}
	}
	@GetMapping("/viewAllProject")
	public ModelAndView viewAllProjects(ModelAndView mView) {
		List<Project> listofProjects = projectService.getAllProject();
		mView.addObject("listofProjects", listofProjects);
		mView.setViewName("super_admin/project_list");
		return mView;
	}
	
	@GetMapping("/viewAllProjecttask")
	public ModelAndView viewAllProjectstask(ModelAndView mView) {
		List<Project> listofProjects = projectService.getAllProject();
		mView.addObject("listofProjects", listofProjects);
		mView.setViewName("manager/filtertask");
		return mView;
	}
	@PostMapping("/viewTasksByProject")
	public ModelAndView viewTasksByProject(@RequestParam("projectId") int projectId, ModelAndView mView) {
		List<Task> listOfTasks = projectService.getTasksByProjectId(projectId);
		mView.addObject("listOfTasks", listOfTasks);
		mView.setViewName("manager/task_list");
		return mView;
	}

	@GetMapping("/toggleAuthority/{project_id}")
	public String toggleAuthority(@PathVariable String project_id) {
		int id = Integer.parseInt(project_id);
		projectService.updateStatus(id);
		return "redirect:/project/viewAllProject";
	}

}