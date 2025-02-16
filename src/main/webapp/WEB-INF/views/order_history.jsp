<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

    <%@ include file="navbar.jsp"%>

    <div class="container mt-4">
        <h2 class="text-center">📜 Order History</h2>

        <c:choose>
            <c:when test="${empty orders}">
                <p class="text-center">You have no orders yet.</p>
            </c:when>
            <c:otherwise>
                <table class="table table-bordered">
                    <thead class="table-dark">
                        <tr>
                            <th>Order ID</th>
                            <th>Date</th>
                            <th>Total Price</th>
                            <th>Items</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td>#${order.id}</td>
                                <td>${order.orderDate}</td>
                                <td>₹${order.totalPrice}</td>
                                <td>
                                    <ul>
                                        <c:forEach var="item" items="${order.orderItems}">
                                            <li>${item.book.title} - ${item.quantity} pcs</li>
                                        </c:forEach>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

</body>
</html>
