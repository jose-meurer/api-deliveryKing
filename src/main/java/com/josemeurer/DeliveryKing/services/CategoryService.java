package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
}
