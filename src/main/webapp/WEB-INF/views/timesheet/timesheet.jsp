<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timesheet</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <% if (request.getParameter("success") != null) { %>
        <div class="toast success">
            Timesheet submitted successfully!
        </div>
    <% } %>

<h1>Timesheet</h1>

<form action="/timesheet/fillTimesheet" method="post" enctype="multipart/form-data">
    <input type="number" name="taskId" placeholder="Enter Task ID" required/><br/>
    <label for="date">Date:</label>
    <input type="datetime-local" id="date" name="date" required/><br/>
    <input type="number" step="0.01" name="hoursLogged" placeholder="Enter Hours Logged" required/><br/>
    <select name="status" required>
        <option value="Submitted">Submitted</option>
        <option value="Approved">Approved</option>
        <option value="Rejected">Rejected</option>
    </select><br/>
    <label for="createdAt">Created At:</label>
    <input type="datetime-local" id="createdAt" name="createdAt" required/><br/>
    <label for="updatedAt">Updated At:</label>
    <input type="datetime-local" id="updatedAt" name="updatedAt"/><br/>
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