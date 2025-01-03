package track.pro.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import track.pro.project.entites.Project;

/*`project_id`, `project_name`, `description`, `created_by`, `status`, `created_at`*/
public class ProjectRowMapper implements RowMapper<Project>{

	@Override
	public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
		int projectId = rs.getInt("project_id");
		String projectName = rs.getString("project_name");
		String description = rs.getString("description");
		int assignedTo = rs.getInt("assigned_to");
		boolean status = rs.getBoolean("status");
		String createdAt = rs.getString("created_at");
		
		return new Project(projectId, projectName, description, assignedTo, status, createdAt);
		
		
		
		
		
	}

}
