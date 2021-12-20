package com.vtca.color.reader.consumer.domain.invoice;

import com.vtca.color.reader.consumer.domain.invoice.detail.OrderDetail;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Invoice {
    //order
    private Long userId;
    private int totalQuantity;
    private double totalAmount;
    private String status;

    //order detail
    private List<OrderDetail> orderDetails;
}
