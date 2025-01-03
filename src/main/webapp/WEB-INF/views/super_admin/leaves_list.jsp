<%@page import="track.pro.leaves.entites.Leaves"%>
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
<div class="centre-text-fullheight">
		<h1>Requested Leaves</h1>
	</div>

<a href="/superAdminDashboard"><button class="button-17">Back to Dashboard</button></a>

<%
	List<Leaves> listOfLeaves = (List<Leaves>) request.getAttribute("leaveRequests");
	%>

<table>
	
		<tr>
			<th>S. No</th>
			<th>Leave Id</th>
			<th>User Id</th>
			<th>Leave type</th>
			<th>Start Date</th>
			<th>End Date</th>
			<th>Request At<th>
			<th>Current Status </th>
			
			
		</tr>
		<%
		int sNo = 0;
		for (Leaves leaves :listOfLeaves ) {
			sNo=sNo+1;
			boolean status = leaves.isStatus();
		%>
 
		<tr>
			<td><%=sNo%></td>
			<td><%=leaves.getLeave_id()%></td>
			<td><%=leaves.getUser_id()%></td>
			<td><%=leaves.getLeave_type()%></td>
			<td><%=leaves.getStart_date()%></td>
			<td><%=leaves.getEnd_date()%></td>
			<td><%=leaves.getRequested_at()%></td>
			
			
			
			
			<td><%=status ? "Leave is Approved" : "Leave Pending"%></td>
			<td><a href="/toggleAuthority/<%=leaves.getLeave_id()%>"><%=status ? "Approved" : "Pending"%></a>
			</td>
		</tr>
 
		<%
		}
		%>
</table>

</body>
</html>