package com.josemeurer.DeliveryKing.controllers;

import com.josemeurer.DeliveryKing.dtos.UserDTO;
import com.josemeurer.DeliveryKing.services.MyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/myprofile")
public class MyProfileController {

    @Autowired
    private MyProfileService myProfileService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<UserDTO> myProfile() {
        UserDTO dto = myProfileService.findMyProfile();
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> deleteMyProfile() {
        myProfileService.deleteMyProfile();
        return ResponseEntity.noContent().build();
    }

//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    @PutMapping
//    public ResponseEntity<UserDTO> updateMyProfile(@Valid @RequestBody UserUpdateDTO updateDto) {
//        UserDTO dto = myProfileService.update(updateDto);
//        return ResponseEntity.ok(dto);
//    }
}
