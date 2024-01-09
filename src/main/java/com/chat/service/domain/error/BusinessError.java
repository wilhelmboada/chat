package com.chat.service.domain.error;

public class BusinessError extends RuntimeException {

    static final long serialVersionUID = 1L;

    public BusinessError(String message) {
        super(message);
    }

}
