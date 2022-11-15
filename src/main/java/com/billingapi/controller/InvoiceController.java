package com.billingapi.controller;

import com.billingapi.controller.request.InvoiceRequest;
import com.billingapi.model.Invoice;
import com.billingapi.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@RequestMapping("/invoice")
@Validated
public class InvoiceController {
    @Autowired
    private InvoiceServiceImpl invoiceService;


    //To find Invoice
    @GetMapping("/{invoiceId}")
    public ResponseEntity<?> findInvoicetById(@PathVariable int invoiceId) {
        Invoice invoice = invoiceService.findInvoicetById(invoiceId);
        return new ResponseEntity<>(invoiceService.findInvoicetById(invoiceId), HttpStatus.OK);
    }

    // To find List of Invoices
    @GetMapping("")
    public ResponseEntity<?> getAllInvoice() {
        return new ResponseEntity<>(invoiceService.getAllInvoice(), HttpStatus.OK);
    }

    //To soft delete particular Invoice
    @PutMapping("/delete/{invoiceId}")
    public ResponseEntity<?> deleteInvoice(@PathVariable int invoiceId, Invoice invoice) {
        Invoice invoices = invoiceService.findInvoicetById(invoiceId);
        invoice.setIsDeleted(invoices.getIsDeleted());
        System.out.println("delete request " + invoiceId);
        return new ResponseEntity<>(invoiceService.deleteInvoice(invoice, invoiceId),
                HttpStatus.OK);
    }

    //To add discount price
    @PutMapping("/addDiscountprice/{invoiceId}")
    public ResponseEntity<?> addDiscountPrice(@PathVariable int invoiceId) {
        Invoice invoice = invoiceService.findInvoicetById(invoiceId);
        invoice.setIsItemOnSale(invoice.getIsItemOnSale());
        invoice.setDiscountOnSale(invoice.getDiscountOnSale());
        System.out.println("addDiscount price " + invoiceId);
        return new ResponseEntity<>(invoiceService.addDiscountPrice(invoice, invoiceId),
                HttpStatus.OK);
    }


    //To add new Invoice
    @PostMapping("")
    public ResponseEntity<?> addInvoice(@Valid @RequestBody InvoiceRequest invoiceRequest) {
        Invoice invoice = InvoiceRequest.toEntity(invoiceRequest);
        invoice = invoiceService.save(invoice);
        return new ResponseEntity<>(invoice, HttpStatus.CREATED);

    }

}
