/*
 * 
 * package track.pro.timesheet.repository;
 * 
 * import java.sql.ResultSet; import java.sql.SQLException;
 * 
 * import org.springframework.jdbc.core.RowMapper;
 * 
 * import track.pro.timesheet.entities.Timesheet;
 * 
 * public class TimesheetRowMapper implements RowMapper<Timesheet>{
 * //`timesheet_id`, `task_id`, `user_id`, `date`, `hours_logged`, `status`,
 * `created_at`, `updated_at`
 * 
 * @Override public Timesheet mapRow(ResultSet rs, int rowNum) throws
 * SQLException { int timesheet_id=rs.getInt("timesheet_id"); int
 * task_id=rs.getInt("task_id"); int user_id=rs.getInt("user_id"); String
 * date=rs.getString("date"); Double hours_logged=rs.getDouble("hours_logged");
 * boolean status=rs.getBoolean("status"); String
 * created_at=rs.getString("created_at");
 * 
 * 
 * return new Timesheet(timesheet_id,task_id,user_id,
 * date,hours_logged,status,created_at); }
 * 
 * }
 */

package track.pro.timesheet.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import track.pro.timesheet.entities.Timesheet;

public class TimesheetRowMapper implements RowMapper<Timesheet> {
    @Override
    public Timesheet mapRow(ResultSet rs, int rowNum) throws SQLException {
        int timesheet_id = rs.getInt("timesheet_id");
        int task_id = rs.getInt("task_id");
        int user_id = rs.getInt("user_id");
        String date = rs.getString("date");
        Double hours_logged = rs.getDouble("hours_logged");
        boolean status = rs.getBoolean("status");
        String created_at = rs.getString("created_at");

        return new Timesheet(timesheet_id, task_id, user_id, date, hours_logged, status, created_at);
    }
}