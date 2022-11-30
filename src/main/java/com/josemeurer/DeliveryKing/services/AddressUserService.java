package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.dtos.AddressUserDTO;
import com.josemeurer.DeliveryKing.entities.AddressUser;
import com.josemeurer.DeliveryKing.repositories.AddressUserRepository;
import com.josemeurer.DeliveryKing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressUserService {

    @Autowired
    private AddressUserRepository addressUserRepository;

    @Autowired
    private UserRepository userRepository;

    public AddressUserDTO insert(Long userId, AddressUserDTO dto) {
        AddressUser entity = new AddressUser();
        dtoToEntity(dto, entity);
        entity.setUser(userRepository.getReferenceById(userId));
        entity = addressUserRepository.save(entity);
        return new AddressUserDTO(entity);
    }

    private void dtoToEntity(AddressUserDTO dto, AddressUser entity) {
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setNumber(dto.getNumber());
    }
}
