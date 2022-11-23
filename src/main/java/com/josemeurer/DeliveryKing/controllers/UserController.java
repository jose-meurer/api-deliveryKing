package com.josemeurer.DeliveryKing.controllers;

import com.josemeurer.DeliveryKing.dtos.UserDTO;
import com.josemeurer.DeliveryKing.dtos.UserInsertDTO;
import com.josemeurer.DeliveryKing.dtos.UserMinDTO;
import com.josemeurer.DeliveryKing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/myprofile")
    public ResponseEntity<UserDTO> myProfile() {
        UserDTO dto = userService.myProfile();
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserMinDTO>> findAllPaged(Pageable pageable) {
        Page<UserMinDTO> page = userService.findAllPaged(pageable);
        return ResponseEntity.ok(page);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO dto = userService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insertUser(@RequestBody UserInsertDTO insertDto) {
        UserDTO dto = userService.insertUser(insertDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
