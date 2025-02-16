<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 30px;
        }
        .table-hover tbody tr:hover {
            background-color: #f9f9f9;
        }
        .btn-action {
            transition: all 0.3s ease-in-out;
        } 
        .btn-action:hover {
            transform: scale(1.1);
        }
        .blocked {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>

    <%@ include file="navbar.jsp" %>

    <div class="container">
        <h2 class="text-center mb-4">User List</h2>

        <c:if test="${empty users}">
            <p class="text-center">No users found.</p>
        </c:if>

        <c:if test="${not empty users}">
            <table class="table table-bordered table-hover text-center">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                       
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.email}</td>
                           
                            <td>
                                <c:choose>
                                    <c:when test="${user.blocked}">
                                        <span class="blocked">Blocked</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-success">Active</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/user/edit/${user.id}" class="btn btn-primary">Edit</a>

                                <a href="/user/delete/{id}=${user.id}" class="btn btn-danger btn-sm btn-action" onclick="return confirm('Are you sure?')">Delete</a>
                                <c:choose>
                                    <c:when test="${user.blocked}">
                                        <a href="/user/unblock?id=${user.id}" class="btn btn-success btn-sm btn-action">Unblock</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/user/block?id=${user.id}" class="btn btn-secondary btn-sm btn-action">Block</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <div class="text-center mt-4">
            <a href="/index" class="btn btn-primary">Back to Home</a>
        </div>
    </div>

</body>
</html>
