package com.vtca.color.reader.consumer.domain.product;

import com.vtca.color.reader.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public Response list() {
        return Response.ok(service.listAll());
    }

    @GetMapping("/products/{price}")
    public Response getProduct(@PathVariable Float price) {
        return Response.ok(service.getProductByPrice(price));
    }

    @GetMapping("/products/{id}")
    public Response get(@PathVariable Integer id) {
        return Response.ok(service.get(id));
    }

    @PostMapping("/products")
    public Response add(@RequestBody Product product) {
        service.save(product);
        return Response.ok();
    }

    @PutMapping("/products/{id}")
    public Response update(@RequestBody Product product, @PathVariable Integer id) {
        service.save(product, id);
        return Response.ok();
    }

    @DeleteMapping("/products/{id}")
    public Response delete(@PathVariable Integer id) {
        service.delete(id);
        return Response.ok();
    }
}
