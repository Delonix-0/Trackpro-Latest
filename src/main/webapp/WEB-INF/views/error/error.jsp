<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link href="../css/styles.css" rel="stylesheet" type="text/css"/>
    <style>
        .error-container {
            text-align: center;
            padding: 40px;
            margin: 20px auto;
            max-width: 600px;
        }
        .error-message {
            color: #dc3545;
            margin: 20px 0;
            padding: 15px;
            border: 1px solid #dc3545;
            border-radius: 4px;
            background-color: #f8d7da;
        }
        .back-button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-top: 20px;
        }
        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Oops! Something went wrong</h1>
        <div class="error-message">
            ${errorMessage != null ? errorMessage : 'An unexpected error occurred. Please try again later.'}
        </div>
        <a href="javascript:history.back()" class="back-button">Go Back</a>
    </div>
</body>
</html> 