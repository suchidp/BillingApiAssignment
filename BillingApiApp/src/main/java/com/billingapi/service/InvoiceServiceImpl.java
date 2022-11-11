package com.billingapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billingapi.dao.InvoiceDao;
import com.billingapi.model.Invoice;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	@Autowired
	private InvoiceDao invoicedao;

	@Override
	public Invoice save(Invoice invoice) {

		return invoicedao.save(invoice);
	}

	@Override
	public List<Invoice> getAllInvoice() {

		return invoicedao.findAll();
	}

	@Override
	public Invoice findInvoicetById(int invoiceId) {
		Optional<Invoice> invoice = invoicedao.findById(invoiceId);
		return invoice.orElse(null);
	}

	@Override
	public List<Invoice> saveAllInvoice(List<Invoice> invoice) {

		return invoicedao.saveAll(invoice);
	}

	/*
	 * public Object delete(int invoiceId) { Invoice invoice =
	 * invoicedao.findById(invoiceId).get(); if (invoice.getIsDeleted().equals(1)) {
	 * 
	 * System.out.println("in voice is deleted successfully  " + invoiceId);
	 * invoicedao.save(invoice);
	 * 
	 * } return "Invoice is deleted successfully!!!"; }
	 * 
	 */
	/*
	 * public Object delete(int invoiceId) { Invoice invoice =
	 * invoicedao.findById(invoiceId).get(); invoice.setIsDeleted(true); return
	 * "Invoice is deleted successfully!!!"; }
	 */
	@Override
	public Invoice deleteInvoice(Invoice invoice, int invoiceId) {
		// Invoice invoice = invoicedao.findById(invoiceId).get();
		invoice.setIsDeleted(true);
		return invoicedao.save(invoice);
	}

	@Override
	public Invoice addDiscountPrice(Invoice invoice, int invoiceId) {
		
		invoice.setIsItemOnSale(true);
		invoice.setDiscountOnSale(invoice.getDiscountOnSale());

		return invoicedao.save(invoice);
	}
}

/*
 * 
 * public Object delete(int invoiceId) { Invoice invoice =
 * invoicedao.findById(invoiceId).get(); if(invoice.getIsDeleted()) {
 * 
 * return "Invoice is deleted successfully!!!";
 * 
 * }
 */
