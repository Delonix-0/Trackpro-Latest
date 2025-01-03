<%@page import="track.pro.user.entites.User"%>
<%@page import="java.util.Collections"%>
<%@page import="track.pro.project.entites.Project"%>
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

 <div class="centre-text-fullheight">
    <h1 > Add Project </h1>
    </div>
    
    <a href="/superAdminDashboard"><button class="button-17">Back to Dashboard</button></a>
    <a href="/project/viewAllProject"><button class="button-17"> Project List</button></a>
 

 
<% if (request.getParameter("success") != null) { %>
    <div class="toast success">
        Project added successfully!
    </div>
<% } %>
 
<%-- <%
    List<Project> listOfProjects = (List<Project>) request.getAttribute("listOfProjects");
    if (listOfProjects != null) {
        Collections.sort(listOfProjects, (r1, r2) -> r1.getProjectId() - r2.getProjectId());
    }
%> --%>
 
<%
List<User> listOfUsers = (List<User>) request.getAttribute("listOfUsers");
if (listOfUsers != null) {
    Collections.sort(listOfUsers, (r1, r2) -> r1.getUser_id() - r2.getUser_id());
}
%>
 
<form action="/project/project" method="post" enctype="multipart/form-data">
    
    <input type="text" name="projectName" placeholder="Enter project name" /> <br />
    <%-- <select name="projectId">
        <% if (listOfProjects != null) {
            for (Project project : listOfProjects) { %>
                <option value="<%= project.getProjectId() %>"><%= project.getProjectName() %></option>
        <% } } %>
    </select><br> --%>
   <div>
    Description:
    <textarea rows="4" cols="50" name="description" placeholder="Enter Description" maxlength="150" required></textarea><br/>
    </div> 
    <br>
    
     <div>
    Assigned To:    
    <select name="assignedTo">
        <% if (listOfUsers != null) {
            for (User user : listOfUsers) { %>
                <option value="<%= user.getUser_id() %>"><%= user.getFull_name() %></option>
        <% } } %>
    </select><br/>
    
    </div>
    <br>
    
    <!-- Status:
    <select name="status" required>
        <option value="true">Active</option>
        <option value="false">Inactive</option>
    </select><br/> -->
    <div>
    Created At:
    <input type="datetime-local" id="createdAt" name="createdAt" required/><br/>
    </div>
    <br>
    
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