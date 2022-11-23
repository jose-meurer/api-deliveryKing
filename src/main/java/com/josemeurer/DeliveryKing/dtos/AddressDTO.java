package com.josemeurer.DeliveryKing.dtos;

import com.josemeurer.DeliveryKing.entities.Address;

import java.io.Serial;
import java.io.Serializable;

public class AddressDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String address;
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
