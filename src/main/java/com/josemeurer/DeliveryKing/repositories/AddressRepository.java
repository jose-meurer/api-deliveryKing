package com.josemeurer.DeliveryKing.repositories;

import com.josemeurer.DeliveryKing.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
