<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Leave</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css"/>
</head>
<body>

	<% if (request.getParameter("success") != null) { %>
	    <div class="toast success">
	        Leave applied successfully!
	    </div>
	<% } %>

<form action="/leave/applyLeave" method="post" enctype="multipart/form-data">
    <input type="number" name="userId" placeholder="Enter User ID" required/><br/>
    
    <select name="leaveType" required>
        <option value="Sick leave">Sick leave</option>
        <option value="Earned leave">Earned leave</option>
        <option value="Casual leave">Casual leave</option>
    </select><br/>
    
    <input type="date" name="startDate" required/><br/>
    
    <input type="date" name="endDate" required/><br/>
    
    <select name="status" required>
        <option value="Pending" selected>Pending</option>
        <option value="Approved">Approved</option>
        <option value="Rejected">Rejected</option>
    </select><br/>
    
    <input type="datetime-local" name="requestedAt" required/><br/>
    
    <input type="number" name="approvedBy" placeholder="Enter Approved By"/><br/>
    
    <input type="submit" value="Submit"/>
</form>

<script>
    // Hide the toast after 3 seconds
    setTimeout(function() {
        var toast = document.querySelector('.toast');
        if (toast) {
            toast.style.display = 'none';
        }
    }, 3000);
</script>

</body>
</html>