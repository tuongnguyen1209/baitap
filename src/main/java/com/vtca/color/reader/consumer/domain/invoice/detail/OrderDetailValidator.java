package com.vtca.color.reader.consumer.domain.invoice.detail;

import com.vtca.color.reader.common.exception.BusinessException;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class OrderDetailValidator {

    public void validate (OrderDetail order) {

        if (Objects.isNull(order)) {
            throw new BusinessException(OrderDetailError.ORDER);
        }

        if (Objects.isNull(order.getOrderId()) || order.getOrderId() <= 0) {
            throw new BusinessException(OrderDetailError.USER_ID_INVALID);
        }

        if (Objects.isNull(order.getColorId()) || order.getColorId() <= 0) {
            throw new BusinessException(OrderDetailError.USER_ID_INVALID);
        }

        if (order.getQuantity() <= 0) {
            throw new BusinessException(OrderDetailError.TOTAL_QUANTITY_INVALID);
        }

        if (order.getPrice() <= 0) {
            throw new BusinessException(OrderDetailError.TOTAL_AMOUNT_INVALID);
        }
    }
}
