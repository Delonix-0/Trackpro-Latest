package track.pro.leaves.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import track.pro.leaves.entites.Leaves;

public class LeavesRowMapper implements RowMapper<Leaves> {

        @Override
        public Leaves mapRow(ResultSet rs, int rowNum) throws SQLException {
                System.out.println("Mapping row " + rowNum);
                try {
                        Leaves leave = new Leaves();
                        leave.setLeave_id(rs.getInt("leave_id"));
                        leave.setUser_id(rs.getInt("user_id"));
                        leave.setLeave_type(rs.getString("leave_type"));
                        leave.setStart_date(rs.getDate("start_date"));
                        leave.setEnd_date(rs.getDate("end_date"));
                        leave.setStatus(rs.getBoolean("status"));
                        leave.setRequested_at(rs.getTimestamp("requested_at"));
                        System.out.println("Successfully mapped leave with ID: " + leave.getLeave_id());
                        return leave;
                } catch (SQLException e) {
                        System.out.println("Error mapping row " + rowNum + ": " + e.getMessage());
                        throw e;
                }
        }
}
