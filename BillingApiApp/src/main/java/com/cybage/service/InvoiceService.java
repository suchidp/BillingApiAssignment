package com.cybage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cybage.model.Invoice;

public interface InvoiceService {
	public Invoice save(Invoice invoice);

	public List<Invoice> saveAllInvoice(List<Invoice> invoice);

	public List<Invoice> getAllInvoice();

	// public Invoice save(Invoice Invoice);
	public Invoice deleteInvoice(Invoice invoice, int invoiceId);

	public Invoice findInvoicetById(int invoiceId);

	public Invoice addDiscountPrice(Invoice invoice, int invoiceId);

}
