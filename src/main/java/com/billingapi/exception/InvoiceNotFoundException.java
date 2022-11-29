package com.billingapi.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * The custom Exception used to  check   an invoice is present or not   .
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvoiceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvoiceNotFoundException(String message) {
        super(message);
    }


}
