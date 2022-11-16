package com.billingapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

@Data
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
	private boolean isItemOnSale;
	@Column(name = "discount_on_sale")
	private double discountOnSale;
	@Column(name = "user_id")
	private int userId;
	@Column(name = "is_deleted")
	private boolean isDeleted ;


}
