<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Leave Information</title>
	<link href="../css/styles.css" rel="stylesheet" type="text/css" />
	<script>
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
			<h1>Leave Information</h1>
		</div>

		<button class="button-17" onclick="redirectToDashboard()">Back to Dashboard</button>
	</header>

	<main>
		<section>
			<div class="centre-text-fullheight">
				<h1>Your Leave Requests</h1>
			</div>

			<c:if test="${not empty errorMessage}">
				<div class="error">${errorMessage}</div>
			</c:if>

			<c:if test="${empty leaveInformation}">
				<div class="centre-text-fullheight">
					<p>No leave records found.</p>
				</div>
			</c:if>

			<c:if test="${not empty leaveInformation}">
				<div class="leave-requests-container">
					<c:forEach items="${leaveInformation}" var="leave">
						<div class="leave-request-card">
							<div class="leave-header">
								<span class="leave-type">${leave.leave_type}</span>
								<span class="${leave.status ? 'status-approved' : 'status-pending'}">
									${leave.status ? 'Approved' : 'Pending'}
								</span>
							</div>
							<div class="leave-details">
								<p><strong>Leave ID:</strong> ${leave.leave_id}</p>
								<p><strong>Start Date:</strong> ${leave.start_date}</p>
								<p><strong>End Date:</strong> ${leave.end_date}</p>
								<p><strong>Requested At:</strong> ${leave.requested_at}</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:if>
		</section>
	</main>

	<style>
		.leave-requests-container {
			padding: 20px;
			max-width: 800px;
			margin: 0 auto;
		}
		
		.leave-request-card {
			background: white;
			border-radius: 8px;
			box-shadow: 0 2px 4px rgba(0,0,0,0.1);
			margin-bottom: 20px;
			overflow: hidden;
		}
		
		.leave-header {
			background: #f8f9fa;
			padding: 15px;
			border-bottom: 1px solid #dee2e6;
			display: flex;
			justify-content: space-between;
			align-items: center;
		}
		
		.leave-type {
			font-weight: bold;
			color: #495057;
		}
		
		.leave-details {
			padding: 15px;
		}
		
		.leave-details p {
			margin: 8px 0;
			color: #495057;
		}
		
		.status-pending {
			color: #f0ad4e;
			font-weight: bold;
		}
		
		.status-approved {
			color: #5cb85c;
			font-weight: bold;
		}
		
		.error {
			color: #dc3545;
			background-color: #f8d7da;
			border: 1px solid #f5c6cb;
			padding: 10px;
			margin: 10px 0;
			border-radius: 4px;
			text-align: center;
		}
		
		.centre-text-fullheight {
			text-align: center;
			padding: 20px 0;
		}
		
		.button-17 {
			margin: 10px;
		}
	</style>
</body>
</html>
