package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.dtos.*;
import com.josemeurer.DeliveryKing.entities.Address;
import com.josemeurer.DeliveryKing.entities.Phone;
import com.josemeurer.DeliveryKing.entities.User;
import com.josemeurer.DeliveryKing.repositories.RoleRepository;
import com.josemeurer.DeliveryKing.repositories.UserRepository;
import com.josemeurer.DeliveryKing.services.exceptions.DatabaseException;
import com.josemeurer.DeliveryKing.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static final String INITIAL_USER_ROLE = "ROLE_USER";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //User
    @Transactional(readOnly = true)
    public UserMaxDTO findMyProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User entity = userRepository.findMyProfile(email);
        return new UserMaxDTO(entity, entity.getPhones(), entity.getAddresses(), entity.getRoles());
    }

    public void deleteMyProfile() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User entity = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
            userRepository.deleteById(entity.getId());
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User not found");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    //Admin
    @Transactional(readOnly = true)
    public Page<UserMinDTO> findAllPaged(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return page.map(UserMinDTO::new);
    }

    @Transactional(readOnly = true)
    public UserMaxDTO findById(Long id) {
        User entity = userRepository.findFullUser(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return new UserMaxDTO(entity, entity.getPhones(), entity.getAddresses(), entity.getRoles());
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    @Transactional
    public UserMaxDTO insert(UserInsertDTO dto) {
        User entity = new User();
        insertDtoToEntity(dto, entity);
        entity.getRoles().clear();
        entity.getRoles().add(roleRepository.findByAuthority(INITIAL_USER_ROLE));
        entity = userRepository.save(entity);
        return new UserMaxDTO(entity, entity.getPhones(), entity.getAddresses(), entity.getRoles());
    }

    private void insertDtoToEntity(UserInsertDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        entity.getPhones().clear();
        entity.getAddresses().clear();

        //Refatorar
        for (PhoneDTO obj: dto.getPhones()) {
            PhoneDTO phoneDto = phoneService.insert(obj);
            entity.getPhones().add(new Phone(phoneDto.getId(), phoneDto.getName(), phoneDto.getPhone()));
        }

        //Refatorar
        for (AddressDTO obj: dto.getAddresses()) {
            AddressDTO addressDto = addressService.insert(obj);
            entity.getAddresses().add(new Address(addressDto.getId(), addressDto.getName(),
                    addressDto.getAddress(), addressDto.getNumber()));
        }
    }
}
