package com.billingapi.controller.request;
import com.billingapi.model.Invoice;
import lombok.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InvoiceRequest {

    @Pattern(regexp = "^[a-zA-Z0-9]{3,30}$")
    private String itemName;

    @DecimalMin(value = "0.01")
    @Digits(integer=8, fraction=2 )
    private BigDecimal price;

    @Digits(integer=2, fraction=2)
    private double tax;

    @Digits(integer=2, fraction=2)
    private double vat;

    @Digits(integer=2, fraction=2)
    private double discount;

    private boolean isItemOnSale;

    @Digits(integer=2, fraction=2)
    private double discountOnSale;

    private String description;

    @Min(value = 1)
    @Max(value = 99999999 )
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
