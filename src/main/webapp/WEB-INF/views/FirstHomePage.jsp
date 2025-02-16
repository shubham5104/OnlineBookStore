<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Online Bookstore</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .btn-animated {
            transition: all 0.3s ease-in-out;
        } 
        .btn-animated:hover {
            transform: scale(1.1);
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }
        .fade-in {
            opacity: 0;
            animation: fadeIn 1s forwards;
        }
        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
    </style>
</head>
<body>

    <%@ include file="navbar2.jsp" %>

    <div class="container mt-4 fade-in">
        <h2 class="text-center mb-4">Welcome to the Online Bookstore 📚</h2>
        
        <!-- ✅ User Login/Registration with Animations -->
        <div class="row justify-content-center mb-4">
            <div class="col-md-6 text-center">
                <h4>User Access</h4>
                <a href="/user/login" class="btn btn-primary btn-animated m-2">User Login</a>
                <a href="Registration_Page" class="btn btn-success btn-animated m-2">User Registration</a>
            </div>
        </div>
    </div>

</body>
</html>
