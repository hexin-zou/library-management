package com.example.springboot.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    private String code;
    private String getcode() {
        return code;
    }
    public ServiceException(String message) {
        super(message);

    }
    public ServiceException(String code,String message) {
        super(message);
        this.code = code;
    }

}
