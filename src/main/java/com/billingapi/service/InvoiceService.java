package com.billingapi.service;

import com.billingapi.model.Invoice;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceService {
	 Invoice save(Invoice invoice);

	 List<Invoice> saveAllInvoice(List<Invoice> invoice);

	 List<Invoice> getAllInvoice();

	
	 Invoice deleteInvoice( int invoiceId);

	 Invoice findInvoiceById(int invoiceId);



}
