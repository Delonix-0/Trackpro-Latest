package track.pro.leaves.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import track.pro.leaves.entites.Leaves;

public class LeavesRowMapper implements RowMapper<Leaves> {
	
	@Override
	public Leaves mapRow(ResultSet rs, int rowNum) throws SQLException {
        Leaves leave = new Leaves();
        leave.setLeave_id(rs.getInt("leave_id"));
        leave.setUser_id(rs.getInt("user_id"));
        leave.setLeave_type(rs.getString("leave_type"));
        leave.setStart_date(rs.getDate("start_date"));
        leave.setEnd_date(rs.getDate("end_date"));
        leave.setStatus(rs.getBoolean("status"));
        leave.setRequested_at(rs.getTimestamp("requested_at"));
        leave.setApproved_by(rs.getInt("approved_by"));
        return leave;
}
}
