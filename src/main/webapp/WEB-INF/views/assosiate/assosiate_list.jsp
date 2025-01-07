<%@page import="track.pro.user.entites.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Assosiate List</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css"/ >
</head>
<body>

	<%
	List<User> listOfUser = (List<User>) request.getAttribute("listOfUser");
	%>
	<div class="centre-text-fullheight">
		<h1>Associate List</h1>
	</div>

	<a href="/managerDashboard">
		<button class="button-17">Back to Dashboard</button>
	</a>

	<table>

		<tr>
			<th>S. No</th>
			<th>User Id</th>
			<th>Full name</th>
			<th>Mobile Number</th>
			<th>Email</th>
			<th>Currrent Status</th>
			<th>Revoke/Authorized</th>
		</tr>
		<%
		int sNo = 0;
		for (User user : listOfUser) {
			sNo = sNo + 1;
			boolean status = user.isIs_authorized();
		%>

		<tr>
			<td><%=sNo%></td>
			<td><%=user.getUser_id()%></td>
			<td><%=user.getFull_name()%></td>

			<td><%=user.getMobile()%></td>
			<td><%=user.getEmail()%></td>



			<td><%=status ? "User Authorized" : "User Revoke"%></td>
			<td><a href="/assosiate/toggleAuthority/<%=user.getUser_id()%>"><%=status ? "Revoke" : "Authorized"%></a>
			</td>
		</tr>

		<%
		}
		%>
	</table>


</body>
</html>