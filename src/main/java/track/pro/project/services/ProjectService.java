package track.pro.project.services;

import java.util.List;

import track.pro.project.entites.Project;

public interface ProjectService {
    int insertProject(Project project);
    
    List<Project> getAllProjects();
    
}

