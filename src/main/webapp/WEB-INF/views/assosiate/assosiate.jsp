<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Associate Dashboard</title>
<script type="text/javascript">
    function getCookie(name) {
        let cookieArr = document.cookie.split(";");
        for (let i = 0; i < cookieArr.length; i++) {
            let cookiePair = cookieArr[i].split("=");
            if (name == cookiePair[0].trim()) {
                return decodeURIComponent(cookiePair[1]);
            }
        }
        return null;
    }

    function setTaskUpdateLink() {
        let username = getCookie("username");
        if (username) {
            let taskLink = document.getElementById("taskUpdateLink");
            taskLink.href = "/task/openTaskPage?user_name=" + username;
        }
    }

    function setLeaveManagementLink() {
        let username = getCookie("username");
        if (username) {
            let leaveLink = document.getElementById("leaveManagementLink");
            leaveLink.href = "/leaves_management?user_name=" + username;
        }
    }

    function setTimesheetManagementLink() {
        let username = getCookie("username");
        if (username) {
            let timesheetLink = document.getElementById("TimesheetManagementLink");
            timesheetLink.href = "/timesheet/openTimesheetPage?user_name=" + username;
        }
    }

    function setProfileManagementLink() {
        let username = getCookie("username");
        if (username) {
            let profileLink = document.getElementById("ProfileManagementLink");
            profileLink.href = "/profile_management?user_name=" + username;
        }
    }

    function clearUsernameCookie() {
        document.cookie = "username=; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT";
        window.location.href = "/openloginPage";
    }
</script>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="header">
    <div class="centre-text-fullheight">
        <h1>Associate Dashboard</h1>
    </div>
</div>
<div class="container">
    <div class="card">
        <h2>Home Page</h2>
        <a href="/openIndexPage">Go to Home</a>
    </div>
    <div class="card" onclick="setTaskUpdateLink()">
        <h2>Task Update</h2>
        <a id="taskUpdateLink" href="#">Update Your Task</a>
    </div>
    <div class="card" onclick="setTimesheetManagementLink()">
        <h2>TimeSheet Management</h2>
        <a id="TimesheetManagementLink" href="#">Manage TimeSheet</a>
    </div>
    <div class="card" onclick="setLeaveManagementLink()">
        <h2>Leaves Management</h2>
        <a id="leaveManagementLink" href="#">Manage Leaves</a>
    </div>
</div>
<div class="container">
    <div class="card" onclick="setProfileManagementLink()">
        <h2>Manage Profile</h2>
        <a id="ProfileManagementLink" href="#">Manage Profile</a>
    </div>
    <div class="card">
        <h2>Logout</h2>
        <a href="javascript:void(0);" onclick="clearUsernameCookie()">Logout</a>
    </div>
</div>
</body>
</html>