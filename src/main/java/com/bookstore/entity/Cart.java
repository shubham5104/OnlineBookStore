package com.bookstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user; 

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private int quantity;
    private double totalPrice;  // This should store the discounted total price

    // Constructors
    public Cart() {}

    public Cart(Users user, Book book, int quantity) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        this.totalPrice = calculateTotalPrice(); // Recalculate price when book changes
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice(); // Recalculate price when quantity changes
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private double calculateTotalPrice() {
        if (book != null) {
            return book.getDiscountedPrice() * quantity;  // Use discounted price
        }
        return 0.0;
    }
}
