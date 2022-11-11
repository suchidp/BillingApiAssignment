package com.cybage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.InvoiceDao;
import com.cybage.model.Invoice;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	@Autowired
	private InvoiceDao invoicedao;

	@Override
	public Invoice save(Invoice invoice) {
		// TODO Auto-generated method stub
		return invoicedao.save(invoice);
	}

	@Override
	public List<Invoice> getAllInvoice() {
		// TODO Auto-generated method stub
		return invoicedao.findAll();
	}
	
	@Override
	public Invoice findInvoicetById(int invoiceId) {
		Optional<Invoice> invoice = invoicedao.findById(invoiceId);
		return invoice.orElse(null);
	}

	@Override
	public List<Invoice> saveAllInvoice(List<Invoice> invoice) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
