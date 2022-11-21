package com.josemeurer.DeliveryKing.dtos;

import com.josemeurer.DeliveryKing.entities.Phone;

import java.io.Serial;
import java.io.Serializable;

public class PhoneDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String phone;

    public PhoneDTO() {
    }

    public PhoneDTO(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public PhoneDTO(Phone entity) {
        this(entity.getId(), entity.getName(), entity.getPhone());
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
