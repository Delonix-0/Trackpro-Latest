package track.pro.manager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import track.pro.tasks.entites.Task;
import track.pro.tasks.repository.TaskRowMapper;
import track.pro.timesheet.entities.Timesheet;
import track.pro.timesheet.repository.TimesheetRowMapper;

@Repository
public class ManagerRepositoryImpl implements ManagerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Task> fetchAllTask() {
		final String GET_ALL_TASK = "SELECT * FROM trackpro.tasks ";
		return jdbcTemplate.query(GET_ALL_TASK, new TaskRowMapper());
	}

}
