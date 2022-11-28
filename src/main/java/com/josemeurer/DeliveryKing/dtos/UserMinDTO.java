package com.josemeurer.DeliveryKing.dtos;

import com.josemeurer.DeliveryKing.entities.User;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public class UserMinDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;

    private Instant createdAt;
    private Instant updatedAt;

    public UserMinDTO() {
    }

    public UserMinDTO(Long id, String name, String email, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserMinDTO(User entity) {
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
