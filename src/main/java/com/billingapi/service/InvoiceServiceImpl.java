package com.billingapi.service;

import com.billingapi.model.Invoice;
import com.billingapi.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;




        /*The method used to save  the Invoice */
        @Override
        public Invoice save(Invoice invoice) {

            return invoiceRepository.save(invoice);
        }

        /* The method used to  get the List  of All invoices.
        */
    @Override
    public List<Invoice> getAllInvoice() {

        return invoiceRepository.findAll();
    }

         /*
         *The method used to  get the List  of All invoices.
         *It also check null value.  .
         */

    @Override
    public Invoice findInvoiceById(int invoiceId) {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
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
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        if (invoice.isDeleted())equals(false); {
            invoice.setDeleted(true);

            invoiceRepository.save(invoice);
        }
        return invoiceRepository.save(invoice);
    }
}



