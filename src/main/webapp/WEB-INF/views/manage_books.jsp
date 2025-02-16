<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.bookstore.entity.Book" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Books</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body> 

    <%@ include file="navbar.jsp" %>

    <div class="container mt-4">
        <h2 class="text-center">📚 Manage Books</h2>

        <!-- ✅ Display Success Message (if any) -->
        <% String success = (String) request.getAttribute("success");
            if (success != null) { %>
            <div class="alert alert-success"><%= success %></div>
        <% } %>

        <!-- ✅ Display Error Message (if any) -->
        <% String error = (String) request.getAttribute("error");
            if (error != null) { %>
            <div class="alert alert-danger"><%= error %></div>
        <% } %>

        <!-- ✅ Add Book Form -->
<form action="${pageContext.request.contextPath}/manage/books/add" method="post" class="mb-4">
    <div class="row">
        <div class="col-md-2">
            <input type="text" name="title" class="form-control" placeholder="Book Title" required>
        </div>
        <div class="col-md-2">
            <input type="text" name="author" class="form-control" placeholder="Author" required>
        </div>
        <div class="col-md-2">
            <input type="text" name="category" class="form-control" placeholder="Category" required>
        </div>
        <div class="col-md-2">
            <input type="text" name="description" class="form-control" placeholder="Description" required>
        </div>
        <div class="col-md-2">
            <input type="text" name="imageUrl" class="form-control" placeholder="Image URL" required>
        </div>
       
        <div class="col-md-2">
            <input type="number" step="0.01" name="discount" class="form-control" placeholder="Discount" required>
        </div>
        <div class="col-md-2">
            <input type="number" step="0.01" name="price" class="form-control" placeholder="Price" required>
        </div>
        <div class="col-md-2">
            <input type="number" name="stock" class="form-control" placeholder="Stock" required>
        </div>
        <div class="col-md-2">
            <button type="submit" class="btn btn-success">➕ Add Book</button>
        </div>
    </div>
</form>
        

        <!-- ✅ List of Books -->
        <table class="table table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Price</th>
                    <th>Stock</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Image</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Book> books = (List<Book>) request.getAttribute("books");
                    if (books != null) {
                        for (Book book : books) {
                %>
                <tr>
                    <td><%= book.getId() %></td>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getAuthor() %></td>
                    <td>₹<%= book.getPrice() %></td>
                    <td><%= book.getStock() %></td>
                    <td><%= book.getDescription() %></td>
                    <td><%= book.getCategory() %></td>
                    <td><img src="<%= book.getImageUrl() %>" alt="Book Image" width="50"></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manage/books/edit/<%= book.getId() %>" class="btn btn-warning">Edit</a>


                        <a href="${pageContext.request.contextPath}/manage/books/delete/<%= book.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?')">🗑 Delete</a>
                    </td>
                </tr>
                <% 
                        }
                    } 
                %>
            </tbody>
        </table>
        
        
    </div>
</body>
</html>
