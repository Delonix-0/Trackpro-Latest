<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login here</title>
</head>
<link  href="../css/styles.css" rel="stylesheet" type="text/css" / >
<body>
<form action="/login" method="post">
		<input type="text" name="username" placeholder="Enter username" /> <br />
		<input type="password" name="password" placeholder="Enter password" />
		<br /> <input type="submit" value="submit" /> <br />
 
	</form>
	<p>
		Didn't sign up! <a href="openRegistrationPage">Register Here</a>
	</p>
	<p>
		<a href="openIndexPage">Home</a>
	</p>
	
 
 
 
 
</body>
</html>