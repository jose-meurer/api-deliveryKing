package com.josemeurer.DeliveryKing.dtos;

import com.josemeurer.DeliveryKing.services.validation.UserInsertValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@UserInsertValid
public class UserInsertDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Size(min = 4, max = 100, message = "Must be between 4 and 100 characters")
    @NotBlank(message = "Required field")
    private String name; //regex

    @NotBlank
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Email invalid")
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\([0-9]{2}\\)[0-9]{5}-[0-9]{4}$", message = "Cellphone invalid")
    private String phone;

    @NotBlank
    private String password; //regex
    private Set<AddressDTO> addresses = new HashSet<>(); //regex

    public UserInsertDTO() {
    }

    public UserInsertDTO(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public Set<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressDTO> addresses) {
        this.addresses = addresses;
    }
}

