<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Book</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

    <%@ include file="navbar.jsp" %>

    <div class="container mt-4">
        <h2>Edit Book</h2>
       <form action="${pageContext.request.contextPath}/manage/books/update" method="post">

            <input type="hidden" name="id" value="${book.id}">

            <div class="mb-3">
                <label>Title</label>
                <input type="text" name="title" class="form-control" value="${book.title}" required>
            </div>

            <div class="mb-3">
                <label>Author</label>
                <input type="text" name="author" class="form-control" value="${book.author}" required>
            </div>

            <div class="mb-3">
                <label>Category</label>
                <input type="text" name="category" class="form-control" value="${book.category}" required>
            </div>

            <div class="mb-3">
                <label>Description</label>
                <input type="text" name="description" class="form-control" value="${book.description}" required>
            </div>

            <div class="mb-3">
                <label>Image URL</label>
                <input type="text" name="imageUrl" class="form-control" value="${book.imageUrl}" required>
            </div>

    

            <div class="mb-3">
                <label>Discount</label>
                <input type="number" step="0.01" name="discount" class="form-control" value="${book.discount}" required>
            </div>

            <div class="mb-3">
                <label>Price</label>
                <input type="number" step="0.01" name="price" class="form-control" value="${book.price}" required>
            </div>

            <div class="mb-3">
                <label>Stock</label>
                <input type="number" name="stock" class="form-control" value="${book.stock}" required>
            </div>

            <button type="submit" class="btn btn-primary">Update</button>
            <a href="${pageContext.request.contextPath}/manage/books/list" class="btn btn-secondary">Cancel</a>
        </form>
    </div>

</body>
</html>
