<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leave Balance</title>
<link href="../css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<form action="LeaveBalance/leaveBalance" method="post">
    
    <input type="number" name="user_id" placeholder="Enter User ID" required/><br/>
    
    <input type="number" name="total_leaves" placeholder="Enter Total Leaves" required/><br/>
    
    <input type="number" name="remaining_leaves" placeholder="Enter Remaining Leaves" required/><br/>
    
    <input type="submit" value="Submit"/>
</form>


</body>
</html>