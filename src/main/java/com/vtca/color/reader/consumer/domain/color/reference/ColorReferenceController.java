package com.vtca.color.reader.consumer.domain.color.reference;

import com.vtca.color.reader.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consumer")
public class ColorReferenceController {

    @Autowired
    private ColorReferenceService service;

    @GetMapping("/color-references/{colorId}")
    public Response get(@PathVariable Long colorId) {
        return Response.ok(service.getColorReferenceBy(colorId));
    }

}
