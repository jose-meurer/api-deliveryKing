//package com.josemeurer.DeliveryKing.services;
//
//import com.josemeurer.DeliveryKing.dtos.AddressDTO;
//import com.josemeurer.DeliveryKing.entities.Address;
//import com.josemeurer.DeliveryKing.repositories.AddressRepository;
//import com.josemeurer.DeliveryKing.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class AddressService {
//
//    @Autowired
//    private AddressRepository addressRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Transactional
//    public AddressDTO insert(AddressDTO dto) {
//        Address entity = new Address();
//        dtoToEntity(dto, entity);
//        entity = addressRepository.save(entity);
//        return new AddressDTO(entity);
//    }
//
//    private void dtoToEntity(AddressDTO dto, Address entity) {
//        entity.setName(dto.getName());
//        entity.setAddress(dto.getAddress());
//        entity.setNumber(dto.getNumber());
//    }
//}
