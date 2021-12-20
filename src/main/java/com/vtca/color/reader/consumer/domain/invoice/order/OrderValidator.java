package com.vtca.color.reader.consumer.domain.invoice.order;

import com.vtca.color.reader.common.exception.BusinessException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@UtilityClass
public class OrderValidator {

    public void validate (Order order) {

        if (Objects.isNull(order)) {
            throw new BusinessException(OrderError.ORDER);
        }

        if (Objects.isNull(order.getUserId()) || order.getUserId() <= 0) {
            throw new BusinessException(OrderError.USER_ID_INVALID);
        }

        if (order.getTotalQuantity() <= 0) {
            throw new BusinessException(OrderError.TOTAL_QUANTITY_INVALID);
        }

        if (order.getTotalAmount() <= 0) {
            throw new BusinessException(OrderError.TOTAL_AMOUNT_INVALID);
        }

        if (StringUtils.isEmpty(order.getStatus())) {
            throw new BusinessException(OrderError.ORDER_STATUS_INVALID);
        }
    }
}
