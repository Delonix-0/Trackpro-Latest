/*
 * package track.pro.leaves.repository;
 * 
 * import org.springframework.jdbc.core.JdbcTemplate; import
 * org.springframework.stereotype.Repository;
 * 
 * import track.pro.leaves.entites.Leaves; import
 * track.pro.leaves.entites.LeaveBalance;
 * 
 * import java.util.List;
 * 
 * @Repository public class LeavesRepositoryImpl implements LeavesRepository {
 * 
 * private final JdbcTemplate jdbcTemplate;
 * 
 * public LeavesRepositoryImpl(JdbcTemplate jdbcTemplate) { this.jdbcTemplate =
 * jdbcTemplate; }
 * 
 * @Override public LeaveBalance fetchLeaveBalanceByUser(int user_id) { String
 * sql =
 * "SELECT total_leaves, remaining_leaves FROM leave_balance WHERE user_id = ?";
 * return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> { LeaveBalance lb =
 * new LeaveBalance(); lb.setUser_id(user_id);
 * lb.setTotal_leaves(rs.getInt("total_leaves"));
 * lb.setRemaining_leaves(rs.getInt("remaining_leaves")); return lb; },
 * user_id); }
 * 
 * @Override public void updateRemainingLeaves(int user_id, int updatedBalance)
 * { String sql =
 * "UPDATE leave_balance SET remaining_leaves = ? WHERE user_id = ?";
 * jdbcTemplate.update(sql, updatedBalance, user_id); }
 * 
 * @Override public int insertLeaveRequest(Leaves leave) { String sql =
 * "INSERT INTO leaves (user_id, leave_type, start_date, end_date, status, requested_at) "
 * + "VALUES (?, ?, ?, ?, ?, ?)"; return jdbcTemplate.update(sql,
 * leave.getUser_id(), leave.getLeave_type(), leave.getStart_date(),
 * leave.getEnd_date(), leave.isStatus(), leave.getRequested_at()); }
 * 
 * @Override public List<Leaves> getAllLeaveRequests() { String sql =
 * "SELECT *FROM trackpro.leaves"; return jdbcTemplate.query(sql, new
 * LeavesRowMapper()); }
 * 
 * @Override public int toggleAuthority(int leaveId) { final String
 * UPDATE_STATUS =
 * "UPDATE `trackpro`.`leaves` SET `status` = !status WHERE `leave_id` = ?";
 * return jdbcTemplate.update(UPDATE_STATUS, leaveId); }
 * 
 * 
 * }
 */


package track.pro.leaves.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import track.pro.leaves.entites.Leaves;
import track.pro.leaves.entites.LeaveBalance;

import java.util.List;

@Repository
public class LeavesRepositoryImpl implements LeavesRepository {

    private final JdbcTemplate jdbcTemplate;

    public LeavesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LeaveBalance fetchLeaveBalanceByUser(int user_id) {
        String sql = "SELECT total_leaves, remaining_leaves FROM leave_balance WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            LeaveBalance lb = new LeaveBalance();
            lb.setUser_id(user_id);
            lb.setTotal_leaves(rs.getInt("total_leaves"));
            lb.setRemaining_leaves(rs.getInt("remaining_leaves"));
            return lb;
        }, user_id);
    }

    @Override
    public void updateRemainingLeaves(int user_id, int updatedBalance) {
        String sql = "UPDATE leave_balance SET remaining_leaves = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, updatedBalance, user_id);
    }

    @Override
    public int insertLeaveRequest(Leaves leave) {
        String sql = "INSERT INTO leaves (user_id, leave_type, start_date, end_date, status, requested_at) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, leave.getUser_id(), leave.getLeave_type(), leave.getStart_date(),
                leave.getEnd_date(), leave.isStatus(), leave.getRequested_at());
    }

    @Override
    public List<Leaves> getAllLeaveRequests() {
        String sql = "SELECT * FROM trackpro.leaves";
        return jdbcTemplate.query(sql, new LeavesRowMapper());
    }

    @Override
    public int toggleAuthority(int leaveId) {
        final String UPDATE_STATUS = "UPDATE trackpro.leaves SET status = !status WHERE leave_id = ?";
        return jdbcTemplate.update(UPDATE_STATUS, leaveId);
    }

    @Override
    public int getLeaveDays(int leaveId) {
        String sql = "SELECT DATEDIFF(end_date, start_date) + 1 AS leave_days FROM leaves WHERE leave_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, leaveId);
    }
    @Override
    public Leaves getLeaveById(int leaveId) {
        String sql = "SELECT * FROM leaves WHERE leave_id = ?";
        return jdbcTemplate.queryForObject(sql, new LeavesRowMapper(), leaveId);
    }
}