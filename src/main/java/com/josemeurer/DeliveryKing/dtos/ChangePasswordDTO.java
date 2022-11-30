package com.josemeurer.DeliveryKing.dtos;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

public class ChangePasswordDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword; //regex

    public ChangePasswordDTO() {
    }

    public ChangePasswordDTO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
