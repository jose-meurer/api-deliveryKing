package com.josemeurer.DeliveryKing.controllers;

import com.josemeurer.DeliveryKing.dtos.AddressDTO;
import com.josemeurer.DeliveryKing.dtos.ChangePasswordDTO;
import com.josemeurer.DeliveryKing.dtos.UserDTO;
import com.josemeurer.DeliveryKing.dtos.UserUpdateDTO;
import com.josemeurer.DeliveryKing.services.MyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

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

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping
    public ResponseEntity<UserDTO> updateMyProfile(@Valid @RequestBody UserUpdateDTO updateDto) {
        UserDTO dto = myProfileService.updateMyProfile(updateDto);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping(path = "/changepassword")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordDTO dto) {
        myProfileService.changePasswordMyProfile(dto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(path = ("/newaddress"))
    public ResponseEntity<Set<AddressDTO>> newAddress(@Valid @RequestBody AddressDTO dto) {
        Set<AddressDTO> setAddressDto = myProfileService.newAddress(dto);
        return ResponseEntity.ok(setAddressDto);
    }
}
