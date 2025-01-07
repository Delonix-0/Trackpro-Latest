<%@page import="track.pro.tasks.entites.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Of Tasks</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<div class="centre-text-fullheight">
		<h1>Task List</h1>
	</div>

	<a href="/managerDashboard"><button class="button-17">Back
			to Dashboard</button></a>
	<a href="/project/viewAllProjecttask"><button class="button-17">Task
			filter</button></a>

	<%
	List<Task> listOfTasks = (List<Task>) request.getAttribute("listOfTasks");
	%>

	<table>
		<tr>
			<th>S. No</th>
			<th>Title</th>
			<th>Description</th>
			<th>Start Time</th>
			<th>Completion Time</th>
			<th>Duration</th>
			<th>Assigned To</th>
			<th>Created At</th>
			<th>Project Id</th>
			<th>Current Status</th>
			<th>Complete/Pending</th>
			<th>Delete Task</th>
		</tr>
		<%
		int sNo = 0;
		for (Task task : listOfTasks) {
			sNo = sNo + 1;
			boolean status = task.isStatus();
		%>
		<tr>
			<td><%=sNo%></td>
			<td><%=task.getTitle()%></td>
			<td><%=task.getDescription()%></td>
			<td><%=task.getStartTime()%></td>
			<td><%=task.getCompTime()%></td>
			<td><%=task.getDuration()%></td>
			<td><%=task.getAssignedTo()%></td>
			<td><%=task.getCreatedAt()%></td>
			<td><%=task.getProjectId()%></td>
			<td><%=status ? "Task is Completed" : "Task Pending"%></td>
			<td><a href="/task/toggleAuthority/<%=task.getTaskId()%>"><%=status ? "Completed" : "Pending"%></a></td>
			<td><input type="hidden" id="taskId_<%=sNo%>"
				value="<%=task.getTaskId()%>" /> <input type="button"
				class="button-delete" value="Delete"
				onclick="deleteTask(<%=sNo%>);" /></td>
		</tr>
		<%
		}
		%>
	</table>

	<script>
function deleteTask(sNo) {
    if (confirm('Are you sure you want to delete this task?')) {
        const taskId = document.getElementById('taskId_' + sNo).value;
        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/task/delete', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // Check if the response contains the success message
                if (xhr.responseText.includes('Task deleted successfully!')) {
                    alert('Task deleted successfully!');
                    location.reload(); // Refresh the page
                } else {
                    alert('Failed to delete the task.');
                }
            }
        };
        xhr.send('taskId=' + taskId);
    }
}
</script>

</body>
</html>