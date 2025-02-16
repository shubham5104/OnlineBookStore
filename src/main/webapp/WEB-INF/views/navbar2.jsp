<%@ page import="com.bookstore.entity.Users" %>
<%@ page session="true" %>
<%
    Users loggedUser = (Users) session.getAttribute("loggedUser");
%>
<style>
    .navbar {
        background-color: #87CEEB !important; /* Light Sky Blue */
    }
    .navbar-brand {
        font-weight: bold;
    }
    .login-btn {
        padding: 10px 20px;
        font-size: 1rem;
        border-radius: 8px;
        transition: all 0.3s ease-in-out;
        border: none;
        color: white !important;
        text-align: center;
        display: inline-block;
    }
    .btn-login {
        background: linear-gradient(to right, #1e90ff, #00bfff); /* Blue Gradient */
    } 
    .btn-logout {
        background: linear-gradient(to right, #ff4b2b, #ff416c); /* Red Gradient */
    }
    .login-btn:hover {
        transform: scale(1.1);
        box-shadow: 0px 5px 12px rgba(0, 0, 0, 0.3);
    }
</style>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">Online Bookstore</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                
                    <li class="nav-item">
                        <a class="nav-link login-btn btn-login" href="/adminlogin">Login</a>
                    </li>
                
                   
                    
            </ul>
        </div>
    </div>
</nav>
