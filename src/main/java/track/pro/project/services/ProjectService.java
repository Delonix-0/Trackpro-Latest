package track.pro.project.services;

import java.util.List;

import track.pro.project.entites.Project;
import track.pro.tasks.entites.Task;
import track.pro.user.entites.User;

public interface ProjectService {

	int insertProject(Project project);

	List<User> getAllUsers();

	int updateStatus(int projectId);
	List<Project> getAllProject();
	List<Task> getTasksByProjectId(int projectId);

}
