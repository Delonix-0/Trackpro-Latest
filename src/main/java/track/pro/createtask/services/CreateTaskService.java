package track.pro.createtask.services;

import java.util.List;

import track.pro.project.entites.Project;
import track.pro.tasks.entites.Task;
import track.pro.user.entites.User;

public interface CreateTaskService {
	int addtaskUser(Task task);

	List<User> getAllUser();
	List<User> getAllEmployee();
	int deleteTask(int taskId);

	List<Project> getAllProject();
	int updateStatus(int taskId);

}
