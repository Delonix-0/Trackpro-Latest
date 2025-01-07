<!DOCTYPE html>
<%@page import="track.pro.tasks.entites.Task"%>
<%@page import="track.pro.user.entites.User"%>
<%@page import="java.util.Collections"%>
<%@page import="track.pro.project.entites.Project"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="UTF-8">
<title>Update Task</title>
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

    function validateForm() {
        var startTime = document.forms["taskForm"]["startTime"].value;
        var compTime = document.forms["taskForm"]["compTime"].value;
        var currentDate = new Date().toISOString().slice(0, 16);

        var startDateTime = new Date(startTime);
        var compDateTime = new Date(compTime);
        var currentDateTime = new Date(currentDate);

        if (startDateTime >= compDateTime) {
            alert("Start time cannot be greater than or equal to completion time.");
            return false;
        }

        var username = getCookie("username");
        if (username) {
            var form = document.forms["taskForm"];
            form.action = "/task/updateTask?user_name=" + encodeURIComponent(username);
        }

        return true;
    }

    function setMinCompTime() {
        var startTime = document.forms["taskForm"]["startTime"].value;
        var compTimeInput = document.forms["taskForm"]["compTime"];
        compTimeInput.min = startTime;
    }

    window.onload = function() {
        var startTimeInput = document.forms["taskForm"]["startTime"];
        var currentDate = new Date();
        var year = currentDate.getFullYear();
        var month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
        var day = currentDate.getDate().toString().padStart(2, '0');
        var hours = currentDate.getHours().toString().padStart(2, '0');
        var minutes = currentDate.getMinutes().toString().padStart(2, '0');
        var minDate = `${year}-${month}-${day}T${hours}:${minutes}`;
        startTimeInput.setAttribute("min", minDate);

        var username = getCookie("username");
        if (username) {
            document.getElementById("username").value = username;
        }

        // Check for success parameter
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('success')) {
            showToast("Task updated successfully!");
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
        <h1>Update Task</h1>
    </div>

    <a href="/assosiateDashboard"><button class="button-17">Back to Dashboard</button></a>

    <% if (request.getParameter("success") != null) { %>
        <div class="toast success">Task updated successfully!</div>
    <% } %>

    <% List<Task> listOfTasks = (List<Task>) request.getAttribute("listOfTasks");
        if (listOfTasks != null) {
            Collections.sort(listOfTasks, (r1, r2) -> r1.getTaskId() - r2.getTaskId());
        }
    %>

    <form name="taskForm" action="/task/updateTask" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="taskId">Task Title:</label>
            <select name="taskId" required>
                <% if (listOfTasks != null) { for (Task task : listOfTasks) { %>
                    <option value="<%= task.getTaskId() %>"><%= task.getTitle() %></option>
                <% } } %>
            </select>
        </div>
        <div class="form-group">
            <label for="startTime">Start Time:</label>
            <input type="datetime-local" name="startTime" required onchange="setMinCompTime()" />
        </div>
        <div class="form-group">
            <label for="compTime">Completion Time:</label>
            <input type="datetime-local" name="compTime" required />
        </div>
        <button type="submit">Submit</button>
    </form>

</body>
</html>