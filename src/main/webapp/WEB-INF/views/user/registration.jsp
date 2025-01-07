<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="track.pro.user.entites.Role"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<!DOCTYPE html>
<html>
<head>
<title>Registration Page</title>
<link rel="preload" href="../images/bg.jpg" as="image">
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
<script>
	function validateForm() {
		const name = document.getElementById("full_name").value;
		const mobileField = document.getElementById("mobile").value;
		const emailField = document.getElementById("email").value;
		const mobilePattern = /^[6-9]\d{9}$/; // Ensures the number starts with 6-9 and is 10 digits long
		const invalidNumbers = [ "0000000000", "1234567890" ];
		const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.(com|in)$/; // Email validation for .com or .in

		// Validate name
		if (name.length <= 1) {
			alert("Name must be more than one letter.");
			return false;
		}

		// Validate mobile number
		if (!mobilePattern.test(mobileField)) {
			alert("Please enter a valid 10-digit mobile number.");
			return false;
		}
		if (invalidNumbers.includes(mobileField)) {
			alert("The entered mobile number is invalid.");
			return false;
		}

		// Validate email
		if (!emailPattern.test(emailField)) {
			alert("Please enter a valid email address ending with .com or .in.");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<%
	List<Role> listOfRoles = (List<Role>) request.getAttribute("listOfRoles");
	if (listOfRoles != null) {
		Collections.sort(listOfRoles, (r1, r2) -> r1.getRoleId() - r2.getRoleId());
	}
	%>

	<div class="centre-text-fullheight">
		<h1>Register Here</h1>
	</div>
	<form action="/registration" method="post"
		enctype="multipart/form-data" onsubmit="return validateForm()"
		class="form">
		<div class="form-group">
			<label for="full_name">Full Name:</label> <input type="text"
				id="full_name" name="full_name" required>
		</div>
		<div class="form-group">
			<label for="user_name">Username:</label> <input type="text"
				id="user_name" name="user_name" placeholder="Enter username"
				required>
		</div>
		<div class="form-group">
			<label>Gender:</label>
			<div>
				<input type="radio" name="gender" value="m"> Male <input
					type="radio" name="gender" value="f"> Female <input
					type="radio" name="gender" value="o"> Other
			</div>
		</div>
		<div class="form-group">
			<label for="mobile">Mobile:</label> <input type="text" id="mobile"
				name="mobile" required>
		</div>
		<div class="form-group">
			<label for="email">Email:</label> <input type="email" id="email"
				name="email" placeholder="Enter Email Address" required>
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password"
				id="password" name="password" placeholder="Enter password" required>
		</div>
		<div class="form-group">
			<label for="profile_image">Upload Profile Image:</label> <input
				type="file" id="profile_image" name="profile_image">
		</div>
		<div class="form-group">
			<label for="profile">Upload Profile:</label> <input type="file"
				id="profile" name="profile">
		</div>
		<div class="form-group">
			<label for="role_id">Select Role:</label> <select name="role_id"
				id="role_id">
				<%
				if (listOfRoles != null) {
					for (Role role : listOfRoles) {
				%>
				<option value="<%=role.getRoleId()%>"><%=role.getRoleName()%></option>
				<%
				}
				}
				%>
			</select>
		</div>
		<div class="form-group">
			<input type="submit" value="Submit">
		</div>

		<!-- Display Error Message -->
		<c:if test="${errorMessage != null}">
			<div style="color: red; margin-bottom: 10px;">${errorMessage}</div>
		</c:if>

		<!-- Display Success Message -->
		<c:if test="${successMessage != null}">
			<div style="color: green; margin-bottom: 10px;">${successMessage}</div>
		</c:if>
		<p>
			Already signed up? <a href="openloginPage">Login Here</a>
		</p>
	</form>
</body>
</html>