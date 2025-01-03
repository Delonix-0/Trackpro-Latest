package track.pro.createtask.repository;

import java.util.List;

import track.pro.project.entites.Project;
import track.pro.tasks.entites.Task;
import track.pro.user.entites.User;

public interface CreateTaskRepository {
	int insertTask(Task task);
	
	List<User> fetchAllUser();
	
	List<User> fetchAllEmployee();
	
	List<Project> fetchAllProject();
	int deleteTask(int taskId);
	
	int toggleAuthority(int taskId);

}
