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

        log.info("conversion into bigdecimals");
        BigDecimal totalDiscount  = (BigDecimal .valueOf(tax).add(BigDecimal .valueOf(vat)).subtract(BigDecimal .valueOf(discount)).subtract(BigDecimal .valueOf(discountOnSale)));
        log.info("totalDiscount is " + totalDiscount);
        BigDecimal subTotal =  ( price.multiply(totalDiscount).divide(BigDecimal .valueOf(100)));
        log.info("subTotal is " + subTotal);
        BigDecimal total = (price.add(subTotal));
        log.info("total is " + total);
        BigDecimal alltotal = total.multiply(BigDecimal .valueOf(quantity));
        log.info("alltotal is " + alltotal);
        invoice.setTotal(alltotal);
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
        if (!invoice.isDeleted()) ;
        {
            invoice.setDeleted(true);
            log.info("invoiceId is " + invoiceId + "  deleted");
            invoiceRepository.save(invoice);
        }
        return invoiceRepository.save(invoice);
    }
}



