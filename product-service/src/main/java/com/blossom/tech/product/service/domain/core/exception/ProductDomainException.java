package com.blossom.tech.product.service.domain.core.exception;

import com.blossom.tech.domain.exception.DomainException;

public class ProductDomainException extends DomainException {
    public ProductDomainException(String message) {
        super(message);
    }

    public ProductDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
