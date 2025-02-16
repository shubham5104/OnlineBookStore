package com.bookstore.repository;

import com.bookstore.entity.Order;
import com.bookstore.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(Users user);
    @Modifying
    @Query("DELETE FROM OrderItem oi WHERE oi.book.id = :bookId")
    void deleteByBookId(@Param("bookId") int bookId);

}
