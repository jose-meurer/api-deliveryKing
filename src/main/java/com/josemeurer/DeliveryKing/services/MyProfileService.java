package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.dtos.UserDTO;
import com.josemeurer.DeliveryKing.entities.User;
import com.josemeurer.DeliveryKing.repositories.UserRepository;
import com.josemeurer.DeliveryKing.services.exceptions.DatabaseException;
import com.josemeurer.DeliveryKing.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyProfileService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDTO findMyProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User entity = userRepository.findMyProfile(email);
        return new UserDTO(entity, entity.getAddresses(), entity.getRoles());
    }

    public void deleteMyProfile() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User entity  = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            userRepository.deleteById(entity.getId());
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User not found");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}