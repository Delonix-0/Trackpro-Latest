package track.pro.createtask.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import track.pro.project.entites.Project;
import track.pro.tasks.entites.Task;
import track.pro.user.entites.User;
@Repository
public class CreateTaskRepositoryImpl implements CreateTaskRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/*
	 * `task_id`, `title`, `description`, `start_time`, `comp_time`, `duration`,
	 * `status`, `assigned_to`, `project_id`, `created_by`, `created_at`
	 */

	@Override
	public int insertTask(Task task) {
		final String INSERT_TASK = "INSERT INTO tasks( `title`,`description`,`status`,`assigned_to`,`project_id`,`created_by`,`created_at`) VALUES (?,?,?,?,?,?,?)";

		return jdbcTemplate.update(INSERT_TASK, task.getTitle(), task.getDescription(), task.isStatus(),
				task.getAssignedTo(), task.getProjectId(), task.getCreatedBy(), task.getCreatedAt());
	}

	@Override
	public List<User> fetchAllUser() {
		final String GET_ALL_USERS = "SELECT * FROM users where role_id = 2";

		return jdbcTemplate.query(GET_ALL_USERS, (rs, rowNum) -> {
			int userId = rs.getInt("user_id");
			String fullname = rs.getString("full_name");
			return new User(userId, fullname);
		});
	}

	@Override
	public List<Project> fetchAllProject() {
		final String GET_ALL_PROJECTS = "SELECT * FROM projects";

		return jdbcTemplate.query(GET_ALL_PROJECTS, (rs, rowNum) -> {
			int projectId = rs.getInt("project_id");
			String projectname = rs.getString("project_name");
			return new Project(projectId, projectname);
		});
	}

	@Override
	public List<User> fetchAllEmployee() {
		final String GET_ALL_USERS = "SELECT * FROM users where role_id = 3";

		return jdbcTemplate.query(GET_ALL_USERS, (rs, rowNum) -> {
			int userId = rs.getInt("user_id");
			String fullname = rs.getString("full_name");
			return new User(userId, fullname);
		});

	}

	@Override
	public int toggleAuthority(int taskId) {
		final String UPDATE_STATUS="UPDATE `trackpro`.`tasks` SET `status` = !status WHERE task_id=?";
		return jdbcTemplate.update(UPDATE_STATUS,taskId);
	}
	

	  @Override
	    public int deleteTask(int taskId) {
	        final String DELETE_TASK = "DELETE FROM `trackpro`.`tasks` WHERE `task_id` = ?";
	        return jdbcTemplate.update(DELETE_TASK, taskId);
	    }


}
