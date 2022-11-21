package com.billingapi.controller.request;

import com.billingapi.model.Invoice;
import lombok.*;

import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;



@Validated
@Data

@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {

    @NotNull(message = "itemName must not be null")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,30}$",
            message = "itemName must be of 3 to 30 length with no special characters")
    private String itemName;
    @DecimalMin(value = "0.01")
    @Digits(integer=8, fraction=2 ,message = "Price must be less than 100000000")
    private BigDecimal price;
    @DecimalMin(value = "0.01")
    @Digits(integer=3, fraction=2,message = "Tax must be less than 1000")
    private double tax;
    @DecimalMin(value = "0.01")
    @Digits(integer=3, fraction=2,message = "Vat must be less than 1000")
    private double vat;
    @DecimalMin(value = "0.01")
    @Digits(integer=2, fraction=2,message = "Discount must be less than 100")
    private double discount;

    private boolean isItemOnSale;
    @DecimalMin(value = "0.01")
    @Digits(integer=3, fraction=2,message = "DiscountOnSale must be less than 1000")
    private double discountOnSale;

    private String description;


    @Min(value = 1, message = "Quantity must be between 1 and 1000")
    @Max(value = 1000   ,message = "Quantity must be between 1 and 1000")
    private Integer quantity;



    @Digits(integer=8, fraction=2)
      private BigDecimal total;
   // private Date date;






    public static Invoice toEntity(InvoiceRequest invoiceRequest) {
        Invoice entity = new Invoice();

        entity.setItemName(invoiceRequest.getItemName());
        entity.setPrice(invoiceRequest.getPrice());
        entity.setTax(invoiceRequest.getTax());
        entity.setVat(invoiceRequest.getVat());
        entity.setDiscount(invoiceRequest.getDiscount());

        entity.setItemOnSale(invoiceRequest.isItemOnSale());
        entity.setDiscountOnSale(invoiceRequest.getDiscountOnSale());
        entity.setDescription(invoiceRequest.getDescription());

        entity.setQuantity(invoiceRequest.getQuantity());

        return entity;
    }


}
