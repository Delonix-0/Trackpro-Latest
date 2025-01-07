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
<link href="../css/styles.css" rel="stylesheet" type="text/css"/ >
<body>
	<%
	List<Project> listOfProject = (List<Project>) request.getAttribute("listofProjects");
	%>





	</form>

	<div class="centre-text-fullheight">
		<h1>Project List</h1>
	</div>
	<a href="/superAdminDashboard"><button class="button-17">Back
			to Dashboard</button></a>

	<table>

		<tr>
			<th>S. No</th>

			<th>Project Name</th>
			<th>Description</th>
			<th>Assigned To Manager</th>
			<th>created At</th>
			<th>Status</th>
			<th>Completed/Pending</th>



		</tr>

		<%
		int sNo = 0;
		for (Project project : listOfProject) {
			sNo = sNo + 1;
			boolean status = project.isStatus();
		%>


		<tr>
			<td><%=sNo%></td>

			<td><%=project.getProjectName()%></td>
			<td><%=project.getDescription()%></td>
			<td><%=project.getAssignedTo()%></td>
			<td><%=project.getCreatedAt()%></td>

			<td><%=status ? "Project is Completed" : "Project is Pending"%></td>
			<td><a
				href="/project/toggleAuthority/<%=project.getProjectId()%>"><%=status ? "Completed" : "Pending"%></a>
			</td>
		</tr>

		<%
		}
		%>

	</table>

</body>
</html>