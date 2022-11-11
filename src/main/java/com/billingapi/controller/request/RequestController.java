package com.billingapi.controller.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.billingapi.model.Invoice;
import com.billingapi.service.InvoiceServiceImpl;

@RestController
@EnableAutoConfiguration
@RequestMapping("/invoicerequest")
public class RequestController {
	@Autowired
	private InvoiceServiceImpl invoiceService;
	
	@PostMapping("")
	public ResponseEntity<?> addInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto) {
		
		
		Invoice invoice =InvoiceRequestDto.toEntity(invoiceRequestDto);
		invoice=invoiceService.save(invoice);
		return new ResponseEntity<>(invoice, HttpStatus.CREATED);
	
	}
	
	@PostMapping("saveall")
	public ResponseEntity<?> addInvoiceList(@RequestBody InvoiceRequestDto invoiceRequestDto) {
		
		
		Invoice invoice =InvoiceRequestDto.toEntity(invoiceRequestDto);
		invoice=invoiceService.save(invoice);
		return new ResponseEntity<>(invoice, HttpStatus.CREATED);
	
	}
}