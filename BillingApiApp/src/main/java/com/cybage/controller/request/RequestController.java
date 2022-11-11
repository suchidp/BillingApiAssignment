package com.cybage.controller.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.dto.InvoiceRequestDto;
import com.cybage.dto.InvoiceResponseDto;
import com.cybage.model.Invoice;
import com.cybage.service.InvoiceServiceImpl;

@RestController
@EnableAutoConfiguration
@RequestMapping("/invoicerequest")
public class RequestController {
	@Autowired
	private InvoiceServiceImpl invoiceService;
	
	@PostMapping("")
	public ResponseEntity<?> addInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto) {
		/*Invoice invoice = new Invoice();
		invoice.setInvoiceId(invoiceRequestDto.getInvoiceId());
		invoice.setItemName(invoiceRequestDto.getItemName());
		invoice.setPrice(invoiceRequestDto.getPrice());
		invoice.setDiscount(invoiceRequestDto.getDiscount());

		invoice.setTax(invoiceRequestDto.getTax());
		invoice.setVat(invoiceRequestDto.getVat());
		invoice.setUserId(invoiceRequestDto.getUserId());
		invoice.setIsItemOnSale(invoiceRequestDto.getIsItemOnSale());
		invoice.setDiscountOnSale(invoiceRequestDto.getDiscountOnSale());
		invoice.setIsDeleted(invoiceRequestDto.getIsDeleted());*/
		
		Invoice invoice =InvoiceRequestDto.toEntity(invoiceRequestDto);
		invoice=invoiceService.save(invoice);
		return new ResponseEntity<>(invoice, HttpStatus.CREATED);
	//	return new ResponseEntity<>(InvoiceResponseDto.fromEntity(invoiceService.save(invoice)), HttpStatus.OK);
	}
	
	@PostMapping("saveall")
	public ResponseEntity<?> addInvoiceList(@RequestBody InvoiceRequestDto invoiceRequestDto) {
		/*Invoice invoice = new Invoice();
		invoice.setInvoiceId(invoiceRequestDto.getInvoiceId());
		invoice.setItemName(invoiceRequestDto.getItemName());
		invoice.setPrice(invoiceRequestDto.getPrice());
		invoice.setDiscount(invoiceRequestDto.getDiscount());

		invoice.setTax(invoiceRequestDto.getTax());
		invoice.setVat(invoiceRequestDto.getVat());
		invoice.setUserId(invoiceRequestDto.getUserId());
		invoice.setIsItemOnSale(invoiceRequestDto.getIsItemOnSale());
		invoice.setDiscountOnSale(invoiceRequestDto.getDiscountOnSale());
		invoice.setIsDeleted(invoiceRequestDto.getIsDeleted());*/
		
		Invoice invoice =InvoiceRequestDto.toEntity(invoiceRequestDto);
		invoice=invoiceService.save(invoice);
		return new ResponseEntity<>(invoice, HttpStatus.CREATED);
	//	return new ResponseEntity<>(InvoiceResponseDto.fromEntity(invoiceService.save(invoice)), HttpStatus.OK);
	}
}