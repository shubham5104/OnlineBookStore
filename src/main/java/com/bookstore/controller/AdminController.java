package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import com.bookstore.entity.Admins;
import com.bookstore.repository.AdminRepository;

@Controller
public class AdminController {
    
    @Autowired
    private AdminRepository adminRepository;
    
    // ✅ Show Admin Registration Page
    @GetMapping("/adminregister")
    public String showAdminRegistrationForm() {
        return "admin_register"; // Ensure "admin_register.jsp" exists
    }
    
    // ✅ Handle Admin Registration
    @PostMapping("/adminregister")
    public String registerAdmin(@RequestParam String name, 
                                @RequestParam String email, 
                                @RequestParam String password, 
                                Model model) {
        if (adminRepository.existsByEmail(email)) {
            model.addAttribute("error", "Email already registered!");
            return "admin_register";
        }
        
        Admins admin = new Admins(name, email, password);
        adminRepository.save(admin);
        return "redirect:/adminlogin"; // ✅ Corrected redirection
    }

    // ✅ Show Admin Login Page
    @GetMapping("/adminlogin")
    public String showAdminLoginPage() {
        return "admin_login"; // Ensure "admin_login.jsp" exists
    }

    // ✅ Handle Admin Login
    @PostMapping("/adminlogin")
    public String loginAdmin(@RequestParam String email, 
                             @RequestParam String password, 
                             HttpSession session, 
                             Model model) {
        Admins admin = adminRepository.findByEmail(email);
        
        if (admin == null || !admin.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid email or password!");
            return "admin_login";
        }

        // ✅ Store admin session
        session.setAttribute("admin", admin);
        return "redirect:/admin/dashboard"; // ✅ Corrected redirection
    }

    // ✅ Handle Admin Logout
    @GetMapping("/adminlogout")
    public String logoutAdmin(HttpSession session) {
        session.invalidate(); // Destroy session
        return "redirect:/adminlogin"; // ✅ Corrected redirection
    }
}
