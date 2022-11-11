package com.billingapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billingapi.model.Invoice;

public interface InvoiceDao extends JpaRepository<Invoice, Integer> {

	
}
