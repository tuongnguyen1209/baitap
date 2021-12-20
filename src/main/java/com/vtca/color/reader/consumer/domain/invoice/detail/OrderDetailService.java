package com.vtca.color.reader.consumer.domain.invoice.detail;

import com.vtca.color.reader.common.exception.BusinessException;
import com.vtca.color.reader.common.logger.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository repo;

    /**
     * Get all order detail by order id
     * @return
     */
    public List<OrderDetail> getOrderDetail(Long orderId) {
        LoggerUtils.info("Execute getOrderDetail method");
        if (Objects.isNull(orderId) || orderId <= 0) {
            throw new BusinessException(OrderDetailError.ORDER_ID_INVALID);
        }
        return repo.findBy(orderId);
    }

    /**
     * Get all order in DB
     * @return
     */
    public List<OrderDetail> listAll() {
        LoggerUtils.info("Execute listAll method");
        return repo.findAll();
    }

    /**
     * Insert new order into DB
     * @param order
     */
    public void save(OrderDetail order) {
        LoggerUtils.info("Execute save method", order.toString());
        if (Objects.isNull(order)) {
            throw new BusinessException(OrderDetailError.ORDER);
        }

        OrderDetailValidator.validate(order);

        repo.save(order);
    }

    /**
     * Update status for existing oder
     * @param order
     * @param id
     */
    public void save(OrderDetail order, Long id) {
        LoggerUtils.info("Execute save method", order.toString(), id);
        if (Objects.isNull(id) || id <= 0L) {
            throw new BusinessException(OrderDetailError.ORDER_ID_INVALID);
        }

        //get update order from DB
        OrderDetail existOrder = this.get(id);

        //check whether updating order is existed or not
        if (Objects.isNull(existOrder)) {
            throw new BusinessException(OrderDetailError.ORDER_NOT_EXIST);
        }

        this.save(existOrder);
    }

    /**
     * Get order info by id
     * @param id
     * @return
     */
    public OrderDetail get(Long id) {
        LoggerUtils.info("Execute get order method", id);
        if (Objects.isNull(id) || id <= 0L) {
            throw new BusinessException(OrderDetailError.ORDER_ID_INVALID);
        }
        return repo.findById(id).get();
    }
}
