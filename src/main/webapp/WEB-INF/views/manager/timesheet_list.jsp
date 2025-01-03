<%@page import="track.pro.timesheet.entities.Timesheet"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timesheet List</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <%
    List<Timesheet> listOfTimesheet = (List<Timesheet>) request.getAttribute("listOfTimesheet");
    %>
    <div class="centre-text-fullheight">
        <h1>Timesheet List</h1>
    </div>
    
    <a href="/managerDashboard"><button class="button-17">Back to Dashboard</button></a>

    <table>
        <tr>
            <th>S. No</th>
            <th>Task Id</th>
            <th>User Id</th>
            <th>Date</th>
            <th>Hours Logged</th>
            <th>Created At</th>
            <th>Status</th>
            <th>Approved/Pending</th>
            <th>Delete Timesheet</th>
        </tr>
        <%
        int sNo = 0;
        for (Timesheet timesheet : listOfTimesheet) {
            sNo = sNo + 1;
            boolean status = timesheet.isStatus();
        %>
        <tr>
            <td><%= sNo %></td>
            <td><%= timesheet.getTask_id() %></td>
            <td><%= timesheet.getUser_id() %></td>
            <td><%= timesheet.getDate() %></td>
            <td><%= timesheet.getHours_logged() %></td>
            <td><%= timesheet.getCreated_at() %></td>
            <td><%= status ? "Timesheet is Approved" : "Timesheet is not Filled" %></td>
            <td><a href="/timesheet/toggleAuthority/<%= timesheet.getTask_id() %>"><%= status ? "Approved" : "Pending" %></a></td>
            <td>
                <input type="hidden" id="timesheetId_<%= sNo %>" value="<%= timesheet.getTimesheet_id() %>" />
                <input type="button" class="button-delete" value="Delete" onclick="deleteTimesheet(<%= sNo %>);" />
            </td>
        </tr>
        <%
        }
        %>
    </table>

    <script>
    function deleteTimesheet(sNo) {
        if (confirm('Are you sure you want to delete this timesheet?')) {
            const timesheetId = document.getElementById('timesheetId_' + sNo).value;
            const xhr = new XMLHttpRequest();
            xhr.open('POST', '/timesheet/delete', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // Check if the response contains the success message
                    if (xhr.responseText.includes('Timesheet deleted successfully!')) {
                        alert('Timesheet deleted successfully!');
                        location.reload(); // Refresh the page
                    } else {
                        alert('Failed to delete the timesheet.');
                    }
                }
            };
            xhr.send('timesheetId=' + timesheetId);
        }
    }
    </script>

</body>
</html>