package track.pro.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import track.pro.project.entites.Project;
import track.pro.project.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Override
	public int insertProject(Project project) {
		return projectRepository.insertProject(project);
	}

	@Override
	public List<Project> getAllProjects() {

		return projectRepository.fetchAllProjects();
	}
}