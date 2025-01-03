
 
package track.pro.tasks.repository;
 
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import org.springframework.jdbc.core.RowMapper;

import track.pro.tasks.entites.Task;
 

 
 
public class TaskRowMapper implements RowMapper<Task> {
 
	/*
	 * `task_id`, `title`, `description`, `start_time`, `comp_time`, `duration`,
	 * `status`, `assigned_to`, `project_id`, `created_by`, `created_at`
	 */
 
	@Override
	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
		int taskId = rs.getInt("task_id");
		String title = rs.getString("title");
		String description = rs.getString("description");
		String startTime = rs.getString("start_time");
		String compTime = rs.getString("comp_time");
		BigDecimal duration = rs.getBigDecimal("duration");
		boolean status = rs.getBoolean("status");
		String assignedTo = rs.getString("assigned_to");
		int projectId = rs.getInt("project_id");
		int createdBy = rs.getInt("created_by");
		String createdAt = rs.getString("created_at");
 
		return new Task(taskId, title, description, startTime, compTime, duration, status, assignedTo, projectId,
				createdBy, createdAt);
	}
}