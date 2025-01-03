<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="track.pro.user.entites.Role"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Page</title>
    <link href="../css/styles.css" rel="stylesheet" type="text/css" />
    <script>
        function validateForm() {
            const name = document.getElementById("full_name").value;
            const mobileField = document.getElementById("mobile").value;
            const emailField = document.getElementById("email").value;
            const mobilePattern = /^[6-9]\d{9}$/; // Ensures the number starts with 6-9 and is 10 digits long
            const invalidNumbers = ["0000000000", "1234567890"];
            const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.(com|in)$/; // Email validation for .com or .in

            // Validate name
            if (name.length <= 1) {
                alert("Name must be more than one letter.");
                return false;
            }

            // Validate mobile number
            if (!mobilePattern.test(mobileField)) {
                alert("Please enter a valid 10-digit mobile number.");
                return false;
            }
            if (invalidNumbers.includes(mobileField)) {
                alert("The entered mobile number is invalid.");
                return false;
            }

            // Validate email
            if (!emailPattern.test(emailField)) {
                alert("Please enter a valid email address ending with .com or .in.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <%
        List<Role> listOfRoles = (List<Role>) request.getAttribute("listOfRoles");
        if (listOfRoles != null) {
            Collections.sort(listOfRoles, (r1, r2) -> r1.getRoleId() - r2.getRoleId());
        }
    %>

    <div class="centre-text-fullheight">
		<h1>Register Here</h1>
	</div>
    <form action="/registration" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
        <br> <label for="full_name">Full Name:</label> <input type="text" id="full_name" name="full_name" required><br>
        <input type="text" name="user_name" placeholder="Enter username" required /><br>
        Gender:
        <input type="radio" name="gender" value="m"> Male
        <input type="radio" name="gender" value="f"> Female
        <input type="radio" name="gender" value="o"> Other<br>
        <br> <label for="mobile">Mobile:</label> <input type="text" id="mobile" name="mobile" required/><br>
        <input type="email" id="email" name="email" placeholder="Enter Email Address" required /><br>
        <input type="password" name="password" placeholder="Enter password" required /><br>
        <div>
        Upload Profile Image:
        <input type="file" name="profile_image" />
        </div>
        <br>
        <div>
        Upload Profile:
        <input type="file" name="profile" />
        </div>
        <br>
        <div>
                Select Role:
        <select name="role_id">
            <% if (listOfRoles != null) { 
                for (Role role : listOfRoles) { %>
                    <option value="<%=role.getRoleId()%>"><%=role.getRoleName() %></option>
            <% }
            } %>
        </select>
        </div>
<br>

        <input type="submit" value="Submit" /><br>
        <!-- Display Error Message -->
        <c:if test="${errorMessage != null}">
            <div style="color: red; margin-bottom: 20px;">${errorMessage}</div>
        </c:if>

        <!-- Display Success Message -->
        <c:if test="${successMessage != null}">
            <div style="color: green; margin-bottom: 20px;">${successMessage}</div>
        </c:if>
        <p>Already signed up? <a href="openloginPage">Login Here</a></p>
    </form>
</body>
</html>