package track.pro.leaves.entites;

import java.sql.Date;
import java.sql.Timestamp;

public class Leaves {
	private int leave_id;
	 private int user_id;
	 private String leave_type;
	 private Date start_date;
	 private Date end_date;
	 private boolean  status;
	 private Timestamp requested_at;
	 private Integer approved_by;
	public Leaves() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Leaves(int leave_id, int user_id, String leave_type, Date start_date, Date end_date, boolean status,
			Timestamp requested_at, Integer approved_by) {
		super();
		this.leave_id = leave_id;
		this.user_id = user_id;
		this.leave_type = leave_type;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
		this.requested_at = requested_at;
		this.approved_by = approved_by;
	}
	public int getLeave_id() {
		return leave_id;
	}
	public void setLeave_id(int leave_id) {
		this.leave_id = leave_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Timestamp getRequested_at() {
		return requested_at;
	}
	public void setRequested_at(Timestamp requested_at) {
		this.requested_at = requested_at;
	}
	public Integer getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(Integer approved_by) {
		this.approved_by = approved_by;
	}
	@Override
	public String toString() {
		return "Leaves [leave_id=" + leave_id + ", user_id=" + user_id + ", leave_type=" + leave_type + ", start_date="
				+ start_date + ", end_date=" + end_date + ", status=" + status + ", requested_at=" + requested_at
				+ ", approved_by=" + approved_by + "]";
	}
}