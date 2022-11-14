package com.billingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billingapi.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	
}
