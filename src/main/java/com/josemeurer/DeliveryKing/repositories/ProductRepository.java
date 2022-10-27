package com.josemeurer.DeliveryKing.repositories;

import com.josemeurer.DeliveryKing.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
