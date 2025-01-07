<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Update Profile</title>
<link href="../css/profile.css" rel="stylesheet" type="text/css" />
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
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
            showToast("Profile updated successfully!");
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
    <div>
        <!-- Placeholder for the dashboard button -->
        <a id="dashboardButton"><button class="button-17">Back to Dashboard</button></a>

        <div class="profile-body">
            <div class="profile-container">
                <h1>Update Profile</h1>

                <c:if test="${not empty error}">
                    <p style="color: red;">${error}</p>
                </c:if>

                <form action="update_profile" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="user_name" value="${user_name}" />
                    <div class="form-group">
                        <label for="full_name">Full Name:</label>
                        <input type="text" name="full_name" value="${full_name}" readonly />
                    </div>
                    <div class="form-group">
                        <label for="mobile">Mobile:</label>
                        <input type="text" id="mobile" name="mobile" value="${mobile}" required />
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" value="${email}" required />
                    </div>
                    <div class="form-group">
                        <label for="profile_image">Profile Image:</label>
                        <input type="file" id="profile_image" name="profile_image" />
                    </div>
                    <div class="form-group">
                        <label for="profile_resume">Profile Resume:</label>
                        <input type="file" id="profile_resume" name="profile_resume" />
                    </div>
                    
                    <div class="form-group">
                        <button type="submit">Update Profile</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        // Get the role_id from the JSP
        var roleId = ${role_id};

        // Determine the href based on the role_id
        var dashboardHref;
        if (roleId === 1) {
            dashboardHref = "/superAdminDashboard";
        } else if (roleId === 2) {
            dashboardHref = "/managerDashboard";
        } else {
            dashboardHref = "/assosiateDashboard";
        }

        // Set the href attribute of the dashboard button
        document.getElementById("dashboardButton").setAttribute("href", dashboardHref);

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