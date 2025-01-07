<%@page import="track.pro.user.entites.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Managers</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<div class="centre-text-fullheight">
		<h1>Manager List</h1>
	</div>

	<a href="/superAdminDashboard"><button class="button-17">Back
			to Dashboard</button></a>
	<%
	List<User> listOfManager = (List<User>) request.getAttribute("listOfManagers");
	%>
	<table border="1">

		<tr>
			<th>S. No</th>
			<th>FullName</th>
			<th>Mobile</th>
			<th>Email</th>
			<th>Current Status</th>
			<th>Authorize/Revoke</th>
		</tr>
		<%
		int sNo = 0;
		for (User manager : listOfManager) {
			sNo = sNo + 1;
			boolean status = manager.isIs_authorized();
		%>
		<tr>
			<td><%=sNo%></td>

			<td><%=manager.getFull_name()%></td>
			<td><%=manager.getMobile()%></td>
			<td><%=manager.getEmail()%></td>
			<td><%=status ? "Manager is Authorized" : "Authorization Pending"%></td>
			<td><a
				href="/superadmin/toggleAuthority/<%=manager.getUser_id()%>"><%=status ? "Revoke" : "Authorize"%></td>
		</tr>

		<%
		}
		%>
	</table>

</body>
</html>



