package com.vtca.color.reader.consumer.domain.invoice.order;

import com.vtca.color.reader.auth.user.User;
import com.vtca.color.reader.auth.user.UserDetailsCustomService;
import com.vtca.color.reader.common.exception.BusinessException;
import com.vtca.color.reader.common.logger.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;


    /**
     * Get all orders of current login user
     * @return
     */
    public List<Order> getListOrderByLoginUser(String token) {
        LoggerUtils.info("Execute getListOrderByLoginUser method");
        User user = userDetailsCustomService.getUserProfile(token);
        return repo.findBy(user.getId());
    }


    /**
     * Get all order in DB
     * @return
     */
    public List<Order> listAll() {
        LoggerUtils.info("Execute listAll method");
        return repo.findAll();
    }

    /**
     * Insert new order into DB
     * @param order
     */
    public Order save(Order order) {
        LoggerUtils.info("Execute save method", order.toString());
        if (Objects.isNull(order)) {
            throw new BusinessException(OrderError.ORDER);
        }

        OrderValidator.validate(order);
        Order ordered = repo.save(order);

        return ordered;
    }

    /**
     * Update status for existing oder
     * @param order
     * @param id
     */
    public void save(Order order, Long id) {
        LoggerUtils.info("Execute save method", order.toString(), id);
        if (Objects.isNull(id) || id <= 0L) {
            throw new BusinessException(OrderError.ORDER_ID);
        }

        //get update order from DB
        Order existOrder = this.get(id);

        //check whether updating order is existed or not
        if (Objects.isNull(existOrder)) {
            throw new BusinessException(OrderError.ORDER_NOT_EXIST);
        }

        existOrder.setStatus(order.getStatus());

        this.save(existOrder);
    }

    /**
     * Get order info by id
     * @param id
     * @return
     */
    public Order get(Long id) {
        LoggerUtils.info("Execute get order method", id);
        if (Objects.isNull(id) || id <= 0L) {
            throw new BusinessException(OrderError.ORDER_ID);
        }
        return repo.findById(id).get();
    }
}
