package com.billingapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billingapi.controller.request.InvoiceRequestDto;
import com.billingapi.controller.request.InvoiceResponseDto;
import com.billingapi.model.Invoice;
import com.billingapi.service.InvoiceService;
import com.billingapi.service.InvoiceServiceImpl;

@RestController
@EnableAutoConfiguration
@RequestMapping("/invoice")
public class InvoiceController {
	@Autowired
	private InvoiceServiceImpl invoiceService;

	@GetMapping("/{invoiceId}")
	public ResponseEntity<?> findInvoicetById(@PathVariable int invoiceId) {
		Invoice invoice = invoiceService.findInvoicetById(invoiceId);
		return new ResponseEntity<>(invoiceService.findInvoicetById(invoiceId), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<?> getAllInvoice() {
		return new ResponseEntity<>(invoiceService.getAllInvoice(), HttpStatus.OK);
	}
	/*
	 * @PostMapping("") public ResponseEntity<?> addInvoice(InvoiceRequestDto
	 * invoiceRequestDTO) {
	 * 
	 * InvoiceResponseDto invoice =
	 * InvoiceResponseDto.fromEntity(invoiceService.save(InvoiceRequestDto.toEntity(
	 * invoiceRequestDTO))); return new ResponseEntity<>(newInvoice,
	 * HttpStatus.ACCEPTED);
	 * 
	 * }
	 */
	/*
	 * 
	 * @PostMapping("") public ResponseEntity<?> addInvoice( @RequestBody Invoice
	 * invoice) { return new ResponseEntity<>(invoiceService.save(invoice),
	 * HttpStatus.CREATED);
	 * 
	 * 
	 * }
	 */

	/*
	 * @PostMapping("save") public ResponseEntity<?> addInvoice( InvoiceRequestDto
	 * invoiceRequestDto)throws CustomException { InvoiceResponseDto
	 * newInvoice=null; try { newInvoice =
	 * InvoiceResponseDto.fromEntity(invoiceService.save(InvoiceRequestDto.toEntity(
	 * invoiceRequestDto))); } catch (Exception e) { e.printStackTrace(); return new
	 * ResponseEntity<>("Invoice Not Created!!", HttpStatus.BAD_REQUEST); } return
	 * new ResponseEntity<>(newInvoice, HttpStatus.CREATED); }
	 */

	@PostMapping("/save")
	public ResponseEntity<?> addInvoice(InvoiceRequestDto invoiceRequestDto) {
		Invoice invoice = new Invoice();
		invoice.setItemName(invoiceRequestDto.getItemName());
		invoice.setPrice(invoiceRequestDto.getPrice());
		invoice.setDiscount(invoiceRequestDto.getDiscount());

		invoice.setTax(invoiceRequestDto.getTax());
		invoice.setVat(invoiceRequestDto.getVat());
		invoice.setUserId(invoiceRequestDto.getUserId());
		invoice.setIsItemOnSale(invoiceRequestDto.getIsItemOnSale());
		invoice.setDiscountOnSale(invoiceRequestDto.getDiscountOnSale());
		invoice.setIsDeleted(invoiceRequestDto.getIsDeleted());
		return new ResponseEntity<>(InvoiceResponseDto.fromEntity(invoiceService.save(invoice)), HttpStatus.OK);
	}

	@PostMapping("/invoicerequest")
	public ResponseEntity<?> addInvoice(@RequestBody List<Invoice> invoice) {
		return new ResponseEntity<>(invoiceService.saveAllInvoice(invoice), HttpStatus.CREATED);

	}

	@PutMapping("/delete/{invoiceId}")
	public ResponseEntity<?> deleteInvoice(@PathVariable int invoiceId, InvoiceRequestDto invoiceRequestDto) {
		Invoice invoice = invoiceService.findInvoicetById(invoiceId);
		invoice.setIsDeleted(invoiceRequestDto.getIsDeleted());
		System.out.println("delete request " + invoiceId);
		return new ResponseEntity<>(InvoiceResponseDto.fromEntity(invoiceService.deleteInvoice(invoice, invoiceId)),
				HttpStatus.OK);
	}

	@PutMapping("/addDiscountprice/{invoiceId}")
	public ResponseEntity<?> addDiscountPrice(@PathVariable int invoiceId, InvoiceRequestDto invoiceRequestDto) {
		Invoice invoice = invoiceService.findInvoicetById(invoiceId);
		invoice.setIsItemOnSale(invoiceRequestDto.getIsItemOnSale());

		invoice.setDiscountOnSale(invoiceRequestDto.getDiscountOnSale());
		System.out.println("addDiscount price " + invoiceId);
		return new ResponseEntity<>(InvoiceResponseDto.fromEntity(invoiceService.addDiscountPrice(invoice, invoiceId)),
				HttpStatus.OK);
	}

}
