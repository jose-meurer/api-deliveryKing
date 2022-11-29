package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.dtos.AddressDTO;
import com.josemeurer.DeliveryKing.entities.Address;
import com.josemeurer.DeliveryKing.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public AddressDTO insert(AddressDTO dto) {
        //Adicionar regex
        //Refatorar

        Address entity = new Address();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setNumber(dto.getNumber());
        entity = addressRepository.save(entity);
        return new AddressDTO(entity);
    }
}
