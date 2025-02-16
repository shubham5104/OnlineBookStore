<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Available Books</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- Include jQuery once -->
    
    <script>
        $(document).ready(function () {
            // Fetch the current cart count every 5 seconds
            setInterval(function () {
                $.ajax({
                    type: "GET",
                    url: "/cart/count",  // Backend endpoint to get the cart count
                    dataType: "json",
                    success: function (response) {
                        $("#cart-count").text(response.cartSize);  // Update the cart count
                    },
                    error: function () {
                        console.log("Failed to fetch cart count.");
                    }
                });
            }, 5000); // 5000 ms = 5 seconds

            $(".add-to-cart").click(function (event) {
                event.preventDefault();

                var bookId = $(this).data("book-id");
                var quantity = $(this).siblings("input[name='quantity']").val();

                $.ajax({
                    type: "POST",
                    url: "/cart/add",
                    data: { bookId: bookId, quantity: quantity },
                    dataType: "json",
                    success: function (response) {
                        alert(response.message); // Show confirmation
                        $("#cart-count").text(response.cartSize); // Update cart count immediately
                    },
                    error: function () {
                        alert("Failed to add book to cart.");
                    }
                });
            });
        });
    </script>
</head>
<body>

    <%@ include file="navbar.jsp"%>

    <div class="container mt-4">
        <h2 class="text-center">Available Books 📚</h2>

        <!-- 🛒 Cart Item Count
        <div class="text-end mb-3">
            <span class="badge bg-success">🛒 Cart Items: <span id="cart-count">${cartSize}</span></span>

        </div> -->

        <div class="row">
            <c:forEach var="book" items="${books}">
                <div class="col-md-4 mb-4">
                    <div class="card shadow-sm">
                        <img src="${book.imageUrl}" class="card-img-top" alt="Book Cover"
                            style="height: 200px; object-fit: cover;">
                        <div class="card-body">
                            <h5 class="card-title">${book.title}</h5>
                            <p class="card-text"><strong>Author:</strong> ${book.author}</p>
                            
                             <p class="text-success fs-4">
                    		<h4 class="dis">${book.discount}% off</h4>
                       	    <s class="text-danger">₹${book.price}</s> ₹${book.discountedPrice.intValue()}
                 		    </p>
                 <!--       <p class="card-text"><strong>Price:</strong> ₹${book.price}</p>-->
                            <p class="card-text"><strong>Stock:</strong> ${book.stock}</p>

                            <div class="input-group mb-2">
                                <input type="number" name="quantity" min="1" max="${book.stock}" class="form-control" required>
                                <button class="btn btn-primary add-to-cart" data-book-id="${book.id}">🛒 Add to Cart</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</body>
</html>
