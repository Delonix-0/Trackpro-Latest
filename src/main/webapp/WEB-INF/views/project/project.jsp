<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
<%@page import="track.pro.project.entites.Project"%>
<%@page import="track.pro.project.services.ProjectService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Project</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<h1>Add Project</h1>

<% if (request.getParameter("success") != null) { %>
    <div class="toast success">
        Project added successfully!
    </div>
<% } %>
<%

  List<Project> listOfProjects= (List<Project>)request.getAttribute("listOfProjects");
   out.print(listOfProjects);

%>

<form action="/project/project" method="post" enctype="multipart/form-data">
    <input type="text" name="projectName" placeholder="Enter Project Name" maxlength="100" required/><br/>
    <textarea rows="4" cols="50" name="description" placeholder="Enter Description" maxlength="150" required></textarea><br/>
    <input type="number" name="createdBy" placeholder="Enter Created By" required/><br/>
    <select name="status" required>
        <option value="Active">Active</option>
        <option value="Completed">Completed</option>
        <option value="On_Hold">On_Hold</option>
    </select><br/>
    <label for="startedAt">Started At:</label>
    <input type="datetime-local" id="startedAt" name="startedAt" required/><br/>
    <label for="completedAt">Completed At:</label>
    <input type="datetime-local" id="completedAt" name="completedAt"/><br/>
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