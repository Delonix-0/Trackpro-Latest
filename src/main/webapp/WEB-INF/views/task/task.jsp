<%@page import="track.pro.tasks.entites.Task"%>
<%@page import="track.pro.user.entites.User"%>
<%@page import="java.util.Collections"%>
<%@page import="track.pro.project.entites.Project"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Task</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css"/>
<script>
    function validateForm() {
        var startTime = document.forms["taskForm"]["startTime"].value;
        var compTime = document.forms["taskForm"]["compTime"].value;
        var currentDate = new Date().toISOString().slice(0, 16);

        if (startTime < currentDate) {
            alert("Start time cannot be before the current date and time.");
            return false;
        }

        if (startTime > compTime) {
            alert("Start time cannot be greater than completion time.");
            return false;
        }
        return true;
    }

    function setMinCompTime() {
        var startTime = document.forms["taskForm"]["startTime"].value;
        var compTimeInput = document.forms["taskForm"]["compTime"];
        compTimeInput.min = startTime;
    }

    window.onload = function() {
        var startTimeInput = document.forms["taskForm"]["startTime"];
        var currentDate = new Date();
        var year = currentDate.getFullYear();
        var month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
        var day = currentDate.getDate().toString().padStart(2, '0');
        var hours = currentDate.getHours().toString().padStart(2, '0');
        var minutes = currentDate.getMinutes().toString().padStart(2, '0');
        var minDate = `${year}-${month}-${day}T${hours}:${minutes}`;
        startTimeInput.setAttribute("min", minDate);
    };
</script>
</head>
<body>

    <div class="centre-text-fullheight">
        <h1>Update Task</h1>
    </div>
    
      <a href="/assosiateDashboard"><button class="button-17">Back to Dashboard</button></a>

<% if (request.getParameter("success") != null) { %>
    <div class="toast success">
        Task added successfully!
    </div>
<% } %>

<%
    List<Task> listOfTasks = (List<Task>) request.getAttribute("listOfTasks");
    if (listOfTasks != null) {
        Collections.sort(listOfTasks, (r1, r2) -> r1.getTaskId() - r2.getTaskId());
    }
%>

<form name="taskForm" action="/task/updateTask" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
    <div>   
        Task Title:
        <select name="taskId">
            <% if (listOfTasks != null) {
                for (Task task : listOfTasks) { %>
                    <option value="<%= task.getTaskId() %>"><%= task.getTitle() %></option>
            <% } } %>
        </select>
    </div>
    <br/>
    <div>
        Start Time:
        <input type="datetime-local" name="startTime" required onchange="setMinCompTime()"/>
    </div>
    <br/>
    <div>
        Completion Time:
        <input type="datetime-local" name="compTime" required/>
    </div>
    <br/>
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