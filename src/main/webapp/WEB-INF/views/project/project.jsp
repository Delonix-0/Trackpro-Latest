<!DOCTYPE html>
<%@page import="track.pro.user.entites.User"%>
<%@page import="java.util.Collections"%>
<%@page import="track.pro.project.entites.Project"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="UTF-8">
<title>Add Project</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />

<script>
	function validateDate() {
		const created_at = document.querySelector('input[name="createdAt"]').value;
		const today = new Date().toISOString().split('T')[0];

		if (created_at.split('T')[0] < today) {
			alert("Dates cannot be in the past.");
			return false;
		}
		return true;
	}

	window.onload = function() {
		const dateInput = document.querySelector('input[name="createdAt"]');
		const today = new Date().toISOString().split('T')[0];
		dateInput.setAttribute("min", today + "T00:00");

		// Check for success parameter
		const urlParams = new URLSearchParams(window.location.search);
		if (urlParams.has('success')) {
			showToast("Project created successfully!");
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

	<div class="centre-text-fullheight">
		<h1>Add Project</h1>
	</div>

	<a href="/superAdminDashboard"><button class="button-17">Back
			to Dashboard</button></a>
	<a href="/project/viewAllProject"><button class="button-17">Project
			List</button></a>

	<%
	if (request.getParameter("success") != null) {
	%>
	<div class="toast success">Project added successfully!</div>
	<%
	}
	%>

	<%
	List<User> listOfUsers = (List<User>) request.getAttribute("listOfUsers");
	if (listOfUsers != null) {
		Collections.sort(listOfUsers, (r1, r2) -> r1.getUser_id() - r2.getUser_id());
	}
	%>

	<form action="/project/project" method="post"
		enctype="multipart/form-data" onsubmit="return validateDate()">
		<div class="form-group">
			<label for="projectName">Project Name:</label> <input type="text"
				name="projectName" placeholder="Enter project name" required />
		</div>
		<div class="form-group">
			<label for="description">Description:</label>
			<textarea rows="4" cols="50" name="description"
				placeholder="Enter Description" maxlength="150" required></textarea>
		</div>
		<div class="form-group">
			<label for="assignedTo">Assigned To:</label> <select
				name="assignedTo" required>
				<%
				if (listOfUsers != null) {
					for (User user : listOfUsers) {
				%>
				<option value="<%=user.getUser_id()%>"><%=user.getFull_name()%></option>
				<%
				}
				}
				%>
			</select>
		</div>
		<div class="form-group">
			<label for="createdAt">Created At:</label> <input
				type="datetime-local" id="createdAt" name="createdAt" required />
		</div>
		<button type="submit">Submit</button>
	</form>

	<script>
		// Hide the toast after 3 seconds
		setTimeout(function() {
			var toast = document.querySelector('.toast');
			if (toast) {
				toast.style.display = 'none';
			}
		}, 3000);
	</script>

</body>
</html>