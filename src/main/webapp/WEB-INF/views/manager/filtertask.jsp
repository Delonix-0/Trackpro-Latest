<%@page import="track.pro.project.entites.Project"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link  href="../css/styles.css" rel="stylesheet" type="text/css" / >
<body>
<%
	List<Project> listOfProject = (List<Project>) request.getAttribute("listofProjects");
	%>

 
 <div class="centre-text-fullheight">
    <h1 >Task filter</h1>
    </div>
<a href="/managerDashboard"><button class="button-17">Back to Dashboard</button></a>
	<form action="${pageContext.request.contextPath}/project/viewTasksByProject" method="post">
    <label for="projectId">Select Project:</label>
    <select name="projectId" id="projectId">
		<% for(Project project:listOfProject){%>
		
		<option value="<%=project.getProjectId()%>"><%=project.getProjectName()%></option>
		
		<% } %>
		</select><br><br>
        
    </select>
    <button type="submit">Submit</button>
</form>

 <div class="centre-text-fullheight">
    <h1 >Project List</h1>
    </div>
    

<table>
		
		<tr>
			<th>S. No</th>
			<th>Project Id</th>
			<th>Project Name</th>
			<th>Description</th>
			<th>Assigned To Manager</th>
			<th>created At</th>
			
			
			
			
		</tr>
		
		<%
		int sNo = 0;
		for (Project project :listOfProject ) {
			sNo=sNo+1;
			boolean status = project.isStatus();
		
		%>
 
		
		<tr>
			<td><%=sNo%></td>
			<td><%=project.getProjectId()%></td>
			<td><%=project.getProjectName()%></td>
			<td><%=project.getDescription()%></td>
			<td><%=project.getAssignedTo()%></td>
			<td><%=project.getCreatedAt()%></td>
			
			
		</tr>
 
		<%
		}
		%>
		
</table>

</body>
</html>