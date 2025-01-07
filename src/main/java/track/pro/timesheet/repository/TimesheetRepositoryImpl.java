/*
 * 
 * package track.pro.timesheet.repository;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.jdbc.core.JdbcTemplate; import
 * org.springframework.stereotype.Repository;
 * 
 * import track.pro.tasks.entites.Task; import
 * track.pro.timesheet.entities.Timesheet; import track.pro.user.entites.User;
 * 
 * @Repository public class TimesheetRepositoryImpl implements
 * TimesheetRepository {
 * 
 * @Autowired private JdbcTemplate jdbcTemplate;
 * 
 * public JdbcTemplate getJdbcTemplate() { return jdbcTemplate; }
 * 
 * public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate =
 * jdbcTemplate; }
 * 
 * @Override public int fillTimesheet(Timesheet timesheet) { final String
 * INSERT_TIMESHEETS =
 * "INSERT INTO timesheets (task_id, user_id, date, hours_logged, status ,created_at) "
 * + "VALUES (?, ?, ?, ?, ?,?)"; return jdbcTemplate.update(INSERT_TIMESHEETS,
 * timesheet.getTask_id(), timesheet.getUser_id(), timesheet.getDate(),
 * timesheet.getHours_logged(),timesheet.isStatus(), timesheet.getCreated_at());
 * }
 * 
 * @Override public List<Task> fetchAllTasks() { final String GET_ALL_TASKS =
 * "SELECT * FROM tasks"; return jdbcTemplate.query(GET_ALL_TASKS, (rs, rowNum)
 * -> { int task_id = rs.getInt("task_id"); String title=rs.getString("title");
 * return new Task(task_id,title); }); }
 * 
 * @Override public List<User> fetchAllUsers() { final String GET_ALL_USERS =
 * "SELECT * FROM trackpro.users where role_id=3"; return
 * jdbcTemplate.query(GET_ALL_USERS, (rs, rowNum) -> { int user_id =
 * rs.getInt("user_id"); String fullName = rs.getString("full_name"); return new
 * User(user_id, fullName); }); }
 * 
 * @Override public List<Timesheet> fetchAllTimesheet() { final String
 * GET_ALL_TIMESHEET = "SELECT * FROM trackpro.timesheets";
 * 
 * return jdbcTemplate.query(GET_ALL_TIMESHEET, new TimesheetRowMapper()); }
 * 
 * @Override public int toggleAuthority(int task_id) { final String
 * UPDATE_STATUS =
 * "UPDATE `trackpro`.`timesheets` SET `status` = !status WHERE task_id = ?";
 * return jdbcTemplate.update(UPDATE_STATUS, task_id); }
 * 
 * @Override public int deleteTimesheet(int timesheetId) { final String
 * DELETE_TIMESHEET = "DELETE FROM trackpro.timesheets WHERE timesheet_id = ?";
 * return jdbcTemplate.update(DELETE_TIMESHEET, timesheetId); } }
 */

package track.pro.timesheet.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import track.pro.tasks.entites.Task;
import track.pro.timesheet.entities.Timesheet;
import track.pro.user.entites.User;

@Repository
public class TimesheetRepositoryImpl implements TimesheetRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int fillTimesheet(Timesheet timesheet) {
		final String INSERT_TIMESHEETS = "INSERT INTO timesheets (task_id, user_id, date, hours_logged, status, created_at) "
				+ "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
		return jdbcTemplate.update(INSERT_TIMESHEETS, timesheet.getTask_id(), timesheet.getUser_id(),
				timesheet.getDate(), timesheet.getHours_logged(), timesheet.isStatus());
	}

	@Override
	public List<Task> fetchAllTasks() {
		final String GET_ALL_TASKS = "SELECT * FROM tasks";
		return jdbcTemplate.query(GET_ALL_TASKS, (rs, rowNum) -> {
			int task_id = rs.getInt("task_id");
			String title = rs.getString("title");
			return new Task(task_id, title);
		});
	}

	@Override
	public List<User> fetchAllUsers() {
		final String GET_ALL_USERS = "SELECT * FROM trackpro.users where role_id=3";
		return jdbcTemplate.query(GET_ALL_USERS, (rs, rowNum) -> {
			int user_id = rs.getInt("user_id");
			String fullName = rs.getString("full_name");
			return new User(user_id, fullName);
		});
	}

	@Override
	public List<Timesheet> fetchAllTimesheet() {
		final String GET_ALL_TIMESHEET = "SELECT * FROM trackpro.timesheets";
		return jdbcTemplate.query(GET_ALL_TIMESHEET, new TimesheetRowMapper());
	}

	@Override
	public int toggleAuthority(int task_id) {
		final String UPDATE_STATUS = "UPDATE `trackpro`.`timesheets` SET `status` = !status WHERE task_id = ?";
		return jdbcTemplate.update(UPDATE_STATUS, task_id);
	}

	@Override
	public int deleteTimesheet(int timesheetId) {
		final String DELETE_TIMESHEET = "DELETE FROM trackpro.timesheets WHERE timesheet_id = ?";
		return jdbcTemplate.update(DELETE_TIMESHEET, timesheetId);
	}

	@Override
	public int getUserIdByUsername(String username) {
		final String GET_USER_ID = "SELECT user_id FROM users WHERE user_name = ?";
		return jdbcTemplate.queryForObject(GET_USER_ID, new Object[] { username }, Integer.class);
	}

	@Override
	public List<Task> getTasksByUserId(int userId) {
		final String GET_TASKS_BY_USER_ID = "SELECT * FROM tasks WHERE assigned_to = ?";
		return jdbcTemplate.query(GET_TASKS_BY_USER_ID, new Object[] { userId }, (rs, rowNum) -> {
			int taskId = rs.getInt("task_id");
			String title = rs.getString("title");
			return new Task(taskId, title);
		});
	}

	@Override
	public User getUserById(int userId) {
		final String GET_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
		return jdbcTemplate.queryForObject(GET_USER_BY_ID, new Object[] { userId }, (rs, rowNum) -> {
			int id = rs.getInt("user_id");
			String fullName = rs.getString("full_name");
			return new User(id, fullName);
		});
	}
}