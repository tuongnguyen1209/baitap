package com.vtca.color.reader.consumer.domain.favorite;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consumer")
public class ConsumerFavoriteController {

    @GetMapping(value = "/favorite")
    public ResponseEntity<?> getConsumerFavoriteInfo() {
        return ResponseEntity.ok("Testing consumer favorite info.........");
    }
}
