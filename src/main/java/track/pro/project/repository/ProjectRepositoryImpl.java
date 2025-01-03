package track.pro.project.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import track.pro.project.entites.Project;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insertProject(Project project) {
		final String INSERT_PROJECTS = "INSERT INTO projects (project_name, description, created_by, status, started_at, completed_at) VALUES (?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(INSERT_PROJECTS, project.getProjectName(), project.getDescription(),
				project.getCreatedBy(), project.getStatus(), project.getStartedAt(), project.getCompletedAt());
	}

	@Override
	public List<Project> fetchAllProjects() {
		final String GET_ALL_PROJECTS = "SELECT * FROM projecs";
		return jdbcTemplate.query(GET_ALL_PROJECTS, (rs, rowNum) -> {

			int projectId = rs.getInt("project_id");
			String projectName = rs.getString("project_name");
			String description = rs.getString("description");
			int createdBy = rs.getInt("created_by");
			String status = rs.getString("status");
			String startedAt = rs.getString("started_at");
			String completedAt = rs.getString("completed_at");
			
			return new Project(projectId, projectName, description, createdBy, status, startedAt, completedAt);

		});
	}
}