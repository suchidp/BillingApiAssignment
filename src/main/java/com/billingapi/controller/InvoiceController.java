package com.billingapi.controller;

import com.billingapi.controller.request.InvoiceRequest;
import com.billingapi.controller.request.InvoiceResponse;
import com.billingapi.exception.InvoiceNotFoundException;
import com.billingapi.model.Invoice;
import com.billingapi.service.InvoiceService;
import com.billingapi.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/invoice")
@Validated
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;


    //To find Invoice
    @GetMapping("/{invoiceId}")
    public ResponseEntity<?> findInvoiceById(@PathVariable int invoiceId) throws InvoiceNotFoundException {
        Invoice invoice = invoiceService.findInvoiceById(invoiceId);
        if (invoice == null) {
            throw new InvoiceNotFoundException("Invoice not found");
        }
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }




    // To find List of Invoices
    @GetMapping()
    public ResponseEntity<?> getAllInvoice() {
        return new ResponseEntity<>(invoiceService.getAllInvoice(), HttpStatus.OK);
    }

    //To soft delete particular Invoice
    @DeleteMapping ("/{invoiceId}")
    public ResponseEntity<?> deleteInvoice(@PathVariable int invoiceId) throws InvoiceNotFoundException{
        Invoice invoice = invoiceService.findInvoiceById(invoiceId);
        if (invoice == null) {
            throw new InvoiceNotFoundException("Invoice not found");

        }
        return new ResponseEntity<>(invoiceService.deleteInvoice( invoiceId), HttpStatus.OK);
    }

    //To add new Invoice
    @PostMapping()
    public ResponseEntity<?> addInvoice(@Valid @RequestBody InvoiceRequest invoiceRequest) {
        Invoice invoice = InvoiceRequest.toEntity(invoiceRequest);

        return new ResponseEntity<>(InvoiceResponse.fromEntity(invoiceService.save(invoice)), HttpStatus.CREATED);

    }

}
