package com.vtca.color.reader.consumer.domain.invoice.order;

import com.vtca.color.reader.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user")
public class OrderController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderService service;

    @GetMapping("/orders")
    public Response loginUserListOrders() {
        return Response.ok(service.getListOrderByLoginUser(request.getHeader("Authorization")));
    }
    /*

    @GetMapping("/orders")
    public Response list() {
        return Response.ok(service.listAll());
    }
    @GetMapping("/orders/{id}")
    public Response get(@PathVariable Long id) {
        return Response.ok(service.get(id));
    }

    @PostMapping("/orders")
    public Response add(@RequestBody Order order) {
        service.save(order);
        return Response.ok();
    }

    @PutMapping("/orders/{id}")
    public Response update(@RequestBody Order order, @PathVariable Long id) {
        service.save(order, id);
        return Response.ok();
    }*/
}
