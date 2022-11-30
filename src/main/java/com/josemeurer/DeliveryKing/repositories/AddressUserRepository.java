package com.josemeurer.DeliveryKing.repositories;

import com.josemeurer.DeliveryKing.entities.AddressUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressUserRepository extends JpaRepository<AddressUser, Long> {
}
