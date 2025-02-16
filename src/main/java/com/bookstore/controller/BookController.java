package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/manage/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    
    // Show Book Management Page
    @GetMapping("/Allbooks")
    public String showManageBooksPage(Model model) {
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            model.addAttribute("message", "No books available at the moment.");
        }

        model.addAttribute("books", books);
        return "books"; // Ensure books.jsp exists and displays books properly
    }
    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            model.addAttribute("book", optionalBook.get());
            return "edit_book";  // Should match edit_book.jsp filename
        }
        return "redirect:/manage/books";  // Redirect if book not found
    }


    @GetMapping("/list") 
    public String showManageBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "manage_books";  // Must match JSP file name
    }
    
    @GetMapping("/details")
    public String viewBookDetails(@RequestParam("id") int bookId, Model model) {
        Optional<Book> book = bookRepository.findById((long) bookId);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
        } else {
            model.addAttribute("book", null);
        }
        return "book_details"; // JSP file without .jsp
    }

    // Add Book
//    @PostMapping("/add")
//    @Transactional
//    public String addBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
//        bookRepository.save(book);
//        redirectAttributes.addFlashAttribute("success", "Book added successfully!");
//        return "redirect:/manage/books/list";
//    }
    
    @PostMapping("/add")
    public String addBook(@RequestParam String title,
                          @RequestParam String author,
                          @RequestParam String category,
                          @RequestParam String description,
                          @RequestParam String imageUrl,
//                          @RequestParam double original_price,
                          @RequestParam double discount,
                          @RequestParam double price, // This will come from the form, but we will calculate it
                          @RequestParam int stock) {
        
        // Calculate the price after discount
//        double calculatedPrice = original_price - (original_price * (discount / 100));

        // Create the book object and set its properties
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setDescription(description);
        book.setImageUrl(imageUrl);
//        book.setOriginalPrice(original_price);
        book.setDiscount(discount);
        book.setPrice(price);  // Store the calculated price
        book.setStock(stock);

        // Save the book to the database
        bookRepository.save(book);

        // Set success message
//        request.setAttribute("success", "Book added successfully!");

        // Redirect to the manage books page
        return "redirect:/manage/books/list"; 
    }

    // Delete Book
    @Transactional
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Book deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Book not found!");
        }
        return "redirect:/manage/books/list";

    }

    // Update Book
    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        Optional<Book> optionalBook = bookRepository.findById(book.getId());

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPrice(book.getPrice());
            existingBook.setStock(book.getStock());
            existingBook.setDiscount(book.getDiscount());
            existingBook.setDescription(book.getDescription());
            existingBook.setImageUrl(book.getImageUrl());
            existingBook.setCategory(book.getCategory());

            bookRepository.save(existingBook);
            redirectAttributes.addFlashAttribute("success", "Book updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Book not found!");
        }

        return "redirect:/manage/books/list"; // Redirect after update
    }
    
}
