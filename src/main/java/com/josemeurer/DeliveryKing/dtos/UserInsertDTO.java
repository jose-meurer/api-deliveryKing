package com.josemeurer.DeliveryKing.dtos;

import com.josemeurer.DeliveryKing.entities.Address;
import com.josemeurer.DeliveryKing.entities.Phone;
import com.josemeurer.DeliveryKing.entities.User;
import com.josemeurer.DeliveryKing.services.validation.UserInsertValid;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

@UserInsertValid
public class UserInsertDTO extends UserMinDTO {
    @Serial
    private static final long serialVersionUID = 1L;

    private String password;
    private Set<PhoneDTO> phones = new HashSet<>();
    private Set<AddressDTO> addresses = new HashSet<>();

    public UserInsertDTO() {
    }

    public UserInsertDTO(Long id, String name, String email, String password) {
        super(id, name, email);
    }

    public UserInsertDTO(User entity, Set<Phone> phones, Set<Address> addresses) {
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getPassword());
        phones.forEach(x -> this.phones.add(new PhoneDTO(x)));
        addresses.forEach(x -> this.addresses.add(new AddressDTO(x)));
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
