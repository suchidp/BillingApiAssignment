package com.billingapi.service;


import com.billingapi.model.Invoice;
import com.billingapi.repository.InvoiceRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;


    /*The method used to save  the Invoice */
    @Override
    public Invoice save(Invoice invoice) {
        /* To calculate Total value */
        log.debug("inside InvoiceService.save() method");
        log.info("Invoice calculation begins");


        BigDecimal price = invoice.getPrice();
        double tax = invoice.getTax();
        double vat = invoice.getVat();
        double discount = invoice.getDiscount();
        double discountOnSale = invoice.getDiscountOnSale();
        int quantity = invoice.getQuantity();
        //conversion into
        log.info("conversion into bigdecimals");

        BigDecimal newtax = new BigDecimal(tax);
        log.info("Tax on " + invoice.getItemName() + " is " + newtax);
        BigDecimal newvat = new BigDecimal(vat);
        log.info("Vat on " + invoice.getItemName() + " is " + newvat);
        BigDecimal newdiscount = new BigDecimal(discount);
        log.info("Discount  on " + invoice.getItemName() + " is " + newdiscount);
        BigDecimal newdiscountonsale = new BigDecimal(discountOnSale);
        log.info("Discount on sale for  " + invoice.getItemName() + " is " + newdiscountonsale);
        BigDecimal newquantity = new BigDecimal(quantity);

        BigDecimal sum;
        BigDecimal newSum = (sum = price.add(newtax).add(newvat)).subtract(newdiscount).subtract(newdiscountonsale);
        log.info("Calculations of bigdecimals is " + newSum);
        BigDecimal subtotal = newSum.multiply(newquantity);
        log.info("quantity  of  " + invoice.getItemName() + " is " + newquantity);
        log.info("Total bill amount is " + subtotal);
        invoice.setTotal(subtotal);

        return invoiceRepository.save(invoice);
    }


    /* The method used to  get the List  of All invoices.
     */
    @Override
    public List<Invoice> getAllInvoice() {
        log.debug("inside getAllInvoice method");
        return invoiceRepository.findAll();
    }
    /*
     *The method used to  get the List  of All invoices.
     *It also check null value.  .
     */

    @Override
    public Invoice findInvoiceById(int invoiceId) {
        log.debug("inside findInvoiceById method");


        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        log.info("invoiceId is " + invoiceId + "  found");
        return invoice.orElse(null);
    }


    @Override
    public List<Invoice> saveAllInvoice(List<Invoice> invoice) {

        return invoiceRepository.saveAll(invoice);
    }








    /*
     * The method used to  delete the invoice.
     This method check  an invoice is present or not   .
     */
    @Override
    public Invoice deleteInvoice(int invoiceId) {
        log.debug("inside findInvoiceById method");
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        log.info("invoiceId is " + invoiceId + "  found");

        if (invoice.isDeleted()) equals(false);

        {

            invoice.setDeleted(true);
            log.info("invoiceId is " + invoiceId + "  deleted");
            invoiceRepository.save(invoice);

        }


        return invoiceRepository.save(invoice);
    }
}



