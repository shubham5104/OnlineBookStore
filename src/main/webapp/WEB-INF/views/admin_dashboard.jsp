<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="assets/css/styles.css">
</head>
<body>

    <%@ include file="navbar.jsp" %>

    <div class="container mt-4">
        <h2 class="text-center">📊 Admin Dashboard</h2>

        <div class="row mt-4">
            <div class="col-md-4">
                <a href="/manage/books/list" class="btn btn-primary w-100">📚 Manage Books</a>
            </div>
            <div class="col-md-4">
                <a href="/user/allusers" class="btn btn-danger w-100">🚫 Manage Users</a>
            </div>
            <div class="col-md-4">
                <a href="admin_reports" class="btn btn-success w-100">📈 View Reports</a>
            </div>
        </div>
    </div>

</body>
</html>
