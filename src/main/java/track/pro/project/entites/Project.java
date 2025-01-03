package track.pro.project.entites;



public class Project {
    private int projectId;
    private String projectName;
    private String description;
    private int createdBy;
    private String status;
    private String startedAt;
    private String completedAt;
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Project(int projectId, String projectName, String description, int createdBy, String status,
			String startedAt, String completedAt) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.createdBy = createdBy;
		this.status = status;
		this.startedAt = startedAt;
		this.completedAt = completedAt;
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
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartedAt() {
		return startedAt;
	}
	public void setStartedAt(String startedAt) {
		this.startedAt = startedAt;
	}
	public String getCompletedAt() {
		return completedAt;
	}
	public void setCompletedAt(String completedAt) {
		this.completedAt = completedAt;
	}
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", description=" + description
				+ ", createdBy=" + createdBy + ", status=" + status + ", startedAt=" + startedAt + ", completedAt="
				+ completedAt + "]";
	}
    
    

    }