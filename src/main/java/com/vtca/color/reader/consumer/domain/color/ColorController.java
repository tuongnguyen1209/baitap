package com.vtca.color.reader.consumer.domain.color;

import com.vtca.color.reader.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consumer")
public class ColorController {

    @Autowired
    private ColorService service;

    @GetMapping("/colors")
    public Response get(@RequestParam String name) {
        return Response.ok(service.getColorBy(name));
    }

    /**
     * Just use 3 param to call:
     * @param color {m_rgb_r, m_rgb_g, m_rgb_b}
     * @return
     */
    @PostMapping("/colors")
    public Response get(@RequestBody Color color) {
        return Response.ok(service.getColorBy(color));
    }


    @GetMapping("/colors/list")
    public Response list() {
        return Response.ok(service.listAll());
    }
/*
    @GetMapping("/colors/{price}")
    public Response getProduct(@PathVariable Double price) {
        return Response.ok(service.getColorByPrice(price));
    }

    @GetMapping("/colors/{id}")
    public Response get(@PathVariable Long id) {
        return Response.ok(service.get(id));
    }

    @PostMapping("/colors")
    public Response add(@RequestBody Color color) {
        service.save(color);
        return Response.ok();
    }

    @PutMapping("/colors/{id}")
    public Response update(@RequestBody Color color, @PathVariable Long id) {
        service.save(color, id);
        return Response.ok();
    }

    @DeleteMapping("/colors/{id}")
    public Response delete(@PathVariable Long id) {
        service.delete(id);
        return Response.ok();
    }
    */

}
