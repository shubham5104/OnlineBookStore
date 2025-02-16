<%@ page import="com.bookstore.entity.Users" %>
<%@ page session="true" %>
<%
    Users loggedAdmin = (Users) session.getAttribute("loggedUser");
%>
<style>
    .navbar {
        background-color: #87CEEB !important; /* Dark Theme */
    }
    .navbar-brand {
        font-weight: bold;
        color: white !important; /* Yellow Highlight */
    }
    .nav-link {
        color: white !important;
        transition: all 0.3s ease-in-out;
    }
    .nav-link:hover {
        color: #ffc107 !important;
        transform: scale(1.1);
    }
    .btn-logout {
        background-color: #dc3545;
        border: none;
        transition: all 0.3s ease-in-out;
    } 
    .btn-logout:hover {
        background-color: #bd2130;
        transform: scale(1.1);
    }
</style>

<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand" href="/admin/dashboard">Admin Panel</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#adminNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="adminNavbar">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/admin/dashboard">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/books">Manage Books</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/orders">Orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/users">Users</a>
                </li>
                
                <% if (loggedAdmin != null) { %>
                    <li class="nav-item">
                        <span class="nav-link">Welcome, <%= loggedAdmin.getEmail() %>!</span>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-logout btn-sm ms-2" href="/admin/logout">Logout</a>
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
