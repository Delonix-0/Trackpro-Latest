package track.pro.project.repository;

import java.util.List;

import track.pro.project.entites.Project;
import track.pro.tasks.entites.Task;
import track.pro.user.entites.User;

public interface ProjectRepository {

	int insertProject(Project project);

	List<User> fetchAllUsers();

	int toggleAuthority(int projectId);
	List<Project> fectchAllProject();
	List<Task> fetchTasksByProjectId(int projectId);

}
