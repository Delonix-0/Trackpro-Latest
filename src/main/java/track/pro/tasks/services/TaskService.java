
 
package track.pro.tasks.services;
 
import java.util.List;
 
import track.pro.project.entites.Project;
import track.pro.tasks.entites.Task;
import track.pro.user.entites.User;
 
public interface TaskService {
	int fillTask(Task task);
	List<Task> getAllTasks();
	List<Project> getAllProjects();
	List<User> getAllUsers();
	Task getTaskById(int taskId);
	void updateTask(Task task);
}