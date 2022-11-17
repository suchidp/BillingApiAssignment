package com.billingapi.controller.request;

import com.billingapi.model.Invoice;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;


@Validated
@Data

@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {
    private int invoiceId;
    @NotNull(message = "itemName must not be null")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,12}$",
            message = "itemName must be of 3 to 12 length with no special characters")
    private String itemName;
    @DecimalMin(value = "0.01")
    @Digits(integer=8, fraction=2)
    private BigDecimal price;
    @DecimalMin(value = "0.01")
    @Digits(integer=3, fraction=2)
    private double tax;
    @DecimalMin(value = "0.01")
    @Digits(integer=3, fraction=2)
    private double vat;
    @DecimalMin(value = "0.01")
    @Digits(integer=3, fraction=2)
    private double discount;

    private Boolean isItemOnSale;
    @DecimalMin(value = "0.01")
    @Digits(integer=3, fraction=2)
    private double discountOnSale;


    public static Invoice toEntity(InvoiceRequest invoiceRequest) {
        Invoice entity = new Invoice();
        entity.setInvoiceId(invoiceRequest.getInvoiceId());
        entity.setItemName(invoiceRequest.getItemName());
        entity.setPrice(invoiceRequest.getPrice());
        entity.setTax(invoiceRequest.getTax());
        entity.setVat(invoiceRequest.getVat());
        entity.setDiscount(invoiceRequest.getDiscount());
        entity.setItemOnSale(invoiceRequest.getIsItemOnSale());
        entity.setDiscountOnSale(invoiceRequest.getDiscountOnSale());

        return entity;
    }


}
