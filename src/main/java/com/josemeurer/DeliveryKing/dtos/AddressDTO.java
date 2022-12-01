package com.josemeurer.DeliveryKing.dtos;

import com.josemeurer.DeliveryKing.entities.Address;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

public class AddressDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank
    @Size(min = 3, max = 100, message = "Must be between 3 and 100 characters")
    private String name;

    @NotBlank
    @Size(min = 4, max = 100, message = "Invalid address")
    private String address;

    @NotBlank
    @Pattern(regexp = "^[0-9]{1,10}", message = "Invalid house number")
    private String number;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String name, String address, String number) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public AddressDTO(Address entity) {
        this(entity.getId(), entity.getName(), entity.getAddress(), entity.getNumber());
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
