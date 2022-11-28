package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.dtos.UserMaxDTO;
import com.josemeurer.DeliveryKing.dtos.UserMinDTO;
import com.josemeurer.DeliveryKing.entities.User;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}
