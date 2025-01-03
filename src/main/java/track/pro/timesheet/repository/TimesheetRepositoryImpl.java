package track.pro.timesheet.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import track.pro.timesheet.entities.Timesheet;

@Repository
public class TimesheetRepositoryImpl implements TimesheetRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int fillTimesheet(Timesheet timesheet) {
        final String INSERT_TIMESHEETS = "INSERT INTO timesheets (task_id, date, hours_logged, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(INSERT_TIMESHEETS, timesheet.getTaskId(), timesheet.getDate(),
                                   timesheet.getHoursLogged(), timesheet.getStatus(), timesheet.getCreatedAt(),
                                   timesheet.getUpdatedAt());
    }
}