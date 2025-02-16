package com.bookstore.controller;

import com.bookstore.entity.Users;
import com.bookstore.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/history")
    public String orderHistory(Model model, HttpSession session) {
        Users loggedUser = (Users) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            return "redirect:/user/login";
        }

        model.addAttribute("orders", orderRepository.findByUser(loggedUser));
        return "order_history";
    }
    
    
}
