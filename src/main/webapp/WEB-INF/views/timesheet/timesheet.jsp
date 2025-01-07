<!DOCTYPE html>
<%@page import="track.pro.user.entites.User"%>
<%@page import="track.pro.tasks.entites.Task"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="UTF-8">
<title>Timesheet</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />

<script>
	function validateDate() {
		const created_at = document.querySelector('input[name="date"]').value;
		const today = new Date().toISOString().split('T')[0];

		if (created_at.split('T')[0] < today) {
			alert("Dates cannot be in the past.");
			return false;
		}
		return true;
	}

	window.onload = function() {
		const dateInput = document.querySelector('input[name="date"]');
		const today = new Date().toISOString().split('T')[0];
		dateInput.setAttribute("min", today + "T00:00");

		// Check for success parameter
		const urlParams = new URLSearchParams(window.location.search);
		if (urlParams.has('success')) {
			showToast("Timesheet submitted successfully!");
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
		<h1>Timesheet</h1>
	</div>

	<a href="/assosiateDashboard"><button class="button-17">Back
			to Dashboard</button></a>

	<%
	if (request.getParameter("success") != null) {
	%>
	<div class="toast success">Timesheet submitted successfully!</div>
	<%
	}
	%>

	<%
	List<Task> listOfTasks = (List<Task>) request.getAttribute("listOfTasks");
	if (listOfTasks != null) {
		Collections.sort(listOfTasks, (r1, r2) -> r1.getTaskId() - r2.getTaskId());
	}
	%>

	<%
	User user = (User) request.getAttribute("user");
	%>

	<form action="/timesheet/fillTimesheet" method="post"
		enctype="multipart/form-data" onsubmit="return validateDate()"
		class="form">
		<div class="form-group">
			<label for="task_id">Task Title:</label> <select name="task_id"
				id="task_id">
				<%
				if (listOfTasks != null) {
					for (Task task : listOfTasks) {
				%>
				<option value="<%=task.getTaskId()%>"><%=task.getTitle()%></option>
				<%
				}
				}
				%>
			</select>
		</div>
		<div class="form-group">
			<label for="full_name">Full Name:</label> <input type="text"
				id="full_name" name="full_name" value="<%=user.getFull_name()%>"
				readonly>
		</div>
		<input type="hidden" name="user_id" value="<%=user.getUser_id()%>">
		<input type="hidden" name="user_name" value="${user_name}">
		<div class="form-group">
			<label for="date">Date:</label> <input type="datetime-local"
				id="date" name="date" required min="2024-12-28T00:00"
				max="2025-01-30T00:00">
		</div>
		<div class="form-group">
			<label for="hours_logged">Hours Logged:</label> <input type="number"
				step="0.01" id="hours_logged" name="hours_logged"
				placeholder="Enter Hours Logged" required>
		</div>
		<div class="form-group">
			<input type="submit" value="Submit">
		</div>
	</form>

	

</body>
</html>