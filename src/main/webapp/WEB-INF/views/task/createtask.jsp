<!DOCTYPE html>
<%@page import="track.pro.project.entites.Project"%>
<%@page import="track.pro.user.entites.User"%>
<%@page import="java.util.Collections"%>
<%@page import="track.pro.tasks.entites.Task"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Create Task</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />



<script>
	function validateDate() {
		const created_at = document.querySelector('input[name="createdAt"]').value;
		const today = new Date().toISOString().split('T')[0];

		if (created_at < today) {
			alert("Dates cannot be in the past.");
			return false;
		}
		return true;
	}

	window.onload = function() {
		const dateInput = document.querySelector('input[name="createdAt"]');
		const today = new Date().toISOString().split('T')[0];
		dateInput.setAttribute("min", today);

		// Check for success parameter
		const urlParams = new URLSearchParams(window.location.search);
		if (urlParams.has('success')) {
			showToast("Task created successfully!");
		}
	};

	function showToast(message) {
		const toast = document.createElement('div');
		toast.className = 'toast show';
		toast.innerText = message;
		document.body.appendChild(toast);

		setTimeout(function() {
			toast.className = toast.className.replace('show', '');
			document.body.removeChild(toast);
		}, 3000);
	}
</script>
</head>
<body>

	<%
	User currentUser = (User) request.getAttribute("currentUser");
	List<Project> userProjects = (List<Project>) request.getAttribute("userProjects");
	List<User> listOfEmployees = (List<User>) request.getAttribute("listOfEmployees");
	%>

	<div class="centre-text-fullheight">
		<h1>Create Task</h1>
	</div>

	<a href="/managerDashboard"><button class="button-17">Back
			to Dashboard</button></a>
	<a href="/task/viewAllTask"><button class="button-17">Task
			List</button></a>

	<form action="/task/createtask" method="post"
		onsubmit="return validateDate()">
		<input type="hidden" name="user_name" value="${user_name}" />

		<div class="form-group">
			<label for="title">Title:</label> <input type="text" id="title"
				name="title" required>
		</div>

		<div class="form-group">
			<label for="description">Description:</label>
			<textarea id="description" name="description" required></textarea>
		</div>

		<div class="form-group">
			<label for="createdBy">Created By:</label> <input type="hidden"
				id="createdBy" name="createdBy"
				value="<%=currentUser.getUser_id()%>"> <input type="text"
				value="<%=currentUser.getFull_name()%>" readonly>
		</div>

		<div class="form-group">
			<label for="assignedTo">Assigned To User:</label> <select
				name="assignedTo" id="assignedTo">
				<%
				for (User user : listOfEmployees) {
				%>
				<option value="<%=user.getUser_id()%>"><%=user.getFull_name()%></option>
				<%
				}
				%>
			</select>
		</div>

		<div class="form-group">
			<label for="projectId">Project ID:</label> <select name="projectId"
				id="projectId">
				<%
				for (Project project : userProjects) {
				%>
				<option value="<%=project.getProjectId()%>"><%=project.getProjectName()%></option>
				<%
				}
				%>
			</select>
		</div>

		<div class="form-group">
			<label for="createdAt">Created At:</label> <input type="date"
				id="createdAt" name="createdAt" required>
		</div>

		<input type="submit" value="Submit">
	</form>

</body>
</html>