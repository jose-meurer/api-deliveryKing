package com.josemeurer.DeliveryKing.repositories;

import com.josemeurer.DeliveryKing.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user JOIN FETCH user.roles role " +
            "WHERE UPPER(user.email) = UPPER(:email) ")
    Optional<User> findByEmail(String email);
//
    @Query("SELECT user FROM User user JOIN FETCH user.addresses address " +
            "WHERE UPPER(user.email) = UPPER(:email) ")
    User findMyProfile(String email);

    @Query("SELECT user FROM User user JOIN FETCH user.addresses " +
            "JOIN FETCH user.roles " +
            "WHERE user.id = :id")
    Optional<User> findFullUser(Long id);
}
