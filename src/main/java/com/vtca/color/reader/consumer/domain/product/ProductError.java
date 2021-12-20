package com.vtca.color.reader.consumer.domain.product;

import com.vtca.color.reader.common.exception.BaseError;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public enum ProductError implements BaseError {
    PRODUCT("PRODUCT_INVALID", "Product is null"),
    PRODUCT_ID("PRODUCT_ID", "Product ID is null"),
    PRODUCT_PRICE("PRODUCT_PRICE", "Product Price is null"),
    PRODUCT_NOT_EXIST("PRODUCT_NOT_EXIST", "Product is not existing");

    private String code;
    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
