package com.josemeurer.DeliveryKing.repositories;

import com.josemeurer.DeliveryKing.entities.Deliveryman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverymanRepository extends JpaRepository<Deliveryman, Long> {
}
