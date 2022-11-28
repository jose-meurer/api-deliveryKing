package com.josemeurer.DeliveryKing.controllers;

import com.josemeurer.DeliveryKing.dtos.UserMaxDTO;
import com.josemeurer.DeliveryKing.dtos.UserMinDTO;
import com.josemeurer.DeliveryKing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    //User
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/myprofile")
    public ResponseEntity<UserMaxDTO> myProfile() {
        UserMaxDTO dto = userService.findMyProfile();
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping(path = "/myprofile")
    public ResponseEntity<Void> deleteMyProfile() {
        userService.deleteMyProfile();
        return ResponseEntity.noContent().build();
    }

    //Admin

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserMinDTO>> findAllPaged(Pageable pageable) {
        Page<UserMinDTO> page = userService.findAllPaged(pageable);
        return ResponseEntity.ok(page);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserMaxDTO> findById(@PathVariable Long id) {
        UserMaxDTO dto = userService.findById(id);
        return ResponseEntity.ok(dto);
    }
}
