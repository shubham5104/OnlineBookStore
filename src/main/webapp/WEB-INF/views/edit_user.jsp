<%@ page import="com.bookstore.entity.Users" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
 
<%@ include file="navbar.jsp" %>

<div class="container mt-4">
    <h2 class="text-center">Edit User</h2>

    <form action="/user/update" method="post" class="w-50 mx-auto">
        <input type="hidden" name="id" value="${user.id}">
        
        <div class="mb-3">
            <label>Name:</label>
            <input type="text" name="name" class="form-control" value="${user.name}" required>
        </div>
        
        <div class="mb-3">
            <label>Username:</label>
            <input type="text" name="username" class="form-control" value="${user.username}" required>
        </div>

        <div class="mb-3">
            <label>Email:</label>
            <input type="email" name="email" class="form-control" value="${user.email}" required>
        </div>

        <div class="mb-3">
            <label>Phone:</label>
            <input type="text" name="phone" class="form-control" value="${user.phone}" required>
        </div>

        <div class="mb-3">
            <label>Address:</label>
            <input type="text" name="address" class="form-control" value="${user.address}" required>
        </div>

        <button type="submit" class="btn btn-success w-100">Update</button>
    </form>
</div>

</body>
</html>
