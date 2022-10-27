package com.josemeurer.DeliveryKing.repositories;

import com.josemeurer.DeliveryKing.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
