package com.vtca.color.reader.consumer.domain.invoice;

import com.vtca.color.reader.common.exception.BusinessException;
import com.vtca.color.reader.common.logger.LoggerUtils;
import com.vtca.color.reader.consumer.domain.color.Color;
import com.vtca.color.reader.consumer.domain.color.ColorService;
import com.vtca.color.reader.consumer.domain.invoice.detail.OrderDetail;
import com.vtca.color.reader.consumer.domain.invoice.detail.OrderDetailService;
import com.vtca.color.reader.consumer.domain.invoice.order.Order;
import com.vtca.color.reader.consumer.domain.invoice.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ColorService colorService;

    /**
     * Insert new invoice = order + order detail into DB
     * @param invoice
     */
    @Transactional
    public void save(Invoice invoice) {
        LoggerUtils.info("Execute save method", invoice.toString());
        if (Objects.isNull(invoice) || CollectionUtils.isEmpty(invoice.getOrderDetails())) {
            throw new BusinessException(InvoiceError.INVOICE_INVALID);
        }

        Order order = Order.builder()
                .userId(invoice.getUserId())
                .totalQuantity(invoice.getTotalQuantity())
                .totalAmount(invoice.getTotalAmount())
                .status(invoice.getStatus())
                .createdBy("SYSTEM")
                .createdDate(LocalDateTime.now())
                .lastUpdateBy("SYSTEM")
                .lastUpdateDate(LocalDateTime.now())
                .build();

        Order ordered = orderService.save(order);

        invoice.getOrderDetails().forEach(orderDetail -> {
            orderDetail.setOrderId(ordered.getId());
            orderDetail.setCreatedBy("SYSTEM");
            orderDetail.setCreatedDate(LocalDateTime.now());
            orderDetail.setLastUpdateBy("SYSTEM");
            orderDetail.setLastUpdateDate(LocalDateTime.now());

            orderDetailService.save(orderDetail);
        });
    }

    /**
     * Get all full info orders of current login user
     * @return
     */
    public Map getListFullInfoInvoice(String token) {
        LoggerUtils.info("Execute getListOrderByLoginUser method");
        List<Order> orders = orderService.getListOrderByLoginUser(token);
        if (CollectionUtils.isEmpty(orders)) {
            return Collections.emptyMap();
        }

        List<OrderDetail> rsDetails = new ArrayList<>();
        List<Color> rsColors = new ArrayList<>();

        orders.forEach(order -> {
            List<OrderDetail> details = orderDetailService.getOrderDetail(order.getId());
            rsDetails.addAll(details);
            details.forEach(dt->{
                Color color = colorService.get(dt.getColorId());
                rsColors.add(color);
            });
        });

        Map<Long, List<Order>> mOrders = orders.stream().collect(Collectors.groupingBy(Order::getId, Collectors.toList()));
        Map<Long, List<OrderDetail>> mOrderDetails = rsDetails.stream().collect(Collectors.groupingBy(OrderDetail::getOrderId, Collectors.toList()));
        Map<Long, List<Color>> mColors = rsColors.stream().collect(Collectors.groupingBy(Color::getId, Collectors.toList()));

        Map<String, Object> result = new HashMap<>();
        result.put("orders", mOrders);
        result.put("orderDetails", mOrderDetails);
        result.put("colors", mColors);

        return result;
    }

}
