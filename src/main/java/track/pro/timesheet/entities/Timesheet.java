package track.pro.timesheet.entities;

import java.time.LocalDateTime;

public class Timesheet {

    private int timesheetId;
    private int taskId;
    private LocalDateTime date;
    private Double hoursLogged;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Timesheet() {
        super();
    }

    public Timesheet(int timesheetId, int taskId, LocalDateTime date, Double hoursLogged, String status, LocalDateTime createdAt,
                     LocalDateTime updatedAt) {
        super();
        this.timesheetId = timesheetId;
        this.taskId = taskId;
        this.date = date;
        this.hoursLogged = hoursLogged;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(int timesheetId) {
        this.timesheetId = timesheetId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getHoursLogged() {
        return hoursLogged;
    }

    public void setHoursLogged(Double hoursLogged) {
        this.hoursLogged = hoursLogged;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Timesheet [timesheetId=" + timesheetId + ", taskId=" + taskId + ", date=" + date + ", hoursLogged="
                + hoursLogged + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }
}