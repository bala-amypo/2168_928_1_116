package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= REQUIRED BY VALIDATOR ================= */

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    /* ================= GETTERS / SETTERS ================= */

    public Long getId() {
        return id;
    }

    // 🔥 REQUIRED: ProjectValidator & UserServiceImpl use this
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // 🔥 REQUIRED
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 🔥 REQUIRED: ProjectValidator & UserServiceImpl use this
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
