package track.pro.project.repository;

import java.util.List;

import track.pro.project.entites.Project;

public interface ProjectRepository {
    int insertProject(Project project);
    List<Project> fetchAllProjects();
}