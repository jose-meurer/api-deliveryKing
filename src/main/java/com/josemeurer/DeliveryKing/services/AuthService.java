package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.entities.User;
import com.josemeurer.DeliveryKing.repositories.UserRepository;
import com.josemeurer.DeliveryKing.services.exceptions.UnauthorizedException;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);

    @Transactional(readOnly = true)
    public User authenticated() { //Retornando user authorization basic
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            logger.info(username);
            return userRepository.findByEmail(username);
        }
        catch (Exception e) {
            throw new UnauthorizedException("Invalid user");
        }
    }
}
