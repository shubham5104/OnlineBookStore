<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/profile.css">
</head>
<body>

<div class="container mt-5">
    <div class="card shadow-lg p-4">
        <h2 class="text-center">User Profile</h2>

        <!-- ✅ Success & Error Messages -->
        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <div class="row">
            <div class="col-md-6">
                <!-- ✅ Profile Details -->
                <h4>Profile Details</h4>
                <p><strong>Name:</strong> ${user.name}</p>
                <p><strong>Email:</strong> ${user.email}</p>
                <p><strong>Phone:</strong> ${user.phone}</p>
                <p><strong>Address:</strong> ${user.address}</p>
            </div>

            <div class="col-md-6">
                <!-- ✅ Edit Profile Form -->
                <h4>Edit Profile</h4>
                <form action="/user/updateProfile" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    
                    <div class="mb-2">
                        <label class="form-label">Name:</label>
                        <input type="text" name="name" class="form-control" value="${user.name}" required>
                    </div>

                    <div class="mb-2">
                        <label class="form-label">Phone:</label>
                        <input type="text" name="phone" class="form-control" value="${user.phone}" required>
                    </div>

                    <div class="mb-2">
                        <label class="form-label">Address:</label>
                        <input type="text" name="address" class="form-control" value="${user.address}" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Update Profile</button>
                </form>
            </div>
        </div>

        <hr>

        <!-- ✅ Change Password Form -->
        <h4>Change Password</h4>
        <form action="/user/changePassword" method="post">
            <input type="hidden" name="id" value="${user.id}">

            <div class="mb-2">
                <label class="form-label">Current Password:</label>
                <div class="input-group">
                    <input type="password" id="currentPassword" name="currentPassword" class="form-control" required>
                    <button type="button" class="btn btn-outline-secondary" onclick="togglePassword('currentPassword', this)">👁</button>
                </div>
            </div>

            <div class="mb-2">
                <label class="form-label">New Password:</label>
                <div class="input-group">
                    <input type="password" id="newPassword" name="newPassword" class="form-control" required>
                    <button type="button" class="btn btn-outline-secondary" onclick="togglePassword('newPassword', this)">👁</button>
                </div>
            </div>

            <div class="mb-2">
                <label class="form-label">Confirm Password:</label>
                <div class="input-group">
                    <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required>
                    <button type="button" class="btn btn-outline-secondary" onclick="togglePassword('confirmPassword', this)">👁</button>
                </div>
            </div>

            <button type="submit" class="btn btn-warning">Change Password</button>
        </form>

        <br>
        <a href="/index" class="btn btn-secondary">Go to Home</a>
    </div>
</div>

<!-- ✅ Bootstrap & JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function togglePassword(inputId, button) {
        var inputField = document.getElementById(inputId);
        if (inputField.type === "password") {
            inputField.type = "text";
            button.innerHTML = "🔒"; // Change to lock icon
        } else {
            inputField.type = "password";
            button.innerHTML = "👁"; // Change back to eye icon
        }
    }
</script>

</body>
</html>
