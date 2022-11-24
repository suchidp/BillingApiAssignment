package com.billingapi.controller.request;

import com.billingapi.model.Invoice;
import lombok.*;

import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.*;
import java.math.BigDecimal;




@Validated
@Data

@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {

    @NotNull(message = "itemName must not be null")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,30}$")
    private String itemName;
    @DecimalMin(value = "0.01")
    @Digits(integer=8, fraction=2 )
    private BigDecimal price;
    @DecimalMin(value = "0.01")
    @Digits(integer=3, fraction=2)
    private double tax;
    @DecimalMin(value = "0.01")
    @Digits(integer=3, fraction=2)
    private double vat;
    @DecimalMin(value = "0.01")
    @Digits(integer=2, fraction=2)
    private double discount;

    private boolean isItemOnSale;
    @DecimalMin(value = "0.01")
    @Digits(integer=3, fraction=2)
    private double discountOnSale;

    private String description;


    @Min(value = 1)
    @Max(value = 1000   )
    private Integer quantity;



    @Digits(integer=8, fraction=2)
      private BigDecimal total;







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
