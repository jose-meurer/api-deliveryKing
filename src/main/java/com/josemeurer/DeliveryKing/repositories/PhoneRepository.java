package com.josemeurer.DeliveryKing.repositories;

import com.josemeurer.DeliveryKing.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
