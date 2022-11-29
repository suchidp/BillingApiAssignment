package com.billingapi.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;



@Entity
@Table(name = "invoice")
@Data
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

	@Column(name = "date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;

	@Column(name = "description")
	private String description;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "total")
	private BigDecimal total;



}
