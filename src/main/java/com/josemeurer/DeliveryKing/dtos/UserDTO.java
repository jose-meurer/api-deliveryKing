package com.josemeurer.DeliveryKing.dtos;

import com.josemeurer.DeliveryKing.entities.User;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;

    private Instant creation;

    private Instant updated;

    private Set<PhoneDTO> phones = new HashSet<>();
    private Set<AddressDTO> addresses = new HashSet<>();

    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, Instant creation, Instant updated) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.creation = creation;
        this.updated = updated;
    }

    public UserDTO(User entity) {
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getCreation(), entity.getUpdated());
        entity.getPhones().forEach(x -> phones.add(new PhoneDTO(x)));
        entity.getAddresses().forEach(x -> addresses.add(new AddressDTO(x)));
        entity.getRoles().forEach(x -> roles.add(new RoleDTO(x)));
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

    public Instant getCreation() {
        return creation;
    }

    public void setCreation(Instant creation) {
        this.creation = creation;
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    public Set<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(Set<PhoneDTO> phones) {
        this.phones = phones;
    }

    public Set<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }
}
