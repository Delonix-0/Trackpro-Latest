<!DOCTYPE html>
<%@page import="track.pro.project.entites.Project"%>
<%@page import="track.pro.user.entites.User"%>
<%@page import="java.util.Collections"%>
<%@page import="track.pro.tasks.entites.Task"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Form</title>
    <link href="../css/styles.css" rel="stylesheet" type="text/css"/>

    <script>
        function validateDate() {
            const created_at = document.querySelector('input[name="createdAt"]').value;
            const today = new Date().toISOString().split('T')[0];

            if (created_at < today) {
                alert("Dates cannot be in the past.");
                return false;
            }
            return true;
        }

        window.onload = function() {
            const dateInput = document.querySelector('input[name="createdAt"]');
            const today = new Date().toISOString().split('T')[0];
            dateInput.setAttribute("min", today);
        };
    </script>
</head>
<body>

<%
    List<User> listOfUsers = (List<User>) request.getAttribute("listOfUsers");
    Collections.sort(listOfUsers, (r1, r2) -> r1.getUser_id() - r2.getUser_id());
%>

<%
    List<Project> listofProjects = (List<Project>) request.getAttribute("listofProjects");
    Collections.sort(listofProjects, (r1, r2) -> r1.getProjectId() - r2.getProjectId());
%>

<%
    List<User> listOfEmployees = (List<User>) request.getAttribute("listOfEmployees");
    Collections.sort(listOfEmployees, (r1, r2) -> r1.getUser_id() - r2.getUser_id());
%>

<div class="centre-text-fullheight">
    <h1>Create Task</h1>
</div>

<a href="/managerDashboard"><button class="button-17">Back to Dashboard</button></a>
<a href="/task/viewAllTask"><button class="button-17">Task List</button></a>
<form action="/task/createtask" method="post" onsubmit="return validateDate()">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required><br><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" required></textarea><br><br>

    Created By:
    <select name="createdBy">
        <% for (User user : listOfUsers) { %>
            <option value="<%= user.getUser_id() %>"><%= user.getFull_name() %></option>
        <% } %>
    </select><br><br>

    Assigned To User:
    <select name="assignedTo">
        <% for (User user : listOfEmployees) { %>
            <option value="<%= user.getUser_id() %>"><%= user.getFull_name() %></option>
        <% } %>
    </select><br><br><br><br>

    Project ID:
    <select name="projectId">
        <% for (Project project : listofProjects) { %>
            <option value="<%= project.getProjectId() %>"><%= project.getProjectName() %></option>
        <% } %>
    </select><br><br>

    <label for="createdAt">Created At:</label>
    <input type="date" id="created_at" name="createdAt" required><br><br>

    <input type="submit" value="Submit">
</form>
</body>
</html>