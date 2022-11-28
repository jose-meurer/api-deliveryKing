package com.josemeurer.DeliveryKing.dtos;

import com.josemeurer.DeliveryKing.entities.Address;
import com.josemeurer.DeliveryKing.entities.Phone;
import com.josemeurer.DeliveryKing.entities.Role;
import com.josemeurer.DeliveryKing.entities.User;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class UserMaxDTO extends UserMinDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Set<PhoneDTO> phones = new HashSet<>();
    private Set<AddressDTO> addresses = new HashSet<>();
    private Set<RoleDTO> roles = new HashSet<>();

    public UserMaxDTO() {
    }

    public UserMaxDTO(Long id, String name, String email, Instant createdAt, Instant updatedAt) {
        super(id, name, email, createdAt, updatedAt);
    }

    public UserMaxDTO(User entity, Set<Phone> phones, Set<Address> addresses, Set<Role> roles) {
        super(entity);
        phones.forEach(x -> this.phones.add(new PhoneDTO(x)));
        addresses.forEach(x -> this.addresses.add(new AddressDTO(x)));
        roles.forEach(x -> this.roles.add(new RoleDTO(x)));
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
