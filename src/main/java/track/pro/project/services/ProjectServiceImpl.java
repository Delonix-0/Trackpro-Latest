package track.pro.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import track.pro.project.entites.Project;
import track.pro.project.repository.ProjectRepository;
import track.pro.tasks.entites.Task;
import track.pro.user.entites.User;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectRepository projectRepository;

	@Override
	public int updateStatus(int projectId) {
		return projectRepository.toggleAuthority(projectId);
	}

	@Override
	public int insertProject(Project project) {
		return projectRepository.insertProject(project);
	}

	@Override
	public List<User> getAllUsers() {
		return projectRepository.fetchAllUsers();
	}
	@Override
	public List<Project> getAllProject() {

		return projectRepository.fectchAllProject();

	}
	
	@Override
	public List<Task> getTasksByProjectId(int projectId) {
	    return projectRepository.fetchTasksByProjectId(projectId);
	}
}
