<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Details</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

    <%@ include file="navbar.jsp" %>
 
    <div class="container mt-4">
        <c:if test="${not empty book}">
            <div class="row">
                <div class="col-md-4">
                    <img src="${book.imageUrl}" class="img-fluid rounded shadow" alt="${book.title}">
                </div>
                <div class="col-md-8">
                    <h2>${book.title}</h2>
                    <h5 class="text-muted">by ${book.author}</h5>
                    <p><strong>Category:</strong> ${book.category}</p>
                    <p><strong>Description:</strong> ${book.description}</p>
                    <p class="text-success fs-4">
                    <h4 class="dis">${book.discount}% off</h4>
                        <s class="text-danger">₹${book.price}</s> ₹${book.discountedPrice.intValue()}
                    </p>

                <!--      <a href="/cart/add?id=${book.id}" class="btn btn-success">🛒 Add to Cart</a> -->
                    <a href="/index" class="btn btn-outline-primary">⬅ Back to Home</a>
                </div>
            </div>
        </c:if>

        <c:if test="${empty book}">
            <p class="text-center text-danger">Book not found.</p>
        </c:if>
    </div>
    
    

</body>
</html>
