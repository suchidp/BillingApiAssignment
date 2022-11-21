package com.billingapi.controller;

import com.billingapi.controller.request.InvoiceRequest;
import com.billingapi.controller.request.InvoiceResponse;


import com.billingapi.exception.InvoiceNotFoundException;
import com.billingapi.model.Invoice;
import com.billingapi.service.InvoiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@RestController
@RequestMapping("/invoice")
@Validated
public class InvoiceController {
    static Logger logger = LogManager.getLogger(InvoiceController.class);
    @Autowired
    private InvoiceService invoiceService;


    /*  To find Invoice
     * @param  invoiceId an Id giving the Invoice of specific Id
     * @return      Invoice of specific Id.
     * @throws InvoiceNotFoundException  If an Invoice of specific Id is not found
     */
    @GetMapping("/{invoiceId}")
    public ResponseEntity<?> findInvoiceById(@PathVariable int invoiceId) throws InvoiceNotFoundException {
        Invoice invoice = invoiceService.findInvoiceById(invoiceId);
        if (invoice == null) {
            throw new InvoiceNotFoundException("Invoice not found");

        }


        logger.info("Invoice " + invoice.getItemName() + "  found");
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }


    /*
     * To get list of all Invoices
     * @return      list of all Invoices .
     */
    @GetMapping()
    public ResponseEntity<?> getAllInvoice() {
        return new ResponseEntity<>(invoiceService.getAllInvoice(), HttpStatus.OK);
    }

    /*  To Delete Invoice
     * @param        invoiceId an Id giving the Invoice of specific Id
     * @return      it delete Invoice of specific Id.
     * @throws InvoiceNotFoundException  If an Invoice of specific Id is not found
     */
    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<?> deleteInvoice(@PathVariable int invoiceId) throws InvoiceNotFoundException {
        Invoice invoice = invoiceService.findInvoiceById(invoiceId);
        if (invoice == null) {
            throw new InvoiceNotFoundException("Invoice not found");

        }
        logger.info("Invoice " + invoice.getItemName() + " deleted");
        return new ResponseEntity<>(invoiceService.deleteInvoice(invoiceId), HttpStatus.OK);
    }

    /*  To Add new Invoice
     * @param        InvoiceRequest a request Dto which giving the request to add new Invoice
     * @return      it return InvoiceResponse to add response to database

     */
    @PostMapping()
    public ResponseEntity<?> addInvoice(@Valid @RequestBody InvoiceRequest invoiceRequest) {
        Invoice invoice = invoiceRequest.toEntity(invoiceRequest);
        logger.info("Invoice " + invoice.getItemName() + " added");
        return new ResponseEntity<>(InvoiceResponse.fromEntity(invoiceService.save(invoice)), HttpStatus.CREATED);

    }

}
