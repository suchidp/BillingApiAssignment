package com.billingapi.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoice")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invoiceId;
	@Column(name = "item_name")
	private String itemName;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "tax")
	private double tax;
	@Column(name = "vat")
	private double vat;
	@Column(name = "discount")
	private double discount;
	@Column(name = "is_item_on_sale")
	private Boolean isItemOnSale;
	@Column(name = "discount_on_sale")
	private double discountOnSale;
	@Column(name = "user_id")
	private int userId;
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	public Invoice() {
		
	}

	public Invoice(int invoiceId, String itemName, BigDecimal price, double tax, double vat, double discount,
			Boolean isItemOnSale, double discountOnSale, int userId, Boolean isDeleted) {
		
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
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
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

	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", itemName=" + itemName + ", price=" + price + ", tax=" + tax
				+ ", vat=" + vat + ", discount=" + discount + ", isItemOnSale=" + isItemOnSale + ", discountOnSale="
				+ discountOnSale + ", userId=" + userId + ", isDeleted=" + isDeleted + "]";
	}

	

	// private List<Invoice> invoice;
	

	
}
