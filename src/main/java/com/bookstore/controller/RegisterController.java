package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;
import com.bookstore.entity.Users;
import com.bookstore.repository.UserRepository; // ✅ Correct spelling

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository; // ✅ Use instance, not static reference

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String name,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phone,
            @RequestParam String address,
            Model model) {

        // Check if username or email already exists
        Optional<Users> existingUserByUsername = userRepository.findByUsername(username);
        Optional<Users> existingUserByEmail = userRepository.findByEmail(email);

        if (existingUserByUsername.isPresent() || existingUserByEmail.isPresent()) {
            model.addAttribute("error", "Username or Email already exists!");
            return "register";
        }

        // Create new user
        Users user = new Users();
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAddress(address);

        userRepository.save(user);

        model.addAttribute("success", "Registration successful! You can now <a href='/user/login'>login</a>.");
        return "register";
    }
}
