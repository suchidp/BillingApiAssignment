package com.billingapi.controller.request;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.BeanUtils;

import com.billingapi.model.Invoice;

public class InvoiceRequestDto {
	private int invoiceId;
	private String itemName;

	private BigDecimal price;

	private double tax;

	private double vat;

	private double discount;

	private Boolean isItemOnSale;

	private double discountOnSale;

	private int userId;

	private Boolean isDeleted;
	

	public InvoiceRequestDto() {
		
	}

	

	public int getInvoiceId() {
		return invoiceId;
	}



	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}



	public InvoiceRequestDto(int invoiceId, String itemName, BigDecimal price, double tax, double vat, double discount,
			Boolean isItemOnSale, double discountOnSale, int userId, Boolean isDeleted, List<Invoice> invoice) {
	
		this.invoiceId = invoiceId;
		this.itemName = itemName;
		this.price = price;
		this.tax = tax;
		this.vat = vat;
		this.discount = discount;
		this.isItemOnSale = isItemOnSale;
		this.discountOnSale = discountOnSale;
		this.userId = userId;
		this.isDeleted = isDeleted;
		//this.invoice = invoice;
	}



	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Boolean getIsItemOnSale() {
		return isItemOnSale;
	}

	public void setIsItemOnSale(Boolean isItemOnSale) {
		this.isItemOnSale = isItemOnSale;
	}

	public double getDiscountOnSale() {
		return discountOnSale;
	}

	public void setDiscountOnSale(double discountOnSale) {
		this.discountOnSale = discountOnSale;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
/*
	public List<Invoice> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}
*/
	public static Invoice toEntity(InvoiceRequestDto invoiceRequestDto) {
		Invoice entity = new Invoice();
		entity.setInvoiceId(entity.getInvoiceId());
		entity.setItemName(entity.getItemName());
		entity.setPrice(entity.getPrice());
		entity.setTax(entity.getTax());
		entity.setVat(entity.getVat());
		entity.setDiscount(entity.getDiscount());
		entity.setIsItemOnSale(entity.getIsItemOnSale());
		entity.setUserId(entity.getUserId());
		entity.setIsDeleted(entity.getIsDeleted());
		BeanUtils.copyProperties(invoiceRequestDto, entity);
		return entity;
	}
	public static InvoiceResponseDto fromEntity(Invoice entity) {
		InvoiceResponseDto invoiceResponseDto = new InvoiceResponseDto();
		BeanUtils.copyProperties(entity, invoiceResponseDto);
		invoiceResponseDto.setInvoiceId(entity.getInvoiceId());
		invoiceResponseDto.setItemName(entity.getItemName());
		invoiceResponseDto.setPrice(entity.getPrice());
		invoiceResponseDto.setTax(entity.getTax());
		invoiceResponseDto.setVat(entity.getVat());
		invoiceResponseDto.setDiscount(entity.getDiscount());
		invoiceResponseDto.setIsItemOnSale(entity.getIsItemOnSale());
		invoiceResponseDto.setUserId(entity.getUserId());
		invoiceResponseDto.setIsDeleted(entity.getIsDeleted());

		return invoiceResponseDto;
	}


	
	

}
