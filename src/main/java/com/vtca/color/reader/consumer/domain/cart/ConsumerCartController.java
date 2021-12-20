package com.vtca.color.reader.consumer.domain.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consumer")
public class ConsumerCartController {

    @GetMapping(value = "/cart")
    public ResponseEntity<?> getConsumerCartInfo() {
        return ResponseEntity.ok("Testing consumer carts info.........");
    }
}
