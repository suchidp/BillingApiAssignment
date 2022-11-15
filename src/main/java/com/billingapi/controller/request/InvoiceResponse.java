package com.billingapi.controller.request;

import com.billingapi.model.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {

    private int invoiceId;
    @NotNull
    private String itemName;

    private BigDecimal price;

    private double tax;

    private double vat;

    private double discount;

    private Boolean isItemOnSale;

    private double discountOnSale;


    public static InvoiceResponse fromEntity(Invoice entity) {
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setItemName(entity.getItemName());
        invoiceResponse.setPrice(entity.getPrice());
        invoiceResponse.setTax(entity.getTax());
        invoiceResponse.setVat(entity.getVat());
        invoiceResponse.setDiscount(entity.getDiscount());
        invoiceResponse.setIsItemOnSale(entity.getIsItemOnSale());
        return invoiceResponse;
    }

}


