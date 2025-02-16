package com.bookstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ✅ Ensure this is Long, not int

    private String name;
    private String email;
    private String password;

    // ✅ Required No-Arg Constructor (JPA Needs This)
    public Admins() {}

    // ✅ Parameterized Constructor
    public Admins(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
