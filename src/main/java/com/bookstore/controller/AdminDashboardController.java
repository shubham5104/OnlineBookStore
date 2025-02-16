package com.bookstore.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookstore.repository.BookRepository;
import com.bookstore.repository.OrderItemRepository;
import com.bookstore.repository.OrderRepository;
import com.bookstore.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {
	
	 @Autowired
	    private OrderRepository orderRepository;

	    @Autowired
	    private OrderItemRepository orderItemRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private BookRepository bookRepository;

    // ✅ Show Admin Dashboard (Only if Logged In)
    @GetMapping("/dashboard")
    public String showAdminDashboard(HttpSession session, HttpServletResponse response) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/adminlogin"; // ✅ Redirect if not logged in
        }

        // 🔹 Prevent caching to avoid Back button issue
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies

        return "admin_dashboard"; // Ensure admin_dashboard.jsp exists
    }

    // ✅ Manage Books (Only if Logged In)
    @GetMapping("/manage_books")
    public String manageBooks(HttpSession session, HttpServletResponse response) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/adminlogin"; 
        }
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        return "manage_books"; // Ensure manage_books.jsp exists
    }

    // ✅ Manage Users (Only if Logged In)
    @GetMapping("/manage_users")
    public String manageUsers(HttpSession session, HttpServletResponse response) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/adminlogin"; 
        }
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        return "manage_users"; // Ensure manage_users.jsp exists
    }

    // ✅ View Reports (Only if Logged In)
//    @GetMapping
//    public String viewReports(HttpSession session, HttpServletResponse response) {
//        if (session.getAttribute("admin") == null) {
//            return "redirect:/adminlogin"; 
//        }
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expires", 0);
//
//        return "admin_reports"; // Ensure admin_reports.jsp exists
//    }
    @GetMapping("/admin_reports")
    public String showAdminReport(Model model) {
        long totalUsers = userRepository.count();
        long totalOrders = orderRepository.count();
        double totalRevenue = orderRepository.findAll().stream().mapToDouble(order -> order.getTotalPrice()).sum();
        long totalBooks = bookRepository.count();

        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalOrders", totalOrders);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("totalBooks", totalBooks);

        return "admin_reports";  // Ensure this JSP page exists
    }
    

    // ✅ Admin Logout
    @GetMapping("/logout")
    public String logoutAdmin(HttpSession session, HttpServletResponse response) {
        session.invalidate(); // ✅ Destroy session

        // 🔹 Prevent Back button from showing dashboard after logout
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        return "redirect:/"; // ✅ Redirect to login page Home Page
    }
}
