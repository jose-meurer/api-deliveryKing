package com.josemeurer.DeliveryKing.dtos;

import com.josemeurer.DeliveryKing.services.validation.UserUpdateValid;

import java.io.Serial;

@UserUpdateValid
public class UserUpdateDTO extends UserMinDTO{
    @Serial
    private static final long serialVersionUID = 1L;
}
