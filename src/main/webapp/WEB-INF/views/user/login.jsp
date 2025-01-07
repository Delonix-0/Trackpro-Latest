<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Here</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
<style>
.form p {
	margin: 5px 0;
	text-align: center;
}
</style>
<script>
function validateForm() {
	var username = document.getElementById("user_name").value;
	var password = document.getElementById("password").value;
	if (username == "" || password == "") {
		alert("Username and Password must be filled out");
		return false;
	}
	return true;
}
</script>
</head>
<body>
	<div class="hero">
		<div class="centre-text-fullheight">
			<h1>Login Here</h1>
		</div>
		<form action="/login" method="post" class="form" onsubmit="return validateForm()">
			<div class="form-group">
				<label for="user_name">Username:</label> 
				<input type="text" id="user_name" name="user_name" placeholder="Enter username" required>
			</div>
			<div class="form-group">
				<label for="password">Password:</label> 
				<input type="password" id="password" name="password" placeholder="Enter password" required>
			</div>
			<div class="form-group">
				<input type="submit" value="Submit">
			</div>
			<p>
				Didn't sign up? <a href="openRegistrationPage">Register Here</a>
			</p>
			<p>
				<a href="openIndexPage">Home</a>
			</p>
			<br /> <br />
			<%
			String message = (String) request.getAttribute("message");
			if (message != null) {
				out.print(message);
			}
			%>
		</form>
	</div>
</body>
</html>