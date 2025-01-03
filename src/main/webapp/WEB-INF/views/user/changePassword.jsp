<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change password</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
 <div class="centre-text-fullheight">
        <h1>Change Password</h1>
    </div>

<div class="container">
<
<form action="/changePassword" method="post">
<div class="form-group">
<label for="user_name">Username:</label>
<input type="text" id="user_name" name="user_name" required>
</div>
<div class="form-group">
<label for="oldPassword">Old Password:</label>
<input type="password" id="oldPassword" name="oldPassword" required>
</div>
<div class="form-group">
<label for="newPassword">New Password:</label>
<input type="password" id="newPassword" name="newPassword" required>
</div>
<div class="form-group">
<button type="submit">Change Password</button>
</div>
<c:if test="${not empty message}">
<div class="message">${message}</div>
</c:if>
</div>
</form>



</body>
</html>