package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

@Controller
public class MainController {

	 @Autowired
	    private BookRepository bookRepository;
	
	@GetMapping("/index")  // ✅ Ensure home page mapping is accessible
	public String home(Model model) {
		List<Book> books = bookRepository.findAll(); // Fetch all books
        model.addAttribute("books", books);
	    return "index";
	}
	@GetMapping("/")  // ✅ Ensure home page mapping is accessible
	public String firstpage(Model model) {
		List<Book> books = bookRepository.findAll(); // Fetch all books
        model.addAttribute("books", books);
	    return "FirstHomePage";
	}

    @GetMapping("/Admin_Login_Page")
    public String login() {
        return "Adminlogin";  // Ensure "Adminlogin.jsp" exists
    }

    @GetMapping("/Registration_Page")
    public String register() {
        return "register";  // Ensure "register.jsp" exists
    }
}
 