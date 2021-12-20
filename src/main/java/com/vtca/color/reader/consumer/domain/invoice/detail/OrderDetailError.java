package com.vtca.color.reader.consumer.domain.invoice.detail;

import com.vtca.color.reader.common.exception.BaseError;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public enum OrderDetailError implements BaseError {
    ORDER("ORDER_INVALID", "Order is null"),
    ORDER_ID_INVALID("ORDER_ID_INVALID", "Order ID is invalid"),
    USER_ID_INVALID("USER_ID_INVALID", "User ID is invalid"),
    TOTAL_QUANTITY_INVALID("TOTAL_QUANTITY_INVALID", "Total Quantity is invalid"),
    TOTAL_AMOUNT_INVALID("TOTAL_AMOUNT_INVALID", "Total Amount is invalid"),
    ORDER_STATUS_INVALID("ORDER_STATUS_INVALID", "Order Status is invalid"),
    ORDER_NOT_EXIST("ORDER_NOT_EXIST", "Order is not existing");

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
