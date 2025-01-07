
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>User Profile</title>
<link href="../css/profile.css" rel="stylesheet" type="text/css" />
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div>
		<!-- Placeholder for the dashboard button -->
		<a id="dashboardButton"><button class="button-17">Back to
				Dashboard</button></a>



		<div class="profile-body">
			<div class="profile-container">
				<h1>User Profile</h1>

				<c:if test="${not empty error}">
					<p style="color: red;">${error}</p>
				</c:if>

				<c:if test="${not empty user}">
					<div class="profile-info">
						<p>
							<strong>Full Name:</strong> ${full_name}
						</p>
						<p>
							<strong>Mobile:</strong> ${mobile}
						</p>
						<p>
							<strong>Email:</strong> ${email}
						</p>
						<p>
							<strong>Role Id:</strong> ${role_id}
						</p>
					</div>

					<h2>Profile Image</h2>
					<c:if test="${not empty profile_image}">
						<img src="data:image/png;base64,${profile_image}"
							alt="Profile Image" class="profile-img">
					</c:if>

					<h2>Profile Document</h2>
					<div>

						<c:if test="${not empty profile}" class="button-row">
							<a href="data:application/pdf;base64,${profile}"
								download="Profile.pdf" class="button-18">Download Profile
								Document</a>

							<a href="/openChangePasswordPage"><button class="button-18">
									Change Password</button></a>

						</c:if>
					</div>

				</c:if>
			</div>
		</div>
	</div>



	<script>
		// Get the role_id from the JSP
		var roleId = ${role_id};

		// Determine the href based on the role_id
		var dashboardHref;
		if (roleId === 1) {
			dashboardHref = "/superAdminDashboard";
		} else if (roleId === 2) {
			dashboardHref = "/managerDashboard";
		} else {
			dashboardHref = "/assosiateDashboard";
		}

		// Set the href attribute of the dashboard button
		document.getElementById("dashboardButton").setAttribute("href",
				dashboardHref);
	</script>
</body>
</html>


