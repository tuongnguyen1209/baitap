package com.vtca.color.reader.consumer.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where p.price>=?1")
    Product findByPrice(float price);
}
