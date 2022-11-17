package com.billingapi.controller.request;

import com.billingapi.model.Invoice;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {

    private int invoiceId;

    private String itemName;

    private BigDecimal price;

    private double tax;

    private double vat;

    private double discount;

    private Boolean isItemOnSale;

    private double discountOnSale;


    public static InvoiceResponse fromEntity(Invoice entity) {
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setInvoiceId(entity.getInvoiceId());
        invoiceResponse.setItemName(entity.getItemName());
        invoiceResponse.setPrice(entity.getPrice());
        invoiceResponse.setTax(entity.getTax());
        invoiceResponse.setVat(entity.getVat());
        invoiceResponse.setDiscount(entity.getDiscount());
        invoiceResponse.setIsItemOnSale(entity.isItemOnSale());
        invoiceResponse.setDiscountOnSale(entity.getDiscountOnSale());

        return invoiceResponse;
    }

}


