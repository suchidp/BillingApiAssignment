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

	@Override
	public Invoice save(Invoice invoice) {

		return invoiceRepository.save(invoice);
	}

	@Override
	public List<Invoice> getAllInvoice() {

		return invoiceRepository.findAll();
	}


	@Override
	public Invoice findInvoiceById(int invoiceId) {
		Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
		return invoice.orElse(null);
	}




	@Override
	public List<Invoice> saveAllInvoice(List<Invoice> invoice) {

		return invoiceRepository.saveAll(invoice);
	}


	@Override
	public Invoice deleteInvoice(int invoiceId) {
		Invoice invoice =invoiceRepository.findById(invoiceId).get();
		if (invoice.isDeleted()== false) {
          invoice.setDeleted(true);
			System.out.println("in voice is deleted successfully  " + invoiceId);
			invoiceRepository.save(invoice);
		}
			return invoiceRepository.save(invoice);
		}
	}



