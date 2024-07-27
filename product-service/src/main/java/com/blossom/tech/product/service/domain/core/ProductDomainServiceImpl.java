package com.blossom.tech.product.service.domain.core;

import com.blossom.tech.product.service.domain.core.cosntant.ProductDomainConstant;
import com.blossom.tech.product.service.domain.core.entity.Product;
import com.blossom.tech.product.service.domain.core.exception.ProductDomainException;

import java.util.logging.Logger;

public class ProductDomainServiceImpl implements ProductDomainService {

    private final Logger log = Logger.getLogger(ProductDomainServiceImpl.class.getName());

    @Override
    public void createProduct(Product product) {
        try {
            product.validateProduct();
            product.initializeProduct();
            log.info(String.format(ProductDomainConstant.PRODUCT_INITIALIZED, product.getId()));
        } catch (ProductDomainException exception) {
            log.severe(exception.getMessage());
            throw exception;
        }
    }

    @Override
    public void updateProduct(Product createdProduct, Product updatedProduct) {
        try {
            updatedProduct.validateProductToUpdate();
            createdProduct.updateProduct(updatedProduct);
        } catch (ProductDomainException exception) {
            log.severe(exception.getMessage());
            throw exception;
        }
    }
}
