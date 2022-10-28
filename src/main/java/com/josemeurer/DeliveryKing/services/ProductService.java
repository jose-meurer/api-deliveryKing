package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.dtos.ProductDTO;
import com.josemeurer.DeliveryKing.dtos.ProductMinDTO;
import com.josemeurer.DeliveryKing.entities.Product;
import com.josemeurer.DeliveryKing.repositories.CategoryRepository;
import com.josemeurer.DeliveryKing.repositories.ProductRepository;
import com.josemeurer.DeliveryKing.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAllPaged(Pageable pageable) {
        Page<Product> list = productRepository.findAll(pageable);
        return list.map(ProductMinDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = productRepository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = productRepository.save(entity);
        return new ProductDTO(entity, entity.getCategories());
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());

        entity.getCategories().clear();
        dto.getCategories().forEach(x -> entity.getCategories().add(categoryRepository.getReferenceById(x.getId())));
    }
}
