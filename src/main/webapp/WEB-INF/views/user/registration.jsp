<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User | Registration</title>
 <link href="../css/styles.css" rel="stylesheet" type="text/css"/>
</head>
<body>

 <form action="/user/registration" method="post" enctype="multipart/form-data">
       <input type="text" name="name" placeholder="Enter name"/><br/>
       
       Gender:
        <input type="radio" name="gender" value="m"/>Male
        <input type="radio" name="gender" value="f"/>Female
        <input type="radio" name="gender" value="t"/>Transgender<br/>
        
        
        <input type="text" name="mobile" placeholder="Enter mobile number"/><br/>
        <input type="email" name="email" placeholder="Enter email Id"/><br/>
        
        <input type="text" name="username" placeholder="Enter username"/><br/>
        <input type="password" name="password" placeholder="Enter password"/><br/>
        
        <input type="submit" value="Submit"/>
        
  </form>
  
   <p> Already sign up! <a href="/user/openLoginPage">Login Here</a></p>
     <p><a href="/user/openIndexPage">Home</a></p>
     <%
       String message=(String)request.getAttribute("message");
     if(message!=null)
    	 out.print(message);
     
     %>

</body>
</html>