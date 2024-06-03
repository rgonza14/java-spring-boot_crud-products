package com.rgonza14.crud_products.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorResponse {
    private Date time = new Date();
    private String message;
    private int status_code;

    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.status_code = statusCode;
    }
}
