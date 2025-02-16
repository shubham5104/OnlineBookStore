$(document).ready(function () {
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
                alert(response.message);
                $("#cart-count").text(response.cartSize);
                $("#total-price").text("₹" + response.totalPrice);
            },
            error: function () {
                alert("Failed to add book to cart.");
            }
        });
    });

    // Other handlers for remove, update, clear cart actions
});
