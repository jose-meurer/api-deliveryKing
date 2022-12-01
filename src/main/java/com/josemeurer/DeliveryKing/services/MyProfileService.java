package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.dtos.AddressDTO;
import com.josemeurer.DeliveryKing.dtos.ChangePasswordDTO;
import com.josemeurer.DeliveryKing.dtos.UserDTO;
import com.josemeurer.DeliveryKing.dtos.UserUpdateDTO;
import com.josemeurer.DeliveryKing.entities.Address;
import com.josemeurer.DeliveryKing.entities.User;
import com.josemeurer.DeliveryKing.repositories.UserRepository;
import com.josemeurer.DeliveryKing.services.exceptions.DatabaseException;
import com.josemeurer.DeliveryKing.services.exceptions.IncorrectPasswordException;
import com.josemeurer.DeliveryKing.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyProfileService {

    @Autowired
    private static final Logger LOG = LoggerFactory.getLogger(MyProfileService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserDTO findMyProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User entity = userRepository.findMyProfile(email);
        return new UserDTO(entity, entity.getAddresses(), entity.getRoles());
    }

    public void deleteMyProfile() {
        try {
            User entity  = myAccount();
            userRepository.deleteById(entity.getId());
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User not found");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    @Transactional
    public UserDTO updateMyProfile(UserUpdateDTO dto) {
        User entity  = myAccount();
        dtoToEntity(dto, entity);
        entity = userRepository.save(entity);
        return new UserDTO(entity, entity.getAddresses(), entity.getRoles());
    }

    @Transactional
    public void changePasswordMyProfile(ChangePasswordDTO dto) {
        User entity = myAccount();
        if (passwordEncoder.matches(dto.getOldPassword(), entity.getPassword())) {
            entity.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            userRepository.save(entity);
        }
        else {
            throw new IncorrectPasswordException("Incorrect old password");
        }
    }

    @Transactional
    public Set<AddressDTO> newAddress(AddressDTO dto) {
        User entity = myAccount();

        Address address = new Address();
        address.setName(dto.getName());
        address.setAddress(dto.getAddress());
        address.setNumber(dto.getNumber());
        entity.getAddresses().add(address);
        entity = userRepository.save(entity);

        Set<AddressDTO> setAddressDto = new HashSet<>();
        entity.getAddresses().forEach(x -> setAddressDto.add(new AddressDTO(x)));
        return setAddressDto;
    }

    private void dtoToEntity(UserUpdateDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
    }

    private User myAccount() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


    //remove address
}
