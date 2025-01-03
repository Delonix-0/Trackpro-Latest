<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manager Dashboard</title>
    <link href="../css/styles.css" rel="stylesheet" type="text/css" />
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

        function setLeaveManagementLink() {
            let username = getCookie("username");
            if (username) {
                let leaveLink = document.getElementById("leaveManagementLink");
                leaveLink.href = "/leaves_management?user_name=" + username;
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
</head>
<body>
    <div class="centre-text-fullheight">
        <h1>Manager Dashboard</h1>
    </div>
    <div class="container">
        <a href="/openIndexPage">
            <div class="card">
                <h2>Home Page</h2>
                <a href="/openIndexPage">Go to Home</a>
            </div>
        </a>

        <div class="card">
            <h2>Task Management</h2>
            <a href="/task/opencreateTaskPage">Manage Tasks</a>
        </div>
        <div class="card">
            <h2>Assosiate Management</h2>
            <a href="/assosiate/viewAllAssosiate">Manage Assosiates</a>
        </div>
        <div class="card">
            <h2>Time Sheet Management</h2>
            <a href="/timesheet/viewAllTimesheet">Manage Time Sheet</a>
        </div>
    </div>
    <div class="container">
        <div class="card">
            <h2>Project Management</h2>
            <a href="/project/viewAllProjecttask">List of Projects</a>
        </div>
        <div class="card" onclick="setLeaveManagementLink()">
            <h2>Leaves Management</h2>
            <a id="leaveManagementLink" href="#">Manage Leaves</a>
        </div>
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