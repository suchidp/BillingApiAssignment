package com.billingapi.service;

import com.billingapi.model.Invoice;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceService {


    /*The method used to save  the Invoice . To save any  Invoice at any location  should be done
              by overriding this method.*/
    Invoice save(Invoice invoice);
    /*The method used to save  the List of Invoices . To save  Invoices,it should be done
                 by overriding this method.*/
    List<Invoice> saveAllInvoice(List<Invoice> invoice);


    /*The method used to get all the Invoices . To find all Invoices should be done
              by overriding this method.*/
    List<Invoice> getAllInvoice();

    /*The method used to  delete the Invoice. To delete any specific Invoice should be done
      by overriding this method.*/
    Invoice deleteInvoice(int invoiceId);


    /*The method used to  find the Invoice . To find any specific Invoice should be done
          by overriding this method.*/
    Invoice findInvoiceById(int invoiceId);


}
