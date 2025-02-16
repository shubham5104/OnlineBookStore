<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Online Bookstore</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        .card {
            transition: transform 0.3s ease-in-out;
        }
        .card:hover {
            transform: scale(1.05);
        } 
        .cart-notification {
            display: none;
            position: fixed;
            top: 20px;
            right: 20px;
            background: #28a745;
            color: white;
            padding: 15px;
            border-radius: 5px;
            z-index: 1000;
        }
        .dis{
        color: green;
        
        }
    </style>
</head>
<body>

    <%@ include file="navbar.jsp" %>

    <div class="container mt-4">
        <h2 class="text-center mb-4">Welcome to the Online Bookstore 📚</h2>

        <!-- ✅ Cart Notification -->
        <div id="cart-notification" class="cart-notification">Book added to cart!</div>

        <!-- ✅ Book Grid (4x2) -->
        <div class="row">
            <c:choose>
                <c:when test="${empty books}">
                    <p class="text-center">No books available.</p>
                </c:when>
                <c:otherwise>
                    <c:forEach var="book" items="${books}" varStatus="status">
                        <c:if test="${status.index < 8}"> <%-- Show only 8 books (4x2) --%>
                            <div class="col-md-3 mb-4">
                                <div class="card h-100 shadow">
                                    <img src="${book.imageUrl}" class="card-img-top" alt="Book Image" style="height: 200px; object-fit: cover;">
                                    <div class="card-body">
                                        <h5 class="card-title">${book.title}</h5>
                                        <p class="card-text">Category: ${book.category}</p>
                                        <p class="text-muted">
                                        <h4 class="dis">${book.discount}% off</h4>
                                           <s> ${book.price}</s> ₹${book.discountedPrice.intValue()}  
                                        </p>
                                        <a href="/manage/books/details?id=${book.id}" class="btn btn-primary">View Details</a>
                                        <button class="btn btn-success add-to-cart" data-id="${book.id}">🛒 Add to Cart</button>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- ✅ More Books Button -->
        <div class="text-center mt-4">
            <a href="/manage/books/Allbooks" class="btn btn-outline-primary">More Books</a>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            $(".add-to-cart").click(function() {
                let bookId = $(this).data("id");

                $.post("/cart/add", { bookId: bookId, quantity: 1 }, function(response) {
                    $("#cart-notification").fadeIn().delay(2000).fadeOut();
                }).fail(function() {
                    alert("Failed to add book to cart. Please try again.");
                });
            });
        });
    </script>

</body>
</html>
