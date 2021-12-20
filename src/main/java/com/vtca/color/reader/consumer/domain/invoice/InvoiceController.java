package com.vtca.color.reader.consumer.domain.invoice;

import com.vtca.color.reader.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user")
public class InvoiceController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoices")
    public Response add(@RequestBody Invoice invoice) {
        invoiceService.save(invoice);
        return Response.ok();
    }

    @GetMapping("/invoices")
    public Response get() {
        return Response.ok(invoiceService.getListFullInfoInvoice(request.getHeader("Authorization")));
    }
}
