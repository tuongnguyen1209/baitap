package com.vtca.color.reader.consumer.domain.invoice.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select p from Order p where p.userId=?1")
    List<Order> findBy(Long userId);
}
