package com.bookstore.controller;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookstore.entity.*;
import com.bookstore.repository.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Show Cart
    @GetMapping
    public String showCart(Model model, HttpSession session) {
        Users loggedUser = (Users) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            return "redirect:/user/login";
        }

        List<Cart> cartItems = cartRepository.findByUser(loggedUser);
        if (cartItems.isEmpty()) {
            model.addAttribute("cartDetails", Collections.emptyMap());
            model.addAttribute("totalPrice", 0.0);
            return "cart";
        }

        Map<Book, Integer> cartDetails = new HashMap<>();
        AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);

        for (Cart cart : cartItems) {
            cartDetails.put(cart.getBook(), cart.getQuantity());
            totalPrice.updateAndGet(v -> v + (cart.getBook().getPrice() * cart.getQuantity()));
        }

        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice.get());
        return "cart";
    }

    // ✅ Add Book to Cart
//    @PostMapping("/add")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> addToCart(@RequestParam Long bookId, @RequestParam int quantity, HttpSession session) {
//        Map<String, Object> response = new HashMap<>();
//        Users loggedUser = (Users) session.getAttribute("loggedUser");
//
//        if (loggedUser == null) {
//            response.put("message", "User not logged in!");
//            return ResponseEntity.ok(response);
//        }
//
//        Book book = bookRepository.findById(bookId).orElse(null);
//        if (book == null || book.getStock() < quantity) {
//            response.put("message", "Book not available!");
//            return ResponseEntity.ok(response);
//        }
//
//        Cart cart = cartRepository.findByUserAndBook(loggedUser, book).orElse(null);
//        if (cart == null) {
//            cart = new Cart();
//            cart.setUser(loggedUser);
//            cart.setBook(book);
//            cart.setQuantity(quantity);
//            cartRepository.save(cart);
//            response.put("message", "Book added to cart!");
//        } else {
//            int newQuantity = cart.getQuantity() + quantity;
//            if (newQuantity > book.getStock()) {
//                response.put("message", "Not enough stock available!");
//            } else {
//                cart.setQuantity(newQuantity);
//                cartRepository.save(cart);
//                response.put("message", "Quantity updated in cart!");
//            }
//        }
//
//        response.put("cartSize", cartRepository.findByUser(loggedUser).size());
//        return ResponseEntity.ok(response);
//    }
    
    
//    NEW------------------ADD------------
    
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addToCart(@RequestParam Long bookId, @RequestParam int quantity, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        Users loggedUser = (Users) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            response.put("message", "User not logged in!");
            return ResponseEntity.ok(response);
        }

        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null || book.getStock() < quantity) {
            response.put("message", "Book not available!");
            return ResponseEntity.ok(response);
        }

        Cart cart = cartRepository.findByUserAndBook(loggedUser, book).orElse(null);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(loggedUser);
            cart.setBook(book);
            cart.setQuantity(quantity);
            cartRepository.save(cart);
            response.put("message", "Book added to cart!");
        } else {
            int newQuantity = cart.getQuantity() + quantity;
            if (newQuantity > book.getStock()) {
                response.put("message", "Not enough stock available!");
            } else {
                cart.setQuantity(newQuantity);
                cartRepository.save(cart);
                response.put("message", "Quantity updated in cart!");
            }
        }

        // Return updated cart details
        double totalPrice = cartRepository.findByUser(loggedUser).stream()
                .mapToDouble(cartItem -> cartItem.getBook().getPrice() * cartItem.getQuantity())
                .sum();
        
        response.put("cartSize", cartRepository.findByUser(loggedUser).size());  // Updated cart size
        response.put("totalPrice", totalPrice);  // Updated total price

        return ResponseEntity.ok(response);
    }

    
//---------------------------------------------------
    
    // ✅ Remove Book from Cart
//    @PostMapping("/remove")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> removeFromCart(@RequestParam Long bookId, HttpSession session) {
//        Map<String, Object> response = new HashMap<>();
//        Users loggedUser = (Users) session.getAttribute("loggedUser");
//
//        if (loggedUser == null) {
//            response.put("message", "User not logged in!");
//            return ResponseEntity.ok(response);
//        }
//
//        Cart cart = cartRepository.findByUserAndBook(loggedUser, bookRepository.findById(bookId).orElse(null)).orElse(null);
//        if (cart != null) {
//            cartRepository.delete(cart);
//            response.put("message", "Book removed from cart!");
//        } else {
//            response.put("message", "Book not found in cart!");
//        }
//
//        response.put("cartSize", cartRepository.findByUser(loggedUser).size());
//        return ResponseEntity.ok(response);
//    }
    
    
//    NEW--------------------REMOVE__________________
    
    @PostMapping("/remove")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> removeFromCart(@RequestParam Long bookId, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        Users loggedUser = (Users) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            response.put("message", "User not logged in!");
            return ResponseEntity.ok(response);
        }

        Book book = bookRepository.findById(bookId).orElse(null);
        Cart cart = cartRepository.findByUserAndBook(loggedUser, book).orElse(null);
        if (cart != null) {
            cartRepository.delete(cart);
            response.put("message", "Book removed from cart!");
        } else {
            response.put("message", "Book not found in cart!");
        }

        // Return updated cart details
        double totalPrice = cartRepository.findByUser(loggedUser).stream()
                .mapToDouble(cartItem -> cartItem.getBook().getPrice() * cartItem.getQuantity())
                .sum();

        response.put("cartSize", cartRepository.findByUser(loggedUser).size());  // Updated cart size
        response.put("totalPrice", totalPrice);  // Updated total price

        return ResponseEntity.ok(response);
    }

//________________________________________________________________________
  
    
    // ✅ Clear Cart (Only for Logged-in User)
//    @PostMapping("/clear")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> clearCart(HttpSession session) {
//        Map<String, Object> response = new HashMap<>();
//        Users loggedUser = (Users) session.getAttribute("loggedUser");
//
//        if (loggedUser == null) {
//            response.put("message", "User not logged in!");
//            return ResponseEntity.ok(response);
//        }
//
//        cartRepository.deleteByUser(loggedUser);
//        response.put("message", "Cart cleared!");
//        response.put("cartSize", 0);
//
//        return ResponseEntity.ok(response);
//    }
    
    
//    -----------NEW CLEAR----------------
    
    @PostMapping("/clear")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> clearCart(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        Users loggedUser = (Users) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            response.put("message", "User not logged in!");
            return ResponseEntity.ok(response);
        }

        cartRepository.deleteByUser(loggedUser);
        
        // Return updated cart details
        response.put("message", "Cart cleared!");
        response.put("cartSize", 0);  // Cart size after clearing
        response.put("totalPrice", 0.0);  // Total price after clearing

        return ResponseEntity.ok(response);
    }

//-------------------------------------------------
    // ✅ Increase or Decrease Quantity
   
//    @PostMapping("/update")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> updateCartQuantity(@RequestParam Long bookId, @RequestParam int change, HttpSession session) {
//        Map<String, Object> response = new HashMap<>();
//        Users loggedUser = (Users) session.getAttribute("loggedUser");
//
//        if (loggedUser == null) {
//            response.put("message", "User not logged in!");
//            return ResponseEntity.ok(response);
//        }
//
//        Cart cart = cartRepository.findByUserAndBook(loggedUser, bookRepository.findById(bookId).orElse(null)).orElse(null);
//        if (cart == null) {
//            response.put("message", "Book not found in cart!");
//            return ResponseEntity.ok(response);
//        }
//
//        int newQuantity = cart.getQuantity() + change;
//        if (newQuantity <= 0) {
//            cartRepository.delete(cart);
//        } else {
//            cart.setQuantity(newQuantity);
//            cartRepository.save(cart);
//        }
//
//        response.put("success", true);
//        return ResponseEntity.ok(response);
//    }
    
//    --------NEW --UODATE ---------------------------------------------

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateCartQuantity(@RequestParam Long bookId, @RequestParam int change, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        Users loggedUser = (Users) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            response.put("message", "User not logged in!");
            return ResponseEntity.ok(response);
        }

        Cart cart = cartRepository.findByUserAndBook(loggedUser, bookRepository.findById(bookId).orElse(null)).orElse(null);
        if (cart == null) {
            response.put("message", "Book not found in cart!");
            return ResponseEntity.ok(response);
        }

        int newQuantity = cart.getQuantity() + change;
        if (newQuantity <= 0) {
            cartRepository.delete(cart);
        } else {
            cart.setQuantity(newQuantity);
            cartRepository.save(cart);
        }

        // Return updated cart details
        double totalPrice = cartRepository.findByUser(loggedUser).stream()
                .mapToDouble(cartItem -> cartItem.getBook().getPrice() * cartItem.getQuantity())
                .sum();
        
        response.put("cartSize", cartRepository.findByUser(loggedUser).size());  // Updated cart size
        response.put("totalPrice", totalPrice);  // Updated total price
        response.put("success", true);

        return ResponseEntity.ok(response);
    }
    
//-------------------------------------------------------------
    
    // ✅ Checkout Order (Convert Cart to Order)
    @PostMapping("/checkout")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkout(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        Users loggedUser = (Users) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            response.put("message", "User not logged in!");
            return ResponseEntity.ok(response);
        }

        List<Cart> cartItems = cartRepository.findByUser(loggedUser);
        if (cartItems.isEmpty()) {
            response.put("message", "Your cart is empty!");
            return ResponseEntity.ok(response);
        }

        double totalPrice = cartItems.stream()
            .mapToDouble(cart -> cart.getBook().getPrice() * cart.getQuantity())
            .sum();

        // ✅ Create Order
        Order order = new Order();
        order.setUser(loggedUser);
        order.setTotalPrice(totalPrice);
        order.setOrderDate(LocalDateTime.now());
        order = orderRepository.save(order);

        // ✅ Convert Cart Items to Order Items
        for (Cart cart : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(cart.getBook());
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setPrice(cart.getBook().getPrice());

            orderItemRepository.save(orderItem);

            // ✅ Update Stock
            Book book = cart.getBook();
            book.setStock(book.getStock() - cart.getQuantity());
            bookRepository.save(book);
        }

        // ✅ Clear Only the User’s Cart
        cartRepository.deleteByUser(loggedUser);

        response.put("message", "Order placed successfully!");
        return ResponseEntity.ok(response);
    }
    
 // Controller example for fetching cart count
//    @GetMapping("/cart/count")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> getCartCount(HttpSession session) {
//        // Assuming Cart is stored in the session
//        Cart cart = (Cart) session.getAttribute("cart");
//        int cartSize = (cart != null) ? cart.getItemCount(null) : 0; // Get cart size from session
//        Map<String, Object> response = new HashMap<>();
//        response.put("cartSize", cartSize);
//        return ResponseEntity.ok(response);
//    }
    // Add book to cart
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long bookId, @RequestParam int quantity, HttpSession session, Model model) {
        Users currentUser = (Users) session.getAttribute("currentUser");  // Get the logged-in user from the session

        if (currentUser != null) {
            Book book = bookRepository.findById(bookId).orElse(null);
            if (book != null && book.getStock() >= quantity) {
                Cart cartItem = new Cart(currentUser, book, quantity);
                cartRepository.save(cartItem);
                
                // Calculate the cart item count for the current user
                int cartSize = cartRepository.findByUser(currentUser).stream()
                        .mapToInt(Cart::getQuantity)
                        .sum();

                model.addAttribute("cartSize", cartSize);  // Add cart size to model
                model.addAttribute("message", "Book added to cart successfully!");
            } else {
                model.addAttribute("message", "Not enough stock for this book.");
            }
        } else {
            model.addAttribute("message", "Please log in to add books to your cart.");
        }
        return "available-books";  // Return to the same page or redirect as needed
    }
    // Method to display available books and cart size
    @GetMapping("/available-books")
    public String showAvailableBooks(Model model, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("currentUser");
        int cartSize = 0;
        if (currentUser != null) {
            cartSize = cartRepository.findByUser(currentUser).stream()
                    .mapToInt(Cart::getQuantity)
                    .sum();
        }
        model.addAttribute("cartSize", cartSize);  // Display the cart item count
        return "available-books";  // Your view name
    }
   
}
