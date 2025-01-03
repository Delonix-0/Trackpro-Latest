<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Super Admin Dashboard</title>
<script type="text/javascript">
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

	function setProfileManagementLink() {
		let username = getCookie("username");
		if (username) {
			let profileLink = document.getElementById("ProfileManagementLink");
			profileLink.href = "/profile_management?user_name=" + username;
		}
	}

	function clearUsernameCookie() {
		document.cookie = "username=; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT";
		window.location.href = "/openloginPage";
	}

	window.onload = setProfileManagementLink;
</script>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="centre-text-fullheight">
		<h1>Admin Dashboard</h1>
	</div>

	<div class="container">
	
		<div class="card">
			<h2>Home Page</h2>
			<a href="/openIndexPage">Go to Home</a>
		</div>
		<div class="card">
			<h2>Manage Managers</h2>
			<a href="/superadmin/viewAllManagers">Manage Managers</a>
		</div>
		<div class="card">
			<h2>Manage Projects</h2>
			<a href="/project/openProjectPage">Manage Projects</a>
		</div>
		<div class="card">
			<h2>Manage Leaves</h2>
			<a href="/leaves">Manage Leaves </a>
		</div>
		</div>
		<div class="container">
				<div class="card">
			<h2>Profile Management</h2>
			<a id="ProfileManagementLink" href="#">Manage Profile</a>
		</div>


		<div class="card">
			<h2>Logout</h2>
			<a href="/openloginPage">Logout </a>
		</div>
		</div>

	</div>



	<%
	String message = (String) request.getAttribute("message");
	if (message != null) {
		out.print(message);
	}
	%>


</body>
</html>