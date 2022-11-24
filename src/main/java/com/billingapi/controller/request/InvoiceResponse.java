package com.billingapi.controller.request;

import com.billingapi.model.Invoice;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;


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
    private boolean isItemOnSale;
    private double discountOnSale;
    private String description;
    private Integer quantity;
    private BigDecimal total;
    private LocalDateTime timestamp;

    public static InvoiceResponse fromEntity(Invoice entity) {
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setInvoiceId(entity.getInvoiceId());
        invoiceResponse.setItemName(entity.getItemName());
        invoiceResponse.setPrice(entity.getPrice());
        invoiceResponse.setTax(entity.getTax());
        invoiceResponse.setVat(entity.getVat());
        invoiceResponse.setDiscount(entity.getDiscount());
        invoiceResponse.setItemOnSale(entity.isItemOnSale());
        invoiceResponse.setDiscountOnSale(entity.getDiscountOnSale());
        invoiceResponse.setTimestamp(entity.getTimestamp().now());
        invoiceResponse.setDescription(entity.getDescription());
        invoiceResponse.setQuantity(entity.getQuantity());
        invoiceResponse.setTotal(entity.getTotal());

        return invoiceResponse;
    }

}


