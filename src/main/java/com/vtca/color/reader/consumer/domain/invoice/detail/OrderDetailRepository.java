package com.vtca.color.reader.consumer.domain.invoice.detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("select p from OrderDetail p where p.orderId=?1")
    List<OrderDetail> findBy(Long orderId);
}
