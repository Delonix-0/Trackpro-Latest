
package track.pro.timesheet.entities;
 
//`timesheet_id`, `task_id`, `full_name`, `date`, `hours_logged`, `status`, `created_at`, `updated_at`
 
public class Timesheet {
 
    private int timesheet_id;
    private int task_id;
    private int user_id;
    private String date;
    private Double hours_logged;
    private boolean status;
    private String created_at;
	public Timesheet() {
		super();		
	}
	public Timesheet(int timesheet_id, int task_id, int user_id, String date, Double hours_logged, boolean status,
			String created_at) {
		super();
		this.timesheet_id = timesheet_id;
		this.task_id = task_id;
		this.user_id = user_id;
		this.date = date;
		this.hours_logged = hours_logged;
		this.status = status;
		this.created_at = created_at;
	}
	public int getTimesheet_id() {
		return timesheet_id;
	}
	public void setTimesheet_id(int timesheet_id) {
		this.timesheet_id = timesheet_id;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getHours_logged() {
		return hours_logged;
	}
	public void setHours_logged(Double hours_logged) {
		this.hours_logged = hours_logged;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}

	