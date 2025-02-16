package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookstore.entity.Users;
import com.bookstore.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/admin/manage/users")
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    // ✅ Show Manage Users Page
    @GetMapping
    public String showManageUsers(Model model) {
        List<Users> users = userRepository.findAll(); // Fetch all users
        System.out.println("Fetched Users: " + users); // Debugging log
        model.addAttribute("users", users);
        return "manage_users";  // Ensure this matches the JSP filename
    }

    // ✅ Delete User
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "redirect:/admin/manage/users";  // Redirect back to manage users page
    }

    // ✅ Toggle User Status (Block/Unblock)
    @GetMapping("/toggleStatus/{id}")
    public String toggleUserStatus(@PathVariable int id) {
        Users user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setBlocked(!user.isBlocked());  // Toggle status
            userRepository.save(user);  // Update user in DB
        }
        return "redirect:/admin/manage/users";
    }
}
