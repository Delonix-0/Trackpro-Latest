<%-- <%@page import="track.pro.timesheet.entities.Timesheet"%>
<%@page import="track.pro.user.entites.User"%>
<%@page import="java.util.Collections"%>
<%@page import="track.pro.tasks.entites.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timesheet</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css"/ >
</head>
<body>
	<%
	if (request.getParameter("success") != null) {
	%>
	<div class="toast success">Timesheet submitted successfully!</div>
	<%
	}
	%>

	<%
	List<Task> listOfTasks = (List<Task>) request.getAttribute("listOfTasks");
	if (listOfTasks != null) {
		Collections.sort(listOfTasks, (r1, r2) -> r1.getTaskId() - r2.getTaskId());
	}
	%>

	<%
	List<User> listOfUsers = (List<User>) request.getAttribute("listOfUsers");
	if (listOfUsers != null) {
		Collections.sort(listOfUsers, (r1, r2) -> r1.getUser_id() - r2.getUser_id());
	}
	%>


	<div class="centre-text-fullheight">
		<h1>Timesheet</h1>
	</div>
	
	   <a href="/assosiate"><button class="button-17">Back to Dashboard</button></a>

	<form action="/timesheet/fillTimesheet" method="post"
		enctype="multipart/form-data" onsubmit="return validateDate()">
		<div>
			Task Title: <select name="task_id">
				<%
				if (listOfTasks != null) {
					for (Task task : listOfTasks) {
				%>
				<option value="<%=task.getTaskId()%>"><%=task.getTitle()%></option>
				<%
				}
				}
				%>
			</select>
		</div>
		<br>

		<div>
			Full Name: <select name="user_id">
				<%
				if (listOfUsers != null) {
					for (User user : listOfUsers) {
				%>
				<option value="<%=user.getUser_id()%>"><%=user.getFull_name()%></option>
				<%
				}
				}
				%>
			</select>


		</div>
		<br>

		<div>
			<label for="date">Date:</label> <input type="datetime-local"
				id="date" name="date" required min="2024-12-28T00:00"
				max="2025-01-30T00:00" />
		</div>
		<br />

		<div>
			Hours Logged: <input type="number" step="0.01" name="hours_logged"
				placeholder="Enter Hours Logged" required />
		</div>
		<br /> <input type="submit" value="Submit" />
	</form>


</body>
</html> --%>

<%@page import="track.pro.user.entites.User"%>
<%@page import="track.pro.tasks.entites.Task"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timesheet</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css"/>
<script>
    function validateDate() {
        var dateInput = document.getElementById("date");
        var selectedDate = new Date(dateInput.value);
        var currentDate = new Date();
        currentDate.setHours(0, 0, 0, 0); // Set to midnight to compare only dates

        if (selectedDate < currentDate) {
            alert("You cannot select a previous date.");
            return false;
        }
        return true;
    }

    window.onload = function() {
        var dateInput = document.getElementById("date");
        var currentDate = new Date().toISOString().split("T")[0];
        dateInput.setAttribute("min", currentDate);
    };
</script>
</head>
<body>
    <%
    if (request.getParameter("success") != null) {
    %>
    <div class="toast success">Timesheet submitted successfully!</div>
    <%
    }
    %>

    <%
    List<Task> listOfTasks = (List<Task>) request.getAttribute("listOfTasks");
    if (listOfTasks != null) {
        Collections.sort(listOfTasks, (r1, r2) -> r1.getTaskId() - r2.getTaskId());
    }
    %>

    <%
    User user = (User) request.getAttribute("user");
    %>

    <div class="centre-text-fullheight">
        <h1>Timesheet</h1>
    </div>
    
    <a href="/assosiateDashboard"><button class="button-17">Back to Dashboard</button></a>

    <form action="/timesheet/fillTimesheet" method="post"
        enctype="multipart/form-data" onsubmit="return validateDate()">
        <div>
            Task Title: <select name="task_id">
                <%
                if (listOfTasks != null) {
                    for (Task task : listOfTasks) {
                %>
                <option value="<%=task.getTaskId()%>"><%=task.getTitle()%></option>
                <%
                }
                }
                %>
            </select>
        </div>
        <br>

        <div>
            Full Name: <input type="text" name="full_name" value="<%=user.getFull_name()%>" readonly />
        </div>
        <br>

        <div>
            <input type="hidden" name="user_id" value="<%=user.getUser_id()%>" />
            <input type="hidden" name="user_name" value="${user_name }" />
        </div>

        <div>
            <label for="date">Date:</label> <input type="datetime-local"
                id="date" name="date" required />
        </div>
        <br />

        <div>
            Hours Logged: <input type="number" step="0.01" name="hours_logged"
                placeholder="Enter Hours Logged" required />
        </div>
        <br /> <input type="submit" value="Submit" />
    </form>
</body>
</html>