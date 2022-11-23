package com.josemeurer.DeliveryKing.dtos;

import com.josemeurer.DeliveryKing.services.validation.UserInsertValid;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@UserInsertValid
public class UserInsertDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String password;

    private Set<PhoneDTO> phones = new HashSet<>();
    private Set<AddressDTO> addresses = new HashSet<>();

    public UserInsertDTO() {
    }

    public UserInsertDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
