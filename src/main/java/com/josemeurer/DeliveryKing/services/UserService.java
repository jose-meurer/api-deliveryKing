package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.dtos.UserDTO;
import com.josemeurer.DeliveryKing.dtos.UserMinDTO;
import com.josemeurer.DeliveryKing.entities.User;
import com.josemeurer.DeliveryKing.repositories.RoleRepository;
import com.josemeurer.DeliveryKing.repositories.UserRepository;
import com.josemeurer.DeliveryKing.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
}
