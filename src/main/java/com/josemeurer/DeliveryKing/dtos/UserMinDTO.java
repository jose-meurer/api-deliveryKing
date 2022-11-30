package com.josemeurer.DeliveryKing.dtos;

import com.josemeurer.DeliveryKing.entities.User;

import java.io.Serial;
import java.io.Serializable;

public class UserMinDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String phone;

    public UserMinDTO() {
    }

    public UserMinDTO(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public UserMinDTO(User entity) {
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone());
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
