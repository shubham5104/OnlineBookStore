package com.bookstore.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookstore.entity.Users;
import com.bookstore.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    
    @GetMapping("/profile")
    public String getProfile(@RequestParam("id") int id, HttpSession session, Model model) {
        Users loggedUser = (Users) session.getAttribute("loggedUser");

        // Prevent unauthorized access
        if (loggedUser == null || loggedUser.getId() != id) {
            return "redirect:/user/login";  
        }

        model.addAttribute("user", loggedUser);
        return "profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam int id,
                                @RequestParam String name,
                                @RequestParam String phone,
                                @RequestParam String address,
                                Model model) {

        Users user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(name);
            user.setPhone(phone);
            user.setAddress(address);
            userRepository.save(user);
            model.addAttribute("user", user);
            model.addAttribute("success", "Profile updated successfully!");
        } else {
            model.addAttribute("error", "User not found!");
        }
        return "profile";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam int id,
                                 @RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 Model model) {

        Users user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (!user.getPassword().equals(currentPassword)) {
                model.addAttribute("error", "Current password is incorrect!");
                return "profile";
            }

            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("error", "New password and confirm password do not match!");
                return "profile";
            }

            user.setPassword(newPassword);
            userRepository.save(user);
            model.addAttribute("user", user);
            model.addAttribute("success", "Password changed successfully!");
        } else {
            model.addAttribute("error", "User not found!");
        }
        return "profile";
    }
    
    // ✅ Show Login Page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // This will show login.jsp
    }

    // ✅ Handle User Login
    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {

        Optional<Users> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();

            // ✅ Check if the user is blocked
            if (user.isBlocked()) {
                model.addAttribute("error", "Your account is blocked. Please contact support.");
                return "login";  // Stay on login page with error
            }

            // ✅ Check if the password matches
            if (user.getPassword().equals(password)) {
                session.setAttribute("loggedUser", user);  // Store user in session
                return "redirect:/index";  // Redirect to home page
            }
        }

        model.addAttribute("error", "Invalid email or password!");
        return "login";  // Stay on login page with error
    }


    // ✅ Handle Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Clear the session
        return "redirect:/";  // Redirect to login page
    }

    // ✅ View All Users
    @GetMapping("/allusers")
    public String viewAllUsers(Model model) {
        List<Users> userList = userRepository.findAll();
        model.addAttribute("users", userList);
        return "manage_users";  // Ensure this matches the JSP file name
    }

    // ✅ Delete User
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "redirect:/user/allusers";  // Redirect to user list
    }

    // ✅ Show Update User Form
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable int id, Model model) {
        Optional<Users> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            return "edit_user";  // Ensure you have edit_user.jsp
        }
        return "redirect:/user/allusers";  // If user not found, go back to list
    }

    // ✅ Handle User Update
    @PostMapping("/update")
    public String updateUser(@RequestParam int id,
                             @RequestParam String name,
                             @RequestParam String username,
                             @RequestParam String email,
                             @RequestParam String phone,
                             @RequestParam String address) {

        Optional<Users> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setName(name);
            user.setUsername(username);
            user.setEmail(email);
            user.setPhone(phone);
            user.setAddress(address);
            userRepository.save(user);
        }

        return "redirect:/user/allusers";  // Redirect to user list
    }
    
    @GetMapping("/block")
    public String blockUser(@RequestParam("id") int id) {
        Users user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setBlocked(true);
            userRepository.save(user);
        }
        return "redirect:/user/allusers";
    }

    @GetMapping("/unblock")
    public String unblockUser(@RequestParam("id") int id) {
        Users user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setBlocked(false);
            userRepository.save(user);
        }
        return "redirect:/user/allusers";
    }
}
