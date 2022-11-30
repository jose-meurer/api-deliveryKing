package com.josemeurer.DeliveryKing.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserInsertDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private Set<AddressUserDTO> addresses = new HashSet<>();

    public UserInsertDTO() {
    }

    public UserInsertDTO(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AddressUserDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressUserDTO> addresses) {
        this.addresses = addresses;
    }
}

