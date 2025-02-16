<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Your Cart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        .cart-table {
            margin-top: 20px;
        }
        .cart-message {
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
        .disabled-btn {
            opacity: 0.5;
            pointer-events: none;
        }
    </style>
</head>
<body>

    <%@ include file="navbar.jsp"%>

    <div class="container mt-4">
        <h2 class="text-center">🛒 Your Cart</h2>

        <!-- ✅ Cart Notification -->
        <div id="cart-message" class="cart-message">Item updated!</div>

        <c:choose>
            <c:when test="${empty cartDetails}">
                <p class="text-center">Your cart is empty.</p>
            </c:when>
            <c:otherwise>
                <table class="table table-bordered cart-table">
                    <thead class="table-dark">
                        <tr>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="entry" items="${cartDetails}">
                            <c:set var="book" value="${entry.key}" />
                            <c:set var="quantity" value="${entry.value}" />
                            <tr id="row-${book.id}">
                                <td>${book.title}</td>
                                <td>${book.author}</td>
                                <td>₹<span id="price-${book.id}">${book.price}</span></td>
                                <td>
                                    <button class="btn btn-sm btn-outline-secondary decrease-qty" data-id="${book.id}" <c:if test="${quantity <= 1}">disabled</c:if>>-</button>
                                    <span id="quantity-${book.id}">${quantity}</span>
                                    <button class="btn btn-sm btn-outline-primary increase-qty" data-id="${book.id}">+</button>
                                </td>
                                <td>₹<span id="total-${book.id}">${book.price * quantity}</span></td>
                                <td>
                                    <button class="btn btn-danger btn-sm remove-item" data-id="${book.id}">Remove</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <h3>Total Price: ₹<span id="total-cart-price">${totalPrice}</span></h3>

                <button id="checkout-btn" class="btn btn-success">Place Order</button>
                <button id="clear-cart" class="btn btn-warning">Clear Cart</button>
            </c:otherwise>
        </c:choose>
    </div>

    <script>
    
    $(document).ready(function() {
        $("#checkout-btn").click(function() {
            $.post("/cart/checkout", function(response) {
                alert(response.message); // Show success or failure message
                if (response.message.includes("Order placed")) {
                    window.location.href = "/order/history"; // Redirect after checkout
                }
            }).fail(function() {
                alert("Checkout failed! Please try again.");
            });
        });
    });
    
    
        $(document).ready(function() {
            // ✅ Increase Quantity
            $(".increase-qty").click(function() {
                let bookId = $(this).data("id");
                updateQuantity(bookId, 1);
            });

            // ✅ Decrease Quantity
            $(".decrease-qty").click(function() {
                let bookId = $(this).data("id");
                updateQuantity(bookId, -1);
            });

            // ✅ Remove item from cart (AJAX)
            $(".remove-item").click(function() {
                let bookId = $(this).data("id");

                $.post("/cart/remove", { bookId: bookId }, function(response) {
                    if (response.success) {
                        $("#row-" + bookId).fadeOut(300, function() { 
                            $(this).remove(); 
                            updateCartTotal(response.totalCartPrice);
                        });
                        showNotification(response.message);
                    } else {
                        alert(response.message);
                    }
                }).fail(function() {
                    alert("Failed to remove item. Try again.");
                });
            });

            // ✅ Clear cart confirmation & AJAX
            $("#clear-cart").click(function() {
                if (confirm("Are you sure you want to clear your cart?")) {
                    $.post("/cart/clear", function(response) {
                        location.reload();
                    });
                }
            });

            // ✅ AJAX Function to Update Quantity
            function updateQuantity(bookId, change) {
                $.post("/cart/update", { bookId: bookId, change: change }, function(response) {
                    if (response.success) {
                        // ✅ Update quantity in the cart page
                        $("#quantity-" + bookId).text(response.newQuantity);

                        // ✅ Update total price for the item
                        $("#total-" + bookId).text("₹" + response.newTotal.toFixed(2));

                        // ✅ Update total cart price
                        updateCartTotal(response.totalCartPrice.toFixed(2));

                        // ✅ Disable decrease button if quantity is 1
                        let decreaseBtn = $(".decrease-qty[data-id='" + bookId + "']");
                        if (response.newQuantity <= 1) {
                            decreaseBtn.prop("disabled", true);
                        } else {
                            decreaseBtn.prop("disabled", false);
                        }

                        showNotification("Cart updated successfully!");
                    } else {
                        alert(response.message);
                    }
                }).fail(function(xhr) {
                    alert("Error updating cart: " + xhr.responseText);
                });
            }

            // ✅ Function to Update Total Cart Price
            function updateCartTotal(total) {
                $("#total-cart-price").text("₹" + total);
            }

            // ✅ Function to Show Cart Update Notification
            function showNotification(message) {
                $("#cart-message").text(message).fadeIn().delay(2000).fadeOut();
            }
        });
    </script>

</body>
</html>
