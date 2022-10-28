package com.josemeurer.DeliveryKing.controllers;

import com.josemeurer.DeliveryKing.dtos.ProductDTO;
import com.josemeurer.DeliveryKing.dtos.ProductMinDTO;
import com.josemeurer.DeliveryKing.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductMinDTO>> findAll(Pageable pageable) {
        Page<ProductMinDTO> page = productService.findAllPaged(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = productService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
        dto = productService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
