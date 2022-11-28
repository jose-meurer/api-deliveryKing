package com.josemeurer.DeliveryKing.controllers;

import com.josemeurer.DeliveryKing.dtos.UserMaxDTO;
import com.josemeurer.DeliveryKing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
