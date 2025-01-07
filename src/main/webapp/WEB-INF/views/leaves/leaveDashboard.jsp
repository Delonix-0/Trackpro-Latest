<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Manager Leave Dashboard</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />


<script>
	function validateForm() {
		var startDate = document.querySelector('input[name="start_date"]').value;
		var endDate = document.querySelector('input[name="end_date"]').value;
		var dateTime = document.querySelector('input[name="date"]').value;
		var currentDate = new Date().toISOString().split('T')[0];

		if (startDate < currentDate) {
			alert("Start date cannot be before the current date.");
			return false;
		}

		if (startDate > endDate) {
			alert("Start date cannot be after the end date.");
			return false;
		}

		if (dateTime.split('T')[0] < currentDate) {
			alert("Date cannot be in the past.");
			return false;
		}

		return true;
	}

	window.onload = function() {
		var startDateInput = document.querySelector('input[name="start_date"]');
		var dateTimeInput = document.querySelector('input[name="date"]');
		var currentDate = new Date().toISOString().split('T')[0];
		startDateInput.setAttribute("min", currentDate);
		dateTimeInput.setAttribute("min", currentDate + "T00:00");

		// Check for success parameter
		const urlParams = new URLSearchParams(window.location.search);
		if (urlParams.has('success')) {
			showToast("Leave request submitted successfully!");
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

	// Function to get the value of a specific cookie by name
	function getCookie(name) {
		let cookieArr = document.cookie.split(";");
		for (let i = 0; i < cookieArr.length; i++) {
			let cookiePair = cookieArr[i].split("=");
			if (name == cookiePair[0].trim()) {
				return decodeURIComponent(cookiePair[1]);
			}
		}
		return null;
	}

	function redirectToDashboard() {
		// Get the user role from the cookie
		let userRole = getCookie("user_role");

		// Redirect based on the user role
		if (userRole == "2") {
			window.location.href = "/managerDashboard";
		} else if (userRole == "3") {
			window.location.href = "/assosiateDashboard";
		}
	}
</script>
</head>
<body>
	<header>
		<div class="centre-text-fullheight-leave">
			<h1>Leave Dashboard</h1>
		</div>

		<button class="button-17" onclick="redirectToDashboard()">Back
			to Dashboard</button>
	</header>

	<main>
		<!-- Leave Balance Section -->
		<section>
			<div class="centre-text-fullheight">
				<h1>Leave Balance</h1>
			</div>
			<form>
				<p>Total Leaves: ${leaveBalance.total_leaves}</p>
				<p>Remaining Leaves: ${leaveBalance.remaining_leaves}</p>
			</form>
		</section>

		<!-- Submit Leave Request Section -->
		<section>
			<div class="centre-text-fullheight-leave">
				<h1>Submit Leave Request</h1>
			</div>
			<c:if test="${not empty errorMessage}">
				<div class="error">${errorMessage}</div>
			</c:if>
			<form action="/submitleaverequest" method="post"
				onsubmit="return validateForm()">
				<!-- Hidden field for user_name -->
				<input type="hidden" name="user_name" value="${user_name}" />
				<div class="form-group">
					<label for="leaveType">Leave Type:</label> <select
						name="leave_type" required>
						<option value="Sick">Sick</option>
						<option value="Casual">Casual</option>
						<option value="Annual">Annual</option>
						<option value="Other">Other</option>
					</select>
				</div>
				<div class="form-group">
					<label for="startDate">Start Date:</label> <input type="date"
						name="start_date" required>
				</div>
				<div class="form-group">
					<label for="endDate">End Date:</label> <input type="date"
						name="end_date" required>
				</div>
				<div class="form-group">
					<label for="date">Date:</label> <input type="datetime-local"
						id="date" name="date" required min="2024-12-28T00:00"
						max="2025-01-30T00:00">
				</div>

				<div class="form-group">
					<button type="submit">Submit Leave Request</button>
				</div>
			</form>
		</section>
	</main>

	
</body>
</html>