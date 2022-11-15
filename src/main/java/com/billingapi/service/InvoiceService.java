package com.billingapi.service;

import com.billingapi.model.Invoice;

import java.util.List;

public interface InvoiceService {
	public Invoice save(Invoice invoice);

	public List<Invoice> saveAllInvoice(List<Invoice> invoice);

	public List<Invoice> getAllInvoice();

	
	public Invoice deleteInvoice(Invoice invoice, int invoiceId);

	public Invoice findInvoicetById(int invoiceId);

	public Invoice addDiscountPrice(Invoice invoice, int invoiceId);

}
