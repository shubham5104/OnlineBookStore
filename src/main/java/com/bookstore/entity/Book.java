package com.bookstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String category;
    private double price; // discounted price
    private double discount; // Discount percentage (e.g., 10 for 10%)
    private int stock;
    private String description;
    private String imageUrl; // URL for book cover
    private double originalPrice;

    public Book() {}

    public Book(String title, String author, double price, double discount, int stock, String description, String imageUrl, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.description = description;
        this.imageUrl = imageUrl;
    }
    
    public double getOriginalPrice() {
        return originalPrice;
    }
    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    // ✅ Auto-calculate discounted price
    public double getDiscountedPrice() {
        return price - (price * discount / 100);
        
    }

	
}
