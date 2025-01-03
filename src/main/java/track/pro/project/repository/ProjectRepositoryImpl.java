package track.pro.project.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import track.pro.project.entites.Project;
import track.pro.tasks.entites.Task;
import track.pro.tasks.repository.TaskRowMapper;
import track.pro.user.entites.User;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertProject(Project project) {
		final String INSERT_PROJECT = "INSERT INTO projects (`project_name`, `description`, " + "`assigned_to`,"
				+ " `status`, `created_at`) VALUES (?,?,?,?,?)";
		return jdbcTemplate.update(INSERT_PROJECT, project.getProjectName(), project.getDescription(),
				project.getAssignedTo(), project.isStatus(), project.getCreatedAt());

	}

	@Override
	public List<User> fetchAllUsers() {

		final String GET_ALL_USERS = "SELECT * FROM trackpro.users where role_id=2";
		return jdbcTemplate.query(GET_ALL_USERS, (rs, rowNum) -> {
			int userId = rs.getInt("user_id");
			String fullName = rs.getString("full_name");
			return new User(userId, fullName);
		});

	}
	@Override
	public List<Project> fectchAllProject() {
		final String GET_ALL_PROJECT = "SELECT * FROM trackpro.projects";

		return jdbcTemplate.query(GET_ALL_PROJECT, new ProjectRowMapper());
	}
	
	@Override
	public List<Task> fetchTasksByProjectId(int projectId) {
	    final String GET_TASKS_BY_PROJECT_ID = "SELECT * FROM trackpro.tasks WHERE project_id = ?";
	    return jdbcTemplate.query(GET_TASKS_BY_PROJECT_ID, new Object[]{projectId}, new TaskRowMapper());
	}

	@Override
	public int toggleAuthority(int projectId) {
		final String UPDATE_STATUS = "UPDATE `trackpro`.`projects` SET `status` = !status  WHERE `project_id` = ?";
		return jdbcTemplate.update(UPDATE_STATUS, projectId);
	}

}
