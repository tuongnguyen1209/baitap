package com.vtca.color.reader.consumer.domain.invoice.detail;

import com.vtca.color.reader.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class OrderDetailController {
    @Autowired
    private OrderDetailService service;

    @GetMapping("/order-details/{orderId}")
    public Response get(@PathVariable Long orderId) {
        return Response.ok(service.getOrderDetail(orderId));
    }
    /*
    @GetMapping("/order-details/{d}")
    public Response get(@PathVariable Long id) {
        return Response.ok(service.get(id));
    }

    @GetMapping("/order-details")
    public Response list() {
        return Response.ok(service.listAll());
    }

    @PostMapping("/order-details")
    public Response add(@RequestBody OrderDetail order) {
        service.save(order);
        return Response.ok();
    }

    @PutMapping("/order-details/{id}")
    public Response update(@RequestBody OrderDetail order, @PathVariable Long id) {
        service.save(order, id);
        return Response.ok();
    }*/
}
