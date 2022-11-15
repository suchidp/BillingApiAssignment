package com.billingapi.controller.request;

import com.billingapi.model.Invoice;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ToString
@Validated
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {

    @NotNull(message = "itemName must not be null")
    private String itemName;

    private BigDecimal price;

    private double tax;

    private double vat;

    private double discount;

    private Boolean isItemOnSale;

    private double discountOnSale;

    public static Invoice toEntity(InvoiceRequest invoiceRequest) {
        Invoice entity = new Invoice();
        entity.setItemName(entity.getItemName());
        entity.setPrice(entity.getPrice());
        entity.setTax(entity.getTax());
        entity.setVat(entity.getVat());
        entity.setDiscount(entity.getDiscount());
        entity.setIsItemOnSale(entity.getIsItemOnSale());
        return entity;
    }


}
