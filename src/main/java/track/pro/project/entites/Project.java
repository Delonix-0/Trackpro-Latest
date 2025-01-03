package track.pro.project.entites;

/*`project_id`, `project_name`, `description`, `created_by`, `status`, `created_at`*/

public class Project {
	private int projectId;
	private String projectName;
	private String description;
	private int assignedTo;
	private boolean status;
	private String createdAt;

	public Project(int projectId, String projectName) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(int projectId, String projectName, String description, int assignedTo, boolean status,
			String createdAt) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.assignedTo = assignedTo;
		this.status = status;
		this.createdAt = createdAt;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", description=" + description
				+ ", assignedTo=" + assignedTo + ", status=" + status + ", createdAt=" + createdAt + "]";
	}

}
