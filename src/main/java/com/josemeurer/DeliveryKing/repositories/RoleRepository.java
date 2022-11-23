package com.josemeurer.DeliveryKing.repositories;

import com.josemeurer.DeliveryKing.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT role FROM Role role WHERE UPPER(role.authority) = UPPER(:name)")
    Role findByAuthority(String name);
}
