<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager Leave dashboard</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
<script>
    function validateForm() {
        var startDate = document.querySelector('input[name="start_date"]').value;
        var endDate = document.querySelector('input[name="end_date"]').value;
        var currentDate = new Date().toISOString().split('T')[0];

        if (startDate < currentDate) {
            alert("Start date cannot be before the current date.");
            return false;
        }

        if (startDate > endDate) {
            alert("Start date cannot be before  end date.");
            return false;
        }
        return true;
    }

    window.onload = function() {
        var startDateInput = document.querySelector('input[name="start_date"]');
        var currentDate = new Date().toISOString().split('T')[0];
        startDateInput.setAttribute("min", currentDate);
    };
</script>
</head>
<body>
<header>
    <div class="centre-text-fullheight">
        <h1>Leave Dashboard</h1>
    </div>
    <a href="/managerDashboard"><button class="button-17">Back to Dashboard</button></a>
</header>

<main>
    <!-- Leave Balance Section -->
    <section>
        <div class="centre-text-fullheight">
            <h1>Leave Balance</h1>
        </div>
        <form>
            <p>Total Leaves: ${leaveBalance.total_leaves}</p>
            <p>Remaining Leaves: ${leaveBalance.remaining_leaves}</p>
        </form>
    </section>

    <!-- Submit Leave Request Section -->
    <section>
        <div class="centre-text-fullheight">
            <h1>Submit Leave Request</h1>
        </div>
        <c:if test="${not empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>
        <form action="/submitleaverequest" method="post" onsubmit="return validateForm()">
            <!-- Hidden field for user_name -->
            <input type="hidden" name="user_name" value="${user_name}" />
            <div>
                <label for="leaveType">Leave Type:</label>
                <select name="leave_type" required>
                    <option value="Sick">Sick</option>
                    <option value="Casual">Casual</option>
                    <option value="Annual">Annual</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            <br>
            <div>
                <label for="startDate">Start Date:</label>
                <input type="date" name="start_date" required>
            </div>
            <br>
            <div>
                <label for="endDate">End Date:</label>
                <input type="date" name="end_date" required>
            </div>
            <br>
            <button type="submit" style="background-color: #319DF8; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px;">Submit Leave Request</button>
        </form>
    </section>
</main>
</body>
</html>