package com.cybage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.model.Invoice;

public interface InvoiceDao extends JpaRepository<Invoice, Integer> {

	
}
