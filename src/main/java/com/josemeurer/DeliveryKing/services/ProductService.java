package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.dtos.ProductMinDTO;
import com.josemeurer.DeliveryKing.entities.Product;
import com.josemeurer.DeliveryKing.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAllPaged(Pageable pageable) {
        Page<Product> list = productRepository.findAll(pageable);
        return list.map(ProductMinDTO::new);
    }
}
