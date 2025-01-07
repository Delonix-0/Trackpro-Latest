package track.pro.tasks.entites;

import java.math.BigDecimal;

public class Task {
	private int taskId;
	private String title;
	private String description;
	private String startTime;
	private String compTime;
	private BigDecimal duration;
	private boolean status;
	private String assignedTo;
	private int projectId;
	private int createdBy;
	private String createdAt;

	public Task(int taskId, String title) {
		super();
		this.taskId = taskId;
		this.title = title;
	}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(int taskId, String title, String description, String startTime, String compTime, BigDecimal duration,
			boolean status, String assignedTo, int projectId, int createdBy, String createdAt) {
		super();
		this.taskId = taskId;
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.compTime = compTime;
		this.duration = duration;
		this.status = status;
		this.assignedTo = assignedTo;
		this.projectId = projectId;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCompTime() {
		return compTime;
	}

	public void setCompTime(String compTime) {
		this.compTime = compTime;
	}

	public BigDecimal getDuration() {
		return duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", title=" + title + ", description=" + description + ", startTime="
				+ startTime + ", compTime=" + compTime + ", duration=" + duration + ", status=" + status
				+ ", assignedTo=" + assignedTo + ", projectId=" + projectId + ", createdBy=" + createdBy
				+ ", createdAt=" + createdAt + "]";
	}

}