<%@ page language="java"contentType="text/html; charset=UTF-8"pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
 
<%@ include file="navbar.jsp" %>

<div class="container mt-5">
    <h2 class="text-center">Login</h2>
    
    <form action="LoginServlet" method="post">
        <div class="mb-3">
            <label>Email:</label>
            <input type="email" class="form-control" name="email" required>
        </div>
        <div class="mb-3">
            <label>Password:</label>
            <input type="password" class="form-control" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
    <p class="mt-3 text-center">Create new Acconut ? <a href="Registration_Page">Register here</a></p>
</div>

</body>
</html>
