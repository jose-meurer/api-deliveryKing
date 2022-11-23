package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.dtos.*;
import com.josemeurer.DeliveryKing.entities.Address;
import com.josemeurer.DeliveryKing.entities.Phone;
import com.josemeurer.DeliveryKing.entities.User;
import com.josemeurer.DeliveryKing.repositories.AddressRepository;
import com.josemeurer.DeliveryKing.repositories.PhoneRepository;
import com.josemeurer.DeliveryKing.repositories.RoleRepository;
import com.josemeurer.DeliveryKing.repositories.UserRepository;
import com.josemeurer.DeliveryKing.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserDTO myProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return new UserDTO(userRepository.findMyProfile(email));
    }

    @Transactional(readOnly = true)
    public Page<UserMinDTO> findAllPaged(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return page.map(UserMinDTO::new);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO insertUser(UserInsertDTO dto) {
        User entity = new User();
        insertUserDtoToEntity(dto, entity);
        entity.getRoles().clear();
        entity.getRoles().add(roleRepository.findByAuthority("ROLE_USER")); //Add com tag de user
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

    private void insertUserDtoToEntity(UserInsertDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        entity.getPhones().clear();
        entity.getAddresses().clear();
        for (PhoneDTO phoneDTO : dto.getPhones()) {
            Phone phone = new Phone();
            phone.setName(phoneDTO.getName());
            phone.setPhone(phoneDTO.getPhone());

            phone = phoneRepository.save(phone);
            entity.getPhones().add(phone);
        }

        for (AddressDTO addressDTO : dto.getAddresses()) {
            Address address  = new Address();
            address.setName(addressDTO.getName());
            address.setAddress(addressDTO.getAddress());
            address.setNumber(addressDTO.getNumber());

            address = addressRepository.save(address);
            entity.getAddresses().add(address);
        }
    }
}
