package track.pro.leave.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import track.pro.leave.entities.Leave;

@Repository
public class LeaveRepositoryImpl implements LeaveRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int applyLeave(Leave leave) {
        final String INSERT_LEAVES = "INSERT INTO leaves(`user_id`, `leave_type`, `start_date`, `end_date`, `status`,"
                + " `requested_at`, `approved_by`) VALUES (?,?,?,?,?,?,?)";

        try {
            return jdbcTemplate.update(INSERT_LEAVES, leave.getUserId(), leave.getLeaveType(),
                    leave.getStartDate(), leave.getEndDate(), leave.getStatus(), leave.getRequestedAt(), leave.getApprovedBy());
        } catch (Exception e) {
            System.err.println("Error executing SQL: " + e.getMessage());
            throw e;
        }
    }
}