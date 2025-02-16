package com.bookstore.repository;

import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;
import com.bookstore.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // ✅ Find all cart items for a specific user
    List<Cart> findByUser(Users user);

    // ✅ Find a specific cart item for a user and book
    Optional<Cart> findByUserAndBook(Users user, Book book);

    // ✅ Delete all cart items for a specific user
    @Transactional
    void deleteByUser(Users user);
    
    // ✅ Update quantity for a specific user and book
    @Transactional
    void deleteByUserAndBook(Users user, Book book);  // Optional: If you need to remove a specific book from the cart
    
    // ✅ Check if a specific cart item exists for the user and book
    boolean existsByUserAndBook(Users user, Book book);
}
