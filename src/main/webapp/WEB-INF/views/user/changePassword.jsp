<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
<style>
.toast {
    visibility: hidden;
    min-width: 250px;
    margin-left: -125px;
    background-color: #333;
    color: #fff;
    text-align: center;
    border-radius: 5px;
    padding: 16px;
    position: fixed;
    z-index: 1;
    left: 50%;
    bottom: 30px;
    font-size: 17px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    opacity: 0;
    transition: opacity 0.5s, bottom 0.5s;
}

.toast.show {
    visibility: visible;
    opacity: 1;
    bottom: 50px;
}
</style>
<script>
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

    function redirectToDashboard() {
        // Get the user role from the cookie
        let userRole = getCookie("user_role");

        // Redirect based on the user role
        if (userRole == "1") {
            window.location.href = "/superAdminDashboard";
        } else if (userRole == "2") {
            window.location.href = "/managerDashboard";
        } else {
            window.location.href = "/assosiateDashboard";
        }
    }

    window.onload = function() {
        // Check for success parameter
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('success')) {
            showToast("Password changed successfully!");
        }
    };

    function showToast(message) {
        const toast = document.createElement('div');
        toast.className = 'toast show';
        toast.innerText = message;
        document.body.appendChild(toast);

        setTimeout(function() {
            toast.className = toast.className.replace('show', '');
            document.body.removeChild(toast);
        }, 3000);
    }
</script>
</head>

<body>
    <div class="centre-text-fullheight">
        <h1>Change Password</h1>
    </div>
    
    <a id="dashboardButton" onclick="redirectToDashboard()"><button class="button-17">Back to Dashboard</button></a>

    <div class="container">
        <form action="/changePassword" method="post">
            <div class="form-group">
                <label for="user_name">Username:</label>
                <input type="text" id="user_name" name="user_name" required>
            </div>
            <div class="form-group">
                <label for="oldPassword">Old Password:</label>
                <input type="password" id="oldPassword" name="oldPassword" required>
            </div>
            <div class="form-group">
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword" required>
            </div>
            <div class="form-group">
                <button type="submit">Change Password</button>
            </div>
            <c:if test="${not empty message}">
                <div class="message">${message}</div>
            </c:if>
        </form>
    </div>

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