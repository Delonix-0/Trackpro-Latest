<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login here</title>
</head>
<link  href="../css/styles.css" rel="stylesheet" type="text/css" / >
<body >

 </div>
<div class="hero">


    <div class="centre-text-fullheight">
		<h1>Login Here</h1>
	</div>
<form action="/login" method="post">
		<input type="text" name="user_name" placeholder="Enter username" /> <br />
		<input type="password" name="password" placeholder="Enter password" />
		<br /> <input type="submit" value="submit" /> <br />
		
		<p>
		Didn't sign up! <a href="openRegistrationPage">Register Here</a>
	</p>
	<p>
		<a href="openIndexPage">Home</a>
		
		<br/>
		<br/>
		<% 
		String message=(String)request.getAttribute("message");
		if(message!=null){
			out.print(message);
		}
		
		%>

	</form>
	
		
	
	



</div>
</body>
</html>