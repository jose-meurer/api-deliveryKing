package com.josemeurer.DeliveryKing.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;

public class OAuthCustomError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String error;

    @JsonProperty("error_description") //Padrao snake_case
    private String errorDescription;

    public OAuthCustomError() {
    }

    public OAuthCustomError(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}